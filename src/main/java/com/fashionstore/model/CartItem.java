package com.fashionstore.model;

public class CartItem {

    private int cartItemId;
    private int cartId;

    private Product product;

    private int quantity;

    private double totalPrice;

    // Default Constructor
    public CartItem() {
    }

    // Parameterized Constructor
    public CartItem(int cartItemId, int cartId, Product product,
                    int quantity, double totalPrice) {

        this.cartItemId = cartItemId;
        this.cartId = cartId;
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    // Getters and Setters

    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}