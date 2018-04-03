package com.niit.daoimpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.ProfilePictureUpload;
import com.niit.model.ProfilePicture;

@Repository("profilePictureDAO")
@Transactional
public class ProfilePictureUploadImpl implements ProfilePictureUpload{

	@Autowired
	private SessionFactory sessionFactory;

	public void save(ProfilePicture profilePicture) {
		Session session=sessionFactory.getCurrentSession();
		session.save(profilePicture);
		
		
	}

	public ProfilePicture getProfilePicture(String loginName) {
		Session session=sessionFactory.getCurrentSession();
		ProfilePicture profilePicture=(ProfilePicture)session.get(ProfilePicture.class, loginName);
		return profilePicture;
	}
	
	
	
}
