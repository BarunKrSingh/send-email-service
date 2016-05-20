package com.telefonica.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.telefonica.domain.EmailContext;
import com.telefonica.messaging.RabbitMQReceiver;

@Component
public class EmailSender {

	private static final Logger logger = LoggerFactory.getLogger(RabbitMQReceiver.class);

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendEmail(EmailContext emailContext) {

		try {

			SimpleMailMessage mailMessage = new SimpleMailMessage();
			
			//mailMessage.setTo(emailContext.getTo());
			//This is for test
			mailMessage.setTo("barun.singh@emc.com");
			
			mailMessage.setFrom(emailContext.getFrom());
			mailMessage.setSubject(emailContext.getSubject());
			mailMessage.setText(emailContext.getBody());
			javaMailSender.send(mailMessage);
			
			logger.debug("Successfully sent the email to the user:{} with information on password change:",emailContext.getTo());

		} catch (Exception e) {
			logger.error("Exception occured sending email to:{} due to :{}", emailContext.getTo(), e);
		}
	}
}