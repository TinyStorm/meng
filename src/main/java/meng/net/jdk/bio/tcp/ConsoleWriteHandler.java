package meng.net.jdk.bio.tcp;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ConsoleWriteHandler implements Runnable {

    private Socket socket;

    public ConsoleWriteHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String line;
        try {
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
            while (true) {
                System.out.println("pls type");
                line = scanner.nextLine();
                if (line != null) {
                    printWriter.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
