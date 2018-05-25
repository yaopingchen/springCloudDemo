package com.business.client;

import com.business.client.dto.UserDTO;

public interface IUserService {
	UserDTO queryUserByNick(String nick);
}
