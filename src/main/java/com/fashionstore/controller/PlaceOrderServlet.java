package com.fashionstore.controller;

import com.fashionstore.dao.OrderDAO;
import com.fashionstore.dao.OrderItemDAO;
import com.fashionstore.dao.impl.CartDAOImpl;
import com.fashionstore.dao.impl.OrderDAOImpl;
import com.fashionstore.dao.impl.OrderItemDAOImpl;
import com.fashionstore.model.CartItem;
import com.fashionstore.model.Order;
import com.fashionstore.model.OrderItem;
import com.fashionstore.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/place-order")
public class PlaceOrderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Check login
        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // 2. Read form fields
        String fullName      = request.getParameter("fullName");
        String address       = request.getParameter("address");
        String city          = request.getParameter("city");
        String state         = request.getParameter("state");
        String pincode       = request.getParameter("pincode");
        String paymentMethod = request.getParameter("paymentMethod");

        // Build delivery address string
        String deliveryAddress = fullName + ", " + address + ", " + city + ", " + state + " - " + pincode;

        // 3. Get cart items
        CartDAOImpl cartDAO = new CartDAOImpl();
        List<CartItem> cartItems = cartDAO.getCartItems(user.getUserId());
        double cartTotal = cartDAO.getCartTotal(user.getUserId());

        if (cartItems == null || cartItems.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/cart");
            return;
        }

        // 4. Create Order object
        Order order = new Order();
        order.setUserId(user.getUserId());
        order.setTotalAmount(cartTotal + 50); // +50 delivery charge
        order.setPaymentMethod(paymentMethod);
        order.setOrderStatus("Placed");
        order.setDeliveryAddress(deliveryAddress);

        // 5. Save order to DB — get generated order ID
        OrderDAO orderDAO = new OrderDAOImpl();
        int orderId = orderDAO.createOrder(order);

        if (orderId == 0) {
            // Order creation failed
            request.setAttribute("error", "Failed to place order. Please try again.");
            request.getRequestDispatcher("/WEB-INF/views/checkout.jsp").forward(request, response);
            return;
        }

        // 6. Save each cart item as an order item
        OrderItemDAO orderItemDAO = new OrderItemDAOImpl();

        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(orderId);
            orderItem.setProductId(cartItem.getProduct().getProductId());
            orderItem.setProductName(cartItem.getProduct().getProductName());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setUnitPrice(cartItem.getProduct().getPrice());
            orderItem.setSubtotal(cartItem.getProduct().getPrice() * cartItem.getQuantity());
            orderItem.setSizeLabel("");
            orderItemDAO.addOrderItem(orderItem);
        }

        // 7. Clear the cart after successful order
        cartDAO.clearCart(user.getUserId());

        // 8. Redirect to order success page
        response.sendRedirect(request.getContextPath() + "/order-success?orderId=" + orderId);
    }
}
