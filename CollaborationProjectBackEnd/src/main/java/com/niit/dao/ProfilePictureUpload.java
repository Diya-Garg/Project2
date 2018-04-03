package com.niit.dao;

import com.niit.model.ProfilePicture;

public interface ProfilePictureUpload {
	
	public void save(ProfilePicture profilePicture);
	public ProfilePicture getProfilePicture(String loginName);
}
