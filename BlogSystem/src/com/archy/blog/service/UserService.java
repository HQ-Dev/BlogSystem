package com.archy.blog.service;

import java.util.List;

import com.archy.blog.dao.UserDao;
import com.archy.blog.model.User;

public class UserService {
	
	private UserDao userDao = new UserDao();
	
	public boolean register(User user, List<String> errors) {
		// 1. 检验注册邮箱是否不合规
		if(isInvalidEmail(user))
			errors.add("未填写邮箱或者邮箱格式不正确！");
		// 2. 判断密码格式是否不正确
		if (isInvalidPassword(user))
			errors.add("请确认密码格式并再次确认密码！");
		// 3. 判断数据库中是否有重复的用户注册
		if (userDao.isEmailOrUserExist(user))
			errors.add("填写的邮箱或者用户名已经被注册，请使用别的用户名或者邮箱！");
		
		if (errors.isEmpty()) {
			if(userDao.createUser(user)) // 创建成功则 return true
				return true;
			else 
				errors.add("向数据库 insert 用户失败！");
				return false;
		} else 
			return false;
	}
	
	
	// 判断邮箱格式是否正确的方法
	private boolean isInvalidEmail(User user) {
		return user.getEmail() == null
				||
				!user.getEmail().matches("^[_a-z0-9-]+([.][_a-z0-9]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$");
	}
	
	// 判断密码的有效性
	private boolean isInvalidPassword(User user) {
		return user.getPassword() == null || user.getPassword().length() < 6 ||
				user.getPassword().length() > 16 || !user.getPassword().equals(user.getConfirmedPassword());
	}
	
}
