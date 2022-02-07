import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


  
public class ConnectionPoolTest {
  
    public static void main(String[] args) {
        long connectionPoolTime, traditionTime, start, end;
        List<Thread> poolWayList = new ArrayList<>();
        ConnectionPool cp = new ConnectionPool(10);
        start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            Thread t = new WorkingThread("connection pool thread" + i, cp);
            t.start();
            poolWayList.add(t);
        }

        //等待所有线程结束
        for (Thread t : poolWayList) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        end = System.currentTimeMillis();
        connectionPoolTime = end - start;

        List<Thread> traditionalWayList = new ArrayList<>();
        start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            Thread t = new TraditionalThread("traditional thread" + i);
            t.start();
            traditionalWayList.add(t);
        }
        for (Thread t : traditionalWayList) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        end = System.currentTimeMillis();
        traditionTime = end - start;
        System.out.println("connection pool time is " + connectionPoolTime + " traditional time is " + traditionTime);
    }

}
  
class WorkingThread extends Thread {
    private ConnectionPool cp;
  
    public WorkingThread(String name, ConnectionPool cp) {
        super(name);
        this.cp = cp;
    }
  
    public void run() {
        Connection c = cp.getConnection();
        System.out.println(this.getName()+ ":\t 获取了一根连接，并开始工作"  );
        try (Statement st = c.createStatement()){

            st.execute("insert into hero1 values(null, 'ty', 1000, 100)");
  
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cp.returnConnection(c);
    }
}

class TraditionalThread extends Thread {
    public TraditionalThread() {
        super();
    }

    public TraditionalThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        try (
            Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/menagerie?characterEncoding=UTF-8","root", "0310");
            Statement s = c.createStatement();
            ) 
        {
            System.out.println(this.getName()+ ":\t 获取了一根连接，并开始工作"  );
            s.execute("insert into hero1 values(null, 'ty', 1000, 100)");    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
