package com.niit.CollaborationProjectBackEnd;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.UserDAO;
import com.niit.model.UserDetails;

public class UserTest {

	static UserDAO userDAO;
	
	@BeforeClass
	public static void initialize(){
		System.out.println("Initializing Test Case");
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		
		userDAO=(UserDAO)context.getBean("userDAO");
		System.out.println("User DAO : "+userDAO);
	}

	@Test
	@Ignore
	public void addUserTest(){
		
		UserDetails user=new UserDetails();
		user.setFirstName("Avni");
		user.setLastName("Agarwal");
		user.setPassword("avni.ag");
		user.setEmail("avniag@gmail.com");
		user.setRole("User");
		user.setStatus("Enabled");
		assertTrue("Problem in Inserting User",userDAO.addUser(user));
	}
	

	@Test
	@Ignore
	public void listUsersTest(){
		List<UserDetails> users=userDAO.getUserDetails();
		if(users.size()==0){
			System.out.println("I m here");
			assertTrue("Users Doesnt exist",users.size()==0);
		}
		else {
			for(UserDetails user :users){
				System.out.println("\n\n"+user);
			}
		}
	}
	

	@Test
	@Ignore
	public void getUserTest(){
		UserDetails User=userDAO.getUser(50);
		assertEquals("Succesfully fetched a single User from the table", "Divya",User.getFirstName());
	}
	
	@Test
	@Ignore
	public void testUpdateUser(){
		UserDetails User=userDAO.getUser(50);
		User.setFirstName("Divya G");
		
		assertEquals("Succesfully updated the name of the User", true,userDAO.updateUser(User));
	}
	
	
	@Test
	@Ignore
	public void testDeleteUser(){
		UserDetails User=userDAO.getUser(150);
		assertEquals("User Deleted Succesfully", true,userDAO.deleteUser(User));
	}
	
}



