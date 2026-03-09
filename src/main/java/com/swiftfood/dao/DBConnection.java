package com.swiftfood.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:oracle:thin:@localhost:1521/XEPDB1";
    private static final String USER = "swiftfood";
    private static final String PASS = "swiftfood123";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Oracle driver not found", e);
        }
        return DriverManager.getConnection(URL, USER, PASS);
    }
}