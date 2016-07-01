package com.archy.blog.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.archy.blog.model.Data;
import com.archy.blog.model.User;
import com.archy.blog.util.security.ArchyBase64;

@WebServlet("/login")
public class UserLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Connection connection = null;
	Statement statement = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		/*
		 *  直接登录 /login 会检查请求中是否有 cookies ，有的话就跳转到用户界面，没有则跳转到登录界面
		 */
		Cookie[] cookies = request.getCookies();
		
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie c = cookies[i];
				if (c.getName().equals("user")) {   // 注意清除cookie privacy history
					//String user = c.getValue();
					
					// 说明请求中含有 cookie ，可以跳过登录阶段，直接进入用户主页
					RequestDispatcher dispatcher = request.getRequestDispatcher("/static/templates/userposts.jsp");
					dispatcher.forward(request, response);
					//response.sendRedirect("/BlogSystem/static/templates/userposts.jsp");
					return;   // 结束该段程序，不再执行
				}
			}
		} else {
			// 创建一个LoginServlet类，处理/login的URL。如果是GET请求，则显示登陆表单页面login.jsp
			response.sendRedirect("/BlogSystem/static/templates/login.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setCharacterEncoding("utf8");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String[] values = request.getParameterValues("remember-me");
		
		// 数据是否和数据库中相等
		User user = new User();
        
        List<String> errors = new ArrayList<>();
        
        // 1. 查看用户名是否重复,密码是否正确
        if (!isUsernameAndPwInvalid(userName, password, user)) {
        	errors.add("用户名或者密码输入错误！");
        }
        
        if (errors.isEmpty()) {
        	// 登陆成功的情况下
        	if (values != null && !values[0].isEmpty()) { // 这里表示用户勾选了Remember Me
        		// 对 cookie 中的值用base64编码加密
        		//String encodeUserName =  ArchyBase64.encode(userName);
        		Cookie cookie = new Cookie("user", userName);
        		
        		cookie.setMaxAge(10 * 60);   // 设置为10分钟 
        		response.addCookie(cookie);
        		request.getSession().setAttribute("user", user);
        		
        		String next = (String) request.getSession().getAttribute("next");
        		if (next == null)
        			request.getRequestDispatcher("userPost?username=" + user.getUserName()).forward(request, response);
        		else 
        			request.getRequestDispatcher(next).forward(request, response);
            	return;
        	} else {  
        		// 没有勾选自动登录的处理情况： 不创建 cookie
        		request.getSession().setAttribute("user", user);
        		String next = (String) request.getSession().getAttribute("next");
        		if (next == null)
        			request.getRequestDispatcher("userPost?username=" + user.getUserName()).forward(request, response);
        		else 
        			request.getRequestDispatcher(next).forward(request, response);
        	}
        } else {
        	// 登录失败
        	request.setAttribute("errors", errors);
        	request.getRequestDispatcher("/static/templates/errors.jsp").forward(request, response);
        }   
        	
     }	
		
	
	/*
	 * 与数据库进行交互
	 */
	
	private boolean isUsernameAndPwInvalid(String userName,String password,User user) {
		boolean result = false;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/blogSystem"
							+ "?useUnicode=true&characterEncoding=utf8", "root", "123456");
			statement = connection.createStatement();
			
			ResultSet rSet = statement.executeQuery("select * from user");
			
			while (rSet.next()) {
				if (userName.equals(rSet.getString("userName"))
						&& password.equals(rSet.getString("password"))) {
					user.setUserId(rSet.getLong("userId"));
					user.setUserName(rSet.getString("userName"));
					user.setEmail(rSet.getString("email"));
					user.setPassword(rSet.getString("password"));
					user.setAvatar(rSet.getString("avatar"));
					user.setDescription(rSet.getString("description"));
					result = true;
				}
			}
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
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
