package com.fashionstore.controller;

import com.fashionstore.dao.impl.CartDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/remove-cart-item")
public class RemoveCartItemServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        int cartItemId =
                Integer.parseInt(request.getParameter("cartItemId"));

        CartDAOImpl dao = new CartDAOImpl();

        dao.removeCartItem(cartItemId);

        response.sendRedirect("cart");
    }
}
