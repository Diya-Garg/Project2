package com.niit.controllers;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.BlogDAO;
import com.niit.model.Blog;
import com.niit.model.UserDetails;


@RestController
public class BlogController {
	
	
	@Autowired
	BlogDAO blogDAO;
	
	
	@GetMapping(value="/demo")
	public ResponseEntity<String> demoPurpose(){
		return new ResponseEntity<String>("Demo Data",HttpStatus.OK);
	}
	
	
	@GetMapping(value="/listBlogs")
	public ResponseEntity<List<Blog>> getListBlogs(HttpSession session){
		
		UserDetails userDetails=(UserDetails)session.getAttribute("userObj");
		
		List<Blog> listBlogs=blogDAO.listBlogs(userDetails.getLoginName());
		if(listBlogs.size()>0){
			return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PostMapping(value="/addBlog")
	public ResponseEntity<String> addBlog(@RequestBody Blog blog,HttpSession session){
		blog.setCreateDate(new java.util.Date());
		blog.setLikes(0);
		blog.setStatus("NA");
		
		UserDetails userDetails=(UserDetails)session.getAttribute("userObj");
		System.out.println("User Details = "+userDetails.getLoginName());
		blog.setLoginName(userDetails.getLoginName());
		
		if(blogDAO.addBlog(blog)){
			return new ResponseEntity<String>("Blog Added Succesfully",HttpStatus.OK);
		}
		else{
			return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);
		}
	}
	
	
	@GetMapping(value="/getBlog/{blogId}")
	public ResponseEntity<Blog> getBlog(@PathVariable int blogId){
		
		Blog blog=blogDAO.getBlog(blogId);
		if(blog==null){
			System.out.println("Blog Not Found");
			return new ResponseEntity<Blog>(blog,HttpStatus.NOT_FOUND);
		}
		else{
			System.out.println("Blog Found");
			return new ResponseEntity<Blog>(blog,HttpStatus.OK);
		}
	}
	
	
	@GetMapping(value="/deleteBlog/{blogId}")
	public ResponseEntity<String> deleteBlog(@PathVariable int blogId){
		
		
		System.out.println("Delete Blog in Rest Controller : "+blogId);
		Blog blog=blogDAO.getBlog(blogId);
		
		if(blogDAO.deleteBlog(blog)){
			return new ResponseEntity<String>("Blog Deleted Succesfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error in Deleting Blog",HttpStatus.OK);
		}
	}
	
	
	@PostMapping(value="/updateBlog")
	public ResponseEntity<String> updateBlog(@RequestBody Blog blog){
		System.out.println(blog);
		if(blogDAO.updateBlog(blog)){
			return new ResponseEntity<String>("Blog Updated Succesfully",HttpStatus.OK);
		}
		else{
			return new ResponseEntity<String>("Error in updating blog",HttpStatus.NOT_FOUND);
		}
	}
	
	
	@GetMapping(value="/approveBlog/{blogId}")
	public ResponseEntity<String> approveBlog(@PathVariable int blogId){
		Blog blog=blogDAO.getBlog(blogId);
		if(blogDAO.approveBlog(blog)){
			return new ResponseEntity<String>("Blog Approved Succesfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error in updating blog",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/rejectBlog/{blogId}")
	public ResponseEntity<String> rejectBlog(@PathVariable int blogId){
		Blog blog=blogDAO.getBlog(blogId);
		if(blogDAO.rejectBlog(blog)){
			return new ResponseEntity<String>("Blog Rejected Succesfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error in rejecting blog",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}





 