package com.fashionstore.controller;

import java.io.IOException;

import com.fashionstore.dao.AdminDAO;
import com.fashionstore.dao.impl.AdminDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/admin/login")
public class AdminLoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private AdminDAO adminDAO;

    @Override
    public void init() {
        adminDAO = new AdminDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(
                "/WEB-INF/views/admin/admin-login.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String username =
                request.getParameter("username");

        String password =
                request.getParameter("password");

        boolean valid =
                adminDAO.login(username, password);

        if(valid) {

            HttpSession session =
                    request.getSession();

            session.setAttribute(
                    "admin", username);

            response.sendRedirect(
                    request.getContextPath()
                    + "/admin/dashboard");

        } else {

            request.setAttribute(
                    "error",
                    "Invalid Username or Password");

            doGet(request, response);
        }
    }
}
