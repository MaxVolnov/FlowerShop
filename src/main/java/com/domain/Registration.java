package com.domain;

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
        boolean loginCorrect = nameValidation(login);
        boolean passwordCorrect = nameValidation(password);
        boolean mailCorrect = nameValidation(e_mail);
        if (!userExist && nameCorrect && loginCorrect && passwordCorrect && mailCorrect) {
            // register();
        }
    }

    public boolean insertAccount(String name, String login, String password, String email) {
        try {
            Connection recordConn = DBConnector.getInstance().getConnection();
            PreparedStatement sti = recordConn.prepareStatement("insert into users (NAME, LOGIN, PASSWORD, E_MAIL, BALANCE, DISCOUNT) values (?, ?, ?, ?, 0, 0)");
            sti.setString(1, name);
            sti.setString(2, login);
            sti.setString(3, password);
            sti.setString(4, email);
            int raws = sti.executeUpdate();
            recordConn.commit();
        } catch (SQLException e) {

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
                    return false;
                }
            }
        } catch (SQLException e) {

        }

        return true;
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
