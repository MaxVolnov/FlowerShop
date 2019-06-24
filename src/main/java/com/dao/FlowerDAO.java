package com.dao;

import com.entities.Flower;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FlowerDAO {
    int flowerId;
    String flowerName;
    int cost;
    int amount;
    static Connection conn = DBConnector.getInstance().getConnection();

    public Flower flowerInfo(Spring flowerId) {
        Flower flower = new Flower();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from stock WHERE flower_id = " + flowerId);
            if (rs.next()) {
                this.flowerId = rs.getInt("FLOWERID");
                this.flowerName = rs.getString("FLOWERNAME");
                this.cost = rs.getInt("COST");
                this.amount = rs.getInt("AMOUNT");
            }
        } catch (
                SQLException e) {
        }
        return flower;
    }

    public static ArrayList getFlowerCatalog() {
        ArrayList<Flower> flowerCatalog = new ArrayList<Flower>();
        Flower tempFlower = new Flower();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from stock");
            while (rs.next()) {
                tempFlower.setFlowerId(rs.getInt("FLOWER_ID"));
                tempFlower.setFlowerName(rs.getString("FLOWER_NAME"));
                tempFlower.setCost(rs.getInt("COST"));
                tempFlower.setAmount(rs.getInt("AMOUNT"));
                flowerCatalog.add(tempFlower);
            }
        } catch (
                SQLException e) {
        }
        return flowerCatalog;
    }
}
