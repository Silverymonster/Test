package com.jiale.Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class doFilter implements Filter {

	/**
	 * FilterChain 过滤链 origin 起源
	 */
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		String origin = servletRequest.getRemoteHost() + ":" + servletRequest.getRemotePort();
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "Authentication");
		/*
		 * response.setHeader("Access-Control-Allow-Methods","POST,GET,OPTIONS,DELETE");
		 * response.setHeader("Access-Control-Max-Age","3600");
		 * response.setHeader("Access-Control-Allow-Credentials","true");
		 */

		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {
		// 销毁
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// 初始化
	}
}
