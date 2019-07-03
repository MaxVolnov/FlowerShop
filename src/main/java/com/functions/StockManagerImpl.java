package com.functions;

import com.dao.FlowerDAO;
import com.dao.StockDAO;
import com.entities.Cart;
import com.entities.Flower;
import com.entities.TempCart;
import com.entities.User;
import com.exceptions.StockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpSession;

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

    public void addToCart(int amount, int flowerId, HttpSession session) {
        Flower orderedFlower = flowerDAO.flowerInfo(String.valueOf(flowerId));
        orderedFlower.setAmount(amount);
        TempCart cart = (TempCart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");
        int totalCost = (int) Math.floor(cart.getTotalCost()+((orderedFlower.getCost()*amount))*((100-user.discount)/100.d));
        cart.setTotalCost(totalCost);
        cart.setOrderedFlower(orderedFlower);
        session.setAttribute("totalCost", cart.getTotalCost());
        session.setAttribute("cart", cart);
        return;
    }

}
