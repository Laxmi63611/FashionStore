package com.fashionstore.controller;

import com.fashionstore.dao.ProductDAO;
import com.fashionstore.dao.ProductSizeDAO;
import com.fashionstore.dao.impl.ProductDAOImpl;
import com.fashionstore.dao.impl.ProductSizeDAOImpl;
import com.fashionstore.model.Product;
import com.fashionstore.model.ProductSize;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/product-details")
public class ProductDetailsServlet extends HttpServlet {

    private ProductDAO productDAO = new ProductDAOImpl();
    private ProductSizeDAO productSizeDAO = new ProductSizeDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");

        if (idParam == null || idParam.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/products");
            return;
        }

        try {
            int productId = Integer.parseInt(idParam);

            // Fetch product
            Product product = productDAO.getProductById(productId);

            if (product == null) {
                response.sendRedirect(request.getContextPath() + "/products");
                return;
            }

            // Fetch sizes for this product
            List<ProductSize> sizes = productSizeDAO.getSizesByProductId(productId);

            // Send both to JSP
            request.setAttribute("product", product);
            request.setAttribute("sizes", sizes);

            request.getRequestDispatcher("/WEB-INF/views/product_details.jsp")
                   .forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/products");
        }
    }
}