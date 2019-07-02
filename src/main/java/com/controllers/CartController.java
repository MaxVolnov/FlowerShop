package com.controllers;

import com.dao.FlowerDAO;
import com.entities.Flower;
import com.entities.TempCart;
import com.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    FlowerDAO flowerDAO;

    @RequestMapping(method = RequestMethod.GET)
    public String cart(HttpSession session, HttpServletRequest request) {
        User user = (User) session.getAttribute("user");
        request.setAttribute("user", user);
        TempCart cart = (TempCart) session.getAttribute("cart");
        request.setAttribute("userCart", cart.getOrderedFlower());
        request.setAttribute("totalCost", cart.getTotalCost());
        return "cart";
    }

    @RequestMapping(method = RequestMethod.POST)
    public void removeFromCart(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        int flowerId = Integer.valueOf(request.getParameter("flowerId"));
        int amount = Integer.valueOf(request.getParameter("amount"));
        int flowerCost = Integer.valueOf(flowerDAO.flowerInfo(String.valueOf(flowerId)).getCost())*amount;
        int totalCost = (int) session.getAttribute("totalCost");
        session.setAttribute("totalCost", totalCost-flowerCost);
        removeFromSession(session, Integer.valueOf(request.getParameter("flowerId")), totalCost-flowerCost);
        response.sendRedirect("/cart");
        cart(session, request);
    }

    private void removeFromSession(HttpSession session, int flowerId, int totalCost) {
        TempCart cart = (TempCart) session.getAttribute("cart");
        for (Flower flower : new ArrayList<Flower>(cart.getOrderedFlower())) {
            if (flower.getFlowerId() == flowerId) {
                cart.getOrderedFlower().remove(flower);
                break;
            }
        }
        cart.setTotalCost(totalCost);
        session.setAttribute("cart", cart);

    }
}
