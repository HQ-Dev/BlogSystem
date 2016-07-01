package com.archy.blog.controller;

import java.io.BufferedReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.archy.blog.model.Post;
import com.archy.blog.model.User;

@WebServlet(description = "处理创建博文成功后如何显示博文详情页面的控制器", urlPatterns = { "/postInfo.do" })
public class UserPostInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Connection connection = null;
	Statement statement = null;
	
	//private final String USERS = "/home/archy/新的项目包/天码营/BlogSystem/WebContent/WEB-INF/UserInfoData/";
	
    public UserPostInfoController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		String userName = user.getUserName();
		String createDate = (String) request.getSession().getAttribute("createDate");
		String title = (String) request.getSession().getAttribute("title");
		Post post = null;
		
		// 点击博文列表页面获得指定文章的文章ID
		String	postId = request.getParameter("postId");
		post = getPost(postId);
		
		request.getSession().setAttribute("post", post);
		
		//readPostFromLocal(userName, createDate, title, request);
		
		request.getRequestDispatcher("/static/templates/post.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	// 从数据库读取博文
	private Post getPost(String postId) {
		Post post = new Post();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/blogSystem"
					+ "?useUnicode=true&characterEncoding=utf8", "root", "123456");
			statement = connection.createStatement();
			
			ResultSet resultSet = statement.executeQuery("select * from post where postId=" + postId);
			while (resultSet.next()) {
				post.setPostId(resultSet.getLong("postId"));
				post.setTitle(resultSet.getString("title"));
				post.setContent(resultSet.getString("content"));
				post.setCreatedDate(resultSet.getString("createdDate"));
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
				if (statement != null)
					statement.close();
			} catch (SQLException ignore) {
				//
			}
		}
		return post;
	}
	
	
	
	
	/*
	private void readPostFromLocal(String userName, String createDate, String title, HttpServletRequest request) 
			throws IOException {
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(new FileInputStream(USERS + userName + "/" + createDate + "-" + title
						+ ".txt"), "utf-8"));
		String text = null;
		StringBuilder builder = new StringBuilder();
		while ((text = reader.readLine()) != null) {
			builder.append(text);
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date(Long.parseLong(createDate));
		String newCreateDate = dateFormat.format(date);
		String content = builder.toString();
		reader.close();
		Post post = new  Post(title, content, newCreateDate);
		request.getSession().setAttribute("post", post);
	}
	*/
}
