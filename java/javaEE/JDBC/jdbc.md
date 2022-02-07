# CRUD（增删减改）

CRUD是最常见的数据库操作，即增删改查
**C** 增加(Create)
**R** 读取查询(Retrieve)
**U** 更新(Update)
**D** 删除(Delete)

# 连接

## 声明数据库连接

```java
Class.forName("com.mysql.jdbc.Driver");
```



## 连接

```java
 c = DriverManager.getConnection(``"jdbc:mysql://host:port/database?characterEncoding="chasSet", "user","password");
```

host: localhost、127.0.0.1、其他IP地址
port: 3306、其他端口
database: 数据库名
chaset: 编码方式，UTF-8等价于mysql中的utf8mb4
user: 用户名
password: 密码

## 创建语句

```java
Statement s = c.createStatement(); //语句
PreparedStatement s = c.createStatement(); //预编译语句，PreparedStatement是Statement的子类
```

## 关闭语句与连接

### 1、传统方式

```java
 	   Connection c = null;
        Statement s = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8", "root","admin");
            s = c.createStatement();
            String sql = "insert into hero values(null," + "'提莫'" + "," + 313.0f + "," + 50 + ")";
            s.execute(sql);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 数据库的连接时有限资源，相关操作结束后，养成关闭数据库的好习惯
            // 先关闭Statement
            if (s != null)
                try {
                    s.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            // 后关闭Connection
            if (c != null)
                try {
                    c.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
```

### 2、try-with-resource方式

```java
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
  
        try (
            Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8",
                "root", "admin");
            Statement s = c.createStatement();             
        )
        {
            String sql = "insert into hero values(null," + "'提莫'" + "," + 313.0f + "," + 50 + ")";
            s.execute(sql);
              
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
```

将其写在try()括号中，能实现自动关闭。

#  CRUD方式

##  增、删、改方式

1、Statement

```java
Statement s = c.createStatement();
String sql = "insert/delete/update ......";
s.execute(sql);
```

2、PreparedStatement

```java
String sql = "insert/delete/update ......";
PreparedStatement ps = c.preparedStatement(sql);
ps.setString()、ps.setInt()、ps.setFloat()
ps.execute();
```

## 查询

```java
/**方法1*/
ResultSet rs = s.executeQuery(sql);
/**方法2*/
ResultSet rs = ps.executeQuery(sql);
ResultSet rs = ps.executeQuery();
/**方法3*/
s.execute(sql);
ResultSet rs = s.getResultSet();
/**方法4*/
ps.execute(sql);
ResultSet rs = ps.getResultSet();
```

## execute executeQuery executeUpdate

execute返回boolean类型，true代表是查询语句，false代表是没有查询到或者增、删、改语句，可以执行所有语句；

executeQuery返回是ResultSet类型，其是查询语句的结果集，可以执行查询语句；

executeUpdate返回的是int类型，其代表多少数据受影响，可以执行除查询语句以外的所有语句。

# Statement与预编译PreparedStatement区别

1、**Statement** 需要进行字符串拼接，可读性和维护性比较差；**PreparedStatement** 使用参数设置，可读性好，不易犯错。

2、PreparedStatement有预编译机制，性能比Statement更快。

3、

```java
// select * from hero where name = '盖伦' OR 1=1
// 因为有OR 1=1，所以恒成立
// 那么就会把所有的英雄都查出来，而不只是盖伦
// 如果Hero表里的数据是海量的，比如几百万条，把这个表里的数据全部查出来
//会让数据库负载变高，CPU100%，内存消耗光，响应变得极其缓慢
```

使用预编译Statement就可以杜绝SQL注入

# 获取插入语句后的自增长id

但是无论是execute还是executeUpdate都不会返回这个自增长id是多少。需要通过**Statement**的**getGeneratedKeys**获取该id。

# Object Relationship MAP(ORM)

ORM=Object Relationship Database Mapping

对象和关系数据库的映射

简单说，**一个对象**，对应数据库里的**一条记录**

# DAO

DAO=**D**ata **A**ccess **O**bject

数据访问对象

实际上就是运用了ORM中的思路，把数据库相关的操作都封装在这个类里面，其他地方看不到JDBC的代码

# 数据库连接池

当有多个线程，每个线程都需要连接数据库执行SQL语句的话，那么每个线程都会创建一个连接，并且在使用完毕后，关闭连接。

创建连接和关闭连接的过程也是比较消耗时间的，当多线程并发的时候，系统就会变得很卡顿。

同时，一个数据库同时支持的连接总数也是有限的，如果多线程并发量很大，那么数据库连接的总数就会被消耗光，后续线程发起的数据库连接就会失

![数据库连接池原理-传统方式](https://stepimagewm.how2j.cn/2654.png)

与传统方式不同，连接池在使用之前，就会创建好一定数量的连接。
如果有任何线程需要使用连接，那么就从连接池里面**借用**，**而不是自己重新创建**.
使用完毕后，又把这个连接**归还**给连接池供下一次或者其他线程使用。
倘若发生多线程并发情况，连接池里的连接被**借用光**了，那么其他线程就会临时等待，直到有连接被**归还**回来，再继续使用。
整个过程，这些连接都**不会被关闭**，而是不断的被循环使用，从而节约了启动和关闭连接的时间。

![数据库连接池原理-使用池](https://stepimagewm.how2j.cn/2655.png)