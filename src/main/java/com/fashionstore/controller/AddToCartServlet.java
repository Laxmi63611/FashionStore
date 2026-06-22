package com.fashionstore.controller;

import java.io.IOException;
import java.util.List;

import com.fashionstore.dao.impl.CartDAOImpl;
import com.fashionstore.model.CartItem;
import com.fashionstore.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = 1;

        String qtyParam = request.getParameter("quantity");
        if (qtyParam != null && !qtyParam.isBlank()) {
            quantity = Integer.parseInt(qtyParam);
        }

        CartDAOImpl dao = new CartDAOImpl();
        boolean status = dao.addToCart(user.getUserId(), productId, quantity);
     // After adding item to cart
        List<CartItem> updatedCart = dao.getCartItems(user.getUserId());
        session.setAttribute("cartCount", updatedCart.size()); // ← ADD THIS

        if (status) {
            response.sendRedirect(request.getContextPath() + "/cart");
        } else {
            response.getWriter().println("Failed To Add Product");
        }
    }
}