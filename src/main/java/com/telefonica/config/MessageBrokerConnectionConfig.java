package com.telefonica.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.telefonica.messaging.Receiver;
import com.telefonica.propertyconfig.MessageBrokerPropertyConfig;

@Configuration
public class MessageBrokerConnectionConfig {	
	
	@Autowired
	private MessageBrokerPropertyConfig rabbitPropertyConfig;
	
	@Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitPropertyConfig.getHostname());
        connectionFactory.setUsername(rabbitPropertyConfig.getUsername());
        connectionFactory.setPassword(rabbitPropertyConfig.getPassword());
        connectionFactory.setPort(rabbitPropertyConfig.getPort());
        return connectionFactory;
    }
	
	@Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }
	
	@Bean
	Queue queue() {
		return new Queue(rabbitPropertyConfig.getQueueName(), false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(rabbitPropertyConfig.getExchangeName());
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(rabbitPropertyConfig.getQueueName());		
	}	
	
	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
		connectionFactory = connectionFactory();
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();		
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(rabbitPropertyConfig.getQueueName());
		container.setMessageListener(listenerAdapter);		
		return container;
	}

    @Bean
    Receiver receiver() {
        return new Receiver();
    }

	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}

}
