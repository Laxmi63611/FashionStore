<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.fashionstore.model.CartItem" %>
<%@ page import="com.fashionstore.model.User" %>

<!DOCTYPE html>
<html>
<head>
    <title>Checkout - Fashion Store</title>

    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/checkout.css">
</head>
<body>

<%@ include file="partials/navbar.jsp" %>

<%
    User user = (User) request.getAttribute("user");
    List<CartItem> cartItems = (List<CartItem>) request.getAttribute("cartItems");
    Double total = (Double) request.getAttribute("cartTotal");
    if (total == null) {
        total = 0.0;
    }
%>

<div class="checkout-page">
    <div class="checkout-container">

        <div class="checkout-form-section">
            <h2>Checkout</h2>
            <p class="checkout-subtitle">Complete your shipping details to place the order.</p>

            <form action="<%= request.getContextPath() %>/place-order" method="post" class="checkout-form">

                <div class="form-group">
                    <label>Full Name</label>
                    <input type="text" name="fullName" value="<%= user != null ? user.getFullName() : "" %>" required>
                </div>

                <div class="form-group">
                    <label>Email</label>
                    <input type="email" name="email" value="<%= user != null ? user.getEmail() : "" %>" required>
                </div>

                <div class="form-group">
                    <label>Phone</label>
                    <input type="text" name="phone" value="<%= user != null ? user.getPhone() : "" %>" required>
                </div>

                <div class="form-group">
                    <label>Address</label>
                    <textarea name="address" rows="4" required><%= user != null ? user.getAddress() : "" %></textarea>
                </div>

                <div class="row">
                    <div class="form-group">
                        <label>City</label>
                        <input type="text" name="city" required>
                    </div>

                    <div class="form-group">
                        <label>State</label>
                        <input type="text" name="state" required>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <label>Pincode</label>
                        <input type="text" name="pincode" required>
                    </div>

                    <div class="form-group">
                        <label>Payment Method</label>
                        <select name="paymentMethod" required>
                            <option value="COD">Cash on Delivery</option>
                            <option value="Card">Card</option>
                            <option value="UPI">UPI</option>
                        </select>
                    </div>
                </div>

                <button type="submit" class="place-order-btn">Place Order</button>
            </form>
        </div>

        <div class="checkout-summary-section">
            <h3>Order Summary</h3>

            <div class="summary-box">
                <div class="summary-row">
                    <span>Items</span>
                    <span><%= cartItems != null ? cartItems.size() : 0 %></span>
                </div>

                <div class="summary-row">
                    <span>Subtotal</span>
                    <span>₹ <%= total %></span>
                </div>

                <div class="summary-row">
                    <span>Delivery</span>
                    <span>₹ 50</span>
                </div>

                <div class="summary-row total-row">
                    <span>Total</span>
                    <span>₹ <%= total + 50 %></span>
                </div>
            </div>
        </div>

    </div>
</div>

<%@ include file="partials/footer.jsp" %>

</body>
</html>