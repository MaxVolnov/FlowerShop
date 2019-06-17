package com.domain;

public class Main {

    public static void main (String[] args){
            DBConnector conn = DBConnector.getInstance();
            Autorisation autorisation = new Autorisation("admin", "admin123");
            User loginUser = autorisation.autentification();
            System.out.println("Login sucsess! User ID: " + loginUser.getId() + ". User Name: " + loginUser.getName() + ". E-mail: " + loginUser.getE_mail());
            conn.Disconnect();
    }
}
