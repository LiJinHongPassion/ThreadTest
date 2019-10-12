package com.cqut.thread02;

import com.cqut.thread02.question01.Demo;

/***
 * 描述：
 *
 * @author LJH
 * @date 2019/10/11-10:11
 * @QQ 1755497577
 */
public class Main {
    public static void main(String[] args) {

        /* 1. 问题描述 */
            Demo.main(args );
        /* 2. 线程同步 */
            /* 2.1 同步代码块 */
                Ticket01.main(args);
            /* 2.2 同步方法 */
                Ticket02.main(args);
            /* 2.3 锁机制 */
                Ticket02.main(args);
    }
}
