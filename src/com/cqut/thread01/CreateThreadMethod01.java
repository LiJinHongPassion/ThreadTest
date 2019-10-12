package com.cqut.thread01;

/**
 * 描述：继承Thread类
 *
 * @author LJH
 * @date 2019/10/11-10:52
 * @QQ 1755497577
 */
public class CreateThreadMethod01 extends Thread {

    private int num;

    public CreateThreadMethod01(int num) {
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
        System.out.println(num + "s执行 -- CreateThreadMethod01.run() -- 继承thread");
    }


    public static void main(String[] args) {

        //创建10个线程
        for(int a = 0;a< 10 ;a++){
            //随机数获取
            int sleepTime = getRandomTime();
            System.out.println("sleep = " + sleepTime);

            //实例化线程
            CreateThreadMethod01 t = new CreateThreadMethod01(sleepTime);

            //执行线程
            t.start();
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
