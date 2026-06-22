<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.fashionstore.model.Product" %>

<link rel="stylesheet"
href="<%= request.getContextPath() %>/assets/css/products.css">

<%@ include file="partials/navbar.jsp" %>

<div class="products-page">

    <!-- PAGE TITLE -->
    <h2 class="products-title">
        Explore Our Fashion Collection
    </h2>

    <!-- FILTER SECTION -->
    <div class="filter-section">

        <form method="get"
              action="products"
              class="filter-bar">

            <!-- CATEGORY -->
            <select name="category">

                <option value="0">All Categories</option>
                <option value="1">Men</option>
                <option value="2">Women</option>
                <option value="3">Kids</option>

            </select>

            <!-- MIN PRICE -->
            <input type="number"
                   name="minPrice"
                   placeholder="Min Price">

            <!-- MAX PRICE -->
            <input type="number"
                   name="maxPrice"
                   placeholder="Max Price">

            <!-- SEARCH -->
            <input type="text"
                   name="keyword"
                   placeholder="Search products">

            <!-- BUTTON -->
            <button type="submit">

                Search

            </button>

        </form>

    </div>

    <!-- PRODUCTS -->
    <div class="products-container">

    <%
        List<Product> list =
        (List<Product>) request.getAttribute("products");

        if(list != null && !list.isEmpty()) {

            for(Product p : list) {
    %>

        <!-- PRODUCT CARD -->
        <div class="product-card">

            <!-- PRODUCT IMAGE -->
            <div class="product-image">

                <img
                src="<%= request.getContextPath() %>/assets/<%= p.getImageUrl() %>"
                alt="Product Image">

            </div>

            <!-- PRODUCT INFO -->
            <div class="product-info">

                <!-- NAME -->
                <h3>

                    <%= p.getProductName() %>

                </h3>

                <!-- DESCRIPTION -->
                <p class="product-desc">

                    <%= p.getDescription() %>

                </p>

                <!-- PRICE -->
                <p class="product-price">

                    ₹ <%= p.getPrice() %>

                </p>

                <!-- BUTTONS -->
                <div class="product-buttons">

                    <!-- VIEW DETAILS -->
                    <a href="<%= request.getContextPath() %>/product-details?id=<%= p.getProductId() %>"
                      class="view-btn">

                        View Details

                    </a>

                    <!-- ADD TO CART -->
                    <form action="<%= request.getContextPath() %>/add-to-cart" method="post">

                        <input type="hidden"
                               name="productId"
                               value="<%= p.getProductId() %>">

                        <button type="submit"
                                class="cart-btn">

                            Add To Cart

                        </button>

                    </form>

                </div>

            </div>

        </div>

    <%
            }

        } else {
    %>

        <div class="no-products">

            <h3>No products found</h3>

        </div>

    <%
        }
    %>

    </div>

</div>

<%@ include file="partials/footer.jsp" %>