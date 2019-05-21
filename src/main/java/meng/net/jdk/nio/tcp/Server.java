package meng.net.jdk.nio.tcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.socket().bind(new InetSocketAddress(6666), 1024);//backlog为最大连接数
        System.out.println("listening on port 6666");
        ServerSocketChannel serverChannel1 = ServerSocketChannel.open();
        serverChannel1.configureBlocking(false);
        serverChannel1.socket().bind(new InetSocketAddress(7777), 1024);//backlog为最大连接数
        System.out.println("listening on port 7777");
        Selector selector = Selector.open();
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        serverChannel1.register(selector, SelectionKey.OP_ACCEPT); //可以绑定多个端口
        Thread.sleep(100);
        new Thread(new ReactorTask(selector)).start();
    }

}
