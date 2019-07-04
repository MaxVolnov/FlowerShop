package com.controllers;

import com.dao.OrderDAO;
import com.dao.UserDAO;
import com.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    OrderDAO orders;
    @Autowired
    UserDAO user;

    @RequestMapping(method = RequestMethod.GET)
    public String orderClosed(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        response.sendRedirect("/user");
        return "user";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String orderPayment(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        int orderId = Integer.valueOf(request.getParameter("orderId"));
        session.setAttribute("paymentOrderId", orderId);
        user.purchasePayment(session);
        orders.setOrderStatus(orderId, OrderStatus.PAYED);
        response.sendRedirect("/user");
        return "user";
    }
}
