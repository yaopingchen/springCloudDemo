package com.demo.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class AccessFilter extends ZuulFilter {
	private static Logger log=LoggerFactory.getLogger(AccessFilter.class);
	@Override
	public Object run() {
		RequestContext context=RequestContext.getCurrentContext();
		HttpServletRequest request=context.getRequest();
		log.info("send {} request to {}",request.getMethod(),request.getRequestURL().toString());
		Object accessToken=request.getParameter("accessToken");
		if(accessToken==null){
			log.warn("access token is empty");
			context.setSendZuulResponse(false);
			context.setResponseStatusCode(401);
			return null;
		}
		log.info("access token ok");
		return null;
	}

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}

}
