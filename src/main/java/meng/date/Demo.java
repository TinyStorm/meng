package meng.date;

import meng.mongo.bean.Student;

import java.io.FileInputStream;
import java.util.TreeSet;

/**
 * try with resource
 */
public class Demo {
    public static void main(String[] args) {

        //申明在try里边的 f1,f2可以不用关闭,jdk7后 继承了AutoCloseable的接口用此种方式会自动关闭。
        try (FileInputStream f1 = new FileInputStream("");
             FileInputStream f2 = new FileInputStream("")) {

        } catch (Exception e) {

        }


        TreeSet set = new TreeSet();
//        set.add(1);
//        set.add(2);
//        set.add(3);
//        set.add(5);
//        set.add(0);
        set.add(new Student());
        set.add(new Student());
        set.add(new Student());
        System.out.println(set);
    }
}
