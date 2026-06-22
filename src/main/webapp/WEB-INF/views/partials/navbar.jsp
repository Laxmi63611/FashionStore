<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet"
      href="<%= request.getContextPath() %>/assets/css/style.css">

<!-- TOP ANNOUNCEMENT BAR -->
<div class="top-bar">

    <div class="top-bar-left">
        <span>Free Shipping on orders over ₹999</span>
        <span>Easy 7-day returns</span>
        <span>New arrivals every week!</span>
    </div>

    <div class="top-bar-right">

        <a href="<%= request.getContextPath() %>/orders">
            Track Order
        </a>

        <a href="<%= request.getContextPath() %>/support">
            Help &amp; Support
        </a>

    </div>

</div>

<!-- MAIN HEADER -->
<header class="main-header">

    <!-- LOGO -->
    <div class="brand">
        <a href="<%= request.getContextPath() %>/home">
            Fashion Store
        </a>
    </div>

    <!-- SEARCH -->
    <form class="search-form"
          action="<%= request.getContextPath() %>/products"
          method="get">

        <select name="category">
            <option value="0">All Categories</option>
            <option value="1">Men</option>
            <option value="2">Women</option>
            <option value="3">Kids</option>
        </select>

        <input type="text"
               name="keyword"
               placeholder="Search for products, brands and more...">

        <button type="submit">
            Search
        </button>

    </form>

    <!-- RIGHT NAV -->
    <div class="nav-right">

        <a href="<%= request.getContextPath() %>/login">
            Login
        </a>

        <a href="<%= request.getContextPath() %>/orders">
            Orders
        </a>

        <a href="<%= request.getContextPath() %>/cart">
            Cart
            <span class="cart-count">
                <%= session.getAttribute("cartCount") != null
                    ? session.getAttribute("cartCount") : 0 %>
            </span>
        </a>

         <a href="<%= request.getContextPath() %>/logout" class="logout-btn">Logout</a>

    </div>

</header>

<!-- CATEGORY NAVIGATION -->
<nav class="category-nav">

    <ul class="nav-links">

        <!-- HOME -->
        <li>
            <a href="<%= request.getContextPath() %>/home"
               class="<%= request.getRequestURI().contains("/home")
                         ? "active" : "" %>">
                Home
            </a>
        </li>

        <!-- PRODUCTS -->
        <li>
            <a href="<%= request.getContextPath() %>/products"
               class="<%= request.getRequestURI().contains("/products")
                         ? "active" : "" %>">
                Products
            </a>
        </li>

        <!-- MEN -->
        <li>
            <a href="<%= request.getContextPath() %>/products?category=1"
               class="<%= request.getQueryString() != null &&
                         request.getQueryString().contains("category=1")
                         ? "active" : "" %>">
                Men
            </a>
        </li>

        <!-- WOMEN -->
        <li>
            <a href="<%= request.getContextPath() %>/products?category=2"
               class="<%= request.getQueryString() != null &&
                         request.getQueryString().contains("category=2")
                         ? "active" : "" %>">
                Women
            </a>
        </li>

        <!-- KIDS -->
        <li>
            <a href="<%= request.getContextPath() %>/products?category=3"
               class="<%= request.getQueryString() != null &&
                         request.getQueryString().contains("category=3")
                         ? "active" : "" %>">
                Kids
            </a>
        </li>

        <!-- NEW ARRIVALS -->
        <li>
            <a href="<%= request.getContextPath() %>/new-arrivals"
               class="<%= request.getRequestURI().contains("/new-arrivals")
                         ? "active" : "" %>">
                New Arrivals
            </a>
        </li>

        <!-- BEST SELLERS -->
        <li>
            <a href="<%= request.getContextPath() %>/best-sellers"
               class="<%= request.getRequestURI().contains("/best-sellers")
                         ? "active" : "" %>">
                Best Sellers
            </a>
        </li>

        <!-- OFFERS -->
        <li>
            <a href="<%= request.getContextPath() %>/offers"
               class="<%= request.getRequestURI().contains("/offers")
                         ? "active" : "" %>">
                Offers
            </a>
        </li>

    </ul>

</nav>