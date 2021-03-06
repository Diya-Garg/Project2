package com.niit.daoimpl;


import java.util.List;

import oracle.net.aso.q;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.BlogDAO;
import com.niit.model.Blog;
import com.niit.model.BlogComment;
import com.niit.model.UserDetails;


@Repository("blogDAO")
@Transactional
public class BlogDAOImpl implements BlogDAO {

	@Autowired
	SessionFactory sessionFactory;

	
	public boolean addBlog(Blog blog) {
		Session session=sessionFactory.getCurrentSession();
		try{
			
			session.save(blog);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
				
		}
	}

	public boolean deleteBlog(Blog blog) {
		Session session=sessionFactory.getCurrentSession();
		
		try{
			session.delete(blog);
			return true;
			
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateBlog(Blog blog) {
		System.out.println("Blog : "+blog.getBlogId()+" "+blog.getBlogName()+" "+blog.getBlogContent());
		Session session=sessionFactory.getCurrentSession();
		//Blog blogObj=(Blog)session.get(Blog.class,blog.getBlogId());
		try{
			session.update(blog);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Blog getBlog(int blogId) {
		Session session=sessionFactory.getCurrentSession();
		Blog blogObj=(Blog)session.get(Blog.class, blogId);
		return blogObj;
	}

	public boolean approveBlog(Blog blog) {
		try{
			blog.setStatus("Approved");
			sessionFactory.getCurrentSession().update(blog);
			System.out.println("I m here 1 ");
			return true;
		}
		catch(Exception e){
			System.out.println("I m here 2 ");
			e.printStackTrace();
		return false;
		}
	}

	public boolean rejectBlog(Blog blog) {
		try{
			blog.setStatus("Rejected");
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
		return false;
		}
	}

	public List<Blog> listBlogs(String userName,String role) {
		Query query=null;
		Session session=sessionFactory.openSession();
		if(role.equals("Role_Admin")){
			query=session.createQuery("from Blog");	
		}
		else if(role.equals("Role_Guest")){
			query=session.createQuery("from Blog where status='Approved'");	
		}
		else { 
		query=session.createQuery("from Blog where loginname=:a");
		query.setParameter("a",userName);
		}
		
		return query.list();
		
	}


	public boolean incrementLikes(Blog blog) {
		try{
		blog.setLikes(blog.getLikes()+1);
		sessionFactory.getCurrentSession().update(blog);
		return true;
		}
		catch(Exception e){
		e.printStackTrace();
		return false;
		}
	}

	public boolean addBlogComment(BlogComment blogComment) {
		try{
			sessionFactory.getCurrentSession().save(blogComment);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}

	public boolean deleteBlogComment(BlogComment blogComment) {
		try{
			sessionFactory.getCurrentSession().delete(blogComment);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}

	public BlogComment getBlogComment(int commentId) {
		Object obj=sessionFactory.getCurrentSession().get(BlogComment.class,commentId);
		try{
			if(obj==null){
				return null;
			}
			else {
				return (BlogComment)obj;
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public List<BlogComment> listBlogComments(int blogId) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from com.niit.model.BlogComment where blogId=:cid");
		query.setParameter("cid",blogId);
		return query.list();
	}

	public List<Blog> listAllApprovedBlogs() {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Blog where status='Approved'");
		return query.list();
	}

	public List<Blog> listPendingBlogs() {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Blog where status='Pending'");
		return query.list();
	}

}
