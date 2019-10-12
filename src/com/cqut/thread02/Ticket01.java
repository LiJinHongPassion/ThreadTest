package com.cqut.thread02;

/**
 * 描述：同步代码块
 *
 * @author LJH
 * @date 2019/10/12-10:02
 * @QQ 1755497577
 */
public class Ticket01 implements Runnable{
    private int ticket = 50;
    
    Object lock = new Object();    
    /*    
    * 执行卖票操作    
    */    
    @Override    
    public void run() {    
        //每个窗口卖票的操作         
        //窗口 永远开启         
        while(true){        
            synchronized (lock) {            
                if(ticket>0){//有票 可以卖                
                    //出票操作                    
                    //使用sleep模拟一下出票时间                     
                    try {                    
                        Thread.sleep(50);                        
                    } catch (InterruptedException e) {                    
                        e.printStackTrace();                        
                    }                    
                    //获取当前线程对象的名字                     
                    String name = Thread.currentThread().getName();                    
                    System.out.println(name + "正在卖:" + ticket--);
                }                
            } 
        }
    }

    public static void main(String[] args) {
        //创建线程任务对象
        Ticket01 ticket = new Ticket01();
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