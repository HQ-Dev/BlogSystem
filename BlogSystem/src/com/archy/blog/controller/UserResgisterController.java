package com.archy.blog.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.archy.blog.model.Data;
import com.archy.blog.model.User;

@WebServlet(name = "UserRegisterController", urlPatterns = { "/register" })
public class UserResgisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserResgisterController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf8");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String userName = request.getParameter("userName");
		String rePassword = request.getParameter("rePassword");
		
		List<User> users = Data.getUsers();
		// 判断注册的邮件用户名不和数据库中的重合
		
		// next
		if (email != "" && password != "" && userName != "" && password.equals(rePassword)) 
		{
			
			User user = new User(userName, password, email);
			users.add(user);
			request.getSession().setAttribute("users", users);
			request.getSession().setAttribute("user", user);
			response.sendRedirect("/BlogSystem/static/templates/userposts.jsp");
		} else {
			response.sendRedirect("/BlogSystem/homepage.jsp");
		}
	}

}
