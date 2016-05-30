package com.telefonica.email;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.telefonica.domain.EmailContext;
import com.telefonica.messaging.RabbitMQReceiver;

@Component
public class EmailSender {

	private static final Logger logger = LoggerFactory.getLogger(RabbitMQReceiver.class);

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendEmail(final EmailContext emailContext) {

		try {

			//SimpleMailMessage mailMessage = new SimpleMailMessage();
			
			MimeMessagePreparator preparator = new MimeMessagePreparator() {
	            @Override
	            public void prepare(MimeMessage mimeMessage) throws Exception {
	               MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
	               
	               //message.setTo(emailContext.getTo());
	               message.setTo("barun.singh@emc.com");
	               message.setFrom(emailContext.getFrom());
	               message.setSubject(emailContext.getSubject());

	               message.setText(emailContext.getBody(), true);
	            }
	         };			
	         
	         javaMailSender.send(preparator);
			
			 logger.debug("Successfully sent the email to the user:{} with information on password change:",emailContext.getTo());

		} catch (Exception e) {
			logger.error("Exception occured sending email to:{} due to :{}", emailContext.getTo(), e);
		}
	}
}