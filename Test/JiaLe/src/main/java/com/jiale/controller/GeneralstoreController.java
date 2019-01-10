package com.jiale.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiale.redis.Service;
import com.jiale.service.ServiceJ;
import com.jiale.util.ChangString;
import com.jiale.util.ToJson;

/**
 * 
 * @author JiaLe 总店控制类
 *
 */

@Controller
@Scope("prototype")
public class GeneralstoreController {

	@Autowired
	ServiceJ service;

	@Autowired
	Service ser;

	String username;

	/**
	 * 
	 * @param json
	 * @return
	 * @throws IOException
	 */

	// 登录
	@RequestMapping(value = "/lblogin", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String lblogin(@RequestParam Map<Object, Object> map, HttpServletRequest request) {
		System.out.println("------------------lblogin----------------");
		System.out.println("--------" + map.get("username"));
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(map.get("username").toString(),
				map.get("encryption").toString());
		Map<String, Object> map1 = new HashMap<>();
		map1.put("data", false);
		System.out.println("进入到查询权限的方法了------------");
		try {
			subject.login(token);
			map1.put("data", true);
			boolean hasRole = subject.hasRole("yh");
				if (hasRole == true) {
					HttpSession session = request.getSession();
					session.setAttribute("username", map.get("username"));
					System.out.println("session:" + (String) session.getAttribute("username"));
					ser.delKey("username");
					// redis存值
					ser.setStr("username", (String) session.getAttribute("username"));
					System.out.println("redis:" + ser.getStr("username"));
					System.out.println("------------");
					return ChangString.getstring(map1);
				}else {return null;}
		} catch (Exception e) {
			System.out.println("登录失败");
		}
		return null;
	}

	// 注册用户
	@RequestMapping(value = "/lbinsertuser", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String lbinsertuser(@RequestParam Map<String, String> map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("-------------------lbinsertuser---------------");
		System.out.println("进入了注册用户方法------------");
		System.out.println("传过来的code是：" + map.get("code"));
		System.out.println("code是：" + ser.getStr("code"));
		if (map.get("code").equals(ser.getStr("code"))) {
			String saltSource = RandomStringUtils.random(6, "123456789abcdefghijklmnopqrstuvwxyz");
			System.out.println("盐值是：" + saltSource);
			String hashAlgorithmName = "MD5";
			String credentials = map.get("password");
			Object salt = new Md5Hash(saltSource);
			// System.out.println(salt);
			int hashIterations = 1024;
			Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
			System.out.println("加密后：" + result);
			HashMap<Object, Object> map2 = new HashMap<>();
			map2.put("username", map.get("username"));
			map2.put("saltvalue", saltSource);
			map2.put("encryption", result.toString());
			map2.put("sex", map.get("sex"));
			map2.put("phone", map.get("phone"));
			System.out.println(map2);
			service.lbinsertuserrole(map2);//添加用户角色中间表
			return ToJson.toJsonArray(service.lbinsertuser(map2));
		} else {
			return null;
		}
	}
	
	// 注册店长
	@RequestMapping(value = "/lbinsertdz", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String lbinsertdz(@RequestParam Map<String, String> map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("-----------------lbinsertdz-----------------");
		System.out.println("进入了注册店长方法------------");
			//service.lbinsertuserrole(map);//添加用户角色中间表
			List<Map> list=(List<Map>) service.lbselectuserrole(map);
			for (Map map3 : list) {
				System.out.println(map3.get("userid"));
				Map map1 = new HashMap();
				map1.put("userid", map3.get("userid").toString());
				map.put("sid", map3.get("userid").toString());
				System.out.println(map1);
				service.lbupdateuid(map);//修改上一级
				service.lbupdateuserrole(map1);//修改用户角色中间表
				service.lbupdateshoprole(map1);//修改角色店鋪中間表
			}
			return ToJson.toJsonArray(service.lbinsertdz(map));
	}

	// 查询图片
	@RequestMapping(value = "/lbselectpic", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String lbselectpic(@RequestParam Map<String, String> map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("---------------lbselectpic-------------------");
		System.out.println("进入了查询图片的方法:------------");
		System.out.println(map);
		return ToJson.toJsonArray(service.lbselectpic(map));
	}

	// 退出登录
	@RequestMapping(value = "/lblogout", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String lblogout(@RequestParam Map<Object, Object> map, HttpServletRequest request) {
		System.out.println("----------------lblogout------------------");
		Subject subject = SecurityUtils.getSubject();
		boolean hasRole = subject.hasRole("yh");
		ser.delKey("username");
		System.out.println("退出成功------------");
		return ChangString.getstring(map);
	}

	// 随机生成验证码
	private final static int codeLength = 5;
	public static String getcode() {
		Random rand = new Random();
		int a;
		String result = "";
		for (int j = 0; j < codeLength; j++) {
			a = Math.abs(rand.nextInt() % 9);
			result += String.valueOf(a);
		}
		return result;
	}

	// 发送短信
	@RequestMapping(value = "/send", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String send(@RequestParam Map map) {
		System.out.println("----------------send------------------");
		System.out.println(map.get("phone") + "---------------");
		String code = GeneralstoreController.getcode();
		ser.delKey("code");
		ser.setStr("code", code);
		System.out.println(code);
		try {
			service.send(map.get("phone").toString(), code);
		} catch (Exception e) {
		}
		return "success";
	}

	// 查询用户表下的教练
	@RequestMapping(value = "/lbselectuserinfo", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String lbselectuserinfo(@RequestParam Map<String, String> map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("-----------------lbselectuserinfo-----------------");
		System.out.println("进入了查询用户信息的方法:------------");
		System.out.println(map);
		return ToJson.toJsonArray(service.lbselectuserinfo(map));
	}
	
	// 模糊查询搜索用户教练店铺
	@RequestMapping(value = "/lbselectuser", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String lbselectuser(@RequestParam Map<String, String> map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("---------------lbselectuser-------------------");
		System.out.println("进入了模糊查询搜索用户教练店铺的方法:------------");
		System.out.println(map);
		return ToJson.toJsonArray(service.lbselectuser(map));
	}
	
	// 查询模板店铺下的信息
	@RequestMapping(value = "/lbselectshopinfo", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String lbselectshopinfo(@RequestParam Map<String, String> map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("---------------lbselectshopinfo-------------------");
		System.out.println("进入了查询模板店铺下的信息的方法:------------");
		System.out.println(map);
		return ToJson.toJsonArray(service.lbselectshopinfo(map));
	}
	
	// 查询模板店铺下的教练
	@RequestMapping(value = "/lbselectshopcoach", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String lbselectshopcoach(@RequestParam Map<String, String> map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("---------------lbselectshopcoach-------------------");
		System.out.println("进入了查询模板店铺下的教练的方法:------------");
		System.out.println(map);
		System.out.println(service.lbselectshopcoach(map)+"123");
		return ToJson.toJsonArray(service.lbselectshopcoach(map));
	}	
			
	// 查询模板店铺下的器材
	@RequestMapping(value = "/lbselectshopequipment", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String lbselectshopequipment(@RequestParam Map<String, String> map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("---------------lbselectshopequipment-------------------");
		System.out.println("进入了查询模板店铺下的器材的方法:------------");
		System.out.println(map);
		return ToJson.toJsonArray(service.lbselectshopequipment(map));
	}

}
