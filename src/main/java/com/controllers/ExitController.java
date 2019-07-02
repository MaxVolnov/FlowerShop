package com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping({"/exit"})
public class ExitController {

    @RequestMapping(method = RequestMethod.GET)
    public String exit(HttpServletResponse response, HttpServletRequest request, HttpSession session) throws IOException {
        session.removeAttribute("user");
        session.removeAttribute("cart");
        session.removeAttribute("flower");
        session.removeAttribute("totalCost");
        response.sendRedirect("/");
        return "login";
    }
}
