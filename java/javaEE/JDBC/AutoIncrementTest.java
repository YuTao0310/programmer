import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class AutoIncrementTest {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "insert into hero values(null,?,?,?)";
        ResultSet rs = null;
        try (Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/menagerie?characterEncoding=UTF-8","root", "0310");
        	// 根据sql语句创建PreparedStatement
            Statement s = c.createStatement();
            PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            ps.setString(1, "提莫");
            ps.setFloat(2, 313.0f);
            ps.setInt(3, 50);
            ps.execute();
            rs = ps.getGeneratedKeys();
            int id = 0;
            if (rs.next()) {
                id = rs.getInt(1);
                System.out.format("更新后的id为 %d %n", id);
            }
            ResultSet rs1 = s.executeQuery("select * from hero where id = " + (--id));
            while(!rs1.next()) {
                rs1.close();
                rs = s.executeQuery("select * from hero where id = " + (--id));
                rs1 = rs;
            } 
            s.execute("delete from hero where id = " + id);

        } catch (SQLException e) {

            e.printStackTrace();
        } 
    }
}
