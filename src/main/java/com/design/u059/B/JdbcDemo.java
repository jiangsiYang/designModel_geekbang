package com.design.u059.B;


import java.sql.*;

/**
 * queryUser() 函数包含很多流程性质的代码，跟业务无关，比如，加载驱动、创建数据库连接、创建 statement、关闭连接、关闭 statement、处理异常。针对不同的 SQL 执行请求，
 * 这些流程性质的代码是相同的、可以复用的，我们不需要每次都重新敲一遍。针对这个问题，Spring 提供了 JdbcTemplate，对 JDBC 进一步封装，来简化数据库编程。使用 JdbcTemplate
 * 查询用户信息，我们只需要编写跟这个业务有关的代码，其中包括，查询用户的 SQL 语句、查询结果与 User 对象之间的映射关系。其他流程性质的代码都封装在了 JdbcTemplate 类中，
 * 不需要我们每次都重新编写。
 */
public class JdbcDemo {
    public User queryUser(long id) {
        Connection conn = null;
        Statement stmt = null;
        try {
            //1.加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "xzg", "xzg");

            //2.创建statement类对象，用来执行SQL语句
            stmt = conn.createStatement();

            //3.ResultSet类，用来存放获取的结果集
            String sql = "select * from user where id=" + id;
            ResultSet resultSet = stmt.executeQuery(sql);

            String eid = null, ename = null, price = null;

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setTelephone(resultSet.getString("telephone"));
                return user;
            }
        } catch (ClassNotFoundException e) {
            // TODO: log...
        } catch (SQLException e) {
            // TODO: log...
        } finally {
            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException e) {
                    // TODO: log...
                }
            if (stmt != null)
                try {
                    stmt.close();
                } catch (SQLException e) {
                    // TODO: log...
                }
        }
        return null;
    }

}
