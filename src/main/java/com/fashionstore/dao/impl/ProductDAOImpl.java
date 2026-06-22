package com.fashionstore.dao.impl;

import com.fashionstore.dao.ProductDAO;
import com.fashionstore.model.Product;
import com.fashionstore.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    private Connection con;

    // =========================
    // CONSTRUCTOR
    // =========================
    public ProductDAOImpl() {

        con = DBConnection.getConnection();
    }

    // =========================
    // ADD PRODUCT
    // =========================
    @Override
    public boolean addProduct(Product product) {

        boolean status = false;

        try {

        	String sql =
        			"INSERT INTO products(category_id, product_name, description, discount_percent, image_url, is_active, price, is_new_arrival, is_best_seller, is_offer) VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, product.getCategoryId());
            ps.setString(2, product.getProductName());
            ps.setString(3, product.getDescription());
            ps.setDouble(4, product.getDiscountPercent());
            ps.setString(5, product.getImageUrl());
            ps.setBoolean(6, product.isActive());
            ps.setDouble(7, product.getPrice());

            ps.setBoolean(8, product.isNewArrival());
            ps.setBoolean(9, product.isBestSeller());
            ps.setBoolean(10, product.isOffer());

            int rows = ps.executeUpdate();

            status = rows > 0;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return status;
    }

    // =========================
    // GET ALL PRODUCTS
    // =========================
    @Override
    public List<Product> getAllProducts() {

        List<Product> list = new ArrayList<>();

        try {

            String sql =
                    "SELECT * FROM products WHERE is_active=true";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(mapProduct(rs));
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    // =========================
    // GET PRODUCT BY ID
    // =========================
    @Override
    public Product getProductById(int productId) {

        Product product = null;

        try {

            String sql =
                    "SELECT * FROM products WHERE product_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, productId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                product = mapProduct(rs);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return product;
    }

    // =========================
    // UPDATE PRODUCT
    // =========================
    @Override
    public boolean updateProduct(Product product) {

        boolean status = false;

        try {

        	String sql =
        			"UPDATE products SET category_id=?, product_name=?, description=?, discount_percent=?, image_url=?, is_active=?, price=?, is_new_arrival=?, is_best_seller=?, is_offer=? WHERE product_id=?";
            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, product.getCategoryId());
            ps.setString(2, product.getProductName());
            ps.setString(3, product.getDescription());
            ps.setDouble(4, product.getDiscountPercent());
            ps.setString(5, product.getImageUrl());
            ps.setBoolean(6, product.isActive());
            ps.setDouble(7, product.getPrice());

            ps.setBoolean(8, product.isNewArrival());
            ps.setBoolean(9, product.isBestSeller());
            ps.setBoolean(10, product.isOffer());

            ps.setInt(11, product.getProductId());

            int rows = ps.executeUpdate();

            status = rows > 0;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return status;
    }

    // =========================
    // DELETE PRODUCT
    // =========================
    @Override
    public boolean deleteProduct(int productId) {

        boolean status = false;

        try {

            String sql =
                    "DELETE FROM products WHERE product_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, productId);

            int rows = ps.executeUpdate();

            status = rows > 0;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return status;
    }

    // =========================
    // GET PRODUCTS BY CATEGORY
    // =========================
    @Override
    public List<Product> getProductsByCategory(int categoryId) {

        List<Product> list = new ArrayList<>();

        try {

            String sql =
                    "SELECT * FROM products WHERE category_id=? AND is_active=true";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, categoryId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(mapProduct(rs));
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    // =========================
    // SEARCH PRODUCTS
    // =========================
    @Override
    public List<Product> searchProducts(String keyword) {

        List<Product> list = new ArrayList<>();

        try {

            String sql =
                    "SELECT * FROM products WHERE product_name LIKE ? AND is_active=true";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(mapProduct(rs));
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    // =========================
    // PRICE RANGE FILTER
    // =========================
    @Override
    public List<Product> getProductsByPriceRange(double minPrice,
                                                 double maxPrice) {

        List<Product> list = new ArrayList<>();

        try {

            String sql =
                    "SELECT * FROM products WHERE price BETWEEN ? AND ? AND is_active=true";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setDouble(1, minPrice);
            ps.setDouble(2, maxPrice);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(mapProduct(rs));
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    // =========================
    // FILTER PRODUCTS
    // =========================
    @Override
    public List<Product> filterProducts(int categoryId,
                                        double minPrice,
                                        double maxPrice,
                                        String keyword) {

        List<Product> list = new ArrayList<>();

        try {

            String sql =
                    "SELECT * FROM products WHERE is_active=true";

            if (categoryId > 0) {

                sql += " AND category_id=?";
            }

            if (keyword != null && !keyword.isEmpty()) {

                sql += " AND product_name LIKE ?";
            }

            if (minPrice > 0 && maxPrice > 0) {

                sql += " AND price BETWEEN ? AND ?";
            }

            PreparedStatement ps =
                    con.prepareStatement(sql);

            int index = 1;

            if (categoryId > 0) {

                ps.setInt(index++, categoryId);
            }

            if (keyword != null && !keyword.isEmpty()) {

                ps.setString(index++, "%" + keyword + "%");
            }

            if (minPrice > 0 && maxPrice > 0) {

                ps.setDouble(index++, minPrice);
                ps.setDouble(index++, maxPrice);
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(mapProduct(rs));
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }
    
    @Override
    public List<Product> getNewArrivals() {

        List<Product> list = new ArrayList<>();

        String sql = "SELECT * FROM products WHERE is_new_arrival = true";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapProduct(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Product> getBestSellers() {

        List<Product> list = new ArrayList<>();

        String sql = "SELECT * FROM products WHERE is_best_seller = true";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapProduct(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Product> getOfferProducts() {

        List<Product> list = new ArrayList<>();

        String sql = "SELECT * FROM products WHERE is_offer = true";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapProduct(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // =========================
    // MAP RESULTSET → PRODUCT
    // =========================
    private Product mapProduct(ResultSet rs) throws Exception {

        Product p = new Product();

        p.setProductId(rs.getInt("product_id"));

        p.setCategoryId(rs.getInt("category_id"));

        p.setProductName(rs.getString("product_name"));

        p.setDescription(rs.getString("description"));

        p.setPrice(rs.getDouble("price"));

        p.setDiscountPercent(
                rs.getDouble("discount_percent"));

        p.setImageUrl(
                rs.getString("image_url"));

        p.setActive(
                rs.getBoolean("is_active"));
        p.setNewArrival(rs.getBoolean("is_new_arrival"));

        p.setBestSeller(rs.getBoolean("is_best_seller"));

        p.setOffer(rs.getBoolean("is_offer"));
        

        return p;
    }
}