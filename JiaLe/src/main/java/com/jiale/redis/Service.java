package com.jiale.redis;

import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class Service {
	@Autowired
	redisDao redisdao;

	public void setStr(String key, String value) {
		redisdao.setStr(key, value);
	}

	public String getStr(String key) {
		return redisdao.getStr(key);
	}

	public void rPushList(String key, String value) {
		redisdao.rPushList(key, value);

	}

	public String lPopList(String key) {
		return redisdao.lPopList(key);
	}

	public void delKey(String key) {
		redisdao.delKey(key);
	}
}
