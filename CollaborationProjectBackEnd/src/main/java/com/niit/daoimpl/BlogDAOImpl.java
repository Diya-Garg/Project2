package com.niit.daoimpl;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.BlogDAO;
import com.niit.model.Blog;


@Repository("blogDAO")
@Transactional
public class BlogDAOImpl implements BlogDAO {

	@Autowired
	SessionFactory sessionFactory;

	
	public boolean addBlog(Blog blog) {
		System.out.println("SessionFactory : "+sessionFactory);
		System.out.println("Session :"+sessionFactory.getCurrentSession());
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

	public boolean deleteBlog(int blogId) {
		Session session=sessionFactory.getCurrentSession();
		Blog blogObj=(Blog)session.get(Blog.class,blogId);
		try{
			if(blogObj==null){
				return false;
			}
			else {
			session.delete(blogObj);
			return true;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateBlog(Blog blog) {
		Session session=sessionFactory.getCurrentSession();
		Blog blogObj=(Blog)session.get(Blog.class,blog.getBlogId());
		try{
			session.merge(blog);
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
			blog.setStatus("A");
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
		return false;
		}
	}

	public boolean rejectBlog(Blog blog) {
		try{
			blog.setStatus("NA");
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
		return false;
		}
	}

	public List<Blog> listBlogs(String userName) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Blog where loginname=:a");
		query.setParameter("a",userName);
		return query.list();
		
	}

}
