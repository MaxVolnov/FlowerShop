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
            return temp;
        }
        return null;
    }

    private String loginPasswordCorrectCheck() {
        try {
            Connection recordConn = DBConnector.getInstance().getConnection();
            Statement loginExistCheck = recordConn.createStatement();
            ResultSet checkLogin = loginExistCheck.executeQuery("select * from users where login like '" + this.login + "%'");
            if (checkLogin == null) {
                System.out.println("User not found");
                return "0";
            }
            if (!password.equals(checkLogin.getString("PASSWORD"))) {
                System.out.println("Password incorrect!");
                return "0";
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
