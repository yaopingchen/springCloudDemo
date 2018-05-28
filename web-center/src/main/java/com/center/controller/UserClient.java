package com.center.controller;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.business.client.IUserService;
@FeignClient("business")
public interface UserClient extends IUserService{
}
