package com.servlet;


import com.entities.User;
import com.enums.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "RoleFilter")
public class RoleFilter implements Filter {

    public void destroy(){
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        User user = (User) httpRequest.getSession().getAttribute("user");
        if (user!= null && user.role == Role.ADMIN){
            httpResponse.sendRedirect("/admin");
        } else {
            httpResponse.sendRedirect("/user");
        }
    }


    public void init(FilterConfig config) throws ServletException {

    }
}
