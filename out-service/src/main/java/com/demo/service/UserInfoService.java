package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.business.client.dto.UserDTO;

@RestController
public class UserInfoService {
	@Autowired
	private UserClient userClient;
	@GetMapping("/getUser")
	public UserDTO getUser(String nick){
		return userClient.queryUserByNick(nick);
	}
}
