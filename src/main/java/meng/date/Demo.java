package meng.date;

import java.io.FileInputStream;

public class Demo {
    public static void main(String[] args) {

        //申明在try里边的 f1,f2可以不用关闭,jdk7后 继承了AutoCloseable的接口用此种方式会自动关闭。
        try (FileInputStream f1 = new FileInputStream("");
             FileInputStream f2 = new FileInputStream("")) {

        } catch (Exception e) {

        }
    }
}
