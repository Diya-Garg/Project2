package com.niit.dao;

import java.util.List;

import com.niit.model.UserDetails;

public interface UserDAO {

	public boolean addUser(UserDetails user);
	public boolean updateUser(UserDetails user);
	public UserDetails getUser(int userId);
	public List<UserDetails> getUserDetails();
	public boolean deleteUser(UserDetails user);
}