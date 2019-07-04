package com.controllers;

import com.dao.OrderDAO;
import com.dao.UserDAO;
import com.entities.TempCart;
import com.exceptions.LowBalanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping ("/order")
public class OrderController {

    @Autowired
    OrderDAO order;
    @Autowired
    UserDAO user;

    @RequestMapping(method = RequestMethod.GET)
    public void cartUpdate(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws LowBalanceException {

    }

    @RequestMapping(method = RequestMethod.POST)
    public void orderPlace (HttpServletRequest request, HttpServletResponse response, HttpSession session) throws LowBalanceException, IOException {
        order.placeOrder(session);
        TempCart cart = (TempCart) session.getAttribute("cart");
        cart.getOrderedFlower().clear();
        cart.setTotalCost(0);
        session.setAttribute("cart", cart);
        response.sendRedirect("/user");
    }
}