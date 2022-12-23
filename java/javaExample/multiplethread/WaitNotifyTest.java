package multiplethread;
import hero.Hero;

public class WaitNotifyTest {
    public static void main(String[] args) {
        Hero gareen = new Hero("盖伦", 616);
        Thread[] recoverThreads = new Thread[2];
        Thread[] hurtThreads = new Thread[5];
        for (int i = 0; i < recoverThreads.length; i++){
            Thread recoverThread = new Thread("recover thread" + (i + 1)) {
                @Override
                public void run() {
                    System.out.println(this.getName() + "开始了！");
                    while(true) {
                        gareen.recover();
                    }
                }
            };
            recoverThreads[i] = recoverThread;
        }

        for (int i = 0; i < hurtThreads.length; i++) {
            Thread hurtThread = new Thread("hurt thread" + (i + 1)) {
                @Override
                public void run() {
                    System.out.println(this.getName() + "开始了！");
                    while(true) {
                        gareen.hurt();
                    }
                }
            };
            hurtThreads[i] = hurtThread;
        }

        for (Thread t : recoverThreads) {
            t.start();
        }

        for (Thread t : hurtThreads) {
            t.start();
        }
    }
}
