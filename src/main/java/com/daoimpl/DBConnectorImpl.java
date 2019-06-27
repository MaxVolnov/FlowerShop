package com.daoimpl;

import com.dao.DBConnector;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Repository
public class DBConnectorImpl implements DBConnector {

    private static DBConnectorImpl instance;
    public static final String DB_URL = "jdbc:h2:~/test";
    public static final String DB_Driver = "org.h2.Driver";
    private static final String username = "sa";
    private static final String password = "";
    public Connection conn;


    public DBConnectorImpl getInstance() {
        if (instance == null){
            instance = new DBConnectorImpl();
        }
        return instance;
    }

    public Connection getConnection() {
        return conn;
    }

    private DBConnectorImpl() {
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

