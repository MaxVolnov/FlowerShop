package com.entities;

import com.dao.DBConnector;

import java.sql.*;

public class Registration{
    String name;
    String login;
    String password;
    String email;

    public Registration (){};

    public Registration (String name, String login, String password, String email) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public void registration(String name, String login, String password, String e_mail) {
        boolean userExist = checkUser(login);
        boolean nameCorrect = nameValidation(name);
        boolean loginCorrect = loginValidation(login);
        boolean passwordCorrect = passwordValidation(password);
        boolean mailCorrect = mailValidation(e_mail);
        if (!userExist && nameCorrect && loginCorrect && passwordCorrect && mailCorrect) {
            insertAccount(login, password, name,  e_mail);
        }
    }

    public boolean insertAccount(String login, String password, String name, String email) {
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

    private boolean checkUser(String login) {
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

    private boolean nameValidation(String name) {
        return true;
    }

    private boolean loginValidation(String login) {
        return true;
    }

    private boolean passwordValidation(String password) {
        return true;
    }

    private boolean mailValidation(String e_mail) {
        return true;
    }


}
