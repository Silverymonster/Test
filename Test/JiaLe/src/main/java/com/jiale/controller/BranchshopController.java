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
 * @author JiaLe 分店控制类
 *
 */

@Controller
@Scope("prototype")
public class BranchshopController {

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

	// 查询店铺一下的图片
	@RequestMapping(value = "/wxyselectpic", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String wxyselectpic(@RequestParam Map<String, String> map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("------------------wxyselectpic---------------");
		System.out.println("进入了查询店铺一的图片的方法:------------");
		System.out.println(map);
		return ToJson.toJsonArray(service.wxyselectpic(map));
	}

	// 店铺一下的教练
	@RequestMapping(value = "/wxyselectshop", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String wxyselectshop(@RequestParam Map<String, String> map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("----------------wxyselectshop------------------");
		System.out.println("进入了店铺一下的教练的方法:------------");
		System.out.println(map.toString());
		return ToJson.toJsonArray(service.wxyselectshop(map));
	}

	// 查询店铺一教练下的用户
	@RequestMapping(value = "/wxyselectuser", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String wxyselectuser(@RequestParam Map<String, String> map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("-------------------wxyselectuser---------------");
		System.out.println("进入了查询店铺一教练下的用户的方法:------------");
		System.out.println(map);
		return ToJson.toJsonArray(service.wxyselectuser(map));
	}

	// 查询店铺一教练下用户的评论
	@RequestMapping(value = "/wxyselectcomments", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String wxyselectcomments(@RequestParam Map<String, String> map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("--------------------wxyselectcomments--------------");
		System.out.println("进入了查询店铺一教练下用户的评论的方法:------------");
		System.out.println(map);
		return ToJson.toJsonArray(service.wxyselectcomments(map));
	}

	// 查询店铺二下的图片
	@RequestMapping(value = "/wxyselectpictwo", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String wxyselectpictwo(@RequestParam Map<String, String> map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("----------------wxyselectpictwo------------------");
		System.out.println("进入了查询店铺二的图片的方法:------------");
		System.out.println(map);
		return ToJson.toJsonArray(service.wxyselectpictwo(map));
	}

	// 店铺二下的器材
	@RequestMapping(value = "/wxyselectequipmenttwo", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String wxyselectequipmenttwo(@RequestParam Map<String, String> map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("---------------wxyselectequipmenttwo-------------------");
		System.out.println("进入了店铺二下的器材的方法:------------");
		System.out.println(map.toString());
		return ToJson.toJsonArray(service.wxyselectequipmenttwo(map));
	}
	
	// 加盟 添加店铺信息
	@RequestMapping(value = "/jminsertshop", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String jminsertshop(@RequestParam Map<String, String> map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("---------------jminsertshop-------------------");
		System.out.println("进入了添加店铺信息的方法:------------");
		System.out.println(map.toString());
		service.jminsertshoprole(map);
		service.jmselectuserid(map);
		service.jmupdateuserrole(map);
		return ToJson.toJsonArray(service.jminsertshop(map));
	}

}
