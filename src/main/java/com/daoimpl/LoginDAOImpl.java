package com.daoimpl;

import com.dao.DBConnector;
import com.dao.LoginDAO;
import com.dao.UserDAO;
import com.entities.User;
import com.enums.Role;
import com.exceptions.LoginException;
import com.exceptions.PasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class LoginDAOImpl implements LoginDAO {

    @Autowired
    UserDAO userDAO;
    @Autowired
    DBConnector connector;

    Connection conn;
    @PostConstruct
    public void init(){
        if (connector != null) {
            conn = connector.getInstance().getConnection();
        }
    }

    @Override
    public User logining (String login, String password) throws LoginException, PasswordException {
        try {
            Statement loginExistCheck = conn.createStatement();
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
                    User temp = userDAO.userInfo(tempId);
                    if (tempId.equals("1")) {
                        temp.role = Role.ADMIN;
                    } else {
                        temp.role = Role.USER;
                    }
                    return temp;
                }
            }

        } catch (SQLException e) {
            System.out.println("SQL Exception in Login.loginPasswordCorrectCheck");
        } catch (LoginException le) {
            throw new LoginException();
        } catch (PasswordException pe) {
            throw new PasswordException();
        }

        return null;
    }
}
