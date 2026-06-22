<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Product</title>

<style>

body{
    font-family:Arial,sans-serif;
    padding:30px;
}

.container{
    width:600px;
    margin:auto;
}

input,textarea,select{
    width:100%;
    padding:10px;
    margin-bottom:15px;
}

button{
    padding:10px 20px;
    background:green;
    color:white;
    border:none;
    cursor:pointer;
}

.checkbox-group{
    margin:15px 0;
}

.checkbox-group label{
    display:block;
    margin-bottom:10px;
}

</style>

</head>
<body>

<div class="container">

<h2>Add Product</h2>

<form action="<%=request.getContextPath()%>/admin/add-product"
      method="post">

    <label>Product Name</label>

    <input type="text"
           name="productName"
           required>

    <label>Description</label>

    <textarea name="description"
              rows="4"></textarea>

    <label>Price</label>

    <input type="number"
           step="0.01"
           name="price"
           required>

    <label>Discount Percent</label>

    <input type="number"
           step="0.01"
           name="discountPercent">

    <label>Image URL</label>

    <input type="text"
           name="imageUrl">

    <label>Category</label>

    <select name="categoryId">

        <option value="1">Men</option>
        <option value="2">Women</option>
        <option value="3">Kids</option>

    </select>

    <div class="checkbox-group">

        <label>
            <input type="checkbox"
                   name="newArrival">
            New Arrival
        </label>

        <label>
            <input type="checkbox"
                   name="bestSeller">
            Best Seller
        </label>

        <label>
            <input type="checkbox"
                   name="offer">
            Offer Product
        </label>

    </div>

    <button type="submit">
        Add Product
    </button>

</form>

<br>

<a href="<%=request.getContextPath()%>/admin/products">
Back to Products
</a>

</div>

</body>
</html>