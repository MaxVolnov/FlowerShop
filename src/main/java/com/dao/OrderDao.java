package com.dao;

import com.domain.*;
import com.enums.OrderStatus;
import com.enums.Role;
import com.exceptions.LowBalanceException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class OrderDao {
    int orderId;
    int totalCost;
    Date orderDate;
    OrderStatus status;
    Connection conn = DBConnector.getInstance().getConnection();

    public Order orderInfo(String orderId) {
        Order order = new Order();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from orders WHERE order_id = " + orderId);
            if (rs.next()) {
                this.orderId = rs.getInt("ORDER_ID");
                this.orderDate = rs.getDate("ORDER_DATE");
                this.totalCost = rs.getInt("TOTAL_COST");
                int statusCode = rs.getInt("STATUS");
                this.status = OrderStatus.findByCode(statusCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;
    }

    public void setOrderStatus(int orderId, OrderStatus status, User user){
        int StatusCode = status.getCode();
        if (user.role.equals(Role.ADMIN)) {
            try {
                Connection orderStatusWrite = DBConnector.getInstance().getConnection();
                PreparedStatement sti = orderStatusWrite.prepareStatement("insert into ORDERS (STATUS) values (statusCode) where order_id = " + orderId);
                orderStatusWrite.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public OrderStatus placeOrder(ArrayList<Flower> orderedFlowers, User user) throws LowBalanceException {
        int balance = Integer.valueOf(user.getBalance());
        int orderTotalCost = 0;
        for (int i = 0; i < orderedFlowers.size(); i++) {
            orderTotalCost += Integer.valueOf(orderedFlowers.get(i).getCost()) * Integer.valueOf(orderedFlowers.get(i).getAmount());
        }
        if (balance <= orderTotalCost) {
            throw new LowBalanceException();
        }
        try {
            for (int i = 0; i < orderedFlowers.size(); i++) {
                orderDetailsWrite(String.valueOf(user.getId()), orderedFlowers.get(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        java.util.Date date = new Date();
        try {
            Connection orderWrite = DBConnector.getInstance().getConnection();
            PreparedStatement sti = orderWrite.prepareStatement("insert into ORDERS (TOTAL_COST , DISCOUNT , DATE , STATUS ) values (?, ?, ?, 0)");
            sti.setString(1, String.valueOf(orderTotalCost));
            sti.setString(2, String.valueOf(user.getDiscount()));
            sti.setString(3, date.toString());
            int raws = sti.executeUpdate();
            orderWrite.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return OrderStatus.PLASED;
    }

    public void orderDetailsWrite(String userId, Flower flower) throws SQLException {
        Connection orderDetailConn = DBConnector.getInstance().getConnection();
        PreparedStatement sti = orderDetailConn.prepareStatement("insert into ORDER_DETAILS (USER_ID, FLOWER_ID, AMOUNT) values (?, ?, ?)");
        sti.setString(1, userId);
        sti.setString(2, String.valueOf(flower.getFlowerId()));
        sti.setString(3, String.valueOf(flower.getFlowerId()));
    }

    public void orderDiscard(String orderId) throws SQLException{

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("delete from order_details WHERE order_id = " + orderId);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}

