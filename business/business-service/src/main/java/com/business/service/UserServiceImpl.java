package com.business.service;

import org.springframework.web.bind.annotation.RestController;

import com.business.client.IUserService;
import com.business.client.dto.UserDTO;
@RestController("userService")
public class UserServiceImpl implements IUserService {

	@Override
	public UserDTO queryUserByNick(String nick) {
		UserDTO user=new UserDTO();
		user.setAge(18);
		user.setNick(nick);
		user.setId(1L);
		user.setPassword("123456");
		return user;
	}

}
