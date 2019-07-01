package com.functions;

import com.dao.FlowerDAO;
import com.dao.StockDAO;
import com.entities.Cart;
import com.entities.Flower;
import com.exceptions.StockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StockManagerImpl implements StockManager {

    @Autowired
    StockDAO stockDAO;
    @Autowired
    Cart cart;
    @Autowired
    FlowerDAO flowerDAO;

    public void sell(int sellAmount, int flowerId) throws StockException {
        int tempAmount = stockDAO.getAmount(flowerId);
        if (sellAmount<=tempAmount){
            stockDAO.setAmount(flowerId, tempAmount-sellAmount);
        } else {
            throw new StockException();
        }
    }

    public Flower addToCart(int amount, int flowerId) {
        Flower orderedFlower = flowerDAO.flowerInfo(String.valueOf(flowerId));
        orderedFlower.setAmount(amount);
        cart.setTotalCost(cart.getTotalCost()+(orderedFlower.getCost()*amount));
        cart.setOrderedFlower(orderedFlower);
        return orderedFlower;
    }

}
