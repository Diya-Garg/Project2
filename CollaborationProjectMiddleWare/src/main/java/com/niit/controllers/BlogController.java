package com.niit.controllers;

import java.util.Date;
import java.util.List;

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


@RestController
public class BlogController {
	
	
	@Autowired
	BlogDAO blogDAO;
	
	@GetMapping(value="/demo")
	public ResponseEntity<String> demoPurpose(){
		return new ResponseEntity<String>("Demo Data",HttpStatus.OK);
	}
	
	@GetMapping(value="/listBlogs")
	public ResponseEntity<List<Blog>> getListBlogs(){
		List<Blog> listBlogs=blogDAO.listBlogs("Travera");
		if(listBlogs.size()>0){
			return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value="/addBlog")
	public ResponseEntity<String> addBlog(@RequestBody Blog blog){
		blog.setCreateDate(new java.util.Date());
		blog.setLikes(0);
		blog.setStatus("NA");
		blog.setLoginName("Divya");
		
		if(blogDAO.addBlog(blog)){
			return new ResponseEntity<String>("Success",HttpStatus.OK);
		}
		else{
			return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/getBlog/{blogId}")
	public ResponseEntity<Blog> getBlog(@PathVariable int blogId){
		
		Blog blog=blogDAO.getBlog(blogId);
		if(blog==null){
			return new ResponseEntity<Blog>(blog,HttpStatus.NOT_FOUND);
		}
		else{
			return new ResponseEntity<Blog>(blog,HttpStatus.OK);
		}
	}
	
	@GetMapping(value="/deleteBlog/{blogId}")
	public ResponseEntity<String> deleteBlog(@PathVariable int blogId){
		if(blogDAO.deleteBlog(blogId)){
			return new ResponseEntity<String>("Blog Deleted Succesfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error in Deleting Blog",HttpStatus.OK);
		}
	}
	
	@PostMapping(value="/updateBlog/{blogId}")
	public ResponseEntity<String> updateBlog(@RequestBody Blog blog){
		
		if(blogDAO.updateBlog(blog)){
			return new ResponseEntity<String>("Blog Updated Succesfully",HttpStatus.OK);
		}
		else{
			return new ResponseEntity<String>("Error in updating blog",HttpStatus.NOT_FOUND);
		}
	}
	
	
	
}
