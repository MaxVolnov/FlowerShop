package com.domain;

import org.omg.CORBA.UserException;

import java.sql.*;

public class User {

    int id = 0;
    String name = null;
    String login = null;
    String password = null;
    String e_mail = null;
    int balance = 0;
    double discount = 0;
    Role role = Role.USER;
    Connection conn = DBConnector.getInstance().getConnection();

    public User() {
    }

    public User(String id) {
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from users WHERE id = " + id);
            this.id = rs.getInt("ID");
            this.login = rs.getString("LOGIN");
            this.password = rs.getString("PASSWEORD");
            this.name = rs.getString("NAME");
            this.e_mail = rs.getString("E_MAIL");
            this.balance = rs.getInt("BALANCE");
            this.discount = rs.getInt("DISCOUNT");
        } catch (SQLException e) {
        }
    }
}
