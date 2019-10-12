package com.cqut.thread04;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述：newCachedThreadPool
 *
 * @author LJH
 * @date 2019/10/12-12:54
 * @QQ 1755497577
 */
public class pool01 {
    public static void main(String[] args) {
        //创建一个可缓存线程池
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            try {
                //sleep可明显看到使用的是线程池里面以前的线程，没有创建新的线程
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cachedThreadPool.execute(new Runnable() {
                public void run() {
                    //打印正在执行的缓存线程信息
                    System.out.println(Thread.currentThread().getName() + "正在被执行");
                }
            });
        }
        //关闭线程池
        cachedThreadPool.shutdown();
    }
}