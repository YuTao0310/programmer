 import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class TransactionTest {
    public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/menagerie?characterEncoding=UTF-8","root", "0310");
			Statement s = c.createStatement();) {
 
            // �������ǰ����
            // �������еĶ��������Ҫô���ɹ���Ҫô��ʧ��
 
            //c.setAutoCommit(false);
            
            s.execute("start transaction;");
            // ��Ѫ��SQL
            String sql1 = "update hero set hp = hp +1 where id = 10";
            s.execute(sql1);
 
            // ��Ѫ��SQL
            // ��С��д��д���� updata(����update)
 
            String sql2 = "update hero set hp = hp -1 where id = 10";
            s.execute(sql2);

            s.execute("commit");
 
            // �ֶ��ύ
           //c.commit();

            s.execute("select * from hero where id = 10");
            ResultSet rs = s.getResultSet();
           //  ResultSet rs = s.executeQuery(sql);
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
}
