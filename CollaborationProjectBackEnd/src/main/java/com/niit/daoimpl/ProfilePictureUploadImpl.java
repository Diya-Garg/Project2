package com.niit.daoimpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.ProfilePictureUpload;
import com.niit.model.ProfilePicture;
import com.niit.model.UserDetails;

@Repository("profilePictureDAO")
@Transactional
public class ProfilePictureUploadImpl implements ProfilePictureUpload{

	@Autowired
	private SessionFactory sessionFactory;

	public void save(ProfilePicture profilePicture) {
		Session session=sessionFactory.getCurrentSession();
		Object obj=session.get(ProfilePicture.class,profilePicture.getLoginName());
		System.out.println(obj);
		if(obj==null){
		session.save(profilePicture);
		}
		else {
			session.merge(profilePicture);
		}
		
	}

	public ProfilePicture getProfilePicture(String loginName) {
		Session session=sessionFactory.getCurrentSession();
		ProfilePicture profilePicture=(ProfilePicture)session.get(ProfilePicture.class, loginName);
		return profilePicture;
	}
	
	
	
}
