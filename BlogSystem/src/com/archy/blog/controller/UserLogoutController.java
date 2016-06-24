package com.archy.blog.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserLogoutController
 */
@WebServlet("/logout.do")
public class UserLogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private final String HOMEPAGE = "/BlogSystem/homepage.jsp";
	
    public UserLogoutController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getSession().getAttribute("user") != null) {
			request.getSession().invalidate();
		}
		response.sendRedirect(HOMEPAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
