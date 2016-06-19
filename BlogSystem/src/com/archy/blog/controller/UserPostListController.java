package com.archy.blog.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.archy.blog.model.Data;
import com.archy.blog.model.Post;

/**
 * Servlet implementation class UserPostController
 */
@WebServlet("/userPost")
public class UserPostListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
		List<Post> posts = (ArrayList<Post>) Data.getPosts();
		
		request.setAttribute("posts", posts);
		
		// 路径一定要写对，路径错误就会找不到文件
		RequestDispatcher dispatcher = request.getRequestDispatcher("/static/templates/userposts.jsp");
		
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
		doGet(request, response);
	}

}
