package com.dfjh.service.impl;

import com.dfjh.bean.User;
import com.dfjh.dao.UserDAO;
import com.dfjh.service.UserService;

public class UserServiceImpl implements UserService{
	private UserDAO userDao;
	public void saveUser(User user) {
		userDao.saveUser(user);
	}
	
//======getter and setter methods=========	
	public UserDAO getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}
	
	
	
}
