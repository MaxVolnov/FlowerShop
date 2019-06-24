package com.exceptions;

public class UserExistException extends Exception {
    @Override
    public void printStackTrace() {
        System.err.println("Логин уже занят!");
    }
}
