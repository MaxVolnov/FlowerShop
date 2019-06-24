package com.functions;

import com.dao.RegistrationDAO;
import com.exceptions.UserExistException;

import static com.dao.RegistrationDAO.insertAccount;

public class Registration{

    public static boolean registration(String login, String password, String name, String e_mail) throws UserExistException {
        boolean userExist = RegistrationDAO.checkUser(login);
        if (userExist){
            throw new UserExistException();
        }
        boolean nameCorrect = RegistrationDAO.nameValidation(name);
        boolean loginCorrect = RegistrationDAO.loginValidation(login);
        boolean passwordCorrect = RegistrationDAO.passwordValidation(password);
        boolean mailCorrect = RegistrationDAO.mailValidation(e_mail);
        if (!userExist && nameCorrect && loginCorrect && passwordCorrect && mailCorrect) {
            boolean registered = insertAccount(login, password, name, e_mail);
            return registered;
        }
        return false;
    }


}
