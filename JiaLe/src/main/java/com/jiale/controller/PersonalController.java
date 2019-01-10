package com.jiale.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiale.redis.Service;
import com.jiale.service.ServiceJ;
import com.jiale.util.ToJson;

/**
 * 
 * @author JiaLe 个人信息控制类
 *
 */

@Controller
@Scope("prototype")
public class PersonalController {

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

	// 查询个人信息
	@RequestMapping(value = "/myxselect", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String myxselect(@RequestParam Map map, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("------------------myxselect----------------");
		ser.getStr("username");
		System.out.println("进入了查询个人信息的方法:------------");
		System.out.println(map);
		return ToJson.toJsonArray(service.myxselect(map));
	}

	// 上传图片
	@RequestMapping(value = "/myxupdatesc", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String myxupdatesc(@RequestParam Map map, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("-----------------myxupdatesc-----------------");
		ser.getStr("username");
		System.out.println("进入了上传图片的方法:------------");
		System.out.println(map);
		return ToJson.toJsonArray(service.myxupdatesc(map));
	}

	// 修改个人信息
	@RequestMapping(value = "/myxupdateyh", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String myxupdateyh(@RequestParam Map map, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("-----------------myxupdateyh-----------------");
		ser.getStr("username");
		System.out.println("进入了修改个人信息的方法:------------");
		System.out.println(map);
		return ToJson.toJsonArray(service.myxupdateyh(map));
	}
}
