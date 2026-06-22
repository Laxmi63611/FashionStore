package com.fashionstore.controller;

import java.io.IOException;

import com.fashionstore.dao.ProductDAO;
import com.fashionstore.dao.impl.ProductDAOImpl;
import com.fashionstore.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/admin/add-product")
public class AdminAddProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

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

        if(session == null ||
           session.getAttribute("admin") == null) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/admin/login");

            return;
        }

        request.getRequestDispatcher(
                "/WEB-INF/views/admin/add-product.jsp")
                .forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        Product product = new Product();

        product.setProductName(
                request.getParameter("productName"));

        product.setDescription(
                request.getParameter("description"));

        product.setPrice(
                Double.parseDouble(
                request.getParameter("price")));

        product.setDiscountPercent(
                Double.parseDouble(
                request.getParameter("discountPercent")));

        product.setImageUrl(
                request.getParameter("imageUrl"));

        product.setCategoryId(
                Integer.parseInt(
                request.getParameter("categoryId")));

        product.setActive(true);

        product.setNewArrival(
                request.getParameter("newArrival") != null);

        product.setBestSeller(
                request.getParameter("bestSeller") != null);

        product.setOffer(
                request.getParameter("offer") != null);

        productDAO.addProduct(product);

        response.sendRedirect(
                request.getContextPath()
                + "/admin/products");
    }
}