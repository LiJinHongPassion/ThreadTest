## 简述 - 线程创建
四种创建方式分类
---

**有返回值**
- 实现callable接口
- 

**无返回值**
- 继承 Thread 类
- 实现 Runnable 接口

---

**thread类api**
- 继承 Thread 类
- 实现 Runnable 接口

**线程池**
- 实现callable接口
- 

---

**Thread 类**
在上一天内容中我们已经可以完成最基本的线程开启，那么在我们完成操作过程中用到了 java.lang.Thread 类，API中该类中定义了有关线程的一些方法，具体如下：
构造方法：
- public Thread() :分配一个新的线程对象。
- public Thread(String name) :分配一个指定名字的新的线程对象。
- public Thread(Runnable target) :分配一个带有指定目标新的线程对象。
- public Thread(Runnable target,String name) :分配一个带有指定目标新的线程对象并指定名字。
常用方法：
- public String getName() :获取当前线程名称。
- public void start() :导致此线程开始执行; Java虚拟机调用此线程的run方法。
- public void run() :此线程要执行的任务在此处定义代码。
- public static void sleep(long millis) :使当前正在执行的线程以指定的毫秒数暂停（暂时停止执行）。
- public static Thread currentThread() :返回对当前正在执行的线程对象的引用。
翻阅API后得知创建线程的方式总共有两种，一种是继承Thread类方式，一种是实现Runnable接口方式，方式一我们上一天已经完成，接下来讲解方式二实现的方式。

## 继承 Thread 类
Thread 类本质上是实现了 Runnable 接口的一个实例，代表一个线程的实例。启动线程的唯一方法就是通过 Thread 类的 start()实例方法。start()方法是一个 native 方法，它将启动一个新线程，并执行 run()方法。
```java
public class MyThread extends Thread {
    public void run() {
        System.out.println("MyThread.run()");
    }
}

//启动
MyThread myThread1 = new MyThread();
myThread1.start();
```

## 实现 Runnable 接口
如果自己的类已经 extends 另一个类，就无法直接 extends Thread，此时，可以实现一个Runnable 接口。
```java
public class MyThread extends OtherClass implements Runnable {
    public void run() {
        System.out.println("MyThread.run()");
    }
}

//启动 MyThread，需要首先实例化一个 Thread，并传入自己的 MyThread 实例：
MyThread myThread = new MyThread();
Thread thread = new Thread(myThread);
thread.start();

//事实上，当传入一个 Runnable target 参数给 Thread 后，Thread 的 run()方法就会调用target.run()
public void run() {
    if (target != null) {
        target.run();
    }
}
```

## ExecutorService 、Callable<Class> 、Future 有返回值 线程
有返回值的任务必须实现 Callable 接口，类似的，无返回值的任务必须 Runnable 接口。执行Callable 任务后，可以获取一个 Future 的对象，在该对象上调用 get 就可以获取到 Callable 任务返回的 Object 了，再结合线程池接口 ExecutorService 就可以实现传说中有返回结果的多线程了。


## Thread 和Runnable的区别
如果一个类继承Thread，则不适合资源共享。但是如果实现了Runable接口的话，则很容易的实现资源共享。
总结：
实现Runnable接口比继承Thread类所具有的优势：
1. 适合多个相同的程序代码的线程去共享同一个资源。
2. 可以避免java中的单继承的局限性。
3. 增加程序的健壮性，实现解耦操作，代码可以被多个线程共享，代码和线程独立。
4. 线程池只能放入实现**Runable或Callable类**线程，不能直接放入继承Thread的类。
<blockquote >扩充：在java中，每次程序运行至少启动2个线程。一个是main线程，一个是垃圾收集线程。因为每当使用java命令执行一个类的时候，实际上都会启动一个JVM，每一个JVM其实在就是在操作系统中启动了一个进程。</blockquote >

## 匿名内部类方式实现线程的创建
使用线程的内匿名内部类方式，可以方便的实现每个线程执行不同的线程任务操作。
使用匿名内部类的方式实现Runnable接口，重新Runnable接口中的run方法：
```java
  public class NoNameInnerClassThread {
   public static void main(String[] args) {            
// new Runnable(){      
// public void run(){          
// for (int i = 0; i < 20; i++) {              
// System.out.println("张宇:"+i);                  
// }              
// }            
//    }; //‐‐‐这个整体  相当于new MyRunnable()    
        Runnable r = new Runnable(){
            public void run(){
                for (int i = 0; i < 20; i++) {
                   System.out.println("张宇:"+i);  
                }
            } 
        };
        new Thread(r).start();
```