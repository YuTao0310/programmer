import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
 
public class ConnectionPool {
 
    List<Connection> cs = new LinkedList<Connection>();
 
    int size;
 
    public ConnectionPool(int size) {
        this.size = size;
        init();
    }
 
    public void init() {
         
        //����ǡǡ����ʹ��try-with-resource�ķ�ʽ����Ϊ��Щ���Ӷ���Ҫ��"��"�ģ���Ҫ���Զ��ر���
        try {
            Class.forName("com.mysql.jdbc.Driver");
            for (int i = 0; i < size; i++) {
                Connection c = DriverManager
                        .getConnection("jdbc:mysql://127.0.0.1:3306/menagerie?characterEncoding=UTF-8", "root", "0310");
 
                cs.add(c);
 
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    public synchronized Connection getConnection() {
        while (cs.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Connection c = cs.remove(0);
        return c;
    }
 
    public synchronized void returnConnection(Connection c) {
        cs.add(0, c);
        this.notifyAll();
    }
 
}
