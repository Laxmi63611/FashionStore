<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.fashionstore.model.Order" %>

<!DOCTYPE html>
<html>
<head>
    <title>Order Placed - Fashion Store</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">
    <style>
        .success-page {
            min-height: 70vh;
            display: flex;
            align-items: center;
            justify-content: center;
            background: #f5f5f5;
            padding: 40px 20px;
        }

        .success-card {
            background: #fff;
            border-radius: 18px;
            box-shadow: 0 4px 24px rgba(0,0,0,0.08);
            padding: 60px 48px;
            text-align: center;
            max-width: 520px;
            width: 100%;
        }

        .success-icon {
            width: 80px;
            height: 80px;
            background: #eaf7f0;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0 auto 24px;
            font-size: 40px;
        }

        .success-card h1 {
            font-size: 28px;
            font-weight: 700;
            color: #1a3a2a;
            margin-bottom: 12px;
        }

        .success-card p {
            font-size: 15px;
            color: #666;
            line-height: 1.7;
            margin-bottom: 8px;
        }

        .order-id-box {
            background: #f0f7f3;
            border: 1.5px dashed #1a5c36;
            border-radius: 10px;
            padding: 16px 24px;
            margin: 24px 0;
        }

        .order-id-box span {
            display: block;
            font-size: 12px;
            color: #888;
            margin-bottom: 4px;
            text-transform: uppercase;
            letter-spacing: 0.5px;
        }

        .order-id-box strong {
            font-size: 22px;
            font-weight: 700;
            color: #1a5c36;
        }

        .order-details {
            text-align: left;
            background: #fafafa;
            border-radius: 10px;
            padding: 16px 20px;
            margin-bottom: 28px;
            font-size: 14px;
        }

        .order-details p {
            display: flex;
            justify-content: space-between;
            margin-bottom: 8px;
            font-size: 14px;
        }

        .order-details p span:first-child {
            color: #888;
        }

        .order-details p span:last-child {
            font-weight: 600;
            color: #333;
        }

        .success-btns {
            display: flex;
            gap: 12px;
            justify-content: center;
            flex-wrap: wrap;
        }

        .btn-home {
            background: #1a5c36;
            color: #fff;
            padding: 12px 28px;
            border-radius: 8px;
            font-size: 14px;
            font-weight: 600;
            text-decoration: none;
            transition: background 0.2s;
        }

        .btn-home:hover { background: #0f3d24; }

        .btn-shop {
            background: #fff;
            color: #1a5c36;
            border: 2px solid #1a5c36;
            padding: 12px 28px;
            border-radius: 8px;
            font-size: 14px;
            font-weight: 600;
            text-decoration: none;
            transition: all 0.2s;
        }

        .btn-shop:hover {
            background: #1a5c36;
            color: #fff;
        }
    </style>
</head>
<body>

<%@ include file="partials/navbar.jsp" %>

<%
    Order order = (Order) request.getAttribute("order");
%>

<div class="success-page">
    <div class="success-card">

        <div class="success-icon">✅</div>

        <h1>Order Placed Successfully!</h1>
        <p>Thank you for shopping with Fashion Store.</p>
        <p>Your order has been confirmed and will be delivered soon.</p>

        <% if (order != null) { %>
        <div class="order-id-box">
            <span>Order ID</span>
            <strong>#<%= order.getOrderId() %></strong>
        </div>

        <div class="order-details">
            <p>
                <span>Payment Method</span>
                <span><%= order.getPaymentMethod() %></span>
            </p>
            <p>
                <span>Order Status</span>
                <span><%= order.getOrderStatus() %></span>
            </p>
            <p>
                <span>Total Amount</span>
                <span>₹ <%= order.getTotalAmount() %></span>
            </p>
            <p>
                <span>Delivery Address</span>
                <span><%= order.getDeliveryAddress() %></span>
            </p>
        </div>
        <% } %>

        <div class="success-btns">
            <a href="<%= request.getContextPath() %>/home" class="btn-home">Go to Home</a>
            <a href="<%= request.getContextPath() %>/products" class="btn-shop">Continue Shopping</a>
            <a href="<%= request.getContextPath() %>/orders" class="btn-shop">View Order History</a>
        </div>

    </div>
</div>

<%@ include file="partials/footer.jsp" %>

</body>
</html>
