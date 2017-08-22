/**
 * Created by ly on 2017/3/3.
 */
public class SynchronizedClass {

    public void A() {
        System.out.println("当前线程：" + Thread.currentThread().getName());
        synchronized (SynchronizedClass.class) {
            System.out.println(Thread.currentThread().getName() + " 进入同步块");
        }
    }

    public synchronized void B() {
        System.out.println(Thread.currentThread().getName() + " 进入同步方法");
    }

    public void C() {
        System.out.println(Thread.currentThread().getName() + " 进入普通方法");
    }

    public static void main(String[] args) {
        SynchronizedClass s = new SynchronizedClass();
        SynchronizedClass d = new SynchronizedClass();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                s.A();
                s.B();
                s.C();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                d.B();
                d.A();
                d.C();
            }
        });


        thread1.setName("线程1");
        thread2.setName("线程2");
        thread1.start();
        thread2.start();
    }
}
