package com.daoimpl;

import com.dao.DBConnector;
import com.dao.OrderDAO;
import com.entities.Flower;
import com.entities.Order;
import com.entities.TempCart;
import com.entities.User;
import com.enums.OrderStatus;
import com.enums.Role;
import com.exceptions.LowBalanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
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
    public void init() {
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

    public OrderStatus placeOrder(HttpSession session) throws LowBalanceException {
        User user = (User) session.getAttribute("user");
        TempCart cart = (TempCart) session.getAttribute("cart");
        int balance = user.getBalance();
        int orderTotalCost = (int) session.getAttribute("totalCost");
        if (balance <= orderTotalCost) {
            throw new LowBalanceException();
        }
        String date = new Date().toString();
        try {
            PreparedStatement sti = conn.prepareStatement("insert into ORDERS (TOTAL_COST , DISCOUNT , DATE, USER_ID, STATUS) values (?, ?, ?, ?, 0)");
            sti.setString(1, String.valueOf(orderTotalCost));
            sti.setString(2, String.valueOf(user.getDiscount()));
            sti.setString(3, date);
            sti.setString(4, Integer.toString(user.getId()));
            int raws = sti.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            PreparedStatement orderIdGetStmt = conn.prepareStatement(String.valueOf(Statement.RETURN_GENERATED_KEYS));
            try (ResultSet generatedKeys = orderIdGetStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int orderId = generatedKeys.getInt(1);
                    for (int i = 0; i < cart.getOrderedFlower().size(); i++) {
                        orderDetailsWrite(String.valueOf(user.getId()), cart.getOrderedFlower().get(i), orderId);
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Flower flower : cart.getOrderedFlower()) {
            takeFlowers(flower);
        }
        return OrderStatus.PLASED;
    }

    public void orderDetailsWrite(String userId, Flower flower, int orderId) throws SQLException {
        PreparedStatement sti = conn.prepareStatement("insert into ORDER_DETAILS (FLOWER_ID, AMOUNT, ORDER_ID, FLOWER_COST) values (?, ?, ?, ?)");
        sti.setInt(1, flower.getFlowerId());
        sti.setInt(2, flower.getAmount());
        sti.setInt(3, orderId);
        sti.setInt(4, flower.getCost());
        conn.commit();
    }

    public void orderDiscard(String orderId) throws SQLException {
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("delete from order_details WHERE order_id = " + orderId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Order> getOrdersList(HttpSession session) {
        ArrayList<Order> orders = new ArrayList<>();
        User user = (User) session.getAttribute("user");
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from orders WHERE user_id= " + user.id);
            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                int status = rs.getInt("status");
                order.setStatus(OrderStatus.findByCode(status));
                order.setTotalCost(rs.getInt("total_cost"));
                order.setDate(rs.getString("date"));
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void takeFlowers(Flower flower) { //не работает, исправить
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select amount from stock WHERE flower_id= " + flower.getFlowerId());
            if (rs.next()) {
                int amount = rs.getInt("amount");
                int newAmount = amount - flower.getAmount();
                PreparedStatement sti = conn.prepareStatement("update STOCK set AMOUNT = " + newAmount +
                        " where FLOWER_ID = " + flower.getFlowerId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

