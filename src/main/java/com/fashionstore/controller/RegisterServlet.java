package com.fashionstore.controller;

import java.io.IOException;

import com.fashionstore.dao.impl.UserDAOImpl;
import com.fashionstore.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/views/register.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	String fullName = request.getParameter("fullName");
    	String email = request.getParameter("email");
    	String phone = request.getParameter("phone");
    	String password = request.getParameter("password");
    	String confirmPassword = request.getParameter("confirmPassword");
    	String gender = request.getParameter("gender");
    	String address = request.getParameter("address");

    	// Password validation
    	if (!password.equals(confirmPassword)) {

    	    request.setAttribute("error",
    	            "Password and Confirm Password do not match");

    	    request.getRequestDispatcher("/WEB-INF/views/register.jsp")
    	           .forward(request, response);

    	    return;
    	}

    	User user = new User();
    	user.setFullName(fullName);
    	user.setEmail(email);
    	user.setPhone(phone);
    	user.setPassword(password);
    	user.setGender(gender);
    	user.setAddress(address);

    	UserDAOImpl dao = new UserDAOImpl();

    	boolean status = dao.registerUser(user);

    	if (status) {

    	    response.sendRedirect(
    	            request.getContextPath() + "/login");

    	} else {

    	    request.setAttribute("error",
    	            "Registration failed");

    	    request.getRequestDispatcher("/WEB-INF/views/register.jsp")
    	           .forward(request, response);
    	}
        }
    }
    
