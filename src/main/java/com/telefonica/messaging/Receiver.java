package com.telefonica.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.telefonica.propertyconfig.MessageBrokerPropertyConfig;
import com.telefonica.util.EmailMessageParser;

public class Receiver {
	
	private static final Logger logger= LoggerFactory.getLogger(Receiver.class);
	
	@Autowired
	private MessageBrokerPropertyConfig rabbitPropertyConfig;
	
	@Autowired
	private EmailMessageParser parseXml;

	public void receiveMessage(String message) {
				
		System.out.println(message);
		logger.debug("Polling the message: {} from the Queue:{} for extracting email details:", message,rabbitPropertyConfig.getQueueName());
		parseXml.emailOperation(message);		
	}

}
