package com.fashionstore.controller;

import com.fashionstore.dao.impl.ProductDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/offers")
public class OfferServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProductDAOImpl productDAO = new ProductDAOImpl();

        request.setAttribute("products",
                productDAO.getOfferProducts());

        request.getRequestDispatcher("/WEB-INF/views/products.jsp")
               .forward(request, response);
    }
}
