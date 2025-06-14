 
package model.service;

import config.Config;
import java.sql.*;

public abstract class BaseService {
protected static final String URL = Config.getDbUrl();
    protected static final String USER = Config.getDbUser();
    protected static final String PASSWORD = Config.getDbPassword();

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    protected void executeUpdate(String sql, Object... params) {
        try (Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void closeResources(Connection conn, PreparedStatement pstmt) {
        try {
            
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}