package com.jiale.intercrptor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.jiale.redis.Service;
import com.jiale.service.ServiceJ;

public class LoginHandle implements HandlerInterceptor {

	Logger logger = Logger.getLogger(LoginHandle.class);
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
	String pre;
	String after;

	@Autowired
	Service ser;
	@Autowired
	ServiceJ service;

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object,
			Exception exception) throws Exception {
		System.out.println("拦截后的after方法");
		String username = ser.getStr("username");
		if (username != null && exception != null) {
			after = df.format(new Date());
			System.out.println("--username--:" + username);
			MDC.put("errorusername", username);
			MDC.put("errorname", exception);
			MDC.put("errortime", after);
			MDC.put("errorlog", 1);
			object = MDC.get("errorname");
			System.out.println(object);
			logger.error("异常日志记录完成");
		}
		System.out.println("通过");
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView mv)
			throws Exception {
		System.out.println("拦截后的post方法");
		StringBuffer performancename = request.getRequestURL();
		System.out.println(request.getRemoteHost());
		after = df.format(new Date());
		HashMap<String, String> map = new HashMap<>();
		map.put("performancename", performancename.toString());
		map.put("performancestar", pre);
		map.put("performancestop", after);
		map.put("performancelog", "1");
		service.performancelog(map);
		System.out.println("性能日志记录完成");
		String username = ser.getStr("username");
		if (username != null) {
			after = df.format(new Date());
			StringBuffer operationname = request.getRequestURL();
			System.out.println("+++username:+++" + username);
			System.out.println("operationname:" + operationname);
			MDC.put("operationusername", username);
			MDC.put("operationname", operationname);
			MDC.put("operationtime", after);
			MDC.put("operationlog", 1);
			logger.info("操作日志记录完成");
			System.out.println("操作日志记录完成");
		} else {
			System.out.println("session到期");
			mv.setViewName("error");
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		System.out.println("拦截前");
		pre = df.format(new Date());
		// 操作日志
		return true;
	}

}
