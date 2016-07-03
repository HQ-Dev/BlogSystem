package com.archy.blog.controller;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.archy.blog.model.Post;
import com.archy.blog.model.User;
import com.archy.blog.service.PostService;


@WebServlet("/createPost")
public class UserPostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserPostController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/templates/createPost.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 1.获取博文的 title 和 content
	    String title = request.getParameter("title");
		String content = request.getParameter("content");
		User user = (User) request.getSession().getAttribute("user");
		PostService postService = new PostService();
		Post post = new Post();
		
		if (title != null && title.length() != 0 && content != null && content.length() != 0) {
			
			if (postService.create(title, content, user)) { // 创建并存储博文成功
				if (!postService.findByCreator(user.getUserId()).isEmpty()) { // 获取博文成功
					int lastIndex = postService.findByCreator(user.getUserId()).size() - 1;
					post = postService.findByCreator(user.getUserId()).get(lastIndex);
					request.getSession().setAttribute("post", post);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/templates/post.jsp");
					dispatcher.forward(request, response);
				}
			} 
			
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/templates/createPost.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	
	
	/*
	private void addPost(String title, String content, String userName, String createDate) 
			throws IOException {
		// 文件地址 + 文件名
		String fileName = "/home/archy/新的项目包/天码营/BlogSystem/WebContent/WEB-INF/UserInfoData/" 
							+ userName + "/" + createDate + "-" + title + ".txt";
		BufferedWriter writer = new BufferedWriter(
					new OutputStreamWriter(
							new FileOutputStream(fileName),"UTF-8"));
		writer.write(content);
		writer.close();
	}
	*/
}
