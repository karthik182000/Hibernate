package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void main(String[] args) {
        final String url = "jdbc:mysql://127.0.0.1:3306/";
        final String username = "root";
        final String password = "Karthik@1817";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {
            String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS ERM";
            statement.executeUpdate(createDatabaseQuery);
            System.out.println("Database created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

