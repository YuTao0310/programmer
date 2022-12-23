package concurrent;

public class InterruptTest {
    public static void main(String[] args) {
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + " " + Thread.interrupted());
                System.out.println(Thread.currentThread() + " " + Thread.interrupted());
                System.out.println(Thread.currentThread() + " " + Thread.interrupted());
                System.out.println(Thread.currentThread() + " " + Thread.interrupted());
                System.out.println(Thread.currentThread() + " " + Thread.currentThread().isInterrupted());
            }
        });
        
        threadOne.start();
        threadOne.interrupt();
        System.out.println(Thread.currentThread() + " " + threadOne.isInterrupted());
    }
}
