package meng.interview;

/**
 * 问题:两个线程 一个循环线程输出a,b,c,d,e...z 一个顺滑你输出1,2,3,4,5..9,0
 * 要求输出的结果是a1b2c3....
 */
public class PrintProblem {

    public static void main(String[] args) {
        char[] letter = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        char[] number = "1234567890".toCharArray();
        Lock o1 = new Lock(true);
        Lock o2 = new Lock(false);
        new Thread(new Task(number, o2, o1)).start();
        new Thread(new Task(letter, o1, o2)).start();
    }

}

class Lock {
    private boolean first;

    Lock(boolean first) {
        this.first = first;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }
}

class Task implements Runnable {

    private char[] toPrint;
    /**
     * 线程控制器
     */
    private Lock controller;

    /**
     * 对方线程控制器
     */
    private Lock targetController;

    Task(char[] toPrint, Lock controller, Lock targetController) {
        this.toPrint = toPrint;
        this.controller = controller;
        this.targetController = targetController;
    }

    @Override
    public void run() {
        int i = 0;
        if (!controller.isFirst()) {
            synchronized (controller) {
                try {
                    controller.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        while (true) {
            try {

                if (i > toPrint.length - 1) {
                    i = 0;
                }
                System.out.print(toPrint[i++]);
                synchronized (targetController) {
                    targetController.notify();
                }
                synchronized (controller) {
                    controller.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}