package com.archy.blog.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.archy.blog.model.Post;

public class PostDao {
	
	private UserDao userDao = new UserDao();
	
	protected Post resultToPost(ResultSet rs) throws SQLException {
		Post post = new Post();
		post.setPostId(rs.getLong("postId"));
		post.setTitle(rs.getString("title"));
		post.setContent(rs.getString("content"));
		post.setCreatedDate(rs.getString("createdDate"));
		post.setCreator(userDao.findById(rs.getLong("creator")));
		
		return post;
	}

	protected List<Post> resultToPosts(ResultSet rs) {
		List<Post> posts = new ArrayList<>();
		try {
			while (rs.next()) {
				Post post = resultToPost(rs);
				if (post != null) {
					posts.add(post);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return posts;
	}

	public boolean create(Post t) {
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return DaoHelper.executeUpdate(
				String.format("insert into post(title, content, createdDate, creator) values('%s', '%s', '%s', %s)",
						t.getTitle(), t.getContent(), t.getCreatedDate(), t.getCreator().getUserId()));
	}

	public boolean update(Post t) {
		return DaoHelper.executeUpdate(String.format("update post set title = '%s', content = '%s' where id = %s",
				t.getTitle(), t.getContent(), t.getPostId()));
	}

	public List<Post> findByCreator(long creatorId) {
		ResultSet resultSet = DaoHelper
				.executeQuery(String.format("select * from `post` where creator = %s", creatorId));
		return resultToPosts(resultSet);
	}

	public Post findByPostId(long postId) {
		ResultSet resultSet = DaoHelper.executeQuery(String.format("select * from `post` where postId = %s", postId));
		List<Post> posts = resultToPosts(resultSet);
		if (posts.isEmpty()) {
			return null;
		}
		return posts.get(0);
	}

	public List<Post> findAll() {
		ResultSet resultSet = DaoHelper.executeQuery("select * from `post`");
		return resultToPosts(resultSet);
	}

	public boolean delete(long id) {
		return DaoHelper.executeUpdate(String.format("delete from `post` where id = %s", id));
	}
	
}
