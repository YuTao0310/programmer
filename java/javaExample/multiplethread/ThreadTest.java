package multiplethread;
  
import hero.Hero;
   
public class ThreadTest {
     
    public static void main(String[] args) {
        final Hero ahri = new Hero();
        ahri.name = "九尾妖狐";
        final Hero annie = new Hero();
        annie.name = "安妮";
        
        Thread t1 = new Thread(){
            public void run(){
            	//占有九尾妖狐
            	synchronized (ahri) {
            		System.out.println("t1 已占有九尾妖狐");
					try {
						//停顿1000毫秒，另一个线程有足够的时间占有安妮
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					System.out.println("t1 试图占有安妮");
					System.out.println("t1 等待中 。。。。");
					synchronized (annie) {
						System.out.println("do something");
					}
				}	
            	
            }
        };
        t1.start();
        Thread t2 = new Thread(){
        	public void run(){
        		//占有安妮
        		synchronized (annie) {
        			System.out.println("t2 已占有安妮");
        			try {
        				
        				//停顿1000毫秒，另一个线程有足够的时间占有暂用九尾妖狐
        				Thread.sleep(1000);
        			} catch (InterruptedException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        			System.out.println("t2 试图占有九尾妖狐");
        			System.out.println("t2 等待中 。。。。");
        			synchronized (ahri) {
        				System.out.println("do something");
        			}
        		}	
        		
        	}
        };
        t2.start();
   }
       
}
