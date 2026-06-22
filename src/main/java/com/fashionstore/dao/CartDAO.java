package com.fashionstore.dao;

import java.util.List;
import com.fashionstore.model.CartItem;

public interface CartDAO {

    boolean addToCart(int userId, int productId, int quantity);

    List<CartItem> getCartItems(int userId);

    boolean updateQuantity(int cartItemId, int quantity);

    boolean removeCartItem(int cartItemId);

    double getCartTotal(int userId);
}