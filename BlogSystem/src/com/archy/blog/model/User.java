package com.archy.blog.model;


public class User {
	
	private static long id = 1;
	private long userId;
	private String userName;
	private String password;
	private String email;
	private String avatar = "/BlogSystem/static/img/avatar-default.jpg";
	private String description = null;
	private String registeredDate = null;
	
	public User() {
	
	}
	
	public User(String userName, String password, String email) {
		this.userId = id;
		id++;
		this.userName = userName;
		this.password = password;
		this.email = email;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long id) {
		this.userId = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(String registeredDate) {
		this.registeredDate = registeredDate;
	}
	

}
