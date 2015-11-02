package com.dfjh.action.user;

import com.dfjh.bean.User;
import com.dfjh.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class SaveUserAction extends ActionSupport{
	private User user;
	private UserService userService;
	
	public String saveUser(){
		System.out.println(123);
//		this.userService.saveUser(user);
		return SUCCESS;
	}
	//======getter and setter methods=========	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
