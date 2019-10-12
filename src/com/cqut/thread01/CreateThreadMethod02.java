package com.cqut.thread01;

/**
 * 描述：实现Runnable接口
 *
 * @author LJH
 * @date 2019/10/11-11:16
 * @QQ 1755497577
 */
public class CreateThreadMethod02 implements Runnable {

    private int num;

    public CreateThreadMethod02(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        try {
            //睡眠num秒
            Thread.sleep(num*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(num + "s执行 -- CreateThreadMethod02.run() -- 实现Runnable");
    }


    public static void main(String[] args) {

        //创建10个线程
        for(int a = 0;a< 10 ;a++){
            //随机数获取
            int sleepTime = getRandomTime();
            System.out.println("sleep = " + sleepTime);

            //实例化线程
            CreateThreadMethod02 t = new CreateThreadMethod02(sleepTime);

            //实例化Thread
            Thread thread = new Thread(t);

            //执行线程
            thread.start();
        }

    }

    /**
     * 描述: 取10以内的随机数
     *
     * @author LJH-1755497577 2019/10/11 11:08
     * @param
     * @return int
     */
    public static int getRandomTime(){
        return (int)(Math.random()*10+1);
    }
}
