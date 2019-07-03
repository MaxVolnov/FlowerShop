package com.daoimpl;

import com.dao.DBConnector;
import com.dao.RegistrationDAO;
import com.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.sql.*;

@Repository
public class RegistrationDAOImpl implements RegistrationDAO {
    @Autowired
    DBConnector connector;

    Connection conn;
    @PostConstruct
    public void init(){
        if (connector != null) {
            conn = connector.getInstance().getConnection();
        }
    }
    public boolean insertAccount(User user) {
        try {
            PreparedStatement sti = conn.prepareStatement("insert into users (LOGIN, PASSWORD, NAME, E_MAIL, BALANCE, DISCOUNT) values (?, ?, ?, ?, 2000, 5)");
            sti.setString(1, user.login);
            sti.setString(2, user.password);
            sti.setString(3, user.name);
            sti.setString(4, user.e_mail);
            int raws = sti.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean checkUser(String login) {
        try {
            Statement loginExistCheck = conn.createStatement();
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

    public  boolean nameValidation(String name) {
        return true;
    }

    public  boolean loginValidation(String login) {
        return true;
    }

    public  boolean passwordValidation(String password) {
        return true;
    }

    public  boolean mailValidation(String e_mail) {
        return true;
    }


}
