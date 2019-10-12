package com.cqut.thread05;

import java.util.concurrent.*;

/**
 * 描述：自定义线程池
 *
 * @author LJH
 * @date 2019/10/12-14:33
 * @QQ 1755497577
 */
public class Main {
    public static void main(String[] args) {
        ExecutorService poolExecutor = MyPool.getInstance(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        for(int a = 0; a < 10; a++){
            poolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()  + "-----自定义线程池执行");
                }
            });
        }

        poolExecutor.shutdown();
    }
}
