package com.archy.blog.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		
		for (int i = 0; i < cookies.length; i++) {
			Cookie c = cookies[i];
			if (c.getName().equals("user")) {   // 注意清除cookie privacy history
				String user = c.getValue();
				
				// 说明请求中含有 cookie ，可以跳过登录阶段，直接进入用户主页
				RequestDispatcher dispatcher = request.getRequestDispatcher("/static/templates/userposts.jsp");
				dispatcher.forward(request, response);
				//response.sendRedirect("/BlogSystem/static/templates/userposts.jsp");
				return;   // 结束该段程序，不再执行
			}
		}
		
		// 创建一个LoginServlet类，处理/login的URL。如果是GET请求，则显示登陆表单页面login.jsp
		response.sendRedirect("/BlogSystem/static/templates/login.jsp");
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
        	response.sendRedirect("/BlogSystem/static/html/loginFailed.html");
        }
        // 登陆成功的情况下
        else {
        	if (values != null && !values[0].isEmpty()) { // 这里表示用户勾选了Remember Me
        		// 对 cookie 中的值用base64编码加密
        		//String encodeUserName =  ArchyBase64.encode(userName);
        		Cookie cookie = new Cookie("user", userName);
        		
        		cookie.setMaxAge(10 * 60);   // 设置为10分钟 
        		response.addCookie(cookie);
        		request.getSession().setAttribute("user", user);
        		
        		String next = (String) request.getSession().getAttribute("next");
        		if (next == null)
        			response.sendRedirect("/BlogSystem/userPost?username=" + user.getUserName());
        		else 
        			response.sendRedirect(next);
            	return;
        	} else {  
        		// 没有勾选自动登录的处理情况： 不创建 cookie
        		request.getSession().setAttribute("user", user);
        		String next = (String) request.getSession().getAttribute("next");
        		if (next == null)
        			response.sendRedirect("/BlogSystem/userPost?username=" + user.getUserName());
        		else 
        			response.sendRedirect(next);
        	}
        }
			
	}

}
