package com.functions;

import com.dao.StockDAO;
import com.exceptions.StockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StockManager {

    @Autowired
    StockDAO stockDAO;

    public void sell(int sellAmount, int flowerId) throws StockException {
        int tempAmount = stockDAO.getAmount(flowerId);
        if (sellAmount<=tempAmount){
            stockDAO.setAmount(flowerId, tempAmount-sellAmount);
        } else {
            throw new StockException();
        }
    }

    public void fill(int fillAmount) {

    }

}
