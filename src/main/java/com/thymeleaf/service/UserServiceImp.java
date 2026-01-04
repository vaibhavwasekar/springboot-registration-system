package com.thymeleaf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thymeleaf.entities.User;
import com.thymeleaf.repositories.UserRepository;
@Service
public class UserServiceImp implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Override
	public boolean registerUser(User user) {


		try {
			userRepository.save(user);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	@Override
	public User loginuser(String email, String password) {
		
		User validuser=userRepository.findByEmail(email);
		
		if(validuser != null && validuser.getPassword().equals(password))
		{
			return validuser;
		}
		else
		{
			return null;
		}
		
	}
	

}
