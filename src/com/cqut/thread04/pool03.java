package com.cqut.thread04;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 描述：ScheduledExecutorService
 *
 * @author LJH
 * @date 2019/10/12-13:26
 * @QQ 1755497577
 */
public class pool03 {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool= Executors.newScheduledThreadPool(3);

        /**
         * 描述：延时执行（只执行一次）
         * @author LJH-1755497577 2019/10/12 13:35
         * @param command 执行线程
         * @param delay 延时
         * @param unit 计时单位
         */
        scheduledThreadPool.schedule(new Runnable(){
            @Override
            public void run() {
                System.out.println("延迟三秒执行");
            }
        }, 3, TimeUnit.SECONDS);

        /**
         * 描述：周期执行
         * @author LJH-1755497577 2019/10/12 13:35
         * @param command 执行线程
         * @param initialDelay 初始化延时
         * @param period 两次开始执行最小间隔时间
         * @param unit 计时单位
         */
        scheduledThreadPool.scheduleAtFixedRate(new Runnable(){
            @Override
            public void run() {
                System.out.println("延迟 1 秒后每三秒执行一次");
            }
        },1,3,TimeUnit.SECONDS);


        /**
         * 描述：周期执行
         * @author LJH-1755497577 2019/10/12 13:35
         * @param command 执行线程
         * @param initialDelay 初始化延时
         * @param period 前一次执行结束到下一次执行开始的间隔时间（间隔执行延迟时间）
         * @param unit 计时单位
         */
        scheduledThreadPool.scheduleWithFixedDelay(new Runnable(){
            @Override
            public void run() {
                System.out.println("延迟 1 秒后每上一个线程执行完成后，间隔3秒，开始下一次线程执行");
            }
        },1,3,TimeUnit.SECONDS);

        //关闭线程池
        scheduledThreadPool.shutdown();
    }
}
