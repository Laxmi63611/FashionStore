<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.fashionstore.model.Order" %>

<!DOCTYPE html>
<html>
<head>
    <title>Order History - Fashion Store</title>

    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">

    <style>
        .orders-page {
            width: min(1200px, 95%);
            margin: 40px auto 60px;
        }

        .orders-page h1 {
            color: #1b5e3a;
            margin-bottom: 20px;
            font-size: 34px;
        }

        .message-box {
            background: #eef8f2;
            border: 1px solid #cde8d6;
            color: #1b5e3a;
            padding: 12px 16px;
            border-radius: 10px;
            margin-bottom: 20px;
            font-weight: 600;
        }

        .orders-table-wrap {
            background: #fff;
            border-radius: 16px;
            box-shadow: 0 4px 18px rgba(0,0,0,0.08);
            overflow-x: auto;
        }

        .orders-table {
            width: 100%;
            border-collapse: collapse;
            min-width: 900px;
        }

        .orders-table th,
        .orders-table td {
            padding: 14px 16px;
            border-bottom: 1px solid #eee;
            text-align: left;
            vertical-align: top;
        }

        .orders-table th {
            background: #f7f9f8;
            color: #1b5e3a;
            font-size: 14px;
            text-transform: uppercase;
            letter-spacing: 0.4px;
        }

        .orders-table td {
            font-size: 14px;
            color: #333;
        }

        .status-badge {
            display: inline-block;
            padding: 6px 10px;
            border-radius: 999px;
            font-size: 12px;
            font-weight: 700;
        }

        .status-placed {
            background: #eaf7ef;
            color: #1b5e3a;
        }

        .status-cancelled {
            background: #ffe8e8;
            color: #c62828;
        }

        .status-delivered {
            background: #e8f1ff;
            color: #0d47a1;
        }

        .status-shipped {
            background: #fff3cd;
            color: #8a6d3b;
        }

        .cancel-btn {
            background: #d9534f;
            color: #fff;
            border: none;
            padding: 10px 14px;
            border-radius: 8px;
            cursor: pointer;
            font-weight: 600;
        }

        .cancel-btn:hover {
            background: #c9302c;
        }

        .no-orders {
            background: #fff;
            padding: 30px;
            border-radius: 16px;
            box-shadow: 0 4px 18px rgba(0,0,0,0.08);
            text-align: center;
            color: #666;
            font-size: 16px;
        }
    </style>
</head>
<body>

<%@ include file="partials/navbar.jsp" %>

<%
    List<Order> orders = (List<Order>) request.getAttribute("orders");
    String message = request.getParameter("message");
%>

<div class="orders-page">
    <h1>Order History</h1>

    <% if (message != null && !message.isBlank()) { %>
        <div class="message-box"><%= message.replace("+", " ") %></div>
    <% } %>

    <% if (orders != null && !orders.isEmpty()) { %>
        <div class="orders-table-wrap">
            <table class="orders-table">
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Date</th>
                        <th>Total Amount</th>
                        <th>Payment Method</th>
                        <th>Status</th>
                        <th>Delivery Address</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                <%
                    for (Order o : orders) {
                        String statusClass = "status-placed";
                        if ("Cancelled".equalsIgnoreCase(o.getOrderStatus())) {
                            statusClass = "status-cancelled";
                        } else if ("Delivered".equalsIgnoreCase(o.getOrderStatus())) {
                            statusClass = "status-delivered";
                        } else if ("Shipped".equalsIgnoreCase(o.getOrderStatus())) {
                            statusClass = "status-shipped";
                        }
                %>
                    <tr>
                        <td>#<%= o.getOrderId() %></td>
                        <td><%= o.getOrderDate() %></td>
                        <td>₹ <%= o.getTotalAmount() %></td>
                        <td><%= o.getPaymentMethod() %></td>
                        <td>
                            <span class="status-badge <%= statusClass %>">
                                <%= o.getOrderStatus() %>
                            </span>
                        </td>
                        <td><%= o.getDeliveryAddress() %></td>
                        <td>
                            <% if ("Placed".equalsIgnoreCase(o.getOrderStatus())) { %>
                                <form action="<%= request.getContextPath() %>/cancel-order" method="post"
                                      onsubmit="return confirm('Are you sure you want to cancel this order?');">
                                    <input type="hidden" name="orderId" value="<%= o.getOrderId() %>">
                                    <button type="submit" class="cancel-btn">Cancel Order</button>
                                </form>
                            <% } else { %>
                                --
                            <% } %>
                        </td>
                    </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
    <% } else { %>
        <div class="no-orders">
            No orders found.
        </div>
    <% } %>
</div>

<%@ include file="partials/footer.jsp" %>

</body>
</html>