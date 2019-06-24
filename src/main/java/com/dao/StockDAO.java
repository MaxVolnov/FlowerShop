package com.dao;

import com.exceptions.StockException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class StockDAO {
    static Connection conn = DBConnector.getInstance().getConnection();

    public static int getAmount(int flowerId) {
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

    public static void setAmount(int flowerId, int newAmount) throws StockException{
        int tempAmount = getAmount(flowerId);
        if (tempAmount>=newAmount) {
            try {
                Connection amountStatusWrite = DBConnector.getInstance().getConnection();
                PreparedStatement sti = amountStatusWrite.prepareStatement("insert into STOCK (AMOUNT) values (newAmount) where flower_id = " + flowerId);
                amountStatusWrite.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            throw new StockException();
        }
    }
}
