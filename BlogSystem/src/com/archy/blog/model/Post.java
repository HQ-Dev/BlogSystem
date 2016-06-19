package com.archy.blog.model;

import java.util.Date;

public class Post {

	private static long postId = 1;
	
	private long id;
	private String title;
	private String content;
	private Date createdTime;
	
	public Post(String title, String content) {
		this.id = postId;
		postId++;
		this.title = title;
		this.content = content;
		this.createdTime = new Date();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedTime() {
		return createdTime;
	}
	
	
}
