package com.fashionstore.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

import com.fashionstore.dao.impl.CartDAOImpl;
import com.fashionstore.model.CartItem;
import com.fashionstore.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    HttpSession session = request.getSession(false);
	    User user = (session != null) ? (User) session.getAttribute("user") : null;
	    if (user == null) {
	        response.sendRedirect(request.getContextPath() + "/login");
	        return;
	    }
	    CartDAOImpl dao = new CartDAOImpl();
	    List<CartItem> cartItems = dao.getCartItems(user.getUserId());
	    double total = dao.getCartTotal(user.getUserId());

	    // ← ADD THIS LINE
	    session.setAttribute("cartCount", cartItems.size());

	    request.setAttribute("cartItems", cartItems);
	    request.setAttribute("cartTotal", total);
	    request.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(request, response);
	}
}