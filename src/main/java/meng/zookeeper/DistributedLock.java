package meng.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class DistributedLock implements Lock {
    public static String LOCK_PATH = "/lock";
    public static String LOCK_INSTANCE = "/instance";
    private String selfPath;
    private ZooKeeper zooKeeper;


    private DistributedLock(String zkConnectStr) {
        try {
            ZooKeeper zooKeeper = new ZooKeeper(zkConnectStr, 1000, new DefaultWatcher());
            Stat stat = zooKeeper.exists(LOCK_PATH, new DefaultWatcher());
            if (stat == null) {
                zooKeeper.create(LOCK_PATH, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

            }
            this.zooKeeper = zooKeeper;
        } catch (IOException e) {
            throw new RuntimeException("connect zk failed");
        } catch (InterruptedException e) {
            throw new RuntimeException("zk exception");
        } catch (KeeperException e) {
            throw new RuntimeException("zk exception");
        }

    }


    @Override
    public void lock() {
        CountDownLatch latch = new CountDownLatch(1);
        try {
            String currentPath = LOCK_PATH + LOCK_INSTANCE;
            selfPath = zooKeeper.create(currentPath, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            //Night gathers, and now my watch begins. It shall not end until my death.
            zooKeeper.getChildren(LOCK_PATH, new ChildrenWatcher(latch, selfPath, zooKeeper));
            latch.await();


        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        //delete
        try {
            zooKeeper.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}

class DefaultWatcher implements Watcher {
    @Override
    public void process(WatchedEvent event) {

    }
}

class ChildrenWatcher implements Watcher {
    private CountDownLatch latch;
    private String selfPath;
    private ZooKeeper zooKeeper;

    public ChildrenWatcher(CountDownLatch latch, String path, ZooKeeper zooKeeper) {
        this.latch = latch;
        this.selfPath = path;
        this.zooKeeper = zooKeeper;
    }

    @Override
    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected.equals(event.getState())) {
            if (Event.EventType.NodeChildrenChanged.equals(event.getType())) {
                try {
                    //for this night, and all the nights to come.
                    List<String> children = zooKeeper.getChildren(DistributedLock.LOCK_PATH, new ChildrenWatcher(latch, selfPath, zooKeeper));
                    Collections.sort(children);

                    if (children.get(0).equals(selfPath)) {
                        //now my watch is end
                        latch.countDown();
                    }

                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
