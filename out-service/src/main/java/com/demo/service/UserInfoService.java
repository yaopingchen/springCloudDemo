package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.business.client.dto.UserDTO;

@RestController
@EnableBinding(CustomSource.class)
public class UserInfoService {
	@Autowired
	private UserClient userClient;

	@GetMapping("/getUser")
	public UserDTO getUser(String nick) {
		return userClient.queryUserByNick(nick);
	}

	@Bean
	@InboundChannelAdapter(value = CustomSource.MYOUTPUT, poller = @Poller(fixedDelay = "10000"))
	public MessageSource<String> timerMessageSource() {
		return new MessageSource<String>() {

			public Message<String> receive() {
				return new GenericMessage<String>("{\"nick\":\"张三\",\"age\":25}");
			}
		};
	}

}
