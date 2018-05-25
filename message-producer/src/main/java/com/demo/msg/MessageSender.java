package com.demo.msg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
@EnableBinding(value={Processor.class})
public class MessageSender {
	private static Logger log=LoggerFactory.getLogger(MessageSender.class);
	@Bean
	@InboundChannelAdapter(value=Processor.OUTPUT,poller=@Poller(fixedDelay="2000"))
	public MessageSource<String> timerMessageSource() {
		return new  MessageSource<String>() {

			public Message<String> receive() {
					return new GenericMessage<String>("{\"name\":\"张三\",\"age\":25,\"id\":5}");
			}
		};
	}
	@StreamListener(Processor.INPUT)
	public void receive(Object payload){
		log.info("payload:"+payload);
	}
}
