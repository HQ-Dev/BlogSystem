package com.archy.blog.model;

public class Post {

	private static long id = 1;
	
	private long postId;
	private String title;
	private String createdDate;
	private String content;
	private User creator;
	public Post() {};
	
	public Post(String title, String createDate) {
		this.postId = id;
		id++;
		this.title = title;
		this.createdDate = createDate;
	}
	
	public Post(String title, String content, String createDate) {
		this.postId = id;
		id++;
		this.title = title;
		this.content = content;
		this.createdDate = createDate;
	}

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
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

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	
}
