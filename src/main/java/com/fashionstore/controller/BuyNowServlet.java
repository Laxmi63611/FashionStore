package com.fashionstore.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/buyNow")
public class BuyNowServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String productId = request.getParameter("productId");

        // Store product in session
        HttpSession session = request.getSession();
        session.setAttribute("buyProductId", productId);

        // Redirect to checkout page
        response.sendRedirect("checkout.jsp");
    }
}
