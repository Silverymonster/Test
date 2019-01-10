package com.jiale.shiro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jiale.service.ServiceJ;

//实例化
@Component
public class MyRealm extends AuthorizingRealm {
	
	@Autowired
	ServiceJ service;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		System.out.println("------------------------");
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		String principal = (String) principalCollection.getPrimaryPrincipal();
		System.out.println("用户名" + principal);
		Map map = new HashMap<>();
		map.put("username", principal);
		List<Map> lblogin = (List<Map>) service.lblogin(map);
		Map m = lblogin.get(0);
		System.out.println("realm中查询出来的用户数据" + m);
		if (m.get("rolename").equals("用户") || m.get("rolename").equals("教练")) {
			System.out.println("za：" + info);
			info.addRole("yh");
		}
		if (m.get("rolename").equals("店长") || m.get("rolename").equals("分店长")) {
			System.out.println("za：" + info);
			info.addRole("dz");
		}
		return info;
	}

	// 认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("--------------doGetAuthenticationInfo开始--------------");
		Map<String, Object> map = new HashMap<>();
		System.out.println("getPrincipal   " + token.getPrincipal());
		map.put("username", token.getPrincipal());
		List<?> login = service.login(map);
		System.out.println("login---" + login);
		String pwd = "";
		String source = "";
		if (login != null) {
			Map m = (Map<String, Object>) login.get(0);
			source = m.get("saltvalue").toString();
			pwd = m.get("encryption").toString();
			System.out.println("source---" + source + "---pwd---" + pwd);
			Map map2=new HashMap<>();
			map2.put("source", source);
			map2.put("pwd", pwd);
			String relname = getName();
			ByteSource bs = new Md5Hash(source);
			SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(token.getPrincipal(), pwd, bs, relname);
			Map map3=new HashMap<>();
			map3.put("username", token.getPrincipal());
			if(service.lblogin(map3).size()==1) {
				System.out.println("---" + info);
				System.out.println("--------------doGetAuthenticationInfo结束--------------");
				return info;
			}
		}else
		{
			return null;
		}
		return null;
		
	}

	// 证书匹配器
	@PostConstruct
	public void setCredentialsMatcher() {
		System.out.println("----------setCredentialsMatcher------------");
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
		matcher.setHashAlgorithmName("MD5");
		matcher.setHashIterations(1024);
		setCredentialsMatcher(matcher);
	}
}
