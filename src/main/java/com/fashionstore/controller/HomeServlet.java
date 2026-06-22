package com.fashionstore.controller;

import com.fashionstore.dao.impl.ProductDAOImpl;
import com.fashionstore.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        ProductDAOImpl productDAO = new ProductDAOImpl();

        List<Product> productList = productDAO.getAllProducts();

        List<Product> featuredProducts = productList;

        if (productList != null && productList.size() > 5) {
            featuredProducts = productList.subList(0, 5);
        }

        request.setAttribute("featuredProducts", featuredProducts);

        request.getRequestDispatcher("/WEB-INF/views/home.jsp")
               .forward(request, response);
    }
}