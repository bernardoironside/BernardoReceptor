package com.senac.receptor.configuration;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.annotation.PostConstruct;

public class MQConfig  {
	@Autowired
	private AmqpAdmin amqpAdmin;
	private Queue queue;
	
	private Queue queue (String queueName){
		return new Queue(queueName, true, false, false);
	}
	
	private DirectExchange createDirectExchange(){
		return new DirectExchange("produtormq");
	}
	
	@PostConstruct
	private void Create (){
		this.queue = new Queue("fila-get-produto");
		
		// Create the direct exchange
		DirectExchange directExchange = createDirectExchange();
		
		// Create the binding
		Binding binding = new Binding(queue.getName(), Binding.DestinationType.QUEUE,
		directExchange.getName(), queue.getName(), null);
		amqpAdmin.declareQueue(queue);
		amqpAdmin.declareExchange(directExchange);
		amqpAdmin.declareBinding(binding);
	}
}
