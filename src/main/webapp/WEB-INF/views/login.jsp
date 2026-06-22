<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login - Fashion Store</title>

    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/auth.css">
</head>
<body>

<%@ include file="partials/navbar.jsp" %>

<div class="auth-page">
    <div class="auth-container">

        <h2>Login</h2>
        <p class="auth-subtitle">Welcome back! Please login to continue.</p>

        <%
            String error = (String) request.getAttribute("error");
            if (error != null) {
        %>
            <div class="auth-error"><%= error %></div>
        <%
            }
        %>

        <form action="<%= request.getContextPath() %>/login" method="post" class="auth-form">

            <div class="form-group">
                <label>Email</label>
                <input type="email" name="email" placeholder="Enter your email" required>
            </div>

            <div class="form-group">
                <label>Password</label>
                <input type="password" name="password" placeholder="Enter your password" required>
            </div>

            <button type="submit" class="auth-btn">Login</button>
        </form>

        <p class="auth-link">
            Don't have an account?
            <a href="<%= request.getContextPath() %>/register">Register here</a>
        </p>

    </div>
</div>

<%@ include file="partials/footer.jsp" %>

</body>
</html>