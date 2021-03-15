package dev.ghimire.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    public static Connection getConnection()
    {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(System.getenv("REIM_SYS_HIB_URL"),System.getenv("GCP_USER"),System.getenv("REIM_SYS_HIB_PASS"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
