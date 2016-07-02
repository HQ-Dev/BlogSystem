package com.archy.blog.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.archy.blog.dao.UserDao;
import com.archy.blog.model.User;
import com.archy.blog.service.UserService;

@WebServlet(name = "UserRegisterController", urlPatterns = { "/register" })
public class UserResgisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Connection connection = null;
	Statement statement = null;
	
    public UserResgisterController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		Cookie[] cookies = request.getCookies();
		
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie c = cookies[i];
				if (c.getName().equals("user")) {   // 注意清除cookie privacy history
					
					// 说明请求中含有 cookie ，可以跳过登录阶段，直接进入用户主页
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/templates/userposts.jsp");
					dispatcher.forward(request, response);
					return;   // 结束该段程序，不再执行
				} else {
					request.getRequestDispatcher("/WEB-INF/templates/register.jsp").forward(request, response);
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
													 throws ServletException, IOException {
		response.setCharacterEncoding("utf8");
		String email = request.getParameter("email");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String confirmedPassword = request.getParameter("rePassword");
		User user = new User(userName, password, confirmedPassword, email);
		
		UserService userService = new UserService();
		List<String> errors = new ArrayList<String>();
		
		
		if (userService.register(user, errors)) {
			request.getRequestDispatcher("/WEB-INF/templates/login.jsp").forward(request, response);
		} else {
			// 跳转到错误页面
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/WEB-INF/templates/errors.jsp").forward(request, response);
		}
	}
	
}
