package multiplethread;

public class HeroTest {
    public static void main(String[] args) {
        Thread t1= new Thread(){
            public void run(){
                int seconds =0;
                
                while(true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.printf("�Ѿ�����LOL %d ��%n", seconds++);
                    
                }               
            }
        };
        t1.setDaemon(false);
        t1.start();
    }
}
