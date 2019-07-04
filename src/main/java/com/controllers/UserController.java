package com.controllers;


import com.dao.FlowerDAO;
import com.dao.OrderDAO;
import com.dao.UserDAO;
import com.entities.Cart;
import com.entities.Flower;
import com.entities.User;
import com.functions.StockManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    StockManager stockManager;
    @Autowired
    Cart cart;
    @Autowired
    UserDAO tempUser;
    @Autowired
    FlowerDAO flowerDAO;
    @Autowired
    OrderDAO order;

    @RequestMapping(method = RequestMethod.GET)
    public String login(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {  //перенести формирование всех элементов из loginController, затем сделать редирект внутри doget
        response.setContentType("text/html");
        ArrayList<Flower> catalog = flowerDAO.getFlowerCatalog();
        request.setAttribute("catalog", catalog);
        User user = (User) session.getAttribute("user");
        String userId = String.valueOf(user.getId());
        user = (User) tempUser.userInfo(userId);
        int balance = user.balance;
        int discount = user.discount;
        request.setAttribute("balance", balance);
        request.setAttribute("discount", discount);
        ArrayList<Order> orders = (ArrayList) order.getOrdersList(session);
        request.setAttribute("orders", orders);
        return "user";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addToCart(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        int amount = Integer.valueOf(request.getParameter("orderAmount"));
        int flowerId = Integer.valueOf(request.getParameter("flowerId"));
        if (amount<1){
            response.sendRedirect("/user");
            return "user";
        }
        stockManager.addToCart(amount, flowerId, session);
        response.sendRedirect("/cart");
        return "cart";
    }
}
