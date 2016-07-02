package com.archy.blog.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.archy.blog.model.User;

public class UserDao {
	
	// 将注册成功的 用户的数据 导入数据库中
	public boolean createUser(User user) {
		// 导入成功则返回 true ，否则返回 false 
		return DaoHelper.executeUpdate(String.format(String.format("INSERT INTO "
				+ "`user` (`userName`, `password`, `email`, `registeredDate`,`avatar`)"
				+ " VALUES ('%s', '%s', '%s', '%s', '%s')", user.getUserName(), user.getPassword(),
				user.getEmail(), user.getRegisteredDate(), user.getAvatar() )));
	}
	
    protected User resultToUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setAvatar(rs.getString("avatar"));
        user.setEmail(rs.getString("email"));
        user.setUserId(rs.getLong("userId"));
        user.setPassword(rs.getString("password"));
        user.setRegisteredDate(rs.getString("registeredDate"));
        return user;
    }
	
	public List<User> findAll() {
        ResultSet resultSet = DaoHelper.executeQuery("select * from `user`");
        return resultToUsers(resultSet);
    }
	
    protected List<User> resultToUsers(ResultSet rs) {
        List<User> users = new ArrayList<>();
        try {
            while (rs.next()) {
                User user = resultToUser(rs);
                if (user != null) {
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    
    // 判断用户名或者邮箱重复
 	public boolean isEmailOrUserExist(User user) {
 		ResultSet rs = DaoHelper.executeQuery("select * from `user`");
 		try {
	 		while (rs.next()) {
	 			return user.getEmail().equals(rs.getString("email"))
	 					|| user.getUserName().equals(rs.getString("userName"));
	 		}
 		} catch (SQLException e) {
 			e.printStackTrace(); 
		}
 		return false;
 	}
}
