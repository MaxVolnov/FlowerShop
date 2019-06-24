package com.dao;

import java.sql.*;

public class RegistrationDAO {

    public static boolean insertAccount(String login, String password, String name, String email) {
        try {
            Connection accountRegistrConn = DBConnector.getInstance().getConnection();
            PreparedStatement sti = accountRegistrConn.prepareStatement("insert into users (LOGIN, PASSWORD, NAME, E_MAIL, BALANCE, DISCOUNT) values (?, ?, ?, ?, 2000, 0)");
            sti.setString(1, login);
            sti.setString(2, password);
            sti.setString(3, name);
            sti.setString(4, email);
            int raws = sti.executeUpdate();
            accountRegistrConn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean checkUser(String login) {
        try {
            Connection recordConn = DBConnector.getInstance().getConnection();
            Statement loginExistCheck = recordConn.createStatement();
            ResultSet checkResult = loginExistCheck.executeQuery("select login from users");
            while (checkResult.next()) {
                if (login == checkResult.getString("LOGIN")) {
                    return true;
                }
            }
        } catch (SQLException e) {

        }

        return false;
    }

    public static boolean nameValidation(String name) {
        return true;
    }

    public static boolean loginValidation(String login) {
        return true;
    }

    public static boolean passwordValidation(String password) {
        return true;
    }

    public static boolean mailValidation(String e_mail) {
        return true;
    }


}
