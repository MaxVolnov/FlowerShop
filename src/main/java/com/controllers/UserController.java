package com.controllers;


import com.dao.UserDAO;
import com.entities.Cart;
import com.entities.Flower;
import com.functions.StockManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @RequestMapping(method = RequestMethod.GET)
    public String login(ModelMap model) throws ServletException, IOException {  //перенести формирование всех элементов из loginController, затем сделать редирект.
        System.out.println("tempUser");
        return ("user");
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addToCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int amount = Integer.valueOf(request.getParameter("orderAmount"));
        int flowerId = Integer.valueOf(request.getParameter("flowerId"));
        String userId = String.valueOf(request.getParameter("userId"));
        Flower addedFlower = stockManager.addToCart(amount, flowerId);
        double discount = tempUser.userInfo(userId).discount;
        request.setAttribute("totalCost", this.cart.getTotalCost());
        ArrayList<Flower> userCart = (ArrayList) cart.getOrderedFlower();
        request.setAttribute("userCart", userCart);
        //response.sendRedirect("/cart");
        return "cart";
    }
}
