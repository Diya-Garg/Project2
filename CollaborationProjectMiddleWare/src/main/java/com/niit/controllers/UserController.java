package com.niit.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

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
	

	
	
	@GetMapping(value="/updateOnlineStatus/{status}/{loginName}")
	public ResponseEntity<String> updateOnlineStatus(@PathVariable String status,@PathVariable String loginName){
		System.out.println("Status : "+status);
		System.out.println("Loginname : "+loginName);
		if(userDAO.updateOnlineStatus(status, loginName)){
			return new ResponseEntity<String>("Status Updated Succesfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Not able to update status succesfully",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping("/deleteUser")
	public ResponseEntity<String> deleteUser(@RequestBody UserDetails user){
		UserDetails userObj=userDAO.getUser(user.getLoginName());
		if(userDAO.deleteUser(userObj)){
			return new ResponseEntity<String>("User deleted succesfully...",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Problem in deleting User...",HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/updateUser")
	public ResponseEntity<String> updateUser(@RequestBody UserDetails user){
		
		if(userDAO.updateUser(user)){
			return new ResponseEntity<String>("User updated succesfully...",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Problem in updating User...",HttpStatus.NOT_FOUND);
		}
	}
	
	
	@GetMapping(value="/getListOfUsers")
	public ResponseEntity<List<UserDetails>> getUsersList(){
		List<UserDetails> list=userDAO.getUserDetails();
		if(list.size()==0){
			return new ResponseEntity<List<UserDetails>>(list,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<UserDetails>>(list,HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PostMapping(value="/login")
	public ResponseEntity<UserDetails> checkLogin(@RequestBody UserDetails userDetails,HttpSession session){
		if(userDAO.checkLogin(userDetails)){
			
			System.out.println("I m valid user");
			UserDetails user=(UserDetails)userDAO.getUser(userDetails.getLoginName());
			userDAO.updateOnlineStatus("online", user.getLoginName());
			session.setAttribute("userObj",user);
			System.out.println("Attribute Added in Session");
			return new ResponseEntity<UserDetails>(user,HttpStatus.OK);
		 	
		}
		else {
			System.out.println("Invalid user");
			return new ResponseEntity<UserDetails>(userDetails,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PostMapping(value="/register")
	public ResponseEntity<String> registerUser(@RequestBody UserDetails userDetails){
		userDetails.setOnlineStatus("Offline");
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
		
		System.out.println("In get user function"+loginName);
		UserDetails userDetails=userDAO.getUser(loginName);
		if(userDetails!=null){
			return new ResponseEntity<UserDetails>(userDetails,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<UserDetails>(userDetails,HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="checkStatus")
	public ResponseEntity<String> checkStatus(){
		return  new ResponseEntity<String>("Checking Status",HttpStatus.OK);
	}
	
}
