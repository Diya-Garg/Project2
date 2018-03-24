package com.niit.CollaborationProjectBackEnd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.BlogDAO;
import com.niit.dao.ForumDAO;
import com.niit.model.Blog;
import com.niit.model.Forum;

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
			assertTrue("Forums Doesnt exist for Travera user",forums.size()==0);
		}
		else {
			for(Forum forum :forums){
				System.out.println("\n\n"+forum);
			}
		}
	}
	

	/*@Test
	@Ignore
	public void getBlogTest(){
		Blog blog=blogDAO.getBlog(50);
		assertEquals("Succesfully fetched a single blog from the table", "Hibernate",blog.getBlogName());
	}
	
	@Test
	@Ignore
	public void testUpdateBlog(){
		Blog blog=blogDAO.getBlog(50);
		blog.setBlogName("Introduction to Hibernate Framework");
		
		assertEquals("Succesfully updated the name of the blog", true,blogDAO.updateBlog(blog));
	}
	
	
	@Test
	@Ignore
	public void testDeleteBlog(){
		Blog blog=blogDAO.getBlog(150);
		assertEquals("Blog Deleted Succesfully", true,blogDAO.deleteBlog(blog));
	}
	
	@Test
	@Ignore
	public void testApproveBlog(){
		Blog blog=blogDAO.getBlog(50);
		assertEquals("Blog approved succesfully", blogDAO.approveBlog(blog));
	}
	
	@Test
	@Ignore
	public void testRejectBlog(){
		Blog blog=blogDAO.getBlog(50);
		assertEquals("Blog rejected succesfully", blogDAO.rejectBlog(blog));
	}
*/	

}
