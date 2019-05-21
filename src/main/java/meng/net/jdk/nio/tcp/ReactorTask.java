package meng.net.jdk.nio.tcp;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;


public class ReactorTask implements Runnable {

    private ByteBuffer readBuffer = ByteBuffer.allocateDirect(1024);
    private ByteBuffer writeBuffer = ByteBuffer.allocateDirect(1024);
    private Selector selector;

    public ReactorTask(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void run() {
        try {
            int selected = 0;
            //select方法会阻塞直到有一个连接,重载,若参数为0则一直阻塞,若参数为正数n,则阻塞直到n毫秒
            while ((selected = selector.select()) > 0) {
//                selector.select();
                System.out.println(selected);
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectedKeys.iterator();
                SelectionKey key = null;
                while (iterator.hasNext()) {
                    key = iterator.next();
                    iterator.remove();
                    try {
                        handleInput(key);
                    } catch (Exception e) {
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleInput(SelectionKey key) throws IOException {
        if (key.isAcceptable()) {
            System.out.println("isAcceptable");
            ServerSocketChannel server = (ServerSocketChannel) key.channel();

            // 新注册channel
            SocketChannel socketChannel = server.accept();
            if (socketChannel == null) {
                return;
            }
            socketChannel.configureBlocking(false);
            // 注意！这里和阻塞io的区别非常大，在编码层面之前的等待输入已经变成了注册事件，这样我们就可以在等待的时候做别的事情，
            // 比如监听更多的socket连接，也就是之前说了一个线程监听多个socket连接。这也是在编码的时候最直观的感受
            socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);


            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
            buffer.put("hi new channel".getBytes());
            buffer.flip();
            socketChannel.write(buffer);
        }
        // 服务端关心的可读，意味着有数据从client传来了，根据不同的需要进行读取，然后返回
        if (key.isReadable()) {
            System.out.println("isReadable");
            SocketChannel socketChannel = (SocketChannel) key.channel();

            readBuffer.clear();
            socketChannel.read(readBuffer);
            readBuffer.flip();

            String receiveData = Charset.forName("UTF-8").decode(readBuffer).toString();
            System.out.println("receiveData:" + receiveData);

            // 把读到的数据绑定到key中
            key.attach("server message echo:" + receiveData);
        }
        // 实际上服务端不在意这个，这个写入应该是client端关心的，这只是个demo,顺便试一下selectionKey的attach方法
        if (key.isWritable()) {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            String message = (String) key.attachment();
            if (message == null) {
                return;
            }
            key.attach(null);

            writeBuffer.clear();
            writeBuffer.put(message.getBytes());
            writeBuffer.flip();
            while (writeBuffer.hasRemaining()) {
                socketChannel.write(writeBuffer);
            }
        }
    }
}
