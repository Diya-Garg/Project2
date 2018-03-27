package com.niit.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.JobDAO;
import com.niit.model.ApplyJob;
import com.niit.model.Job;

@Repository("jobDAO")
@Transactional
public class JobDAOImpl implements JobDAO{

	@Autowired
	SessionFactory sessionFactory;

	
	public boolean addJob(Job job) {
		try{
			sessionFactory.getCurrentSession().save(job);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteJob(Job job) {
		try{
			sessionFactory.getCurrentSession().delete(job);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateJob(Job job) {
		try{
			sessionFactory.getCurrentSession().update(job);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public List<Job> listAllJobs() {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from com.niit.model.Job");
		return query.list();
	}

	public boolean applyJob(ApplyJob applyJob) {
		try{
			sessionFactory.getCurrentSession().save(applyJob);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public List<ApplyJob> getAllAppliedJobDetails() {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from com.niit.model.ApplyJob");
		return query.list();
	}

	public Job getJob(int jobId) {
		try{
			Session session=sessionFactory.getCurrentSession();
			Job job=(Job)session.get(Job.class, jobId);
			return job;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}

}
