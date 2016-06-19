package com.archy.blog;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RefreshTime
 * 项目要求 servlet 页面现实实时的时间（不断变化），同时需要在页面中现实 urls 上的参数内容
 */
@WebServlet("/RefreshTime")
public class RefreshTime extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RefreshTime() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
						throws ServletException, IOException {
		
		// 设置响应内容类型
		response.setContentType("text/html; charset=UTF-8");
		//response.setCharacterEncoding("UTF-8");
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		// 刷新网页最简单的方式是使用响应对象的方法 serIntHeader()
		response.setIntHeader("Refresh", 30);   // 30s更新一次，为在URL中输入参数留操作时间
		
		// 获取当前时间
		Calendar calendar = new GregorianCalendar();
		String am_pm;
		int hour = calendar.get(Calendar.HOUR);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		if (calendar.get(Calendar.AM_PM) == 0) {
			am_pm = "AM";
		} else {
			am_pm = "PM";
		}
		
		String time = hour + ":" + minute + ":" + second + " " + am_pm;
		
		PrintWriter out = response.getWriter();
		out.println("<html>\n<head>\n<title>实时刷新时间获取用户输入参数</title>");
		out.println("<body>");
		out.println("<p>");
		out.println("当前时间为： " + time + "</p>");
		
		// 通过URL参数获取用户输入，并将输入进行加法运算并显示
		int a = Integer.valueOf(request.getParameter("para1"));
		int b = Integer.valueOf(request.getParameter("para2"));
		out.printf("a + b = %d", a+b);
		out.print("</body>\n</html>");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
						throws ServletException, IOException {
		doGet(request, response);
	}

}
