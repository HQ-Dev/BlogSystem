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


@WebServlet("/createPost")
public class UserPostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Connection connection = null;
	Statement statement = null;
	
    public UserPostController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 1.获取博文的 title 和 content, 用户的用户名，创建文章时间
	    String title = request.getParameter("title");
		String content = request.getParameter("content");
		User user = (User) request.getSession().getAttribute("user");
		long creator = user.getUserId();
		String userName = user.getUserName();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String createdDate = dateFormat.format(new Date());
		
		Post post = new Post(title, content, createdDate);
		request.setAttribute("post", post);
		
		// 2.调用方法存储文章到指定文件夹
		if (title != null && title.length() != 0 && content != null && content.length() != 0) {
			//addPost(title, content, userName, createDate);
			addPostWithMysql(title, content, creator, createdDate);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/static/templates/post.jsp");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/static/templates/createPost.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	
	private void addPostWithMysql(String title, String content, Long creator, String createdDate) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/blogSystem"
					+ "?useUnicode=true&characterEncoding=utf8", "root", "123456");
			statement = connection.createStatement();
			
			statement.executeUpdate
					(String.format("INSERT INTO `post` (`title`, `content`, `creator`, `createdDate`) "
							+ "VALUES ('%s', '%s', '%d', '%s');", title,content,creator,createdDate));
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
				if (statement != null)
					statement.close();
			} catch (SQLException ignore) {
				// ignore
			}
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
