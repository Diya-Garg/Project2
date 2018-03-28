package com.niit.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.UserDAO;
import com.niit.model.Job;
import com.niit.model.UserDetails;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	
	public boolean registerUser(UserDetails user) {
		try{
			sessionFactory.getCurrentSession().save(user);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateUser(UserDetails user) {
		try{
			sessionFactory.getCurrentSession().update(user);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public UserDetails getUser(String loginName) {
		try{
			Session session=sessionFactory.getCurrentSession();
			UserDetails user=(UserDetails)session.get(UserDetails.class, loginName);
			return user;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public List<UserDetails> getUserDetails() {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from com.niit.model.UserDetails");
		return query.list();
	}

	public boolean deleteUser(UserDetails user) {
		try{
			sessionFactory.getCurrentSession().delete(user);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean checkLogin(UserDetails user) {
		try{
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from UserDetails where loginName=:x and password=:y");
			query.setParameter("x",user.getLoginName());
			query.setParameter("y",user.getPassword());
			
			List<UserDetails> list=query.list();
			if(list.size()>0){
				UserDetails userDetailsObj=list.get(0);
				return true;
			}
			else {
				return false;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateOnlineStatus(String status, UserDetails user) {
		try{
			user.setOnlineStatus(status);
			sessionFactory.getCurrentSession().update(user);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

}
