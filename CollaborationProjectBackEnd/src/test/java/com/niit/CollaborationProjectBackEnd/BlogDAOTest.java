package com.niit.CollaborationProjectBackEnd;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.BlogDAO;
import com.niit.model.Blog;
import com.niit.model.BlogComment;

public class BlogDAOTest {

	static BlogDAO blogDAO;
	
	@BeforeClass
	public static void initialize(){
		System.out.println("Initializing Test Case");
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		
		blogDAO=(BlogDAO)context.getBean("blogDAO");
		System.out.println("Blog DAO : "+blogDAO);
	}

	@Test
	@Ignore
	public void addBlogTest(){
		System.out.println("In addBlogTest");
		Blog blog=new Blog();
		blog.setBlogName("Angular 4");
		blog.setBlogContent("Works on Typescript");
		blog.setLoginName("Travera");
		blog.setStatus("NA");
		blog.setCreateDate(new java.util.Date());
		blog.setLikes(0);
		assertTrue("Problem in inserting Blog",blogDAO.addBlog(blog));
	}
	

	@Test
	@Ignore
	public void listBlogsTest(){
		List<Blog> blogs=blogDAO.listBlogs("Travera");
		if(blogs.size()==0){
			System.out.println("I m here");
			assertTrue("Blogs Doesnt exist for Travera user",blogs.size()==0);
		}
		else {
			for(Blog blog :blogs){
				System.out.println("\n\n"+blog);
			}
		}
	}
	

	@Test
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
	
	@Test
	@Ignore
	public void testIncrementLikes(){
		Blog blog=blogDAO.getBlog(50);
		System.out.println("Blog Object : "+blog);
		assertEquals("Likes incremented succesfully", blogDAO.incrementLikes(blog));
	}
	
	@Test
	@Ignore
	public void testAddBlogComment(){
		System.out.println("Adding Blog Comment");
		BlogComment blogComment=new BlogComment();
		blogComment.setCommentText("Hibernate is used to solve the impedence mismatch b/w Relational database management system and Object Oriented Programming");
		blogComment.setLoginname("John");
		blogComment.setBlogId(50);
		blogComment.setCommentDate(new Date());
		assertEquals("Blog Comment Added", blogDAO.addBlogComment(blogComment));
		
	}
	
	@Test
	@Ignore
	public void testDeleteBlogComment(){
		BlogComment comment=blogDAO.getBlogComment(100);
		assertEquals("Comment Deleted Succesfully", true,blogDAO.deleteBlogComment(comment));
	}
	
	@Test
	@Ignore
	public void testGetBlogComment(){
		assertNotNull("Blog Comment fetched succesfully",blogDAO.getBlogComment(100));
	}
	
 
	@Test
	@Ignore
	public void listBlogComments(){
		List<BlogComment> blogComments=blogDAO.listBlogComments(50);
		if(blogComments.size()==0){
			assertTrue("Comments Doesnt exist for blog having blog id",blogComments.size()==0);
		}
		else {
			for(BlogComment comment :blogComments){
				System.out.println("\n\n"+comment);
			}
		}
	}
}



