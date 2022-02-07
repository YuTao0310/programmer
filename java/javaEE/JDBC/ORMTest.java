import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
 
  
public class ORMTest {
  
    public static Hero get(int id) {
        Hero hero = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/menagerie?characterEncoding=UTF-8","root", "0310");
			Statement s = c.createStatement();) {

            String sql = "select * from hero where id = " + id;
  
            ResultSet rs = s.executeQuery(sql);
  
            // 因为id是唯一的，ResultSet最多只能有一条记录
            // 所以使用if代替while
            if (rs.next()) {
                String name = rs.getString(2);
                float hp = rs.getFloat("hp");
                int damage = rs.getInt(4);
                hero = new Hero(id, name, hp, damage);
            }
  
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return hero;
    }

    public static void add(Hero h) {
        int id = h.id;
        String name = h.name;
        float hp = h.hp;
        int damage = h.damage;
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/menagerie?characterEncoding=UTF-8","root", "0310");
			Statement s = c.createStatement();) {

            String sql = String.format("insert into hero values(%d, '%s', %f, %d)", id, name, hp, damage);
  
            s.execute(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    public static void delete(Hero h) {
        int id = h.id;
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/menagerie?characterEncoding=UTF-8","root", "0310");
			Statement s = c.createStatement();) {

            String sql = String.format("delete from hero where id = %d",id);
  
            s.execute(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

     
    public static void update(Hero h) {
        int id = h.id;
        String name = h.name;
        float hp = h.hp;
        int damage = h.damage;
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/menagerie?characterEncoding=UTF-8","root", "0310");
			Statement s = c.createStatement();) {

            String sql = String.format("update hero set name = '%s', hp = %f, damage = %d where id = %d", name, hp, damage, id);
  
            s.execute(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}
    } 

    public static List<Hero> list() {
        List<Hero> l = new ArrayList<>();
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/menagerie?characterEncoding=UTF-8","root", "0310");
			Statement s = c.createStatement();) {

            String sql = String.format("select * from hero");
  
            ResultSet rs = s.executeQuery(sql);

            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                float hp = rs.getFloat("hp");
                int damage = rs.getInt("damage");
                l.add(new Hero(id, name, hp, damage));
            }

		} catch (SQLException e) {
			e.printStackTrace();
		} 

        return l;

    }
  
    public static void main(String[] args) {
        add(new Hero(20, "ty", 2000, 100));
        add(new Hero(21, "ty1", 2100, 110));
        delete(new Hero(21, "ty1", 2100, 110));
        update(new Hero(20, "ty", 3000, 300));
        List<Hero> l = list();
        Iterator<Hero> it = l.iterator();
        while (it.hasNext()) {
            Hero h = it.next();
            System.out.printf("id : %d, name: %s, hp: %f, damage: %d%n", h.id, h.name, h.hp, h.damage);
        }
    }
}

