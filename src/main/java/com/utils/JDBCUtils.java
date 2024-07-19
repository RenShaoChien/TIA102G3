package com.utils;



import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ClassName： JDBCUtils
 * package：jdbcutils
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/6/29 @{TIME}
 * @Version 1.0
 */
public class JDBCUtils {
    private static DataSource ds = null;
    static {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/tia102g3");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws Exception {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        String URL = "jdbc:mysql://localhost:3306/tia102g3?serverTimezone=Asia/Taipei";
//        String USER = "root";
//        String PASSWORD = "123456";
//        return DriverManager.getConnection(URL, USER, PASSWORD);
        return ds.getConnection();

    }

    /**
     * 關閉資源
     * @param conn
     * @param ps
     * @param rs
     */
    public static void closeResource(Connection conn, Statement ps, ResultSet rs) {
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if (ps != null)
                ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
