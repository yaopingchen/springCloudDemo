package com.demo.biz;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
@EnableBinding(value={SinkSender.SinkOutput.class})
public class SinkSender {
	@Bean
	@InboundChannelAdapter(value="output-1",poller=@Poller(fixedDelay="2000"))
	public MessageSource<String> timerMessageSource() {
		return new  MessageSource<String>() {
			
			@Override
			public Message<String> receive() {
				// TODO Auto-generateStringd method stub
				return new GenericMessage<String>("{\"name\":\"张三\",\"age\":25}");
			}
		};
	}
	public interface SinkOutput{
		@Output("output-1")
		MessageChannel outPut1();
		@Output("output-2")
		MessageChannel outPut2();
	}
}
