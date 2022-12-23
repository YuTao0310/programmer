package multiplethread;
 
import java.util.LinkedList;
 
public class ThreadPool {
 
    // �̳߳ش�С
    int threadPoolSize;
 
    // �����������൱��Prodecer��Consumer�е�Stack
    LinkedList<Runnable> tasks = new LinkedList<Runnable>();
 
    // ��ͼ����������߳�
 
    public ThreadPool() {
        threadPoolSize = 10;
 
        // ����10�������������߳�
        /** �൱��ProducerConsumer��main�� */
        /** ���synchronized(tasks)��ʵҲ����ʡ�� */
        synchronized (tasks) {
            for (int i = 0; i < threadPoolSize; i++) {
                new TaskConsumeThread("�����������߳� " + i).start();
            }
        }
    }
 
    /** �൱��Producer */
    public void add(Runnable r) {
        /** �൱��ProducerConsumer��stack��push���� */
        synchronized (tasks) {
            tasks.add(r);
            // ���ѵȴ��������������߳�
            tasks.notifyAll();
        }
    }
 
    /** �൱��Consumer */
    class TaskConsumeThread extends Thread {
        public TaskConsumeThread(String name) {
            super(name);
        }
 
        Runnable task;
 
        public void run() {
            System.out.println("������ " + this.getName());
            while (true) {
                /** �൱��ProducerConsumer�е�Stack��pull���� */
                synchronized (tasks) {
                    while (tasks.isEmpty()) {
                        try {
                            tasks.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    task = tasks.removeLast();
                    // �������������߳̿��Լ����������
                    tasks.notifyAll();
 
                }
                System.out.println(this.getName() + " ��ȡ�����񣬲�ִ��");
                task.run();
            }
        }
    }
 
}