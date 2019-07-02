package com.exceptions;

public class LoginException extends Exception {
    @Override
    public void printStackTrace() {
        System.err.println("Пользователь не найден");
    }
}
