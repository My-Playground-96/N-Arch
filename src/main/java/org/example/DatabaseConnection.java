package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static String url = "jdbc:mysql://localhost:3306/StudentsDB";
    private static String user = "root";
    private static String password = "nwBDK@4112";
    private static Connection connection;
    private static DatabaseConnection instance;
    private DatabaseConnection(){
        try {
            DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public Connection getConnection(){
        return  connection;
    }

    public static DatabaseConnection  getInstance() {
        return instance== null ? new DatabaseConnection() : instance;
    }
}
