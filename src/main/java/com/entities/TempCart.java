package com.entities;

import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Repository
public class TempCart implements Cart{
    private static TempCart instance;
    int userId;
    int discount;
    int totalCost;
    ArrayList<Flower> orderedFlower = new ArrayList<>();


    public TempCart(){
    }

    public TempCart(HttpSession session){
        User user = (User) session.getAttribute("user");
        userId = user.id;
        discount = user.discount;
        orderedFlower = null;
    }
    @Override
    public TempCart getInstance() {
        if (instance == null){
            instance = new TempCart();
        }
        return instance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ArrayList<Flower> getOrderedFlower() {
        return orderedFlower;
    }

    public void setOrderedFlower(Flower flower) {
        this.orderedFlower.add(flower);
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }
}
