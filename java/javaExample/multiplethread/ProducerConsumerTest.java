package multiplethread;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


public class ProducerConsumerTest {

    List<Integer> list1 = new LinkedList<>();
    List<Integer> list2 = new LinkedList<>();
    int length = 100;

    
    private class producer implements Runnable {
        public void run() {
            try {
                for (int i = 0; i < length; i++) {
                    list2.add(1);
                    list1.add(1);
                    Thread.sleep(10);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class consumer implements Runnable {
        public void run() {
            while(list1.size() < length) {
                for (int index = 0; index < length; index++) {
                    int len1 = list1.size();
                    int len2 = list2.size();
                    if (len1 != len2) {
                        System.out.println(Thread.currentThread().getName() + ": len1--" + len1 + " len2--" + len2);
                    }
                }
            }
        }
    }
    private void test() {
        Thread p1 = new Thread(new producer());
        Thread c1 = new Thread(new consumer());
        p1.start();
        c1.start();
        try {
            p1.join();
            c1.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new ProducerConsumerTest().test();
    }
}
