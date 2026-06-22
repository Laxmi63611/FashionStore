package com.fashionstore.controller;

import java.io.IOException;
import java.util.List;

import com.fashionstore.dao.ProductDAO;
import com.fashionstore.dao.impl.ProductDAOImpl;
import com.fashionstore.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/admin/products")
public class AdminProductsServlet extends HttpServlet {

    private ProductDAO productDAO;

    @Override
    public void init() {
        productDAO = new ProductDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        if(session == null || session.getAttribute("admin") == null){

            request.getSession().setAttribute(
                "redirectAfterLogin",
                request.getRequestURI());

            response.sendRedirect(
                request.getContextPath() + "/admin/login");

            return;
        }

        List<Product> products =
                productDAO.getAllProducts();

        request.setAttribute("products", products);

        request.getRequestDispatcher(
                "/WEB-INF/views/admin/products.jsp")
                .forward(request, response);
    }
    
}