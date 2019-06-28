package com.functions;

import com.entities.Flower;
import com.exceptions.StockException;

public interface StockManager {

    public void sell(int sellAmount, int flowerId) throws StockException;
    public Flower addToCart(int amount, int flowerId);
}
