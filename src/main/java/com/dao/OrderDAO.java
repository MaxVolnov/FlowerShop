package com.dao;

import com.entities.Flower;
import com.entities.Order;
import com.entities.User;
import com.enums.OrderStatus;
import com.exceptions.LowBalanceException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDAO {
    public Order orderInfo(String orderId);

    public void setOrderStatus(int orderId, OrderStatus status, User user);

    public OrderStatus placeOrder(ArrayList<Flower> orderedFlowers, User user) throws LowBalanceException ;

    public void orderDetailsWrite(String userId, Flower flower, int orderId) throws SQLException;

    public void orderDiscard(String orderId) throws SQLException ;
}
