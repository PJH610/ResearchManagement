package com.zhuoyue.researchManement.filter;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CorsFilter implements Filter
{
	private String allowOrigin;
	private String allowMethods;
	private String allowCredentials;
	private String allowHeaders;
	private String exposeHeaders;
	
	public void init(FilterConfig filterConfig) throws ServletException
	{
		allowOrigin = filterConfig.getInitParameter("allowOrigin");
		allowMethods = filterConfig.getInitParameter("allowMethods");
		allowCredentials = filterConfig.getInitParameter("allowCredentials");
		allowHeaders = filterConfig.getInitParameter("allowHeaders");
		exposeHeaders = filterConfig.getInitParameter("exposeHeaders");
	}
	
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
	{
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		if (StringUtils.isNotEmpty(allowOrigin))
		{
			List<String> allowOriginList = Arrays.asList(allowOrigin.split(","));
			if (CollectionUtils.isNotEmpty(allowOriginList))
			{
				String currentOrigin = request.getHeader("Origin");
				if (allowOriginList.contains(currentOrigin))
				{
					response.setHeader("Access-Control-Allow-Origin", currentOrigin);
				}
			}
		}
		if (StringUtils.isNotEmpty(allowMethods))
		{
			response.setHeader("Access-Control-Allow-Methods", allowMethods);
		}
		if (StringUtils.isNotEmpty(allowCredentials))
		{
			response.setHeader("Access-Control-Allow-Credentials", allowCredentials);
		}
		if (StringUtils.isNotEmpty(allowHeaders))
		{
			response.setHeader("Access-Control-Allow-Headers", allowHeaders);
		}
		if (StringUtils.isNotEmpty(exposeHeaders))
		{
			response.setHeader("Access-Control-Expose-Headers", exposeHeaders);
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}
	
	public void destroy()
	{
	
	}
}
