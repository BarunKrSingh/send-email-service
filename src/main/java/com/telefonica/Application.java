package com.telefonica;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.telefonica.config.RabbitPropertyConfig;
import com.telefonica.messaging.RabbitMQReceiver;

@SpringBootApplication
public class Application {
	
	/*@Autowired
	RabbitTemplate rabbitTemplate;	
	
	@Autowired
	RabbitPropertyConfig config;
	
	@Autowired
	MessageListenerAdapter listenerAdapter;*/

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	
}
