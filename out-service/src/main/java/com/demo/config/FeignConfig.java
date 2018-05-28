package com.demo.config;

import java.util.Enumeration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class FeignConfig implements RequestInterceptor {
	private static final String tokenKey = "accessToken";

	public void apply(RequestTemplate template) {
		template.header(tokenKey, getHeaders(getHttpServletRequest()).get(tokenKey));

	}

	private HttpServletRequest getHttpServletRequest() {
		try {
			return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		} catch (Exception e) {
			return null;
		}
	}

	private Map<String, String> getHeaders(HttpServletRequest request) {
		Map<String, String> map = new ConcurrentHashMap<String, String>();
		Enumeration<String> enumeration = request.getHeaderNames();
		while (enumeration.hasMoreElements()) {
			String key = enumeration.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}
		return map;
	}

}