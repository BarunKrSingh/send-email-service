package com.telefonica.messaging;

import org.springframework.beans.factory.annotation.Autowired;

import com.telefonica.email.ParseXMLForEmailInput;

public class RabbitMQReceiver {
	
	@Autowired
	private ParseXMLForEmailInput parsexml;

	public void receiveMessage(String message) {
		System.out.println("Received <" + message + ">");
		parsexml.callSendEmail(message);
		
	}

}
