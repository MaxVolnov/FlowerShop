package com.daoimpl;

import com.dao.DBConnector;
import com.dao.StockDAO;
import com.exceptions.StockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@Repository
public class StockDAOImpl implements StockDAO {
    @Autowired
    DBConnector connector;

    Connection conn;
    @PostConstruct
    public void init(){
        if (connector != null) {
            conn = connector.getInstance().getConnection();
        }
    }
    public int getAmount(int flowerId) {
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from stock WHERE flower_id = " + String.valueOf(flowerId));
            if (rs.next()) {
                int flowerAmount = rs.getInt("AMOUNT");
                return flowerAmount;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void setAmount(int flowerId, int newAmount) throws StockException{
        int tempAmount = getAmount(flowerId);
        if (tempAmount>=newAmount) {
            try {
                PreparedStatement sti = conn.prepareStatement("insert into STOCK (AMOUNT) values (newAmount) where flower_id = " + flowerId);
                conn.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            throw new StockException();
        }
    }
}
