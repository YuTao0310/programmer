package multiplethread;
  
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
  
public class ThreadPoolTest {
  
    public static void main(String[] args) throws InterruptedException {
          
        ThreadPoolExecutor threadPool= new ThreadPoolExecutor(10, 15, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
          
        threadPool.execute(new Runnable(){
            @Override
            public void run() {
                System.out.println("хннЯ1");
            } 
        });
        
  
    }
  
}
