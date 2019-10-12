package com.cqut.thread03;

/**
 * 描述：time waiting
 *
 * @author LJH
 * @date 2019/10/12-11:06
 * @QQ 1755497577
 */
public class MyThread01 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if ((i) % 10 == 0) {
                System.out.println("‐‐‐‐‐‐‐" + i);
            }
            System.out.print(i);
            try {
                //线程睡眠1秒，超时后自动唤醒
                Thread.sleep(1000);
                System.out.print("    线程睡眠1秒！\n");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new MyThread01().start();
    }
}
