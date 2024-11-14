package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
private static final String URL = "jdbc:sqlite:books.db";
private static final String USER = "root";
private static final String PASSWORD = "your-password";
public static Connection getConnection() throws SQLException, SQLException {
return DriverManager.getConnection(URL);
}
}