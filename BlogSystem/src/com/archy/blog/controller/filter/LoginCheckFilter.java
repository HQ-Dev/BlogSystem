package com.archy.blog.controller.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.archy.blog.model.User;


@WebFilter(urlPatterns={"/WEB-INF/templates/createPost.jsp",
		"/WEB-INF/templates/post.jsp","/WEB-INF/templates/userList.jsp",
		"/WEB-INF/templates/userposts.jsp"})
public class LoginCheckFilter implements Filter {

    public LoginCheckFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request2 = (HttpServletRequest) request;
		Cookie[] cookies = request2.getCookies();
		User user = (User) request2.getSession().getAttribute("user");
		
		if (user == null || cookies == null) {
			// 未登陆则跳转到登录界面进行登录
			HttpServletResponse response2 = (HttpServletResponse) response;
			String next = request2.getRequestURI();
			request2.getSession().setAttribute("next", next);
			response2.sendRedirect("/BlogSystem/login");
		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
