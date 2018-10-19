package com.demo.filter;

import com.demo.common.JwtUtils;
import com.demo.common.exception.BizException;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;

import javax.servlet.http.HttpServletRequest;

public class AccessFilter extends ZuulFilter {
	private static final String loginUrl="/userApi/user/login";
	private static Logger log=LoggerFactory.getLogger(AccessFilter.class);
	@Override
	public Object run() {
		RequestContext context=RequestContext.getCurrentContext();
		HttpServletRequest request=context.getRequest();
		log.info("send {} request to {}",request.getMethod(),request.getRequestURL().toString());
		//登录直接通过
		if(loginUrl.equalsIgnoreCase(request.getRequestURI())){
			return null;
		}
		Object accessToken=request.getParameter("accessToken");
		if(accessToken==null){
			log.warn("access token is empty");
			context.setSendZuulResponse(false);
			context.setResponseStatusCode(401);
			return null;
		}
		try{
			JwtUtils.validateJWT(accessToken.toString());
		} catch (BizException e){
			log.error("code:"+e.getCode()+" msg: "+e.getMessage(),e);
			context.setSendZuulResponse(false);
			context.setResponseStatusCode(401);
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
