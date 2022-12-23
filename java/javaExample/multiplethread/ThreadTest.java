package multiplethread;
  
import hero.Hero;
   
public class ThreadTest {
     
    public static void main(String[] args) {
        final Hero ahri = new Hero();
        ahri.name = "��β����";
        final Hero annie = new Hero();
        annie.name = "����";
        
        Thread t1 = new Thread(){
            public void run(){
            	//ռ�о�β����
            	synchronized (ahri) {
            		System.out.println("t1 ��ռ�о�β����");
					try {
						//ͣ��1000���룬��һ���߳����㹻��ʱ��ռ�а���
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					System.out.println("t1 ��ͼռ�а���");
					System.out.println("t1 �ȴ��� ��������");
					synchronized (annie) {
						System.out.println("do something");
					}
				}	
            	
            }
        };
        t1.start();
        Thread t2 = new Thread(){
        	public void run(){
        		//ռ�а���
        		synchronized (annie) {
        			System.out.println("t2 ��ռ�а���");
        			try {
        				
        				//ͣ��1000���룬��һ���߳����㹻��ʱ��ռ�����þ�β����
        				Thread.sleep(1000);
        			} catch (InterruptedException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        			System.out.println("t2 ��ͼռ�о�β����");
        			System.out.println("t2 �ȴ��� ��������");
        			synchronized (ahri) {
        				System.out.println("do something");
        			}
        		}	
        		
        	}
        };
        t2.start();
   }
       
}
