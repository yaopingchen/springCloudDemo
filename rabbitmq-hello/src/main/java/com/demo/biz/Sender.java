package com.demo.biz;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {
	@Autowired
	private AmqpTemplate template;
	public void send(){
		String context="hello "+new Date();
		System.err.println("Sender : "+context);
		this.template.convertAndSend("hello",context);
	}
}
