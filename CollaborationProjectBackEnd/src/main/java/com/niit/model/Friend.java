package com.niit.model;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
@SequenceGenerator(name="friendidseq",sequenceName="friend_id_sequence")
public class Friend {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="friendidseq")
	private int friendId;
	private String loginName;
	private String friendLoginName;
	private String status; //pending , accepted or none
	
	public int getFriendId() {
		return friendId;
	}
	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getFriendLoginName() {
		return friendLoginName;
	}
	public void setFriendLoginName(String friendLoginName) {
		this.friendLoginName = friendLoginName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Friend [friendId=" + friendId + ", loginName=" + loginName
				+ ", friendLoginName=" + friendLoginName + ", status=" + status
				+ "]";
	}
	
	
	
}
