import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryTest {

    public static void list(Statement s, int start, int count) {

        try {
            String sql = "select * from hero limit " + start + ", " + count;

            //  s.execute(sql);
            //  ResultSet rs = s.getResultSet();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");// ����ʹ���ֶ���
                String name = rs.getString(2);// Ҳ����ʹ���ֶε�˳��
                float hp = rs.getFloat("hp");
                int damage = rs.getInt(4);
                System.out.printf("%d\t%s\t%f\t%d%n", id, name, hp, damage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/menagerie?characterEncoding=UTF-8",
				"root", "0310"); Statement s = c.createStatement();) {
            list(s, 0, 2);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
