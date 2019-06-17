package com.domain;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Flower {
    int flowerId;
    String flowerName;
    int cost;
    int amount;
    Connection conn = DBConnector.getInstance().getConnection();

    public Flower(int flowerId){
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from users WHERE id = " + flowerId);
            if (rs.next()) {
                this.flowerId = rs.getInt("FLOWERID");
                this.flowerName = rs.getString("FLOWERNAME");
                this.cost = rs.getInt("COST");
                this.amount = rs.getInt("AMOUNT");
            }
        } catch (SQLException e) {
        }
    }

    public int getFlowerId() {
        return flowerId;
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

    public void sell (int sellAmount){
        this.amount -= sellAmount;
    }

    public void fill (int fillAmount){
        this.amount += fillAmount;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
