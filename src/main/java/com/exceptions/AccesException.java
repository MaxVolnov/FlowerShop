package com.exceptions;

public class AccesException extends Exception{
    @Override
    public void printStackTrace() {
        System.err.println("Нет прав для доступа");
    }
}
