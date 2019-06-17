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
            if (rs.next()) {
                this.id = rs.getInt("ID");
                this.login = rs.getString("LOGIN");
                this.password = rs.getString("PASSWORD");
                this.name = rs.getString("NAME");
                this.e_mail = rs.getString("E_MAIL");
                this.balance = rs.getInt("BALANCE");
                this.discount = rs.getInt("DISCOUNT");
            }
        } catch (SQLException e) {
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getE_mail() {
        return e_mail;
    }

    public Role getRole() {
        return role;
    }

    public int getBalance() {
        return balance;
    }

    public double getDiscount() {
        return discount;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }
}
