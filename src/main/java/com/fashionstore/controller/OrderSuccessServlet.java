package com.fashionstore.controller;

import com.fashionstore.dao.OrderDAO;
import com.fashionstore.dao.impl.OrderDAOImpl;
import com.fashionstore.model.Order;
import com.fashionstore.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/order-success")
public class OrderSuccessServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String orderIdParam = request.getParameter("orderId");
        if (orderIdParam == null) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }

        int orderId = Integer.parseInt(orderIdParam);

        OrderDAO orderDAO = new OrderDAOImpl();
        Order order = orderDAO.getOrderById(orderId);

        request.setAttribute("order", order);
        request.getRequestDispatcher("/WEB-INF/views/order_success.jsp")
               .forward(request, response);
    }
}
