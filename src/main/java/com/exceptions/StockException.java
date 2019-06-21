package com.exceptions;

public class StockException extends Exception {
    @Override
    public void printStackTrace(){
        System.err.println("Недостаточно цветов на складе!");
    }
}



