package com.telefonica.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendEmail {
	
	public static  void sendEmailOperation(SendEmailAttributes attributes) {
		
		
		//get the attribute which is saved
		
		
		// Sender's email ID needs to be mentioned
	     
//	      final String username1 = "abc";//change accordingly
//	      final String password1 = "*****";//change accordingly
//	      
	      
	      // Recipient's email ID needs to be mentioned.
		  String from1 = attributes.getSenderEmailId();//change accordingly
	      String to1 = attributes.getRecieverEmailId();//change accordingly
	      String subject=attributes.getSubject();
	      String body1=attributes.getBody();
		
	      System.out.println(from1+":"+to1+":"+subject+":"+body1+":");
	      
		 // Sender's email ID needs to be mentioned
	      String from = "satyampce@gmail.com";//change accordingly
	      final String username = "satyampce@gmail.com";//change accordingly
	      final String password = "P@ssw0rdemc";//change accordingly
	      
	      
	      // Recipient's email ID needs to be mentioned.
	      String to = "search4satty@gmail.com";//change accordingly

	     

	      // smptp server
	      String host = "smtp.gmail.com";

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "587");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	      new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	         }
	      });

	      try {
	         // Create a default MimeMessage object.
	         Message message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.setRecipients(Message.RecipientType.TO,
	         InternetAddress.parse(to));

	         // Set Subject: header field
	         message.setSubject(attributes.getSubject());

	         // Now set the actual message
	         message.setText(attributes.getBody());

	         // Send message
	         Transport.send(message);

	         System.out.println("Sent message successfully....");

	      } catch (MessagingException e) {
	            throw new RuntimeException(e);
	      }
	   }
}