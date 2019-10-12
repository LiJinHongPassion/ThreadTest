package com.cqut.thread01;

/**
 * 描述：创建线程的四种方式并执行
 *
 * @author LJH
 * @date 2019/10/11-10:49
 * @QQ 1755497577
 */
public class Main {
    public static void main(String[] args) {

        /* 前期学习 */
        /* 1. 继承Thread类 */
        CreateThreadMethod01.main(args);
        /* 2. 实现Runnable接口 */
        CreateThreadMethod02.main(args);

        /* 后期学习 */
        /* 3. 实现 Callable 接口 */
        /* 4. 基于线程池的方式 */

    }
}
