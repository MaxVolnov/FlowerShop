package com.functions;

import com.entities.User;
import com.exceptions.UserExistException;

public interface Registration {
    public boolean registration(User user) throws UserExistException;
}
