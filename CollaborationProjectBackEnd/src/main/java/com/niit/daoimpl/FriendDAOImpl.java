package com.niit.daoimpl;


import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.dao.FriendDAO;
import com.niit.model.Friend;
import com.niit.model.UserDetails;

@Repository("friendDAO")
@Transactional
public class FriendDAOImpl implements FriendDAO {

	@Autowired
	SessionFactory sessionFactory;

	public boolean sendFriendRequest(Friend friend) {
		try{
			System.out.println("In Send Friend Request function");
			Session session=sessionFactory.getCurrentSession();
			friend.setStatus("Pending");
			session.save(friend);
			System.out.println("Friend Request Sent..");
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteFriendRequest(int friendId) {
		try{
			System.out.println("In Delete Friend Request function...");
			Session session=sessionFactory.getCurrentSession();
			Friend friend=(Friend)session.get(Friend.class, friendId);
			if(friend.getStatus().equals("Pending")){
				session.delete(friend);
				System.out.println("Friend Request Deleted...");
			}
		
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<UserDetails> showSuggestedFriend(String loginname) {
		String sql="select loginname from userdetails where loginname not in (select friendloginname from Friend where loginname='"+loginname+"') and loginname!='"+loginname+"' and role='Role_User'";
		System.out.println(sql);
		Session session=sessionFactory.openSession();
		SQLQuery query=session.createSQLQuery(sql);
		List<Object> suggestFriendname=(List<Object>)query.list();
		List<UserDetails> suggestFriendList=new ArrayList<UserDetails>();
		int i=0;
		while(i<suggestFriendname.size())
		{
			UserDetails userDetail=(UserDetails)session.get(UserDetails.class, (String)suggestFriendname.get(i));
			suggestFriendList.add(userDetail);
			i++;
		}
		System.out.println("Suggested Friends : "+suggestFriendList);
		return suggestFriendList;
	}

	public List<Friend> showAllFriends(String loginname) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Friend where loginName =:currentuser and status='Approved')");
		query.setParameter("currentuser", loginname);
		List<Friend> listFriends = (List<Friend>) query.list();
		return listFriends;
	}

	public List<Friend> showRequestPendingList(String loginname) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Friend where loginName =:currentuser and status='Pending')");
		query.setParameter("currentuser", loginname);
		List<Friend> listFriends = (List<Friend>) query.list();
		return listFriends;
	}

	public boolean acceptFriendRequest(int friendId) {
		try{
		Session session=sessionFactory.getCurrentSession();
		Friend friend=(Friend)session.get(Friend.class,friendId);
		friend.setStatus("Approved");
		session.merge(friend);
		System.out.println("Friend Request Accepted...");
		return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

}
