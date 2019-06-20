package com.servlet;

import com.domain.Authorisation;
import com.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/")
public class AuthorisationServlet extends HttpServlet {
    private static String URL = "jdbc:h2:~/test";
    private static String login = null;
    private static String password = null;
    private static final String select = "select * from users where user_id = &; ";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = Authorisation.authentication(login, password);

        request.getSession().setAttribute("user", user);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        try {
            writer.println("<h2>login/register page</h2>");
        } finally {
            writer.close();
        }
    }


}


