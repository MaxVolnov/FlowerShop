package com.daoimpl;

import com.dao.DBConnector;
import com.dao.FlowerDAO;
import com.entities.Flower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@Repository
public class FlowerDAOImpl implements FlowerDAO {
    int flowerId;
    String flowerName;
    int cost;
    int amount;
    @Autowired
    DBConnector connector;

    Connection conn;
    @PostConstruct
    public void init(){
        if (connector != null) {
            conn = connector.getInstance().getConnection();
        }
    }

    public Flower flowerInfo(String flowerId) {
        Flower flower = new Flower();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from stock WHERE flower_id = " + flowerId);
            if (rs.next()) {
                flower.setFlowerId(rs.getInt("FLOWER_ID"));
                flower.setFlowerName(rs.getString("FLOWER_NAME"));
                flower.setCost(rs.getInt("COST"));
                flower.setAmount(rs.getInt("AMOUNT"));
            }
        } catch (
                SQLException e) {
        }
        return flower;
    }


    public ArrayList getFlowerCatalog() {
        ArrayList<Flower> flowerCatalog = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from stock");
            while (rs.next()) {
                Flower tempFlower = new Flower();
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
