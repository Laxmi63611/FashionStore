package com.fashionstore.controller;

import com.fashionstore.dao.ProductDAO;
import com.fashionstore.dao.impl.ProductDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/new-arrivals")
public class NewArrivalServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProductDAO dao = new ProductDAOImpl();

        request.setAttribute("products", dao.getNewArrivals());

        request.getRequestDispatcher("/WEB-INF/views/products.jsp")
               .forward(request, response);
    }
}
