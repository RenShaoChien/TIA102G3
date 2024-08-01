package com.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
@Component
public class JDBCUtils {
    @Autowired
    private DataSource ds;

    public Connection getConnection() throws Exception {
        return ds.getConnection();
    }

    /**
     * 關閉資源
     *
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
