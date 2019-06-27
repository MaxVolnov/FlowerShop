package com.servlet;

import com.daoimpl.FlowerDAOImpl;
import com.entities.Flower;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


public class UserServlet extends HttpServlet {

    @Autowired
    FlowerDAOImpl flowerDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("работает");
        response.setContentType("text/html");
        ArrayList <Flower> catalog = flowerDAO.getFlowerCatalog();
        request.setAttribute("catalog", catalog);
        getServletConfig().getServletContext().getRequestDispatcher("/user.jsp").forward(request,response);
    }
}
