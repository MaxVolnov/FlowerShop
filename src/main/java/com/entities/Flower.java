package com.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "stock")
public class Flower {

    @Id
    @Column (name="flower_id")
    int flowerId;
    @Column (name="flower_name")
    String flowerName;
    @Column
    int cost;
    @Column
    int amount;

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


