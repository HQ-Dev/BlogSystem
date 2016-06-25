package com.archy.blog.model;

public class Comment {

	private static long id = 1;
	
	// 评论的id,便于查找和删除评论
	private long comment_id;
	private String comment = null;
	// 评论所对应的博文 id
	private long post_id;
	
	public Comment(String comment, long post_id) {
		this.comment_id = id;
		id++;
		this.post_id = post_id;
		this.comment = comment;
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
	
}
