package com.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import java.io.IOException;

@Controller
@RequestMapping({"/user"})
public class UserController {

    @RequestMapping(method = RequestMethod.GET)
    public String login(ModelMap model) throws ServletException, IOException {
        System.out.println("user");
        return ("user");
    }
}
