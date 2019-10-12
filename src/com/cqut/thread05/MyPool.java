package com.cqut.thread05;

import java.util.concurrent.*;

/**
 * 描述：自定义线程池
 *
 * @author LJH
 * @date 2019/10/12-14:46
 * @QQ 1755497577
 */
public class MyPool extends ThreadPoolExecutor {

    private MyPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    /**
     * 描述:自定义线程池
     *
     * @author LJH-1755497577 2019/10/12 14:55
     * @param corePoolSize 指定了线程池中的线程数量。
     * @param maximumPoolSize 指定了线程池中的最大线程数量
     * @param keepAliveTime 当前线程池数量超过 corePoolSize 时，多余的空闲线程的存活时间，即多次时间内会被销毁。
     * @param unit keepAliveTime 的单位。
     * @param workQueue 任务队列，被提交但尚未被执行的任务。
     * @param threadFactory 线程工厂，用于创建线程，一般用默认的即可。
     * @param handler 拒绝策略，当任务太多来不及处理，如何拒绝任务。
     * @return java.util.concurrent.ThreadPoolExecutor
     */
    public static ThreadPoolExecutor getInstance(int corePoolSize,
                                                 int maximumPoolSize,
                                                 long keepAliveTime,
                                                 TimeUnit unit,
                                                 BlockingQueue<Runnable> workQueue,
                                                 ThreadFactory threadFactory,
                                                 RejectedExecutionHandler handler){
        return new MyPool( corePoolSize,  maximumPoolSize,  keepAliveTime,
                unit,  workQueue, threadFactory, handler);
    }
}
