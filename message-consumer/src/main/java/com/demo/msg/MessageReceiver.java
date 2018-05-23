package com.demo.msg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;

import com.demo.dto.User;
@EnableBinding(Processor.class)
public class MessageReceiver {
	private static Logger log=LoggerFactory.getLogger(MessageReceiver.class);
	@StreamListener(Processor.INPUT)
	public void receive(User payload){
		log.info("payload:"+payload);
	}
}
