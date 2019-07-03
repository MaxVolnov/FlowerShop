package com.controllers;

import com.dao.FlowerDAO;
import com.dao.LoginDAO;
import com.entities.TempCart;
import com.entities.User;
import com.enums.Role;
import com.exceptions.LoginException;
import com.exceptions.PasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    FlowerDAO flowerDao;
    @Autowired
    LoginDAO loginDAO;



    @RequestMapping(method = RequestMethod.GET)
    public String login(ModelMap model){
        System.out.println("login");
       return ("login");
    }

    @RequestMapping(method = RequestMethod.POST)
    protected String doPost(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        PrintWriter writer = response.getWriter();
        try {
            User user = loginDAO.logining(login, password);
            if (session.getAttribute("user")==null){
                session.setAttribute("user", user);
            }
            if (user.getRole() == Role.ADMIN){
                response.sendRedirect("/admin");
                return "admin";
            } else if (user.getRole() == Role.USER){

                if (session.getAttribute("cart")==null){
                    TempCart cart = new TempCart(session);
                    session.setAttribute("cart", cart);
                }
                response.sendRedirect("/user");
                return "user";
            }
        } catch (LoginException e) {
            writer.println("User does not exist. Please, sign up!");
        } catch (PasswordException e) {
            writer.println("Password is incorrect!");
        }
        return "";

    }





}
