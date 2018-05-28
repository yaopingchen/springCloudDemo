package com.business.client;

import org.springframework.web.bind.annotation.PostMapping;

import com.business.client.dto.UserDTO;

public interface IUserService {
	@PostMapping("/queryUserByNick")
	UserDTO queryUserByNick(String nick);
}
