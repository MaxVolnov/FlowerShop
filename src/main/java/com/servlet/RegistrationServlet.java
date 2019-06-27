package com.servlet;

import com.exceptions.UserExistException;
import com.functions.Registration;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class RegistrationServlet extends HttpServlet {

    @Autowired
    Registration registration;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String eMail = request.getParameter("eMail");
        System.out.println("data received");
        try {
            boolean success = registration.registration(login, password, name, eMail);
            if (success) {
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (UserExistException ue) {
            ue.printStackTrace();
        }



    }

    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getRequestDispatcher("registration.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Registration";
    }
}
