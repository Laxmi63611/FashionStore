package com.fashionstore.controller;

import com.fashionstore.dao.impl.ProductDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/best-sellers")
public class BestSellerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProductDAOImpl productDAO = new ProductDAOImpl();

        request.setAttribute("products",
                productDAO.getBestSellers());

        request.getRequestDispatcher("/WEB-INF/views/products.jsp")
               .forward(request, response);
    }
}
