package com.telefonica.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.telefonica.email.ParseRabbitMessageForEmailInput;
import com.telefonica.propertyconfig.RabbitPropertyConfig;

public class RabbitMQReceiver {
	
	private static final Logger logger= LoggerFactory.getLogger(RabbitMQReceiver.class);
	
	@Autowired
	private RabbitPropertyConfig rabbitPropertyConfig;
	
	@Autowired
	private ParseRabbitMessageForEmailInput parseXml;

	public void receiveMessage(String message) {
				
		System.out.println(message);
		logger.debug("Polling the message: {} from the Queue:{} for extracting email details:", message,rabbitPropertyConfig.getQueueName());
		parseXml.emailOperation(message);		
	}

}
