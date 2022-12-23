import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

   
public class PreparedStatementTest {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
 
        long statementTime, PreparedStatementTime, start, end;
        String sql = "insert into hero1 values(null,?,?,?)";
        try (Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/menagerie?characterEncoding=UTF-8","root", "0310");
        	// 根据sql语句创建PreparedStatement
            Statement s = c.createStatement();
            PreparedStatement ps = c.prepareStatement(sql);
        ) {
            start = System.currentTimeMillis();
            sql = "insert into hero1 values(null,'提莫',313.0, 50)";
            // sql = "insert into hero1 values(null," + "'提莫'" + "," + 313.0f + "," + 50 + ")";
            for (int i = 0; i < 10000; i++) {
                s.execute(sql);
            }
            end = System.currentTimeMillis();
            statementTime = end - start;

            start = System.currentTimeMillis();
            for (int i = 0; i < 10000; i++) {
                ps.setString(1, "提莫");
                ps.setFloat(2, 313.0f);
                ps.setInt(3, 50);
                // 执行
                ps.execute();
            }
            end = System.currentTimeMillis();
            PreparedStatementTime = end - start;

            System.out.println("statement time is " + statementTime + " prepared statement time is " + PreparedStatementTime);
            // 设置参数
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
   
    }
}
