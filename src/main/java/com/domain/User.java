package com.domain;

import com.exceptions.AccesException;

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
            ResultSet rs = st.executeQuery("select * from users WHERE user_id = " + id);
            if (rs.next()) {
                this.id = rs.getInt("USER_ID");
                this.login = rs.getString("LOGIN");
                this.password = rs.getString("PASSWORD");
                this.name = rs.getString("NAME");
                this.e_mail = rs.getString("E_MAIL");
                this.balance = rs.getInt("BALANCE");
                this.discount = rs.getInt("DISCOUNT");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User(String login, String password, String name, String e_mail) {
        this.role = Role.USER;
        this.login = login;
        this.password = password;
        this.name = name;
        this.e_mail = e_mail;
        this.balance = 0;
        this.discount = 0.0;
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

    public String getPassword() { return password; }

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

    public void setDiscount(double discount, User user, User admin) throws AccesException {
        if (admin.role.equals(Role.ADMIN)) {
            user.discount = discount;
            System.out.println("Discount of " + user.name + " changed to " + user.discount);
        } else {
            throw new AccesException();
        }
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
