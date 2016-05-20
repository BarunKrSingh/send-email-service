package com.telefonica.email;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.telefonica.domain.EmailContext;
import com.telefonica.messaging.RabbitMQReceiver;


@Configuration
public class ParseRabbitMessageForEmailInput {	
	
	private static final Logger logger= LoggerFactory.getLogger(RabbitMQReceiver.class);
	
	@Autowired
	private EmailSender emailSender;

	private DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
	
	private EmailContext getEmailDetailsfromXML(String message)
	{	
		 DocumentBuilder builder;
		 EmailContext attributes=new EmailContext();
		 
		 try
		 {			 
			 builder= factory.newDocumentBuilder();	
			 Document document= builder.parse(new InputSource(new StringReader(message)));
			 NodeList msg= document.getElementsByTagName("msg");
			 
			 if(msg.getLength()>0)
			 {
				 String sender=document.getElementsByTagName("se").item(0).getTextContent();
				 attributes.setFrom(sender);
				 
				 String reciever=document.getElementsByTagName("re").item(0).getTextContent();
				 attributes.setTo(reciever);
				 
				 String subject=document.getElementsByTagName("sb").item(0).getTextContent();
				 attributes.setSubject(subject);
				 
				 String body=document.getElementsByTagName("bd").item(0).getTextContent();
				 attributes.setBody(body);	
				 
				 logger.debug("Succesfully extracted the email content from message taken from RabbitMQ for sending email:");
			 }
			 else				 
			 {
				 logger.debug("Invalid Input from the Queue:");
				 return null;
			 }
			 
		 }
		 catch(Exception e)
		 {
			 logger.error("Exception occured extracting the email content details from the rabbit message due to :{}", e);
		 }
		 return attributes;
    
	}
	
	public void emailOperation( String stringifiedXML)	{
		 
		 EmailContext emailContext = getEmailDetailsfromXML(stringifiedXML);
		 emailSender.sendEmail(emailContext);
	}
}

