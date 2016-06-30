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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UserRegisterController", urlPatterns = { "/register" })
public class UserResgisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Connection connection = null;
	Statement statement = null;
	
    public UserResgisterController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
													 throws ServletException, IOException {
		response.setCharacterEncoding("utf8");
		String email = request.getParameter("email");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String confirmedPassword = request.getParameter("rePassword");
		String avatar = "/blogSystem/static/templates/img/avatat-default.jpg";
		// next
		List<String> errors = new ArrayList<String>();
		
		// 1. 检验注册邮箱是否合规
		if (isInvalidEmail(email)) {
			errors.add("未填写邮箱或者邮箱格式不正确！");
		}
		// 2. 判断用户名或者邮箱是否已经注册过
		if (isEmailOrUserExist(email,userName)) {
			errors.add("填写的邮箱或者用户名已经被注册，请使用别的用户名或者邮箱！");
		}
		// 3. 判断密码的格式
		if (isInvalidPassword(password, confirmedPassword)) {
			errors.add("请确认密码格式并再次确认密码！");
		}
		
		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			// 跳转到错误页面
			request.getRequestDispatcher("/static/templates/errors.jsp").forward(request, response);
		} else 
		{
			// 没有错误，则将注册信息存储到数据库表中 user ，并跳转到登录界面
			if (saveRegisterUser(email, userName, password, avatar) != 0) {
				// 存储用户成功
				request.getRequestDispatcher("/static/templates/login.jsp").forward(request, response);
			} else {
				// 存储用户失败
				errors.add("存储用户失败!");
				request.setAttribute("errors", errors);
				request.getRequestDispatcher("/static/templates/errors.jsp").forward(request, response);
			}
		}
	}
	
	
	
	/*
	 * 写各类与数据库交互方法，来供注册控制器调用 
	 */
	
	// 判断邮箱格式是否正确的方法
	private boolean isInvalidEmail(String email) {
		return email == null
				||
				!email.matches("^[_a-z0-9-]+([.][_a-z0-9]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$");
	}
	
	// 判断邮箱是否已经存在
	private boolean isEmailOrUserExist(String email, String userName) {
		boolean isExist = false;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = 
					DriverManager.getConnection("jdbc:mysql://localhost:3306/blogSystem"
							+ "?useUnicode=true&characterEncoding=utf8", "root", "123456");
			statement = connection.createStatement();
			
			// 有结果则返回true, 无结果则返回 false
			ResultSet rs = statement.executeQuery("select * from `user`");
			while (rs.next()) {
				isExist = email.equals(rs.getString("email"))
						|| userName.equals(rs.getString("userName"));
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch(SQLException ignore) {
				// 
			}
		}
		return isExist;
	}
	
	// 判断密码的有效性
	private boolean isInvalidPassword(String password, String confirmedPassword) {
		return password == null || password.length() < 6 ||
				password.length() > 16 || !password.equals(confirmedPassword);
	}
	
	// 存储注册用户到数据库
	private int saveRegisterUser(String email,String userName,String password,String avatar) {
		int result = 0;
		try {
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = 
					DriverManager.getConnection("jdbc:mysql://localhost:3306/blogSystem"
							+ "?useUnicode=true&characterEncoding=utf8", "root", "123456");
			statement = connection.createStatement();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String registerDate = sdf.format(new Date());
			
			result = statement.executeUpdate(String.format("INSERT INTO "
					+ "`user` (`userName`, `password`, `email`, `registeredDate`,`avatar`)"
					+ " VALUES ('%s', '%s', '%s', '%s', '%s')", userName,password,email,registerDate,avatar));
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException ignore) {
				//
			}
		}
		return result;
	}
	
}
