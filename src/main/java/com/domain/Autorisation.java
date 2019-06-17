package com.domain;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Autorisation {
    String login;
    String password;

    public Autorisation() {
        login = null;
        password = null;
    }

    public Autorisation(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User autentification() {
        String userId = loginPasswordCorrectCheck();
        if (!userId.equals("0")) {
            User temp = new User(userId);
            if (userId.equals("1")) {
                temp.role = Role.ADMIN;
            } else {
                temp.role = Role.USER;
            }
            return temp;
        }
        return null;
    }

    private String loginPasswordCorrectCheck() {
        try {
            Connection recordConn = DBConnector.getInstance().getConnection();
            Statement loginExistCheck = recordConn.createStatement();
            ResultSet checkLogin = loginExistCheck.executeQuery("select * from users where login like '" + this.login + "%'");
            if (checkLogin.equals(null)) {
                System.out.println("User not found");
                return null;
            }
            String dbPassword = null;
            String tempId = null;
            if (checkLogin.next()) {
                dbPassword = checkLogin.getString("PASSWORD");
                tempId = checkLogin.getString("USER_ID");
            }
            if (!password.equals(dbPassword)) {
                System.out.println("Password incorrect!");
            } else {
                return tempId;
            }

        } catch (SQLException e) {
            System.out.println("SQL Exception in Autorisation.loginPasswordCorrectCheck");
        }
        System.out.println("User not found");
        return "0";
    }

    private boolean passwordValidation(String password) {

        return true;
    }
}


