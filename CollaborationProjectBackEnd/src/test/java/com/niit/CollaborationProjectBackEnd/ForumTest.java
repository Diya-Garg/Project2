package com.niit.CollaborationProjectBackEnd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.BlogDAO;
import com.niit.dao.ForumDAO;
import com.niit.model.Blog;
import com.niit.model.Forum;
import com.niit.model.ForumComment;

public class ForumTest {

	static ForumDAO forumDAO;
	
	@BeforeClass
	public static void initialize(){
		System.out.println("Initializing Test Case");
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		
		forumDAO=(ForumDAO)context.getBean("forumDAO");
		
	}

	@Test
	@Ignore
	public void addForumTest(){
		
		Forum forum=new Forum();
		forum.setForumName("Servlets");
		forum.setForumContent("Servlet is a web component of JEE which is used to create dynamic web pages at runtime");
		forum.setLoginName("Divya Garg");
		forum.setStatus("NA");
		forum.setCreateDate(new java.util.Date());
		
		assertTrue("Problem in inserting Forum",forumDAO.addForum(forum));
		
	}
	

	@Test
	@Ignore
	public void listForumsTest(){
		List<Forum> forums=forumDAO.listForums("Divya Garg");
		if(forums.size()==0){
			assertTrue("Forums Doesnt exist for Divya Garg user",forums.size()==0);
		}
		else {
			for(Forum forum :forums){
				System.out.println("\n\n"+forum);
			}
		}
	}
	

	@Test
	@Ignore
	public void getForumTest(){
		Forum forum=forumDAO.getForum(50);
		assertEquals("Succesfully fetched a single forum from the table", "Introduction to JEE",forum.getForumName());
	}
	
	@Test
	@Ignore
	public void testUpdateForum(){
		Forum forum=forumDAO.getForum(50);
		forum.setForumName("Maven");
		
		assertEquals("Succesfully updated the name of the forum", true,forumDAO.updateForum(forum));
	}
	
	
	@Test
	@Ignore
	public void testDeleteForum(){
		Forum forum=forumDAO.getForum(1050);
		assertEquals("Forum Deleted Succesfully", true,forumDAO.deleteForum(forum));
	}
	
	
	@Test
	@Ignore
	public void testApproveForum(){
		Forum forum=forumDAO.getForum(50);
		assertEquals("Forum approved succesfully", true,forumDAO.approveForum(forum));
	}
	
	@Test
	@Ignore
	public void testRejectForum(){
		Forum forum=forumDAO.getForum(50);
		assertEquals("Forum rejected succesfully", true,forumDAO.rejectForum(forum));
	}

	@Test
	@Ignore
	public void testAddForumComment(){
		ForumComment comment=new ForumComment();
		comment.setCommentText("Maven is helpful in adding the required dependencies in the project");
		comment.setLoginName("Divya Garg");
		comment.setForumId(50);
		comment.setCommentDate(new Date());
		
		assertEquals("Comment Added Succesfully", true,forumDAO.addForumComment(comment));
	}
	
	@Test
	@Ignore
	public void testlistForumComments(){
		List<ForumComment> forumComments=forumDAO.listForumComments(50);
		if(forumComments.size()==0){
			assertTrue("Forums Doesnt have any comment yet",forumComments.size()==0);
		}
		else {
			for(ForumComment comment :forumComments){
				System.out.println("\n\n"+comment);
			}
		}
	}
	
	@Test
	@Ignore
	public void testGetForumComment(){
		ForumComment forumComment=forumDAO.getForumComment(150);
		assertEquals("Succesfully fetched a single comment from the forum tidable", "Servlets is a helper program of Web server which is used to create dynamic web pages",forumComment.getCommentText());
	}

	@Test
	@Ignore
	public void testDeleteForumComment(){
		ForumComment comment=forumDAO.getForumComment(150);
		assertEquals("Comment Deleted Succesfully", true,forumDAO.deleteForumComment(comment));
	}

}













