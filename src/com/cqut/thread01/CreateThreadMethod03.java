package com.cqut.thread01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 描述：实现callable接口(未完成)
 *
 * @author LJH
 * @date 2019/10/11-11:39
 * @QQ 1755497577
 */
public class CreateThreadMethod03 implements Callable {

    private int num;

    public CreateThreadMethod03(int num) {
        this.num = num;
    }

    @Override
    public Object call() throws Exception {
        try {
            //睡眠num秒
            Thread.sleep(num*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(num + "s执行 -- CreateThreadMethod03.call() -- 实现Callable");
        return "callable return value ";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int taskSize = 10;
        //创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(taskSize);
        // 创建多个有返回值的任务
        List<Future> list = new ArrayList<>();
        for (int i = 0; i < taskSize; i++) {
            Callable c = new CreateThreadMethod03(getRandomTime());
            // 执行任务并获取 Future 对象
            Future f = pool.submit(c);
            System.out.println("res：" + f.get().toString());
            list.add(f);
        }
        // 关闭线程池
        pool.shutdown();
        // 获取所有并发任务的运行结果
//        for (Future f : list) {
//            // 从 Future 对象上获取任务的返回值，并输出到控制台
//            System.out.println("res：" + f.get().toString());
//        }
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
