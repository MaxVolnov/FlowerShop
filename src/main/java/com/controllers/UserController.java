package com.controllers;


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

@Controller
@RequestMapping({"/user"})
public class UserController {
    @Autowired
    StockManager stockManager;

    @RequestMapping(method = RequestMethod.GET)
    public String login(ModelMap model) throws ServletException, IOException {
        System.out.println("user");
        return ("user");
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addToCart(HttpServletRequest request, HttpServletResponse response) {
        int amount = Integer.valueOf(request.getParameter("orderAmount"));
        int flowerId = Integer.valueOf(request.getParameter("flowerId"));
        Flower addedFlower = stockManager.addToCart(amount, flowerId);
        return "cart";
    }
}
