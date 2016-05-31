package com.telefonica.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.telefonica.propertyconfig.MailPropertyConfig;

@Configuration
public class MailConfig {
	
	@Autowired
	private MailPropertyConfig mailPropertyConfig;

	@Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.auth", mailPropertyConfig.isAuth());
        mailProperties.put("mail.smtp.starttls.enable", mailPropertyConfig.isStarttls());
        mailSender.setJavaMailProperties(mailProperties);
        mailSender.setHost(mailPropertyConfig.getHost());
        mailSender.setPort(mailPropertyConfig.getPort());
        mailSender.setProtocol(mailPropertyConfig.getProtocol());
        //mailSender.setUsername(mailPropertyConfig.getUsername());
        //mailSender.setPassword(mailPropertyConfig.getPassword());
        return mailSender;
    }
}
