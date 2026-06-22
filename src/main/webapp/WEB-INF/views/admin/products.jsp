<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.fashionstore.model.Product" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Products</title>

<style>

body{
    font-family:Arial;
    margin:20px;
}

table{
    width:100%;
    border-collapse:collapse;
}

th,td{
    border:1px solid #ddd;
    padding:10px;
    text-align:center;
}

th{
    background:#222;
    color:white;
}

.btn{
    padding:6px 10px;
    text-decoration:none;
    color:white;
}

.edit{
    background:green;
}

.delete{
    background:red;
}

.add{
    background:black;
    padding:10px;
    color:white;
    text-decoration:none;
}

</style>

</head>

<body>

<h2>Product Management</h2>

<a href="<%=request.getContextPath()%>/admin/add-product">

    <button>
        Add Product
    </button>

</a>

<br><br>

<table>

<tr>
    <th>ID</th>
    <th>Name</th>
    <th>Price</th>
    <th>Discount</th>
    <th>Category</th>
    <th>Edit</th>
    <th>Delete</th>
</tr>

<%
List<Product> products =
    (List<Product>) request.getAttribute("products");

if(products != null){

    for(Product p : products){
%>

<tr>
    <td><%= p.getProductId() %></td>
    <td><%= p.getProductName() %></td>
    <td><%= p.getPrice() %></td>
    <td><%= p.getDiscountPercent() %>%</td>
    <td><%= p.getCategoryId() %></td>

    <td>
        <a class="btn edit"
           href="<%=request.getContextPath()%>/admin/edit-product?id=<%=p.getProductId()%>">
           Edit
        </a>
    </td>

    <td>
        <a class="btn delete"
           href="<%=request.getContextPath()%>/admin/delete-product?id=<%=p.getProductId()%>">
           Delete
        </a>
    </td>
</tr>

<%
    }
}
%>

</table>
