package meng.net.jdk.bio.tcp;

import java.io.IOException;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8888);
        new Thread(new ReadHandler(socket)).start();
        new Thread(new ConsoleWriteHandler(socket)).start();
    }
}
