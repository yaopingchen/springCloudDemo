package com.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class TestController {
	@Value("${from}")
	private String from;
	@Value("${password}")
	private String password;

	@RequestMapping("/from")
	public String from() {
		return from;
	}
	@RequestMapping("/password")
	public String password() {
		return password;
	}
}
