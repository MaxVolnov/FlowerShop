package com.domain;

import com.dao.UserDAO;
import com.enums.Role;
import com.exceptions.LoginException;
import com.exceptions.PasswordException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Authorisation {
    String login;
    String password;

    public Authorisation() {
        login = null;
        password = null;
    }

    public Authorisation(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User authentication(String login, String password) {
        try {
            String userId = loginPasswordCorrectCheck();
            if (!userId.equals("0")) {
                User temp = UserDAO.userInfo(userId);
                if (userId.equals("1")) {
                    temp.role = Role.ADMIN;
                } else {
                    temp.role = Role.USER;
                }
                return temp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private String loginPasswordCorrectCheck() throws LoginException, PasswordException {
        try {
            Connection loginConn = DBConnector.getInstance().getConnection();
            Statement loginExistCheck = loginConn.createStatement();
            ResultSet checkLogin = loginExistCheck.executeQuery("select * from users where login like '" + this.login + "%'");
            if (checkLogin.equals(null)) {
                throw new LoginException();
            }
            String dbPassword = null;
            String tempId = null;
            if (checkLogin.next()) {
                dbPassword = checkLogin.getString("PASSWORD");
                tempId = checkLogin.getString("USER_ID");
            }
            if (!password.equals(dbPassword)) {
                throw new PasswordException();
            } else {
                return tempId;
            }

        } catch (SQLException e) {
            System.out.println("SQL Exception in Authorisation.loginPasswordCorrectCheck");
        }
        return null;
    }

    private boolean passwordValidation(String password) {

        return true;
    }
}


