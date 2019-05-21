package meng.net.jdk.bio.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Server {

    static ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 5L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(20));


    public static void main(String[] args) throws IOException {


        ServerSocket server = new ServerSocket(8888);
        for (; ; ) {
            Socket socket = server.accept();
            System.out.println("receive connect");
            executor.submit(new ReadHandler(socket));
            executor.submit(new ConsoleWriteHandler(socket));//服务端和客户端单一交流
        }

    }



}
