package com.domain;

public class Main {

    public static void main (String[] args){
            DBConnector conn = DBConnector.getInstance();
            Autorisation autorisation = new Autorisation("admin", "admin123");
            autorisation.autentification();
            conn.Disconnect();
    }
}
