package com.niit.controllers;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.niit.dao.ProfilePictureUpload;
import com.niit.model.ProfilePicture;
import com.niit.model.UserDetails;
import com.sun.media.jfxmedia.Media;

@RestController
public class ProfilePictureController {

	@Autowired
	ProfilePictureUpload profilePictureDAO;
	
	@RequestMapping(value="/doUpload",method=RequestMethod.POST)
	public ResponseEntity<?> uploadPicture(@RequestParam(value="file")CommonsMultipartFile fileUpload,HttpSession session){
		
		UserDetails userDetails=(UserDetails)session.getAttribute("userObj");
		
		if(userDetails==null){
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
		else {
			ProfilePicture profile=new ProfilePicture();
			profile.setLoginName(userDetails.getLoginName());
			profile.setImage(fileUpload.getBytes());
			profilePictureDAO.save(profile);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value="/viewProfilePicture/{loginName}",method=RequestMethod.GET,produces={MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_PNG_VALUE})
	public byte[] getProfilePicture(@PathVariable("loginName")String loginName){
		
			ProfilePicture profile=profilePictureDAO.getProfilePicture(loginName);
			if(profile==null){
				return null;
			}
			else {
				System.out.println("Profile is not Null");
			}
			System.out.println("Login Name : "+profile.getLoginName());
			System.out.println("Image : "+profile.getImage());
			return profile.getImage();
			 
		
	}
	
	
}
