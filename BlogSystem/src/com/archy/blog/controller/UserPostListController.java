package com.archy.blog.controller;

import java.io.File;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.archy.blog.model.Post;
import com.archy.blog.model.User;

@WebServlet("/userPost")
public class UserPostListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection connection = null;
	Statement statement = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
		//getPostsFromLocal(request);
		User user = (User) request.getSession().getAttribute("user");
		List<Post> posts = new ArrayList<>();
		
		posts = getPostsFromMysql(user, posts);
		
		request.setAttribute("posts", posts);
		
		// 路径一定要写对，路径错误就会找不到文件
		RequestDispatcher dispatcher = request.getRequestDispatcher("/static/templates/userposts.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	
	// 用过数据库获得博文s
	private ArrayList<Post> getPostsFromMysql(User user, List<Post> posts) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/blogSystem"
					+ "?useUnicode=true&characterEncoding=utf8", "root", "123456");
			statement = connection.createStatement();
			
			int creator = (int) user.getUserId();
			
			ResultSet resultSet = statement.executeQuery("select * from post where creator=" + creator);
			while(resultSet.next()){
				Post post = new Post();
				post.setPostId(resultSet.getLong("postId"));
				post.setTitle(resultSet.getString("title"));
				post.setContent(resultSet.getString("content"));
				post.setCreatedDate(resultSet.getString("createdDate"));
				
				posts.add(post);
			}
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
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
		
		return  (ArrayList<Post>) posts;
	}
	
	
	
	
	
	
	// TreeMap 排序用，因为希望信息的日期越近的排在越上面
	private class DateComparator implements Comparator<Date> {
		@Override
		public int compare(Date d1, Date d2) {
			return  -d1.compareTo(d2);
		}
	}
	private DateComparator comparator = new DateComparator();
	
	private Map<Date, String> getPostsFromLocal(HttpServletRequest request) {
		// 获取用户名为获得数据的地址做准备
		User user = (User) request.getSession().getAttribute("user");
		String userName = user.getUserName();
		
		String title = null;
		String createdDate = null; 
		
		File userPostsData = new File("/home/archy/新的项目包/天码营/BlogSystem/WebContent/WEB-INF/UserInfoData/" + userName);
		String[] txts = userPostsData.list();
		
		Map<Date, String> posts = new TreeMap<Date,String>(comparator);
		for (String txt : txts) {
			title = txt.substring(txt.indexOf('-')+1, txt.indexOf(".txt"));
			createdDate = txt.substring(0, txt.indexOf('-'));
			
			posts.put(new Date(Long.parseLong(createdDate)), title); 
		}
		
		request.getSession().setAttribute("postsMap", posts);
		
		return posts;
	}
	
	

}
