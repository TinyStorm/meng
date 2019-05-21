package meng.net.jdk.bio.udp;

import java.io.IOException;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        DatagramSocket socket = new DatagramSocket(12345);
        String hello = "hello";
        new Thread(() -> {
            byte[] data = new byte[20];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            while (true) {
                try {
                    socket.receive(packet);
                    System.out.println(new String(packet.getData()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        while (true) {
            DatagramPacket packet = new DatagramPacket(hello.getBytes(), hello.length());
            packet.setAddress(Inet4Address.getByName("localhost"));
            packet.setPort(54321);
            socket.send(packet);
            Thread.sleep(1000);
        }
    }
}
