package com.fashionstore.controller;

import com.fashionstore.dao.impl.CartDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/update-cart")
public class UpdateCartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        int cartItemId = Integer.parseInt(request.getParameter("cartItemId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        CartDAOImpl dao = new CartDAOImpl();

        // Minimum quantity = 1
        if (quantity < 1) {
            quantity = 1;
        }

        dao.updateQuantity(cartItemId, quantity);

        response.sendRedirect("cart");
    }
}
