package com.archy.blog.model;

import java.util.Date;

public class Comment {

	private static long id = 1;
	
	// 评论的id,便于查找和删除评论
	private long comment_id;
	private String comment = null;
	// 评论者
	private User creator;
	// 评论所对应的博文 id
	private long post_id;
	// 评论创建时间
	private Date commentDate;
	
	public Comment(String comment, long post_id, User creator) {
		this.comment_id = id;
		id++;
		this.post_id = post_id;
		this.comment = comment;
		this.creator = creator;
		this.commentDate = new Date();
	}
	
	public long getComment_id() {
		return comment_id;
	}
	public void setComment_id(long comment_id) {
		this.comment_id = comment_id;
	}
	public Long getPost_id() {
		return post_id;
	}
	public void setPost_id(long post_id) {
		this.post_id = post_id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User commentUser) {
		this.creator = commentUser;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	
	
}
