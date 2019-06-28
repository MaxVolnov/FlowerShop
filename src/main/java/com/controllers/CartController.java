package com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/cart")
public class CartController {

    @RequestMapping(method = RequestMethod.GET)
    public String getCart(ModelMap model, HttpServletRequest request, HttpServletResponse response){
        System.out.println("cart");

        return "cart";
    }
}
