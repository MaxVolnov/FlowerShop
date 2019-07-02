package com.functions;

import com.entities.Cart;
import com.exceptions.StockException;

import javax.servlet.http.HttpSession;

public interface StockManager {

    public void sell(int sellAmount, int flowerId) throws StockException;
    public void addToCart(int amount, int flowerId, Cart cart, HttpSession session);
}
