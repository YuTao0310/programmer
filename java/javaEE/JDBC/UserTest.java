import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

   
public class UserTest {

    private static char getRandomChar(){
        double rand0 = Math.random();
        int rand;
        if(rand0 < 1.0/3){
            rand = (int)(Math.random() * 10 + 48);
        }else if(rand0 < 2.0/3){
            rand = (int)(Math.random() * 26 + 65);
        }else{
            rand = (int)(Math.random() * 26 + 97);
        }
        return (char)rand;
    }

    private static String getRandomString(int length) {
        String str = "";
        for (int i = 0; i < length; i++) {
            str += getRandomChar();
        }
        return str;
    }


    private static String getNumberString(int length) {
        String str = "";
        for (int i = 0; i < length; i++) {
            int num = (int)(Math.random() * 10 + 48);
            str = str + (char)num;
        }
        return str;
    }
    public static void main(String[] args) {
   
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
   
        String sql = "insert into user values(null, ?, ?)";
        try (
            Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/menagerie?characterEncoding=UTF-8",
                "root", "0310");
            PreparedStatement ps = c.prepareStatement(sql);          
        )
        {
            for (int i = 0; i < 10000; i++) {
                ps.setString(1, getRandomString(3));
                ps.setString(2, getNumberString(4));
                ps.execute();
            }
            
               
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}