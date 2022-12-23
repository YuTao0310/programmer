package multiplethread;
import hero.Hero;

public class DeadLockTest {
    public static void main(String[] args) {
        Hero a = new Hero();
        Hero b = new Hero();
        Hero c = new Hero();
        Thread t1 = new Thread() {
            @Override
            public void run() {
                synchronized (a) {
                    System.out.println(this.getName() + "����ռ��a");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(this.getName() + "��ͼռ��b");
                    System.out.println(this.getName() + "�ȴ���");
                    synchronized (b) {
                        System.out.println("do something");
                    }
                }
            }
        };
        t1.setName("t1");
        
        Thread t2 = new Thread() {
            @Override
            public void run() {
                synchronized (b) {
                    System.out.println(this.getName() + "����ռ��b");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(this.getName() + "��ͼռ��c");
                    System.out.println(this.getName() + "�ȴ���");
                    synchronized (c) {
                        System.out.println("do something");
                    }
                }
            }
        };
        t2.setName("t2");
        Thread t3 = new Thread() {
            @Override
            public void run() {
                synchronized (c) {
                    System.out.println(this.getName() + "����ռ��c");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(this.getName() + "��ͼռ��a");
                    System.out.println(this.getName() + "�ȴ���");
                    synchronized (a) {
                        System.out.println("do something");
                    }
                }
            }
        };
        t3.setName("t3");
        t1.start();
        t2.start();
        t3.start();
    }
}