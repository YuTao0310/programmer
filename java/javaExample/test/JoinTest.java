package test;

public class JoinTest {


    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e ) {
                e.printStackTrace();
            }
            System.out.println("Thread One!");
        });
    
        Thread threadTwo = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread Two!");
        });
        threadOne.start();
        threadTwo.start();
        threadOne.join();
        threadTwo.join();
        System.out.println("Thread Main!");
    }
}
