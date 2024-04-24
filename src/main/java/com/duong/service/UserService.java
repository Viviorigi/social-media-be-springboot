package com.duong.service;

import java.util.List;

import com.duong.exceptions.UserException;
import com.duong.models.User;

public interface UserService {
	
	public User registerUser(User user);
	
	public User findUserById(Integer userId) throws UserException;
	
	public User findUserByEmail(String email);
	
	public User followUser(Integer reqUser,Integer userId2) throws Exception;
	
	public User updateUser(User user,Integer userId) throws Exception;
	
	public List<User> searchUser(String query);
	
	public User findUserByJwt(String jwt);
	
}
