package com.functions;

import com.dao.RegistrationDAO;
import com.entities.User;
import com.exceptions.UserExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RegistrationImpl implements Registration {

    @Autowired
    RegistrationDAO registrationDAO;

    public boolean registration(User user) throws UserExistException {
        boolean userExist = registrationDAO.checkUser(user.login);
        if (userExist){
            throw new UserExistException();
        }
        boolean nameCorrect = registrationDAO.nameValidation(user.name);
        boolean loginCorrect = registrationDAO.loginValidation(user.login);
        boolean passwordCorrect = registrationDAO.passwordValidation(user.password);
        boolean mailCorrect = registrationDAO.mailValidation(user.e_mail);
        if (!userExist && nameCorrect && loginCorrect && passwordCorrect && mailCorrect) {
            boolean registered = registrationDAO.insertAccount(user);
            return registered;
        }
        return false;
    }


}
