package com.archy.blog.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.archy.blog.model.Data;
import com.archy.blog.model.User;

@WebServlet("/login")
public class UserLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setCharacterEncoding("utf8");
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String[] values = request.getParameterValues("remember-me");
		
		
		// 数据是否和数据库中相等
		User user = Data.getByUsername(userName);
        if (user == null || !user.getPassword().equals(password)) {
        	response.sendRedirect("/BlogSystem/homepage.jsp");
        } else {
        	request.getSession().setAttribute("user", user);
        	response.sendRedirect("/BlogSystem/userPost?username=" + user.getUserName());
        }
			
		
		
		
	}

}
