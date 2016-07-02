package com.archy.blog.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoHelper {
	private static String DRIVER_NAME = "com.mysql.jdbc.Driver";
	private static String DB_URL = "jdbc:mysql://localhost:3306/blogSystem?useUnicode=true&characterEncoding=utf8";
	private static String USERNAME = "root";
	private static String PASSWORD = "123456";
	
	public static ResultSet executeQuery(String sql) {
		System.out.println(sql);
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(DRIVER_NAME).newInstance();
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			stmt = conn.createStatement();
			return stmt.executeQuery(sql);
			
		} catch (Exception e) {
			System.out.println("MYSQL ERROR:" + e.getMessage());
			return null;
		}
	}
	
	public static boolean executeUpdate(String sql) {
		System.out.println(sql);
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(DRIVER_NAME).newInstance();
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			
			return true;
		} catch (Exception e) {
			System.out.println("MYSQL ERROR:" + e.getMessage());
			return false;
		} finally {
			try {
				if (conn != null) conn.close();
				if (stmt != null) stmt.close();
			} catch (SQLException ignore) {
				// ignore
			}
		}
		
	}
	
	
}
