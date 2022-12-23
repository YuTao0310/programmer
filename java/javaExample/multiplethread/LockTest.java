package multiplethread;
 
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
 
public class LockTest {
 
    public static String now() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }
 
    public static void log(String msg) {
        System.out.printf("%s %s %s %n", now() , Thread.currentThread().getName() , msg);
    }
 
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        
        Thread t1 = new Thread() {
            public void run() {
                try {
                    log("�߳�����");
                    log("��ͼռ�ж���lock");
 
                    lock.lock();
 
                    log("ռ�ж���lock");
                    log("����5���ҵ�����");
                    Thread.sleep(5000);
                    log("��ʱ�ͷŶ��� lock�� ���ȴ�");
                    condition.await();
                    log("����ռ�ж��� lock��������5���ҵ�����");
                    Thread.sleep(5000);
 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    log("�ͷŶ���lock");
                    lock.unlock();
                }
                log("�߳̽���");
            }
        };
        t1.setName("t1");
        t1.start();
        try {
            //����t1��2��
            Thread.sleep(2000);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        Thread t2 = new Thread() {
 
            public void run() {
                try {
                    log("�߳�����");
                    log("��ͼռ�ж���lock");
 
                    lock.lock();
 
                    log("ռ�ж���lock");
                    log("����5���ҵ�����");
                    Thread.sleep(5000);
                    log("���ѵȴ��е��߳�");
                    condition.signal();
 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    log("�ͷŶ���lock");
                    lock.unlock();
                }
                log("�߳̽���");
            }
        };
        t2.setName("t2");
        t2.start();
    }
 
}
