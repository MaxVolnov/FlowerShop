package com.domain;

import com.exceptions.AccesException;

public class Main {

    public static void main(String[] args) {
        DBConnector conn = DBConnector.getInstance();
        Authorisation authorisation = new Authorisation("admin", "admin123");
        User loginAdmin = authorisation.authentication();
        System.out.println("Login sucsess! User ID: " + loginAdmin.getId() + ". User Name: " + loginAdmin.getName() + ". E-mail: " + loginAdmin.getE_mail());
        User registerUser = new User("user2", "user2", "user2", "user2@google.com");
        Registration register = new Registration();
        register.registration(registerUser.login, registerUser.password, registerUser.name, registerUser.e_mail);
        Authorisation authorisation2 = new Authorisation("user2", "user2");
        User loginUser2 = authorisation2.authentication();
        System.out.println("Login sucsess! User ID: " + loginUser2.getId() + ". User Name: " + loginUser2.getName() + ". E-mail: " + loginUser2.getE_mail());
        try {
            loginUser2.setDiscount(0.2, loginUser2, loginAdmin);
            loginUser2.setDiscount(0.2, loginUser2, loginUser2);
        } catch (AccesException ae) {
            ae.printStackTrace();
        }






        conn.Disconnect();
    }
}
