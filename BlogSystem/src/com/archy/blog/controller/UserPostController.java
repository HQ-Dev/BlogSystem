package com.archy.blog.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.archy.blog.model.Post;


@WebServlet("/createPost")
public class UserPostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserPostController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		Post post = new Post(title, content);
		
		request.setAttribute("post", post);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/static/templates/post.jsp");
		
		dispatcher.forward(request, response);
	}

}
