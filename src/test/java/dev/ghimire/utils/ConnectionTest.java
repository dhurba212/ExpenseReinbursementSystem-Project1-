package dev.ghimire.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionTest {

    @Test
    void connection_test_1(){
        Connection conn = ConnectionUtil.getConnection();
        System.out.println(conn);
        Assertions.assertNotNull(conn);
    }
}
