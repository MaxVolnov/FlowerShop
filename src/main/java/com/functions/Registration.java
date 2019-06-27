package com.functions;

import com.dao.RegistrationDAO;
import com.exceptions.UserExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Registration{

    @Autowired
    RegistrationDAO registrationDAO;

    public boolean registration(String login, String password, String name, String e_mail) throws UserExistException {
        boolean userExist = registrationDAO.checkUser(login);
        if (userExist){
            throw new UserExistException();
        }
        boolean nameCorrect = registrationDAO.nameValidation(name);
        boolean loginCorrect = registrationDAO.loginValidation(login);
        boolean passwordCorrect = registrationDAO.passwordValidation(password);
        boolean mailCorrect = registrationDAO.mailValidation(e_mail);
        if (!userExist && nameCorrect && loginCorrect && passwordCorrect && mailCorrect) {
            boolean registered = registrationDAO.insertAccount(login, password, name, e_mail);
            return registered;
        }
        return false;
    }


}
