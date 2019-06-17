package com.domain;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

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
            ResultSet rs = st.executeQuery("select * from orders WHERE exists (select * from orders where order_id = " + orderId)); // перенести запрос в сетстатус и отладить
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

    public void setOrderStatus(int orderId, OrderStatus status) {
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from orders WHERE order_id = " + orderId);
            if (rs.next()) {
                this.orderId = rs.getInt("ORDER_ID");
                int statusCode = rs.getInt("STATUS");
                this.status = OrderStatus.findByCode (statusCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
