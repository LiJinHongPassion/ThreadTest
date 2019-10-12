package com.cqut.thread04;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述：singleThreadExecutor
 *
 * @author LJH
 * @date 2019/10/12-13:55
 * @QQ 1755497577
 */
public class pool04 {
    public static void main(String[] args) {
        //创建一个单线程化的线程池
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {
                public void run() {
                    try {
                        //结果依次输出，相当于顺序执行各个任务
                        System.out.println(Thread.currentThread().getName() + "正在被执行,打印的值是:" + index);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        //关闭线程池
        singleThreadExecutor.shutdown();
    }
}
