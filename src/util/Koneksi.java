package util;

import java.sql.*;

public class Koneksi {
    private static final String URL = "jdbc:mysql://localhost:3306/klinik";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}