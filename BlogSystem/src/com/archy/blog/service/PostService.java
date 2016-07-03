package com.archy.blog.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.archy.blog.dao.PostDao;
import com.archy.blog.model.Post;
import com.archy.blog.model.User;

public class PostService {
	private PostDao postDao = new PostDao();

	public List<Post> findAll() {
		return postDao.findAll();
	}

	public List<Post> findByCreator(long creatorId) {
		return postDao.findByCreator(creatorId);
	}

	public Post findByPostId(long postId) {
		return postDao.findByPostId(postId);
	}

	public boolean create(String title, String content, User creator) {
		Post post = new Post();
		post.setContent(content);
		post.setCreator(creator);
		post.setTitle(title);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String createdDate = dateFormat.format(new Date());
		post.setCreatedDate(createdDate);
		return postDao.create(post);
	}

	public boolean deleteById(long id) {
		return postDao.delete(id);
	}

	public boolean update(Long id, String title, String content) {
		Post post = new Post();
		post.setPostId(id);
		post.setContent(content);
		post.setTitle(title);
		return postDao.update(post);
	}
}
