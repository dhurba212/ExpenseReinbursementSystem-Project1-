package dev.ghimire.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    public static Connection getConnection()
    {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:postgresql://35.193.167.131:5432/expenseReimbursementSystemDB","......",".....");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
