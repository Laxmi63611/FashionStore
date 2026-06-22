<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.fashionstore.model.Product"%>

<%
Product p = (Product) request.getAttribute("product");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Product</title>

<style>

body{
    font-family:Arial;
    margin:20px;
}

form{
    width:600px;
}

input,textarea,select{
    width:100%;
    padding:10px;
    margin-bottom:10px;
}

button{
    padding:10px 20px;
    background:green;
    color:white;
    border:none;
}

</style>

</head>

<body>

<h2>Edit Product</h2>

<form method="post"
      action="<%=request.getContextPath()%>/admin/edit-product">

<input type="hidden"
       name="productId"
       value="<%=p.getProductId()%>">

Name

<input type="text"
       name="productName"
       value="<%=p.getProductName()%>">

Description

<textarea name="description"><%=p.getDescription()%></textarea>

Price

<input type="number"
       step="0.01"
       name="price"
       value="<%=p.getPrice()%>">

Discount

<input type="number"
       step="0.01"
       name="discount"
       value="<%=p.getDiscountPercent()%>">

Category

<select name="categoryId">

<option value="1"
<%=p.getCategoryId()==1?"selected":""%>>
Men
</option>

<option value="2"
<%=p.getCategoryId()==2?"selected":""%>>
Women
</option>

<option value="3"
<%=p.getCategoryId()==3?"selected":""%>>
Kids
</option>

</select>

Image URL

<input type="text"
       name="imageUrl"
       value="<%=p.getImageUrl()%>">

<label>
<input type="checkbox"
       name="newArrival"
       <%=p.isNewArrival()?"checked":""%>>
New Arrival
</label>

<br><br>

<label>
<input type="checkbox"
       name="bestSeller"
       <%=p.isBestSeller()?"checked":""%>>
Best Seller
</label>

<br><br>

<label>
<input type="checkbox"
       name="offer"
       <%=p.isOffer()?"checked":""%>>
Offer Product
</label>

<br><br>

<button type="submit">
Update Product
</button>

</form>

</body>
</html>