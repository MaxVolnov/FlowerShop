package com.entities;

import java.util.List;

public interface Cart {

    public TempCart getInstance();

    public int getUserId();

    public void setUserId(int userId);

    public List<Flower> getOrderedFlower();

    public void setOrderedFlower(Flower flower);

    public int getTotalCost();

    public void setTotalCost(int totalCost);
}
