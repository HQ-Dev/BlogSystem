package com.archy.blog.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Comment {

	
	// 评论的id,便于查找和删除评论
	private long comment_id;
	private String content = null;
	// 评论创建时间
	private String commentDate;
	// 评论者
	private User creator;
	// 评论所对应的博文 id
	private long post_id;
	
	public Comment() {};
	
	public Comment(String content, User creator, long post_id) {
		this.content = content;
		this.creator = creator;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-HH-dd hh:mm:ss");
		this.commentDate = sdf.format(new Date());
		this.post_id = post_id;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User commentUser) {
		this.creator = commentUser;
	}

	public String getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}
}
