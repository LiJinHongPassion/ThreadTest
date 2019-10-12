package com.cqut.thread02;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述：锁机制
 *
 * @author LJH
 * @date 2019/10/12-10:19
 * @QQ 1755497577
 */
public class Ticket03 implements Runnable {
    private int ticket = 50;

    Lock lock = new ReentrantLock();

    /*
     * 执行卖票操作
     */
    @Override
    public void run() {
        //每个窗口卖票的操作         
        //窗口 永远开启
        while (true) {
            lock.lock();
            if (ticket > 0) {//有票 可以卖
                //出票操作
                //使用sleep模拟一下出票时间
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    // TODO Auto‐generated catch block
                    e.printStackTrace();
                }
                //获取当前线程对象的名字
                String name = Thread.currentThread().getName();
                System.out.println(name + "正在卖:" + ticket--);
            }
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        //创建线程任务对象
        Ticket03 ticket = new Ticket03();
        //创建三个窗口对象
        Thread t1 = new Thread(ticket, "窗口1");
        Thread t2 = new Thread(ticket, "窗口2");
        Thread t3 = new Thread(ticket, "窗口3");

        //同时卖票
        t1.start();
        t2.start();
        t3.start();
    }
}
