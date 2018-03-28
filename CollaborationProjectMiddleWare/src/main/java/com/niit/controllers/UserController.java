package com.niit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.UserDAO;
import com.niit.model.UserDetails;


@RestController
public class UserController {

	@Autowired
	UserDAO userDAO;
	
	
	@PostMapping(value="/login")
	public ResponseEntity<UserDetails> checkLogin(@RequestBody UserDetails userDetails){
		if(userDAO.checkLogin(userDetails)){
			UserDetails user=(UserDetails)userDAO.getUser(userDetails.getLoginName());
			userDAO.updateOnlineStatus("Y", user);
			return new ResponseEntity<UserDetails>(user,HttpStatus.OK);
			
		}
		else {
			return new ResponseEntity<UserDetails>(userDetails,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PostMapping(value="/register")
	public ResponseEntity<String> registerUser(@RequestBody UserDetails userDetails){
		userDetails.setOnlineStatus("N");
		userDetails.setRole("Role_User");
		
		if(userDAO.registerUser(userDetails)){
			return new ResponseEntity<String>("User Registered Succesfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error in Registering User . Please try again",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping(value="getUser/{loginName}")
	public ResponseEntity<UserDetails> getUserByLoginName(@PathVariable String loginName){
		
		System.out.println(loginName);
		UserDetails userDetails=userDAO.getUser(loginName);
		if(userDetails!=null){
			return new ResponseEntity<UserDetails>(userDetails,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<UserDetails>(userDetails,HttpStatus.NOT_FOUND);
		}
	}
	
}
