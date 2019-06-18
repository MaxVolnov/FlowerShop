package com.domain;

import com.exceptions.LowBalanceException;

import java.sql.*;
import java.util.Date;


/*import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;*/

public class Order {
    int orderId;
    int totalCost;
    Date orderDate;
    OrderStatus status;
    Connection conn = DBConnector.getInstance().getConnection();

    public Order() {
    }

    public Order(String orderId) {
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from orders WHERE order_id = " + orderId);
            if (rs.next()) {
                this.orderId = rs.getInt("ORDER_ID");
                this.orderDate = rs.getDate("ORDER_DATE");
                this.totalCost = rs.getInt("TOTAL_COST");
                int statusId = rs.getInt("STATUS");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getOrderStatus(int orderId) {
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from orders WHERE exists (select * from orders where order_id = " + orderId + ")");
            if (rs.next()) {
                this.orderId = rs.getInt("ORDER_ID");
                int statusCode = rs.getInt("STATUS");
                this.status = OrderStatus.findByCode(statusCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setOrderStatus(int orderId, OrderStatus status, User user){
        int StatusCode = status.getCode();
        if (user.role.equals(Role.ADMIN)) {
            try {
                Connection orderStatusWrite = DBConnector.getInstance().getConnection();
                PreparedStatement sti = orderStatusWrite.prepareStatement("insert into ORDERS (STATUS) values (statusCode)");
                orderStatusWrite.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public OrderStatus placeOrder(String[][] orderedFlowers/*flowerId, amount, cost*/, String[] userInfo /*userId, balance, discount*/) throws SQLException, LowBalanceException {
        int balance = Integer.valueOf(userInfo[1]);
        int orderTotalCost = 0;
        for (int i = 0; i < orderedFlowers.length; i++) {
            orderTotalCost += Integer.valueOf(orderedFlowers[1][i]) * Integer.valueOf(orderedFlowers[2][i]);
        }
        if (balance <= orderTotalCost) {
            throw new LowBalanceException();
        }
        try {
            for (int i = 0; i < orderedFlowers.length; i++) {
                orderDetailsWrite(userInfo[0], orderedFlowers[i][0], orderedFlowers[i][1]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Date date = new Date();
        try {
            Connection orderWrite = DBConnector.getInstance().getConnection();
            PreparedStatement sti = orderWrite.prepareStatement("insert into ORDERS (TOTAL_COST , DISCOUNT , DATE , STATUS ) values (?, ?, ?, 0)");
            sti.setString(1, String.valueOf(orderTotalCost));
            sti.setString(2, userInfo[1]);
            sti.setString(3, date.toString());
            int raws = sti.executeUpdate();
            orderWrite.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.status = OrderStatus.PLASED;
        return status;
    }

    public void orderDetailsWrite(String userId, String flowerId, String amount) throws SQLException {
        Connection orderDetailConn = DBConnector.getInstance().getConnection();
        PreparedStatement sti = orderDetailConn.prepareStatement("insert into ORDER_DETAILS (USER_ID, FLOWER_ID, AMOUNT) values (?, ?, ?)");
        sti.setString(1, userId);
        sti.setString(2, flowerId);
        sti.setString(3, amount);
    }

    public void orderDiscard(String orderId) {
        //пройтись по всем orderdeails и удалить их, потом удалить сам order
    }
}
