package com.business.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.web.bind.annotation.RestController;

import com.business.client.IUserService;
import com.business.client.dto.UserDTO;
@RestController("userService")
@EnableBinding(CustomSink.class)
public class UserServiceImpl implements IUserService {
private static final Logger logger =LoggerFactory.getLogger(UserServiceImpl.class);
	@Override
	public UserDTO queryUserByNick(String nick) {
		UserDTO user=new UserDTO();
		user.setAge(18);
		user.setNick(nick);
		user.setId(1L);
		user.setPassword("123456");
		return user;
	}
	@StreamListener(CustomSink.MYINPUT)
	public void receiveMsg(UserDTO user){
		logger.info("receiveMsg : "+user.toString());
	}
	

}
