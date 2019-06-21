package com.entities;

import com.dao.DBConnector;

import java.sql.Connection;

public class Flower {
    int flowerId;
    String flowerName;
    int cost;
    int amount;
    Connection conn = DBConnector.getInstance().getConnection();

    public Flower() {
    }

    public int getFlowerId() {
        return flowerId;
    }

    public void setFlowerId(int flowerId) {
        this.flowerId = flowerId;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setFlowerName(String flowerName) {
        this.flowerName = flowerName;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }



    public String getFlowerName() {
        return flowerName;
    }

    public int getCost() {
        return cost;
    }

    public int getAmount() {
        return amount;
    }

}


