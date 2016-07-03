package com.archy.blog.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.archy.blog.model.Comment;

public class CommentDao {
	
	private UserDao userDao = new UserDao();
	
	public boolean createComment(Comment comment) {
		return DaoHelper.executeUpdate(String.format("insert into comment(`content`,`commentDate`,"
				+ "`creator`,`postId`) values('%s', '%s', %d, %d) ", 
				comment.getContent(), comment.getCommentDate(), 
				comment.getCreator().getUserId(), comment.getPost_id()));
	}

	public List<Comment> findCommentsByPostId(long postId) {
		ResultSet rs = DaoHelper.executeQuery
				(String.format("select * from comment where postId = %d", postId));
		
		return resultToComments(rs);
	}

	protected List<Comment> resultToComments(ResultSet rs) {
		List<Comment> comments = new ArrayList<>();
		
		try {
			while (rs.next()) {
				Comment comment = resultToComment(rs);;
				if (comment != null) {
					comments.add(comment);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comments;
	}
	
	protected Comment resultToComment(ResultSet rs) {
		Comment comment = new Comment();
		try {
			comment.setComment_id(rs.getInt("commentId"));
			comment.setContent(rs.getString("content"));
			comment.setCommentDate(rs.getString("commentDate"));
			comment.setCreator(userDao.findById(rs.getLong("creator")));
			comment.setPost_id(rs.getInt("postId"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comment;
	}
	
	
}
