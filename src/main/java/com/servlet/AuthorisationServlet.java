package com.servlet;

import com.dao.FlowerDAO;
import com.entities.Flower;
import com.functions.Authorisation;
import com.entities.User;
import com.enums.Role;
import com.exceptions.LoginException;
import com.exceptions.PasswordException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class AuthorisationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        PrintWriter writer = response.getWriter();
        try {
            User user = Authorisation.authentication(login, password);
            request.getSession().setAttribute("user", user);
            if (user.role == Role.ADMIN){
                request.getRequestDispatcher("admin.jsp").forward(request, response);
            } else if (user.role == Role.USER){
                response.setContentType("text/html");
                ArrayList<Flower> catalog = FlowerDAO.getFlowerCatalog();
                request.setAttribute("catalog", catalog);
                getServletConfig().getServletContext().getRequestDispatcher("/user.jsp").forward(request,response);
                request.getRequestDispatcher("user.jsp").forward(request, response);
            }
        } catch (LoginException e) {
            writer.println("User does not exist. Please, sign up!");
        } catch (PasswordException e) {
            writer.println("Password is incorrect!");
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        try {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } finally {
            writer.close();
        }
    }


}


