package com.exceptions;

public class PasswordException extends Exception {
    @Override
    public void printStackTrace(){
        System.err.println("Неверный пароль");
    }
}
