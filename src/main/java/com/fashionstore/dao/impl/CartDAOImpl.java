package com.fashionstore.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fashionstore.dao.CartDAO;
import com.fashionstore.model.CartItem;
import com.fashionstore.model.Product;
import com.fashionstore.util.DBConnection;

public class CartDAOImpl implements CartDAO {

    // NO stored connection — every method gets a fresh one

    @Override
    public boolean addToCart(int userId, int productId, int quantity) {

        ProductDAOImpl productDAO = new ProductDAOImpl();
        Product product = productDAO.getProductById(productId);

        if (product == null) return false;

        double totalPrice = product.getPrice() * quantity;

        String sql = "INSERT INTO cart_items(user_id, product_id, quantity, total_price) VALUES(?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, productId);
            ps.setInt(3, quantity);
            ps.setDouble(4, totalPrice);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<CartItem> getCartItems(int userId) {
        List<CartItem> list = new ArrayList<>();

        String sql = "SELECT c.cart_item_id, c.quantity, c.total_price, " +
                     "p.product_id, p.product_name, p.price, p.image_url " +
                     "FROM cart_items c " +
                     "JOIN products p ON c.product_id = p.product_id " +
                     "WHERE c.user_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setPrice(rs.getDouble("price"));
                product.setImageUrl(rs.getString("image_url"));

                CartItem item = new CartItem();
                item.setCartItemId(rs.getInt("cart_item_id"));
                item.setQuantity(rs.getInt("quantity"));
                item.setTotalPrice(rs.getDouble("total_price"));
                item.setProduct(product);

                list.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public boolean updateQuantity(int cartItemId, int quantity) {

        // First get the product price for this cart item
        String getSql = "SELECT p.price FROM cart_items c " +
                        "JOIN products p ON c.product_id = p.product_id " +
                        "WHERE c.cart_item_id = ?";

        double price = 0;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement getPs = con.prepareStatement(getSql)) {

            getPs.setInt(1, cartItemId);
            ResultSet rs = getPs.executeQuery();

            if (rs.next()) {
                price = rs.getDouble("price");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        double totalPrice = price * quantity;

        String sql = "UPDATE cart_items SET quantity = ?, total_price = ? WHERE cart_item_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, quantity);
            ps.setDouble(2, totalPrice);
            ps.setInt(3, cartItemId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean removeCartItem(int cartItemId) {

        String sql = "DELETE FROM cart_items WHERE cart_item_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, cartItemId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public double getCartTotal(int userId) {

        String sql = "SELECT COALESCE(SUM(total_price), 0) AS total FROM cart_items WHERE user_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble("total");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    // NEW METHOD — clears all cart items for a user after order is placed
    public boolean clearCart(int userId) {

        String sql = "DELETE FROM cart_items WHERE user_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}