package com.fashionstore.dao;

import com.fashionstore.model.ProductSize;
import java.util.List;

public interface ProductSizeDAO {
    List<ProductSize> getSizesByProductId(int productId);
}
