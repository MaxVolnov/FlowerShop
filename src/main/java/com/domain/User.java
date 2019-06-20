package com.domain;

import com.enums.Role;
import com.exceptions.AccesException;

public class User {

    public int id = 0;
    public String name = null;
    public String login = null;
    public String password = null;
    public String e_mail = null;
    public int balance = 0;
    public double discount = 0;
    public Role role = Role.USER;

    public User() {}



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
