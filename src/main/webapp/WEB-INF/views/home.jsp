<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.fashionstore.model.Product" %>

<!DOCTYPE html>
<html>
<head>
    <title>Fashion Store</title>

    <link rel="stylesheet"
          href="<%= request.getContextPath() %>/assets/css/style.css">

    <link rel="stylesheet"
          href="<%= request.getContextPath() %>/assets/css/home.css">
</head>
<body>

<%@ include file="partials/navbar.jsp" %>

<div class="home-page">

    <!-- HERO SECTION -->
    <section class="hero-section">

        <div class="hero-content">

            <span class="hero-badge">
                NEW COLLECTION
            </span>

            <h1>Elevate Your Style</h1>

            <p>Discover the latest trends in fashion.</p>
            <p>Premium quality, unmatched comfort.</p>

            <div class="hero-actions">

                <a href="<%= request.getContextPath() %>/products"
                   class="btn btn-primary">
                    Shop Now
                </a>

                <a href="#categories"
                   class="btn btn-secondary">
                    View Collections
                </a>

            </div>

        </div>

        <div class="hero-visual">

            <img src="<%= request.getContextPath() %>/assets/images/men_tshirt.jpg" alt="Men Fashion">
            <img src="<%= request.getContextPath() %>/assets/images/women_dress.jpg" alt="Women Fashion">
            <img src="<%= request.getContextPath() %>/assets/images/women_top.jpg" alt="Women Top">
            <img src="<%= request.getContextPath() %>/assets/images/kids_shirt.jpg" alt="Kids Fashion">

        </div>

    </section>

    <!-- FEATURES -->
    <section class="features-strip">

        <div class="feature-card">
            <h3>Free Shipping</h3>
            <p>On orders over ₹999</p>
        </div>

        <div class="feature-card">
            <h3>Secure Payment</h3>
            <p>100% secure checkout</p>
        </div>

        <div class="feature-card">
            <h3>Easy Returns</h3>
            <p>7-day return policy</p>
        </div>

        <div class="feature-card">
            <h3>24/7 Support</h3>
            <p>Dedicated support</p>
        </div>

    </section>

    <!-- CATEGORY SECTION -->
    <section class="category-section" id="categories">

        <div class="section-header">
            <h2>Shop by Category</h2>
            <a href="<%= request.getContextPath() %>/products">View All →</a>
        </div>

        <div class="category-grid">

            <a class="category-card" href="<%= request.getContextPath() %>/products?category=1">
                <div class="category-text">
                    <h3>Men</h3>
                    <p>Latest styles for men</p>
                    <span>Shop Now →</span>
                </div>
                <img src="<%= request.getContextPath() %>/assets/images/men_tshirt.jpg" alt="Men">
            </a>

            <a class="category-card" href="<%= request.getContextPath() %>/products?category=2">
                <div class="category-text">
                    <h3>Women</h3>
                    <p>Trendy & elegant styles</p>
                    <span>Shop Now →</span>
                </div>
                <img src="<%= request.getContextPath() %>/assets/images/women_dress.jpg" alt="Women">
            </a>

            <a class="category-card" href="<%= request.getContextPath() %>/products?category=3">
                <div class="category-text">
                    <h3>Kids</h3>
                    <p>Comfort & style for kids</p>
                    <span>Shop Now →</span>
                </div>
                <img src="<%= request.getContextPath() %>/assets/images/kids_shirt.jpg" alt="Kids">
            </a>

            

        </div>

    </section>

    <!-- FEATURED PRODUCTS -->
    <section class="featured-products">

        <div class="section-header">
            <h2>Featured Products</h2>
            <a href="<%= request.getContextPath() %>/products">View More →</a>
        </div>

        <div class="product-grid">
        <%
            List<Product> featuredProducts =
                    (List<Product>) request.getAttribute("featuredProducts");

            if (featuredProducts != null && !featuredProducts.isEmpty()) {
                for (Product p : featuredProducts) {
        %>

            <div class="product-card">

                <div class="product-image">
                    <img src="<%= request.getContextPath() %>/assets/<%= p.getImageUrl() %>"
                         alt="<%= p.getProductName() %>">
                </div>

                <div class="product-content">
                    <h3><%= p.getProductName() %></h3>
                    <p><%= p.getDescription() %></p>
                    <div class="price">₹ <%= p.getPrice() %></div>

                    <div class="product-actions">
                        <a class="view-btn"
                           href="<%= request.getContextPath() %>/product-details?id=<%= p.getProductId() %>">
                            View Details
                        </a>

                        <form action="<%= request.getContextPath() %>/add-to-cart" method="post">
                            <input type="hidden" name="productId" value="<%= p.getProductId() %>">
                            <button type="submit" class="cart-btn">Add to Cart</button>
                        </form>
                    </div>
                </div>

            </div>

        <%
                }
            } else {
        %>
            <p class="no-products">No products available</p>
        <%
            }
        %>
        </div>

    </section>

</div>

<%@ include file="partials/footer.jsp" %>

</body>
</html>