package com.niit.controllers;

import java.util.Date;





import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;


import com.niit.model.Message;
import com.niit.model.OutputMessage;

@RestController
public class ChatController {

	/*
	 * Dependency to add
	 * spring web socket
	 * spring messaging
	 * Web socket Api
	 * sockjs
	 * stomp-websocket
	 * angular
	* */
	@MessageMapping("/chat")
	@SendTo("/topic/message")
	public OutputMessage sendMessage(Message message){
		return new OutputMessage(message,new Date());
	}
}
