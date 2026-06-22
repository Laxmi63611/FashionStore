package com.fashionstore.controller;

import java.io.IOException;

import com.fashionstore.dao.ProductDAO;
import com.fashionstore.dao.impl.ProductDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/admin/delete-product")
public class DeleteProductServlet extends HttpServlet {

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

        productDAO.deleteProduct(productId);

        response.sendRedirect(
                request.getContextPath()
                + "/admin/products");
    }
}
