package meng.net.netty.tcp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server {
    private int port = 9999;

    /**
     * 创建两个EventLoop的组，EventLoop 这个相当于一个处理线程，
     * 是Netty接收请求和处理IO请求的线程。不理解的话可以百度NIO图解
     */
        /*
        相关资料：NioEventLoopGroup是一个处理I/O操作的多线程事件循环。
        Netty为不同类型的传输提供了各种EventLoopGroup实现。
        在本例中，我们正在实现一个服务器端应用程序，因此将使用两个NioEventLoopGroup。
        第一个，通常称为“boss”，接受传入的连接。
        第二个，通常称为“worker”，当boss接受连接并注册被接受的连接到worker时，处理被接受连接的流量。
        使用了多少线程以及如何将它们映射到创建的通道取决于EventLoopGroup实现，甚至可以通过构造函数进行配置。
        */
    public Server(int port) {
        this.port = port;
    }

    public void init() throws InterruptedException {
        EventLoopGroup acceptor = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        EventLoopGroup custom = new DefaultEventLoopGroup(20);

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(acceptor, worker);
        bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new MyDecoder()).addLast(new MyEncoder()).addLast(custom, new BizHandler());
            }
        });
//        bootstrap.handler(new MyDecoder()).handler(new MyEncoder());
        // 绑定端口，开始接收进来的连接
        ChannelFuture future = bootstrap.bind(port).sync();
        future.channel().closeFuture().sync();

    }

    public static void main(String[] args) {
        Server server = new Server(8888);
        try {
            server.init();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
