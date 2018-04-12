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
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.ForumDAO;
import com.niit.model.Blog;
import com.niit.model.Forum;
import com.niit.model.ForumComment;
import com.niit.model.UserDetails;


@RestController
public class ForumController {

	@Autowired
	ForumDAO forumDAO;
	

	@GetMapping(value="/checkForum")
	public ResponseEntity<String> checkingPurpose(){
		return new ResponseEntity<String>("Demo Data",HttpStatus.OK);
	}
	
	@PostMapping(value="/addForum")
	public ResponseEntity<String> addForum(@RequestBody Forum forum,HttpSession session){
		forum.setCreateDate(new java.util.Date());
		forum.setStatus("NA");
		
		UserDetails userDetails=(UserDetails)session.getAttribute("userObj");
		forum.setLoginName(userDetails.getLoginName());
		
		
		if(forumDAO.addForum(forum)){
			return new ResponseEntity<String>("Forum Added Succesfully",HttpStatus.OK);
		}
		else{
			return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/listForums")
	public ResponseEntity<List<Forum>> getListForums(HttpSession session){
		
		UserDetails userDetails=(UserDetails)session.getAttribute("userObj");
		
		List<Forum> listForums=forumDAO.listForums(userDetails.getLoginName());
		if(listForums.size()>0){
			return new ResponseEntity<List<Forum>>(listForums,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Forum>>(listForums,HttpStatus.NOT_FOUND);
		}
	}
	
	
	@GetMapping(value="/getForum/{forumId}")
	public ResponseEntity<Forum> getBlog(@PathVariable int forumId){
		
		Forum forum=forumDAO.getForum(forumId);
		if(forum==null){
			System.out.println("Forum Not Found");
			return new ResponseEntity<Forum>(forum,HttpStatus.NOT_FOUND);
		}
		else{
			System.out.println("Forum Found");
			return new ResponseEntity<Forum>(forum,HttpStatus.OK);
		}
	}
	
	@GetMapping(value="/deleteForum/{forumId}")
	public ResponseEntity<String> deleteForum(@PathVariable int forumId){
		
		
		System.out.println("Delete Forum in Rest Controller : "+forumId);
		Forum forum=forumDAO.getForum(forumId);
		
		if(forumDAO.deleteForum(forum)){
			return new ResponseEntity<String>("Forum Deleted Succesfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error in Deleting Forum",HttpStatus.OK);
		}
	}
	
	
	@PostMapping(value="/updateForum")
	public ResponseEntity<String> updateForum(@RequestBody Forum forum){
		System.out.println(forum);
		if(forumDAO.updateForum(forum)){
			return new ResponseEntity<String>("Forum Updated Succesfully",HttpStatus.OK);
		}
		else{
			return new ResponseEntity<String>("Error in updating Forum",HttpStatus.NOT_FOUND);
		}
	}

	
	@GetMapping(value="/approveForum/{forumId}")
	public ResponseEntity<String> approveForum(@PathVariable int forumId){
		Forum forum=forumDAO.getForum(forumId);
		if(forumDAO.approveForum(forum)){
			return new ResponseEntity<String>("Forum Approved Succesfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error in Approving Forum",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/rejectForum/{forumId}")
	public ResponseEntity<String> rejectForum(@PathVariable int forumId){
		Forum forum=forumDAO.getForum(forumId);
		if(forumDAO.rejectForum(forum)){
			return new ResponseEntity<String>("Forum Rejected Succesfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error in rejecting forum",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value="/addForumComment")
	public ResponseEntity<String> addForumComment(@RequestBody ForumComment forumComment,HttpSession session){
		forumComment.setLoginName("aviagg");
		forumComment.setForumId(1100);
		forumComment.setCommentDate(new Date());
		if(forumDAO.addForumComment(forumComment)){
			return new ResponseEntity<String>("Forum Comment Added...",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error in Adding Forum Comment...",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping(value="/deleteForumComment/{commentId}")
	public ResponseEntity<String> deleteForumComment(@PathVariable int commentId){
		
		
		System.out.println("Delete Forum Comment in Rest Controller : "+commentId);
		ForumComment forumComment=forumDAO.getForumComment(commentId);
		
		if(forumDAO.deleteForumComment(forumComment)){
			return new ResponseEntity<String>("Forum Comment Deleted Succesfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error in Deleting Forum Comment",HttpStatus.OK);
		}
	}
	
	@GetMapping(value="/getForumComment/{commentId}")
	public ResponseEntity<ForumComment> getForumComment(@PathVariable int commentId){
		ForumComment forumComment=forumDAO.getForumComment(commentId);
		if(forumComment!=null){
			return new ResponseEntity<ForumComment>(forumComment,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<ForumComment>(forumComment,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	 
	@GetMapping("/getForumComments/{forumId}")
	public ResponseEntity<List<ForumComment>> listForumComments(@PathVariable int forumId){
		List<ForumComment> list=forumDAO.listForumComments(1100);
		if(list.size()!=0){
			return new ResponseEntity<List<ForumComment>>(list,HttpStatus.OK);
		}
		else {
		return new ResponseEntity<List<ForumComment>>(list,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

















