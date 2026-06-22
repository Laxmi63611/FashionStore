package com.fashionstore.model;

public class Product {

    private int productId;
    private int categoryId;

    private String productName;
    private String description;

    private double price;
    private double discountPercent;

    private String imageUrl;

    private boolean isActive;
    
    private boolean newArrival;
    private boolean bestSeller;
    private boolean offer;

    // =========================
    // DEFAULT CONSTRUCTOR
    // =========================
    public Product() {

    }

    // =========================
    // PARAMETERIZED CONSTRUCTOR
    // =========================
    public Product(int productId,
                   int categoryId,
                   String productName,
                   String description,
                   double price,
                   double discountPercent,
                   String imageUrl,
                   boolean isActive,
                   boolean newArrival,
                   boolean bestSeller,
                   boolean offer) {

        this.productId = productId;
        this.categoryId = categoryId;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.discountPercent = discountPercent;
        this.imageUrl = imageUrl;
        this.isActive = isActive;
        this.newArrival=newArrival;
        this.bestSeller=bestSeller;
        this.offer=offer;
    }

    // =========================
    // GETTERS & SETTERS
    // =========================

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
    public boolean isNewArrival() {
        return newArrival;
    }

    public void setNewArrival(boolean newArrival) {
        this.newArrival = newArrival;
    }

    public boolean isBestSeller() {
        return bestSeller;
    }

    public void setBestSeller(boolean bestSeller) {
        this.bestSeller = bestSeller;
    }

    public boolean isOffer() {
        return offer;
    }

    public void setOffer(boolean offer) {
        this.offer = offer;
    }
}