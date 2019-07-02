package com.daoimpl;

import com.dao.DBConnector;
import com.dao.OrderDAO;
import com.entities.Flower;
import com.entities.Order;
import com.entities.User;
import com.enums.OrderStatus;
import com.enums.Role;
import com.exceptions.LowBalanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

@Repository
public class OrderDAOImpl implements OrderDAO {
    int orderId;
    int totalCost;
    Date orderDate;
    OrderStatus status;
    @Autowired
    DBConnector connector;

    Connection conn;
    @PostConstruct
    public void init(){
        if (connector != null) {
            conn = connector.getInstance().getConnection();
        }
    }
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

    public void setOrderStatus(int orderId, OrderStatus status, User user) {
        int statusCode = status.getCode();
        if (user.role.equals(Role.ADMIN)) {
            try {
                PreparedStatement sti = conn.prepareStatement("insert into ORDERS (STATUS) values (" + statusCode + ") where order_id = " + orderId);
                conn.commit();
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
        Date date = new Date();
        try {
            PreparedStatement sti = conn.prepareStatement("insert into ORDERS (TOTAL_COST , DISCOUNT , DATE, USER_ID, STATUS) values (?, ?, ?, ?, 0)");
            sti.setString(1, String.valueOf(orderTotalCost));
            sti.setString(2, String.valueOf(user.getDiscount()));
            sti.setString(3, date.toString());
            sti.setString(4, Integer.toString(user.getId()));
            int raws = sti.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select ORDER_ID from orders WHERE order_id = " + orderId);
            if (rs.next()) {
                int orderId = rs.getInt("ORDER_ID");
            }
            for (int i = 0; i < orderedFlowers.size(); i++) {
                orderDetailsWrite(String.valueOf(user.getId()), orderedFlowers.get(i), orderId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return OrderStatus.PLASED;
    }

    public void orderDetailsWrite(String userId, Flower flower, int orderId) throws SQLException {
        PreparedStatement sti = conn.prepareStatement("insert into ORDER_DETAILS (USER_ID, FLOWER_ID, AMOUNT, ORDER_ID) values (?, ?, ?)");
        sti.setString(1, userId);
        sti.setString(2, String.valueOf(flower.getFlowerId()));
        sti.setString(3, String.valueOf(flower.getFlowerId()));
    }

    public void orderDiscard(String orderId) throws SQLException {
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("delete from order_details WHERE order_id = " + orderId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

