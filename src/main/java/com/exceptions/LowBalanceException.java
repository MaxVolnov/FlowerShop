package com.exceptions;

public class LowBalanceException extends Exception {
    @Override
    public void printStackTrace() {
        System.err.println("Недостаточно средств! Пополните баланс");
    }
}
