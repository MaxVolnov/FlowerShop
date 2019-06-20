package com.dao;

import com.domain.DBConnector;
import com.enums.Role;
import com.domain.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDAO {

    int id = 0;
    String name = null;
    String login = null;
    String password = null;
    String e_mail = null;
    int balance = 0;
    double discount = 0;
    Role role = Role.USER;
    static Connection conn = DBConnector.getInstance().getConnection();

    public static User userInfo(String id) {
        User user = new User();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from users WHERE user_id = " + id);
            if (rs.next()) {
                user.id = rs.getInt("USER_ID");
                user.login = rs.getString("LOGIN");
                user.password = rs.getString("PASSWORD");
                user.name = rs.getString("NAME");
                user.e_mail = rs.getString("E_MAIL");
                user.balance = rs.getInt("BALANCE");
                user.discount = rs.getInt("DISCOUNT");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
