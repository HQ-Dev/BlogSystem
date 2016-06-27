import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

public class JDBC_Test {

	public static void main(String[] args) {

		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			// 客户端连接数据库的编码设置非常重要！！
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/blogSystem?useUnicode=true&characterEncoding=utf8", "root", "123456");
			
			stmt = conn.createStatement();
			
			stmt.executeUpdate("INSERT INTO `user`(userName,password,avatar,email,description,createdDate) VALUES ('Bob','123456','/BlogSystem/static/img/avatar-default.jpg','Bob@163.com','Java学徒','2016-02-12 13:12:00'),('Dasiy','123456','/BlogSystem/static/img/avatar-default.jpg','Dasiy@163.com','Java学徒','2016-02-15 17:11:00')");  
			
			stmt.executeUpdate("INSERT INTO post (title,content,creator,createdDate) VALUES ('异常处理','异常定义了程序中遇到的非致命的错误，比如如程序要打开一个不存的文件、网络连接中断、除零操作、作数越界、装载一个不存在的类等情况。',3,'2016-04-23 14:12:40'), ('变量与数据类型','Java是一门静态语言，要求在使用一个变量前要求必须声明它的类型。',4,'2016-03-20 15:10:10'),('单个文章页面','完成文章列表页后，继续制作单个文章的相关页面',4,'2016-04-22 10:22:00')"); 
			
			
			stmt.executeUpdate("INSERT INTO comment(comment,c_creator,c_postId,commentDate) VALUES('Please!',3,11,'2016-04-30 23:09:30')");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("MYSQL ERROR:" + e.getMessage());
			
		} finally {
			try{
				if (stmt != null) {
					stmt.close();
				}
			
				if (conn != null) {
					conn.close();
				}
			} catch(SQLException e) {
				
			}
			
		}
	}

}
