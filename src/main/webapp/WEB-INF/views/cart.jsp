<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.fashionstore.model.CartItem" %>

<%@ include file="partials/navbar.jsp" %>

<link rel="stylesheet"
href="<%= request.getContextPath() %>/assets/css/cart.css">

<div class="cart-page">

    <h2>Your Shopping Cart</h2>

    <%
        List<CartItem> cartItems =
        (List<CartItem>) request.getAttribute("cartItems");

        Double total =
        (Double) request.getAttribute("cartTotal");

        if(total == null){
            total = 0.0;
        }
    %>

    <div class="cart-container">

        <!-- LEFT SIDE -->
        <div class="cart-items">

            <%
                if(cartItems != null && !cartItems.isEmpty()) {

                for(CartItem item : cartItems) {
            %>

            <div class="cart-item">

                <!-- PRODUCT IMAGE -->
                <div class="cart-image">

                  <img
        src="<%= request.getContextPath() %>/assets/<%= item.getProduct().getImageUrl() %>"
        alt="<%= item.getProduct().getProductName() %>">

                </div>

                <!-- PRODUCT DETAILS -->
                <div class="cart-details">

                    <h3>
                        <%= item.getProduct().getProductName() %>
                    </h3>

                    <p class="price">
                        ₹ <%= item.getProduct().getPrice() %>
                    </p>

                    <!-- QUANTITY -->
                    <div class="quantity-box">

                        <!-- MINUS -->
                        <form action="update-cart" method="post">

                            <input type="hidden"
                                   name="cartItemId"
                                   value="<%= item.getCartItemId() %>">

                            <input type="hidden"
                                   name="quantity"
                                   value="<%= item.getQuantity() - 1 %>">

                            <button type="submit"
                                    class="qty-btn">

                                -

                            </button>

                        </form>

                        <!-- QUANTITY -->
                        <span class="qty-number">

                            <%= item.getQuantity() %>

                        </span>

                        <!-- PLUS -->
                        <form action="update-cart" method="post">

                            <input type="hidden"
                                   name="cartItemId"
                                   value="<%= item.getCartItemId() %>">

                            <input type="hidden"
                                   name="quantity"
                                   value="<%= item.getQuantity() + 1 %>">

                            <button type="submit"
                                    class="qty-btn">

                                +

                            </button>

                        </form>

                    </div>

                    <!-- ITEM TOTAL -->
                    <p class="item-total">

                        Total :
                        ₹ <%= item.getTotalPrice() %>

                    </p>

                    <!-- REMOVE BUTTON -->
                    <a href="remove-cart-item?cartItemId=<%= item.getCartItemId() %>"
                       class="remove-btn">

                        Remove

                    </a>

                </div>

            </div>

            <%
                    }
                } else {
            %>

            <div class="empty-cart">

                <h3>Your cart is empty</h3>

                <a href="<%= request.getContextPath() %>/products"
                   class="continue-btn">

                    Shop Now

                </a>

            </div>

            <%
                }
            %>

        </div>

        <!-- RIGHT SIDE -->
        <div class="cart-summary">

            <h3>Order Summary</h3>

            <div class="summary-row">

                <span>Subtotal</span>

                <span>
                    ₹ <%= total %>
                </span>

            </div>

            <div class="summary-row">

                <span>Delivery</span>

                <span>₹ 50</span>

            </div>

            <div class="summary-row total-row">

                <span>Total</span>

                <span>
                    ₹ <%= total + 50 %>
                </span>

            </div>

            <!-- CHECKOUT -->
              <a href="<%= request.getContextPath() %>/checkout" class="checkout-btn">
                   Proceed To Checkout
              </a>

            <!-- CONTINUE SHOPPING -->
            <a href="<%= request.getContextPath() %>/products"
               class="continue-btn">

                Continue Shopping

            </a>

        </div>

    </div>

</div>

<%@ include file="partials/footer.jsp" %>