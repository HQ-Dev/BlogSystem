package com.archy.blog.service;

import java.util.List;

import com.archy.blog.dao.CommentDao;
import com.archy.blog.model.Comment;
import com.archy.blog.model.Post;
import com.archy.blog.model.User;

public class CommentService {
	
	private CommentDao commentDao = new CommentDao();
	
	public boolean createComment(String content, Post post, User user) {
		
		Comment comment = new Comment(content, user, post.getPostId());
		return commentDao.createComment(comment);
	}
	
	// 查找某篇博文的所有评论
	public List<Comment> findCommentsByPostId(long postId) {
		return commentDao.findCommentsByPostId(postId);
	}
	
}
