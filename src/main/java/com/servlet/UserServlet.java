package com.servlet;

import com.dao.FlowerDAO;
import com.entities.Flower;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("работает");
        response.setContentType("text/html");
        ArrayList <Flower> catalog = FlowerDAO.getFlowerCatalog();
        request.setAttribute("catalog", catalog);
        getServletConfig().getServletContext().getRequestDispatcher("/user.jsp").forward(request,response);
    }
}
