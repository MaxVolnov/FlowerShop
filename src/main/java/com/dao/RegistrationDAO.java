package com.dao;

public interface RegistrationDAO {

    public boolean insertAccount(String login, String password, String name, String email);

    public boolean checkUser(String login) ;

    public boolean nameValidation(String name);

    public boolean loginValidation(String login);

    public boolean passwordValidation(String password);

    public boolean mailValidation(String e_mail);


}
