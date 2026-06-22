package com.fashionstore.controller;

import com.fashionstore.dao.OrderDAO;
import com.fashionstore.dao.impl.OrderDAOImpl;
import com.fashionstore.model.Order;
import com.fashionstore.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/cancel-order")
public class CancelOrderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String orderIdParam = request.getParameter("orderId");
        if (orderIdParam == null || orderIdParam.isBlank()) {
            response.sendRedirect(request.getContextPath() + "/orders?message=Invalid+order");
            return;
        }

        try {
            int orderId = Integer.parseInt(orderIdParam);

            OrderDAO orderDAO = new OrderDAOImpl();
            Order order = orderDAO.getOrderById(orderId);

            if (order == null || order.getUserId() != user.getUserId()) {
                response.sendRedirect(request.getContextPath() + "/orders?message=Order+not+found");
                return;
            }

            if (!"Placed".equalsIgnoreCase(order.getOrderStatus())) {
                response.sendRedirect(request.getContextPath() + "/orders?message=Only+placed+orders+can+be+cancelled");
                return;
            }

            boolean status = orderDAO.updateOrderStatus(orderId, "Cancelled");

            if (status) {
                response.sendRedirect(request.getContextPath() + "/orders?message=Order+cancelled+successfully");
            } else {
                response.sendRedirect(request.getContextPath() + "/orders?message=Failed+to+cancel+order");
            }

        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/orders?message=Invalid+order+id");
        }
    }
}
