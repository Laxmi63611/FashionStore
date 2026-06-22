package com.fashionstore.dao;

import com.fashionstore.model.Order;
import java.util.List;

public interface OrderDAO {

    // Create new order
    int createOrder(Order order);

    // Get order by ID
    Order getOrderById(int orderId);

    // Get all orders for a user
    List<Order> getOrdersByUserId(int userId);

    // Update order status (Placed, Shipped, Delivered, etc.)
    boolean updateOrderStatus(int orderId, String status);

    // Delete order (optional)
    boolean deleteOrder(int orderId);
}
