package meng.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;

public class DemoWatcher implements Watcher {


    public static String ZK_CONNECT_STRING = "localhost:2181";
    public static int SESSION_TIMEOUT = 5000;


    public void process(WatchedEvent watchedEvent) {

        if (Event.KeeperState.SyncConnected.equals(watchedEvent.getState())) {
            System.out.println("DemoWatcher process " + System.currentTimeMillis());
        }

    }


    public static void main(String[] args) {
        /*
        new Thread(() -> {
            DemoWatcher demoWatcher1 = new DemoWatcher();
            ZooKeeper zooKeeper1 = null;
            try {
                zooKeeper1 = new ZooKeeper(ZK_CONNECT_STRING, SESSION_TIMEOUT, demoWatcher1);
                zooKeeper1.exists("/demo", event -> {
                    System.out.println("second exists watcher process " + System.currentTimeMillis());
                });
                Thread.sleep(100000000);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (KeeperException e) {
                e.printStackTrace();
            }

        }).start();
        */

        DemoWatcher demoWatcher = new DemoWatcher();
        try {
            ZooKeeper zooKeeper = new ZooKeeper(ZK_CONNECT_STRING, SESSION_TIMEOUT, demoWatcher);
            zooKeeper.getChildren("/demo", event -> {
                System.out.println("main exists watcher process " + System.currentTimeMillis());
                if (Event.KeeperState.SyncConnected.equals(event.getState())) {
                    if (Event.EventType.NodeChildrenChanged.equals(event.getType())) {

                        System.out.println("node changed:" + event.getPath());
                    }
                }
            });
            System.out.println(System.currentTimeMillis());
            String path = zooKeeper.create("/demo/test", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            zooKeeper.delete(path, -1);

            Thread.sleep(10000);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }
}
