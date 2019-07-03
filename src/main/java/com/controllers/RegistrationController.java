package com.controllers;

import com.entities.User;
import com.exceptions.UserExistException;
import com.functions.Registration;
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
@RequestMapping({"/registration"})
public class RegistrationController {

    @Autowired
    Registration registration;

    @RequestMapping(method = RequestMethod.GET)
    public String login(ModelMap model) throws ServletException, IOException {
        System.out.println("register");
        return ("registration");
    }

    @RequestMapping(method = RequestMethod.POST)
    public void registration(HttpServletRequest request, HttpServletResponse response) throws IOException, UserExistException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String eMail = request.getParameter("eMail");
        User registrant = new User(login, password, name, eMail);
        boolean success = registration.registration(registrant);
        if (success) {
            response.sendRedirect("/");
        } else {
            request.setAttribute("message", "Something wrong!");
            response.sendRedirect("/registration");
        }

    }
}
