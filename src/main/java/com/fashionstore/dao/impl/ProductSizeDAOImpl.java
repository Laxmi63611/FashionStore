package com.fashionstore.dao.impl;

import com.fashionstore.dao.ProductSizeDAO;
import com.fashionstore.model.ProductSize;
import com.fashionstore.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductSizeDAOImpl implements ProductSizeDAO {

    @Override
    public List<ProductSize> getSizesByProductId(int productId) {

        List<ProductSize> sizes = new ArrayList<>();

        String sql = "SELECT * FROM product_sizes WHERE product_id = ? ORDER BY product_size_id";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ProductSize size = new ProductSize();
                    size.setProductSizeId(rs.getInt("product_size_id"));
                    size.setProductId(rs.getInt("product_id"));
                    size.setSizeLabel(rs.getString("size_label"));
                    size.setStockQuantity(rs.getInt("stock_quantity"));
                    size.setSkuCode(rs.getString("sku_code"));
                    size.setAvailable(rs.getInt("is_available") == 1);
                    sizes.add(size);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sizes;
    }
}