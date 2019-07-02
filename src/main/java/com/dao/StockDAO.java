package com.dao;

import com.exceptions.StockException;

public interface StockDAO {

    public int getAmount(int flowerId);

    public void setAmount(int flowerId, int newAmount) throws StockException;
}
