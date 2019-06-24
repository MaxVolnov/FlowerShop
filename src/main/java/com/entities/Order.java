package com.entities;

import com.enums.OrderStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table (name="orders")
public class Order {
    @Id
    @Column (name="order_id")
    int orderId;
    @Column (name="total_cost")
    int totalCost;
    @Column
    int discount;
    @Column (name="date")
    Date orderDate;
    @Column (name="user_id")
    int userId;
    @Column
    OrderStatus status;


    public Order() {}

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }
}
