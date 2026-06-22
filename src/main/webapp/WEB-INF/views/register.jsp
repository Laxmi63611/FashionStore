<%@ page contentType="text/html; charset=UTF-8" %>

<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/auth.css">

<%@ include file="partials/navbar.jsp" %>

<div class="auth-page">
    <div class="auth-container">

    <h2>Create Account</h2>

    <form action="register" method="post">

        <input type="text" name="fullName" placeholder="Full Name" required>

        <input type="email" name="email" placeholder="Email" required>

        <input type="text" name="phone" placeholder="Phone" required>

        <input type="password" name="password" placeholder="Password" required>
        <input type="password"
       name="confirmPassword"
       placeholder="Confirm Password"
       required>

        <select name="gender">
            <option value="">Select Gender</option>
            <option value="Male">Male</option>
            <option value="Female">Female</option>
        </select>

        <textarea name="address" placeholder="Address"></textarea>

        <button type="submit">Register</button>

    </form>

    <p>Already have account? <a href="login">Login</a></p>

</div>

<%@ include file="partials/footer.jsp" %>