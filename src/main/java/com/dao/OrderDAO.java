package com.dao;

import com.entities.Flower;
import com.entities.Order;
import com.enums.OrderStatus;
import com.exceptions.LowBalanceException;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDAO {
    public Order orderInfo(String orderId);

    public void setOrderStatus(int orderId, OrderStatus status);

    public OrderStatus placeOrder(HttpSession session) throws LowBalanceException ;

    public void orderDetailsWrite(String userId, Flower flower, int orderId) throws SQLException;

    public void orderDiscard(String orderId) throws SQLException ;

    public ArrayList<Order> getOrdersList(HttpSession session);

    public void takeFlowers (Flower flower);

    public int getOrderCost (int orderId);
}
