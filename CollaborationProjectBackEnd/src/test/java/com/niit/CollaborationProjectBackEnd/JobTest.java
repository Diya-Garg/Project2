package com.niit.CollaborationProjectBackEnd;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.BlogDAO;
import com.niit.dao.JobDAO;
import com.niit.model.ApplyJob;
import com.niit.model.Forum;
import com.niit.model.Job;

public class JobTest {

	static JobDAO jobDAO;
	
	@BeforeClass
	public static void initialize(){
		System.out.println("Initializing Test Case");
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		
		jobDAO=(JobDAO)context.getBean("jobDAO");
		System.out.println("Blog DAO : "+jobDAO);
	}
	
	
	@Test
	@Ignore
	public void testAddJob(){
		
		try{
		Job job=new Job();
		job.setJobDesignation("Business Development Executive" ); 
		job.setCompany("Just Dial");
		job.setSalary(25000);
		job.setLocation("Meerut");
		job.setJobDesc("To explore the market and create customers for the company");
		
		String dateToApply="20-04-2018";
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		
		job.setLastDateApply(sdf.parse(dateToApply));
		
		assertEquals("Job Added Succesfully",true,jobDAO.addJob(job));
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	

	@Test
	@Ignore
	public void listAllJobs(){
		List<Job> jobs=jobDAO.listAllJobs();
		if(jobs.size()==0){
			assertTrue("Jobs Doesnt exist for user",jobs.size()==0);
		}
		else {
			for(Job job :jobs){
				System.out.println("\n\n"+job);
			}
		}
	}
	
	
	
	@Test
	@Ignore
	public void testUpdateJob(){
		Job job=jobDAO.getJob(50);
		job.setJobDesignation("Sr. Technical Mentor");
		
		assertEquals("Succesfully updated the designation of the job", true,jobDAO.updateJob(job));
	}
	
	@Test
	@Ignore
	public void testDeleteJob(){
		Job job=jobDAO.getJob(150);
		assertEquals("Succesfully deleted the job", true,jobDAO.deleteJob(job));
	}
	
	
	@Test
	@Ignore
	public void applyJob(){
		ApplyJob applyJob=new ApplyJob();
		applyJob.setJobId(50);
		applyJob.setLoginname("Divya Garg");
		applyJob.setApplyDate(new Date());
		assertEquals("Succesfully applied the job",true,jobDAO.applyJob(applyJob));
	}
	
	@Test
	@Ignore
	public void listAllAppliedJobs(){
		List<ApplyJob> applyJobs=jobDAO.getAllAppliedJobDetails();
		if(applyJobs.size()==0){
			assertTrue("No one has applied for this job",applyJobs.size()==0);
		}
		else {
			for(ApplyJob applyJob :applyJobs){
				System.out.println("\n\n"+applyJob);
			}
		}
	}
}


















