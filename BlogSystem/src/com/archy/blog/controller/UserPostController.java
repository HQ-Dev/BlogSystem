package com.archy.blog.controller;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.archy.blog.model.User;


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
		request.setCharacterEncoding("utf-8");
		// 1.获取博文的 title 和 content, 用户的用户名，创建文章时间
	    String title = request.getParameter("title");
		String content = request.getParameter("content");
		User user = (User) request.getSession().getAttribute("user");
		String userName = user.getUserName();
		//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		//String createDate = dateFormat.format(new Date());
		String createDate = String.valueOf(new Date().getTime());
		request.getSession().setAttribute("title", title);
		request.getSession().setAttribute("createDate", createDate);
		
		// 2.调用方法存储文章到指定文件夹
		if (title != null && title.length() != 0 && content != null && content.length() != 0) {
			addPost(title, content, userName, createDate);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/postInfo.do");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/static/templates/createPost.jsp");
			dispatcher.forward(request, response);
		}
	}
	
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
	
}
