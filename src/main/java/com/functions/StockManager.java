package com.functions;

import com.dao.StockDAO;
import com.exceptions.StockException;

public class StockManager {

    public void sell(int sellAmount, int flowerId) throws StockException {
        int tempAmount = StockDAO.getAmount(flowerId);
        if (sellAmount<=tempAmount){
            StockDAO.setAmount(flowerId, tempAmount-sellAmount);
        } else {
            throw new StockException();
        }
    }

    public void fill(int fillAmount) {

    }

}
