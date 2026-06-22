package com.fashionstore.controller;

import com.fashionstore.dao.impl.ProductDAOImpl;
import com.fashionstore.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request,
	                     HttpServletResponse response)
	        throws ServletException, IOException {

	    ProductDAOImpl dao = new ProductDAOImpl();

	    // =========================
	    // GET FILTER VALUES
	    // =========================

	    String categoryStr = request.getParameter("category");
	    String minPriceStr = request.getParameter("minPrice");
	    String maxPriceStr = request.getParameter("maxPrice");
	    String keyword = request.getParameter("keyword");

	    int categoryId = 0;

	    double minPrice = 0;

	    double maxPrice = 0;

	    // =========================
	    // CATEGORY
	    // =========================

	    if(categoryStr != null &&
	       !categoryStr.isEmpty()) {

	        categoryId =
	        Integer.parseInt(categoryStr);
	    }

	    // =========================
	    // MIN PRICE
	    // =========================

	    if(minPriceStr != null &&
	       !minPriceStr.isEmpty()) {

	        minPrice =
	        Double.parseDouble(minPriceStr);
	    }

	    // =========================
	    // MAX PRICE
	    // =========================

	    if(maxPriceStr != null &&
	       !maxPriceStr.isEmpty()) {

	        maxPrice =
	        Double.parseDouble(maxPriceStr);
	    }

	    // =========================
	    // KEYWORD
	    // =========================

	    if(keyword == null) {

	        keyword = "";
	    }

	    // =========================
	    // FILTER PRODUCTS
	    // =========================

	    List<Product> products =
	            dao.filterProducts(
	                    categoryId,
	                    minPrice,
	                    maxPrice,
	                    keyword
	            );

	    // =========================
	    // SEND TO JSP
	    // =========================

	    request.setAttribute(
	            "products",
	            products
	    );

	    request.getRequestDispatcher(
	            "WEB-INF/views/products.jsp"
	    ).forward(request, response);
	}
}
