package com.fashionstore.controller;

import java.io.IOException;

import com.fashionstore.dao.ProductDAO;
import com.fashionstore.dao.impl.ProductDAOImpl;
import com.fashionstore.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/admin/edit-product")
public class EditProductServlet extends HttpServlet {

    private ProductDAO productDAO;

    @Override
    public void init() {
        productDAO = new ProductDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        int productId =
                Integer.parseInt(request.getParameter("id"));

        Product product =
                productDAO.getProductById(productId);

        request.setAttribute("product", product);

        request.getRequestDispatcher(
                "/WEB-INF/views/admin/edit-product.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        Product product = new Product();

        product.setProductId(
                Integer.parseInt(request.getParameter("productId")));

        product.setCategoryId(
                Integer.parseInt(request.getParameter("categoryId")));

        product.setProductName(
                request.getParameter("productName"));

        product.setDescription(
                request.getParameter("description"));

        product.setPrice(
                Double.parseDouble(request.getParameter("price")));

        product.setDiscountPercent(
                Double.parseDouble(request.getParameter("discount")));

        product.setImageUrl(
                request.getParameter("imageUrl"));

        product.setActive(true);

        product.setNewArrival(
                request.getParameter("newArrival") != null);

        product.setBestSeller(
                request.getParameter("bestSeller") != null);

        product.setOffer(
                request.getParameter("offer") != null);

        productDAO.updateProduct(product);

        response.sendRedirect(
                request.getContextPath()
                + "/admin/products");
    }
}
