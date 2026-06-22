<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Login</title>

<style>

body{
    font-family: Arial;
    background:#f5f5f5;
}

.container{
    width:400px;
    margin:100px auto;
    background:white;
    padding:30px;
    border-radius:10px;
}

input{
    width:100%;
    padding:10px;
    margin:10px 0;
}

button{
    width:100%;
    padding:12px;
    background:#000;
    color:white;
    border:none;
}

.error{
    color:red;
}

</style>

</head>

<body>

<div class="container">

<h2>Admin Login</h2>

<%
String error=(String)request.getAttribute("error");
if(error!=null){
%>

<p class="error"><%=error%></p>

<%
}
%>

<form action="<%= request.getContextPath() %>/admin/login"
      method="post">

<input type="text"
       name="username"
       placeholder="Username">

<input type="password"
       name="password"
       placeholder="Password">

<button type="submit">
Login
</button>

</form>

</div>

</body>
</html>