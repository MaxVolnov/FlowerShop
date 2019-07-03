package com.dao;

import com.entities.User;

public interface RegistrationDAO {

    public boolean insertAccount(User user);

    public boolean checkUser(String login) ;

    public boolean nameValidation(String name);

    public boolean loginValidation(String login);

    public boolean passwordValidation(String password);

    public boolean mailValidation(String e_mail);


}
