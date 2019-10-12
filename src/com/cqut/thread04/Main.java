package com.cqut.thread04;

/**
 * 描述：四种线程池
 *
 * @author LJH
 * @date 2019/10/12-12:38
 * @QQ 1755497577
 */
public class Main {
    public static void main(String[] args) {
        /* 1. newCachedThreadPool */
        pool01.main(args);
        /* 2. newFixedThreadPool */
        pool02.main(args);
        /* 3. newScheduledThreadPool */
        pool03.main(args);
        /* 4. newSingleThreadExecutor */
        pool04.main(args);
    }
}
