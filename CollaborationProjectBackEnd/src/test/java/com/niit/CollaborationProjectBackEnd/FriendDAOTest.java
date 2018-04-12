package com.niit.CollaborationProjectBackEnd;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.BlogDAO;
import com.niit.dao.FriendDAO;
import com.niit.model.Blog;
import com.niit.model.BlogComment;
import com.niit.model.Friend;
import com.niit.model.UserDetails;


public class FriendDAOTest {

	static FriendDAO friendDAO;
	
	@BeforeClass
	public static void initialize(){
		System.out.println("Initializing Test Case");
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		
		friendDAO=(FriendDAO)context.getBean("friendDAO");
		System.out.println("Friend DAO : "+friendDAO);
	}

	@Test
	@Ignore
	public void sendFriendRequestTestCase(){
		Friend friend=new Friend();
		friend.setFriendLoginName("sagar.garg");
		friend.setLoginName("vasuagg");
		friend.setStatus("Pending");
		assertTrue("Problem in Sending Friend Request",friendDAO.sendFriendRequest(friend));
	}
	
	@Test
	@Ignore
	public void showRequestPendingListTestCase(){
		assertNotNull("No Pending Request Exist",friendDAO.showRequestPendingList("vasuagg"));
	}
	
	@Test
	@Ignore
	public void showSuggestedFriendTestCase(){
		List<UserDetails> listSuggestedFriends =friendDAO.showSuggestedFriend("vasuagg");
		 assertNotNull("Problem found ",listSuggestedFriends);
			System.out.println("<========================Suggested friends=====================>");
		 for(UserDetails UserDetails:listSuggestedFriends){
			 System.out.println(UserDetails.getLoginName());
		 }
	}
	
	@Test
	@Ignore
	public void showAllFriends(){
		assertNotNull("No Friend Exist",friendDAO.showAllFriends("vasuagg"));	
	}
	
	@Test
	@Ignore
	public void acceptFriendRequestTestCase(){
		assertTrue("Problem in Accepting Friend Request",friendDAO.acceptFriendRequest(50));
	}
	
	

}



