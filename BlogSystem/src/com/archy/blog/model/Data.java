package com.archy.blog.model;

import java.util.ArrayList;
import java.util.List;

import jdk.internal.dynalink.beans.StaticClass;

public class Data {
	
	public static List<Post> posts = new ArrayList<>();
	
//	static 
//	{
//		posts.add(new Post( "天码营介绍", "天码营秉承让技术学习更加高效和便捷的使命，致力于打造新一代的技术学习服务平台，提供创新并且专业的内容、工具与服务，帮助学习者与从业者实现个人价值。"));
//		posts.add(new Post( "MyBatis 入门", "MyBatis是支持定制化SQL、存储过程以及高级映射的优秀持久层框架。MyBatis避免了几乎所有的JDBC代码和手动设置参数以及获取结果集。MyBatis可以对配置和原生Map使用简单的XML或注解,将接口和Java的POJOs（Plain Old Java Objects,普通的Java对象)映射成数据库中的记录。"));
//		posts.add(new Post( "JDBC进阶", "接下来介绍一个新的jdbc类：PreparedStatement接口，要使用游标就必须涉及这个接口，它继承自Statement接口。"));	
//	}
	
	public static List<Post> getPosts() {
		return posts;
	}
	
	public static List<User> users = new ArrayList<>();
	
	static {
		users.add(new User("John", "123456", "tianmaying1@163.com"));
		users.add(new User("Lily", "123456", "tianmaying2@163.com"));
		users.add(new User("Dasiy", "123456", "tianmaying3@163.com"));
	}
	
	public static List<User> getUsers() {
		return users;
	}
	
    public static User getByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUserName().equals(username))
                .findFirst()
                .orElse(null);
    }

}
