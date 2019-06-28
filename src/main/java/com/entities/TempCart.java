package com.entities;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TempCart implements Cart{
    private static TempCart instance;
    int userId;
    List<Flower> orderedFlower;
    int totalCost;

    public TempCart() {
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

    public List<Flower> getOrderedFlower() {
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
