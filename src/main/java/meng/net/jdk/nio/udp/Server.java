package meng.net.jdk.nio.udp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * 和nio一样 udpChannel既可以做客户端有能做服务端,区别在于是否绑定端口
 */
public class Server {

    public static void main(String[] args) throws IOException {

        DatagramChannel channel = DatagramChannel.open();
        channel.socket().bind(new InetSocketAddress(9999));

        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();
        /*阻塞，等待发来的数据*/
        channel.receive(buf);
        /*设置缓冲区可读*/
        buf.flip();

		/*循环读出所有字符*/
        while (buf.hasRemaining()) {
            System.out.print((char) buf.get());
        }


    }
}
