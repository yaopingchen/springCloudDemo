package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.business.client.dto.UserDTO;

@RestController
@EnableBinding(CustomSource.class)
public class UserInfoService {
	@Autowired
	private UserClient userClient;
	@Autowired
	private CustomSource customSource;

	@GetMapping("/getUser")
	public UserDTO getUser(String nick) {
		sendMsg();
		return userClient.queryUserByNick(nick);
	}

	private void sendMsg() {
		customSource.myOutput().send(MessageBuilder.withPayload("{\"nick\":\"张三\",\"age\":25}").build());
		
	}
}
