package com.functions;

import com.dao.UserDAO;
import com.dao.DBConnector;
import com.entities.User;
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

    public Authorisation() { }

    public static User authentication(String login, String password) throws LoginException, PasswordException {
        try {
                Connection loginConn = DBConnector.getInstance().getConnection();
                Statement loginExistCheck = loginConn.createStatement();
                ResultSet checkLogin = loginExistCheck.executeQuery("select * from users where login like '" + login + "%'");
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
                    if (!tempId.equals("0")) {
                        User temp = UserDAO.userInfo(tempId);
                        if (tempId.equals("1")) {
                            temp.role = Role.ADMIN;
                        } else {
                            temp.role = Role.USER;
                        }
                        return temp;
                    }
                }

            } catch (SQLException e) {
                System.out.println("SQL Exception in Authorisation.loginPasswordCorrectCheck");
            } catch (LoginException le) {
            throw new LoginException();
        } catch (PasswordException pe) {
            throw new PasswordException();
        }

        return null;
    }


    private boolean passwordValidation(String password) {

        return true;
    }
}


