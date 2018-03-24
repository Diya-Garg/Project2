package com.niit.daoimpl;


import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.niit.dao.ForumDAO;
import com.niit.model.Forum;
import com.niit.model.ForumComment;

@Repository("forumDAO")
@Transactional
public class ForumDAOImpl implements ForumDAO{

	@Autowired
	SessionFactory sessionFactory;

	
	public boolean addForum(Forum forum) {
		
		try{
		sessionFactory.getCurrentSession().save(forum);
		return true;
		}
		catch(Exception e){e.printStackTrace();}
		return false;
	}

	public boolean deleteForum(Forum forum) {
		try{
			sessionFactory.getCurrentSession().delete(forum);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateForum(Forum forum) {
		try{
			sessionFactory.getCurrentSession().update(forum);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	public Forum getForum(int forumId) {
		Forum forum=(Forum)sessionFactory.getCurrentSession().get(Forum.class,forumId);
		return forum;
	}

	public boolean approveForum(Forum forum) {
		forum.setStatus("A");
		try{
			sessionFactory.getCurrentSession().update(forum);
			return true;
		}
		catch(Exception e){e.printStackTrace();}
		return false;
	}

	public boolean rejectForum(Forum forum) {
		forum.setStatus("NA");
		try{
			sessionFactory.getCurrentSession().update(forum);
			return true;
		}
		catch(Exception e){e.printStackTrace();}
		return false;
	}

	public List<Forum> listForums(String userName) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from com.niit.model.Forum where loginName=:uname");
		query.setParameter("uname",userName);
		return query.list();
	}

	public boolean addForumComment(ForumComment ForumComment) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteForumComment(ForumComment ForumComment) {
		// TODO Auto-generated method stub
		return false;
	}

	public ForumComment getForumComment(int commentId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ForumComment> listForumComments(int ForumId) {
		// TODO Auto-generated method stub
		return null;
	}

}
