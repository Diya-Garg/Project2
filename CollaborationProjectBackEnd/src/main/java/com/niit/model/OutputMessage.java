package com.niit.model;

import java.util.Date;

public class OutputMessage extends Message{

	private Date messageTime;
	
	public OutputMessage(Message original,Date msgTime){
		setId(original.getId());
		setMessage(original.getMessage());
		this.messageTime=msgTime;
	}

	public Date getMessageTime() {
		return messageTime;
	}

	public void setMessageTime(Date messageTime) {
		this.messageTime = messageTime;
	}
	
	
	
}
