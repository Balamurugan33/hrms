package com.ideas2it.hrms.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AppFilter implements Filter {

    private ServletContext context;
    
    public void init(FilterConfig arg0) throws ServletException {
   
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String loginURI = req.getContextPath() + "/";

        boolean loggedIn = (session != null) && (session.getAttribute("id") != null);
        boolean loginRequest = req.getRequestURI().equals(loginURI);
        boolean authenticateRequest = (req.getRequestURI().endsWith("login") || req.getRequestURI().endsWith("register")) && (req.getParameter("userName") != null);

        if (loggedIn || loginRequest || authenticateRequest) {
            chain.doFilter(request, response);
        } else if (req.getRequestURI().endsWith("logout")) {
            // HTTP 1.1
            res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            // HTTP 1.0
            res.setHeader("Pragma", "no-cache"); 
            res.setDateHeader("Expires", 0);
            chain.doFilter(request, response);
        } else {
            res.sendRedirect(loginURI);
        }
    }   

    public void destroy() {

    }
}