package com.center.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.business.client.dto.UserDTO;

@RestController
public class UserController {
	private static final Logger logger=LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserClient userclient;
	@GetMapping("/login")
	public Boolean login(UserDTO user){
		logger.debug(user==null?"user is null":user.toString());
		UserDTO userDTO=userclient.queryUserByNick(user.getNick());
		logger.debug(userDTO==null?"userDTO is null":userDTO.toString());
		return userDTO.getPassword().equals(user.getPassword());
		
	}
}
