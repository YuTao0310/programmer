import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
   
public class JDBCTest {
    public static void main(String[] args) {
   
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
   
        try (
            Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/menagerie?characterEncoding=UTF-8",
                "root", "0310");
            Statement s = c.createStatement();             
        )
        {
            String sql = "insert into hero values(null,'ÌáÄª',313.0 , 50)";
           // String sql = "insert into hero values(null," + "'ÌáÄª'" + "," + 313.0f + "," + 50 + ")";
            s.execute(sql);
               
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}