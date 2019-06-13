package com.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {


    public static final String DB_URL = "jdbc:h2:~/test";
    public static final String DB_Driver = "org.h2.Driver";
    private static final String username =  "sa";
    private static final String password = "";


    public DBConnector () {
        try {
            Class.forName(DB_Driver);
            Connection conn = DriverManager.getConnection(DB_URL, username, password);
            System.out.println("Connection established!");
            conn.close();
            System.out.println("Connectrion closed");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL error!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("JDBC driver is not found!");
        }
    }
}
