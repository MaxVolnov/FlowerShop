package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static DBConnector instance;
    public static final String DB_URL = "jdbc:h2:~/test";
    public static final String DB_Driver = "org.h2.Driver";
    private static final String username = "sa";
    private static final String password = "";
    public Connection conn;


    public static DBConnector getInstance() {
        if (instance == null){
            instance = new DBConnector();
        }
        return instance;
    }

    public Connection getConnection() {
        return conn;
    }

    private DBConnector() {
        try {
            Class.forName(DB_Driver);
            conn = DriverManager.getConnection(DB_URL, username, password);
            System.out.println("Connection established!");

            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL error!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("JDBC driver is not found!");
        }
    }

    public void Disconnect() {
        try {
            conn.close();
            System.out.println("Connectrion closed");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL error!");
        }
    }
}

