package com.dao;

import com.entities.User;
import com.exceptions.LoginException;
import com.exceptions.PasswordException;

public interface LoginDAO {

    public User logining (String login, String password) throws LoginException, PasswordException;
}
