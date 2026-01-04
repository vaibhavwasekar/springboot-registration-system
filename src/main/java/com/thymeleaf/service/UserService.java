package com.thymeleaf.service;

import com.thymeleaf.entities.User;

public interface UserService {

	public boolean registerUser(User user);
	
	public User loginuser(String email,String password);
}
