## 简述 - 线程安全
**问题**
如果有多个线程在同时运行，而这些线程可能会同时运行这段代码。程序每次运行结果和单线程运行的结果是一样的，而且其他的变量的值也和预期的是一样的，就是线程安全的。我们通过一个案例，演示线程的安全问题：
电影院要卖票，我们模拟电影院的卖票过程。假设要播放的电影是 “葫芦娃大战奥特曼”，本次电影的座位共100个(本场电影只能卖100张票)。我们来模拟电影院的售票窗口，实现多个窗口同时卖 “葫芦娃大战奥特曼”这场电影票(多个窗口一起卖这100张票)需要窗口，采用线程对象来模拟；需要票，Runnable接口子类来模拟
模拟票：
```java       
public class Ticket implements Runnable {
    private int ticket = 100;
    /*
     * 执行卖票操作
     */
    @Override
    public void run() {
        //每个窗口卖票的操作
        //窗口 永远开启
        while (true) {
            if (ticket > 0) {//有票 可以卖
                //出票操作
                //使用sleep模拟一下出票时间
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto‐generated catch block
                    e.printStackTrace();
                }
                //获取当前线程对象的名字
                String name = Thread.currentThread().getName();
                System.out.println(name + "正在卖:" + ticket‐‐);
            }
        }
    }
}
```
测试类：
```java
public class Demo {
    public static void main(String[] args) {    
        //创建线程任务对象        
        Ticket ticket = new Ticket();        
        //创建三个窗口对象        
        Thread t1 = new Thread(ticket, "窗口1");        
        Thread t2 = new Thread(ticket, "窗口2");        
        Thread t3 = new Thread(ticket, "窗口3");        
               
        //同时卖票        
        t1.start();        
        t2.start();
        t3.start();        
    }    
}
```
结果中有一部分这样现象：
```text
窗口3正在卖:5
窗口3正在卖:4
窗口1正在卖:3
窗口2正在卖:4
窗口1正在卖:2
窗口3正在卖:0
窗口3正在卖:-1
窗口2正在卖:1
```
发现程序出现了两个问题：
1. 相同的票数,比如5这张票被卖了两回。
2. 不存在的票，比如0票与-1票，是不存在的。
这种问题，几个窗口(线程)**票数不同步了**，这种问题称为线程不安全。线程安全问题都是由全局变量及静态变量引起的。若每个线程中对全局变量、静态变量只有读操作，而无写操作，一般来说，这个全局变量是线程安全的；若有多个线程同时执行写操作，一般都需要考虑线程同步，否则的话就可能影响线程安全。

## 线程同步
当我们使用多个线程访问同一资源的时候，且多个线程中对资源有写的操作，就容易出现线程安全问题。要解决上述多线程并发访问一个资源的安全性问题:也就是解决重复票与不存在票问题，Java中提供了同步机制(synchronized)来解决。
根据案例简述：
```text
窗口1线程进入操作的时候，窗口2和窗口3线程只能在外等着，窗口1操作结束，窗口1和窗口2和窗口3才有机会进入代码去执行。也就是说在某个线程修改共享资源的时候，其他线程不能修改该资源，等待修改完毕同步之后，才能去抢夺CPU资源，完成对应的操作，保证了数据的同步性，解决了线程不安全的现象。
```

为了保证每个线程都能正常执行原子操作,Java引入了线程同步机制。
那么怎么去使用呢？有三种方式完成同步操作：
<blockquote >
1. 同步代码块。
2. 同步方法。
3. 锁机制。
</blockquote >

#### 同步代码块
同步代码块 ： synchronized 关键字可以用于方法中的某个区块中，表示只对这个区块的资源实行互斥访问。
格式:
```java
synchronized(同步锁){
      需要同步操作的代码
 }
```
 
同步锁:
对象的同步锁只是一个概念,可以想象为在对象上标记了一个锁.
1. 锁对象 可以是任意类型。
2. 多个线程对象 要使用同一把锁。
注意:在任何时候,最多允许一个线程拥有同步锁,谁拿到锁就进入代码块,其他的线程只能在外等着(BLOCKED)。
使用同步代码块解决代码：
```java
public class Ticket implements Runnable{
    private int ticket = 100;    
    
    Object lock = new Object();    
    /*    
    * 执行卖票操作    
    */    
    @Override    
    public void run() {    
        //每个窗口卖票的操作         
        //窗口 永远开启         
        while(true){        
            synchronized (lock) {            
                if(ticket>0){//有票 可以卖                
                    //出票操作                    
                    //使用sleep模拟一下出票时间                     
                    try {                    
                        Thread.sleep(50);                        
                    } catch (InterruptedException e) {                    
                        e.printStackTrace();                        
                    }                    
                    //获取当前线程对象的名字                     
                    String name = Thread.currentThread().getName();                    
                    System.out.println(name+"正在卖:"+ticket‐‐);                    
                }                
            } 
        }
    }
}
```

#### 同步方法
使用synchronized修饰的方法,就叫做同步方法,保证A线程执行该方法的时候,其他线程只能在方法外等着。
格式：
```java
public synchronized void method(){
    /*可能会产生线程安全问题的代码*/
    }
```
同步锁是谁?
对于非static方法,同步锁就是this。
对于static方法,我们使用当前方法所在类的字节码对象(类名.class)。
使用同步方法代码如下：
```java
public class Ticket02 implements Runnable {
    private int ticket = 100;

    /*
     * 执行卖票操作
     */
    @Override
    public void run() {
        //每个窗口卖票的操作         
        //窗口 永远开启         
        while (true) {
            sellTicket();
        }
    }

    /*
     * 锁对象 是 谁调用这个方法 就是谁
     *   隐含 锁对象 就是  this
     *
     */
    public synchronized void sellTicket() {
        if (ticket > 0) {//有票 可以卖  
            //出票操作
            //使用sleep模拟一下出票时间
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto‐generated catch block  
                e.printStackTrace();
            }
            //获取当前线程对象的名字
            String name = Thread.currentThread().getName();
            System.out.println(name + "正在卖:" + ticket‐‐);
        }
    }
}
```

#### Lock 锁
java.util.concurrent.locks.Lock 机制提供了比synchronized代码块和synchronized方法更广泛的锁定操作,同步代码块/同步方法具有的功能Lock都有,除此之外更强大,更体现面向对象。Lock锁也称同步锁，加锁与释放锁方法化了，如下：
- public void lock() :加同步锁。
- public void unlock() :释放同步锁。
使用如下：
```java
public class Ticket03 implements Runnable {
    private int ticket = 50;

    Lock lock = new ReentrantLock();

    /*
     * 执行卖票操作
     */
    @Override
    public void run() {
        //每个窗口卖票的操作         
        //窗口 永远开启         
        while (true) {
            lock.lock();
            if (ticket > 0) {//有票 可以卖
                //出票操作                 
                //使用sleep模拟一下出票时间                 
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    // TODO Auto‐generated catch block
                    e.printStackTrace();
                }
                //获取当前线程对象的名字
                String name = Thread.currentThread().getName();
                System.out.println(name + "正在卖:" + ticket--);
            }
            lock.unlock();
        }
    }
}
```
