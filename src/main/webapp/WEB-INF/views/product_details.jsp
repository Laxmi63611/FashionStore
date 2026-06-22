<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.fashionstore.model.Product" %>
<%@ page import="com.fashionstore.model.ProductSize" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <title>Product Details</title>

    <!-- GLOBAL CSS -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">

    <!-- PRODUCT DETAILS CSS -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/product_details.css">
</head>
<body>

<%@ include file="partials/navbar.jsp" %>

<%
    Product p     = (Product) request.getAttribute("product");
    List<ProductSize> sizes = (List<ProductSize>) request.getAttribute("sizes");
%>

<div class="product-details-page">
    <div class="product-details-container">

        <!-- ===== IMAGE SECTION ===== -->
        <div class="product-image-section">
            <img src="<%= request.getContextPath() %>/assets/<%= p.getImageUrl() %>"
                 alt="<%= p.getProductName() %>">
        </div>

        <!-- ===== INFO SECTION ===== -->
        <div class="product-info-section">

            <h1><%= p.getProductName() %></h1>

            <p class="product-description"><%= p.getDescription() %></p>

            <h2 class="product-price">₹ <%= p.getPrice() %></h2>

            <p class="discount-text"><%= p.getDiscountPercent() %>% OFF</p>

            <!-- ===== SIZE SELECTOR ===== -->
            <% if (sizes != null && !sizes.isEmpty()) { %>
            <div class="size-section">

                <p class="size-label-title">Select Size</p>

                <div class="size-options">
                    <% for (ProductSize size : sizes) { %>
                        <button type="button"
                                class="size-btn <%= !size.isAvailable() ? "size-unavailable" : "" %>"
                                data-size="<%= size.getSizeLabel() %>"
                                data-stock="<%= size.getStockQuantity() %>"
                                <%= !size.isAvailable() ? "disabled" : "" %>>
                            <%= size.getSizeLabel() %>
                        </button>
                    <% } %>
                </div>

                <p class="size-stock-info" id="stockInfo"></p>

            </div>
            <% } %>

            <!-- ===== BUTTONS ===== -->
            <div class="product-buttons">

    <form action="<%= request.getContextPath() %>/add-to-cart" method="post">
        <input type="hidden" name="productId" value="<%= p.getProductId() %>">
        <input type="hidden" name="selectedSize" id="selectedSizeInput" value="">
        <button type="submit" class="cart-btn">Add To Cart</button>
    </form>

    <a href="<%= request.getContextPath() %>/checkout" class="buy-now-btn">
        Buy Now
    </a>

</div>

        </div><!-- end product-info-section -->

    </div><!-- end product-details-container -->
</div><!-- end product-details-page -->

<%@ include file="partials/footer.jsp" %>

<!-- Size selector JavaScript -->
<script>
    const sizeBtns        = document.querySelectorAll('.size-btn:not(.size-unavailable)');
    const stockInfo       = document.getElementById('stockInfo');
    const selectedSizeInput = document.getElementById('selectedSizeInput');

    sizeBtns.forEach(function(btn) {
        btn.addEventListener('click', function() {

            // Remove active class from all buttons
            document.querySelectorAll('.size-btn').forEach(function(b) {
                b.classList.remove('size-active');
            });

            // Mark this button active
            btn.classList.add('size-active');

            // Update hidden input so size is sent with the form
            if (selectedSizeInput) {
                selectedSizeInput.value = btn.getAttribute('data-size');
            }

            // Show stock info
            var stock = parseInt(btn.getAttribute('data-stock'));
            if (stockInfo) {
                stockInfo.textContent = stock + ' items left in stock';
                stockInfo.style.color = stock < 5 ? '#e63946' : '#1a5c36';
            }
        });
    });
</script>

</body>
</html>
