package com.telefonica.email;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.context.annotation.Configuration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


@Configuration
public class ParseXMLForEmailInput {

	 DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
	SendEmailAttributes getInputfromXML(String xml)
	{
		
		System.out.println(xml+"1");
		 DocumentBuilder builder;
		 SendEmailAttributes attributes=new SendEmailAttributes();
		 try
		 {
			 builder= factory.newDocumentBuilder();
			 
			 //Using string  reader
			 System.out.println(xml+"2");
			 Document document= builder.parse(new InputSource(new StringReader(xml)));
			 NodeList msg= document.getElementsByTagName("msg");
			 if(msg.getLength()>0)
			 {
				 
				 String sender=document.getElementsByTagName("se").item(0).getTextContent();
				 attributes.setSenderEmailId(sender);
				 System.out.println(sender);
				 String reciever=document.getElementsByTagName("re").item(0).getTextContent();
				 attributes.setRecieverEmailId(reciever);
				 System.out.println(reciever);
				 String subject=document.getElementsByTagName("sb").item(0).getTextContent();
				 attributes.setSubject(subject);
				 System.out.println(subject);
				 String body=document.getElementsByTagName("bd").item(0).getTextContent();
				 attributes.setBody(body);
				 System.out.println(body);
			 }
			 else
				 
			 {
				 System.out.println("Invalid Input");
				 return null;
			 }
			 
		 }
		 catch(Exception e)
		 {
			 
		 }
		 return attributes;
    
	}
	
	public void callSendEmail( String StringifiedXML)
	{
		 //String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><msg><se>abc12467@telefonica.es</se><re>user1@telefonica.es</re><sb>New password for logging into the Telefonica portal</sb><bd>Your new password is 0z1n6rup generated on 2016-05-16</bd></msg>";
		 SendEmailAttributes attributes=new ParseXMLForEmailInput().getInputfromXML(StringifiedXML);
		 new SendEmail().sendEmailOperation(attributes);
	}
}

