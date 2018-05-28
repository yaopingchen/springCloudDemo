package com.demo.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.business.client.dto.UserDTO;
import com.demo.config.FeignConfig;

@FeignClient(value = "api-gateway", configuration = FeignConfig.class)
public interface UserClient {
	@PostMapping("/business/queryUserByNick")
	UserDTO queryUserByNick(String nick);
}
