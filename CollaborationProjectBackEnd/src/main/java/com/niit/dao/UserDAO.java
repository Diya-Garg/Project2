package com.niit.dao;

import java.util.List;

import com.niit.model.UserDetails;

public interface UserDAO {

	public boolean registerUser(UserDetails user);
	public boolean checkLogin(UserDetails user);
	public boolean updateOnlineStatus(String status,UserDetails user);
	public UserDetails getUser(String loginName);
	public List<UserDetails> getUserDetails();
	public boolean deleteUser(UserDetails user);
	public boolean updateUser(UserDetails user);
}