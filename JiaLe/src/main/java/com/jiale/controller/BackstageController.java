package com.jiale.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jiale.redis.Service;
import com.jiale.service.ServiceJ;
import com.jiale.util.ChangString;
import com.jiale.util.ExcleBook;
import com.jiale.util.FileUtils;
import com.jiale.util.ToJson;

/**
 * 
 * @author JiaLe 后台控制类
 *
 */

@RestController
@Scope("prototype")
public class BackstageController {

	@Autowired
	ServiceJ service;

	@Autowired
	Service ser;
	
	@Autowired
	ExcleBook ex;
	
	
	/**
	 * 
	 * @param json
	 * @return
	 * @throws IOException
	 */

	// 登录
	@RequestMapping(value = "/saxlogin", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String login(@RequestParam Map<Object, Object> map, HttpServletRequest request) {
		System.out.println("-----------------saxlogin-----------------");
		System.out.println("-----" + map.get("username") + "------" + map.get("encryption"));
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(map.get("username").toString(),
				map.get("encryption").toString());
		Map<String, Object> map1 = new HashMap<>();
		map1.put("data", false);
		try {
			subject.login(token);
			map1.put("data", true);
			boolean hasRole = subject.hasRole("dz");
				if (hasRole == true) {
					HttpSession session = request.getSession();
					session.setAttribute("user", map.get("username").toString());
					System.out.println("session里的值是：" + session.getAttribute("user"));
					ser.delKey("username");
					ser.setStr("username", (String) session.getAttribute("user"));
					return ChangString.getstring(map1);
				}else {return null;}
		} catch (Exception e) {
			System.out.println("登录失败");
		}
		return  null;
	}

	// 查询列表
	@RequestMapping(value = "/saxliebiao", produces = "text/html;charset=utf-8")
	@ResponseBody
	public String liebiao(@RequestParam Map<Object, Object> map, HttpServletRequest request) {
		System.out.println("----------------saxliebiao------------------");
		Subject subject = SecurityUtils.getSubject();
		boolean hasRole = subject.hasRole("dz");
		String username = ser.getStr("username");
		System.out.println("----username----" + username);
		map.put("username", username);
		List<Map> liebiao = (List<Map>) service.liebiao(map);
		List<Map> geshi1 = ChangString.geshi1(liebiao);
		System.out.println(geshi1);
		return ChangString.getstring(geshi1);
	}

	// 查询教练
	@RequestMapping(value = "saxselectcoach", produces = "text/html;charset=utf-8")
	@ResponseBody
	public String selectcoach(@RequestParam Map map, HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		boolean hasRole = subject.hasRole("dz");
		System.out.println(map);
		int object = Integer.parseInt(map.get("page").toString());
		map.put("page", (object - 1) * 5);
		System.out.println("---------------");
		String username = ser.getStr("username");
		map.put("username", username);
		List<Map> selectCoach = (List<Map>) service.selectCoach(map);
		String getstring = ChangString.getstring(selectCoach);
		return getstring;
	}

	// 查询用户
	@RequestMapping(value = "/saxselectuser", produces = "text/html;charset=utf-8")
	@ResponseBody
	public String selectuser(@RequestParam Map map, HttpServletRequest request) {
		System.out.println("-----------------saxselectuser-----------------");
		Subject subject = SecurityUtils.getSubject();
		boolean hasRole = subject.hasRole("dz");
		System.out.println("---------------");
		String username = ser.getStr("username");
		map.put("username", username);
		List<Map> selectCoach = (List<Map>) service.selectuser(map);
		String getstring = ChangString.getstring(selectCoach);
		return getstring;
	}

	// 查询角色
	@RequestMapping(value = "/saxselectroles", produces = "text/html;charset=utf-8")
	@ResponseBody
	public String selectroles(@RequestParam Map map, HttpServletRequest request) {
		System.out.println("-----------------saxselectroles-----------------");
		Subject subject = SecurityUtils.getSubject();
		boolean hasRole = subject.hasRole("dz");
		System.out.println("---------------");
		String username = ser.getStr("username");
		map.put("username", username);
		List<Map> selectCoach = (List<Map>) service.selectroles(map);
		String getstring = ChangString.getstring(selectCoach);
		return getstring;

	}

	// 修改
	@RequestMapping(value = "/saxupdateru", produces = "text/html;charset=utf-8")
	@ResponseBody
	public String updateru(@RequestParam Map map, HttpServletRequest request) {
		System.out.println("-----------------saxupdateru-----------------");
		Subject subject = SecurityUtils.getSubject();
		boolean hasRole = subject.hasRole("dz");
		System.out.println("---------------");
		String username = ser.getStr("username");
		map.put("username", username);
		String getstring = ChangString.getstring(map);
		System.out.println(getstring);

		// List<Map> selectCoach = (List<Map>) service.selectroles(map);
		// String getstring = ChangString.getstring(selectCoach);

		return null;

	}

	// 查询所有店铺
	@RequestMapping(value = "/selectshop", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String selectshop(@RequestParam Map<String, String> map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("----------------selectshop------------------");
		Subject subject = SecurityUtils.getSubject();
		boolean hasRole = subject.hasRole("dz");
		System.out.println("进入了查询所有店铺的方法:------------");
		System.out.println("map:" + map);
		return ToJson.toJsonArray(service.selectshop(map));

	}

	// 查询器材
	@RequestMapping(value = "/selectequipment", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String selectequipment(@RequestParam Map<String, String> map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("----------------selectequipment------------------");
		Subject subject = SecurityUtils.getSubject();
		boolean hasRole = subject.hasRole("dz");
		System.out.println("进入了查询器材的方法:------------");
		System.out.println("map:" + map);
		return ToJson.toJsonArray(service.saxequipment(map));
	}

	// 根据用户名查询所有店铺
	@RequestMapping(value = "/selectnameshop", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String selectnameshop(@RequestParam Map<String, String> map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("----------------selectnameshop------------------");
		Subject subject = SecurityUtils.getSubject();
		boolean hasRole = subject.hasRole("dz");
		System.out.println("进入了根据用户名查询店铺的方法:------------");
		System.out.println("map:" + map);
		return ToJson.toJsonArray(service.selectnameshop(map));

	}

	// 根据登录名查询所对应的店铺
	@RequestMapping(value = "/selectloginshop", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String selectloginshop(@RequestParam Map<String, String> map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("----------------selectloginshop------------------");
		Subject subject = SecurityUtils.getSubject();
		boolean hasRole = subject.hasRole("dz");
		System.out.println("进入了根据登录名查询所对应的店铺的方法:------------");
		System.out.println("map:" + map);
		return ToJson.toJsonArray(service.selectloginshop(map));

	}

	// 查询性能日志
	@RequestMapping(value = "/selectperformancelog", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String selectperformancelog(@RequestParam Map<String, String> map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("---------------selectperformancelog-------------------");
		Subject subject = SecurityUtils.getSubject();
		boolean hasRole = subject.hasRole("dz");
		System.out.println("进入了查询性能日志的方法:------------");
		System.out.println("map:" + map);
		return ToJson.toJsonArray(service.selectperformancelog(map));

	}

	// 查询操作日志
	@RequestMapping(value = "/selectoperationlog", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String selectoperationlog(@RequestParam Map<String, String> map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("----------------selectoperationlog------------------");
		Subject subject = SecurityUtils.getSubject();
		boolean hasRole = subject.hasRole("dz");
		System.out.println("进入了查询操作日志的方法:------------");
		System.out.println("map:" + map);
		return ToJson.toJsonArray(service.selectoperationlog(map));

	}

	// 查询报错日志
	@RequestMapping(value = "/selecterrorlog", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String selecterrorlog(@RequestParam Map<String, String> map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("----------------selecterrorlog------------------");
		Subject subject = SecurityUtils.getSubject();
		boolean hasRole = subject.hasRole("dz");
		System.out.println("进入了查询报错日志的方法:------------");
		System.out.println("map:" + map);
		return ToJson.toJsonArray(service.selecterrorlog(map));

	}

	// 修改角色
	@RequestMapping(value = "/saxupdaterole", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saxupdaterole(@RequestParam Map<String, String> map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("----------------saxupdaterole------------------");
		Subject subject = SecurityUtils.getSubject();
		boolean hasRole = subject.hasRole("dz");
		System.out.println("进入了修改角色的方法:------------");
		System.out.println("map:" + map);
		// 修改角色和用户中间表
		map.put("username", ser.getStr("username"));
		Map map1=(Map) service.saxselectuserinfouserid(map).get(0);
		map.put("uid", map1.get("userid").toString());
		List<Map> saxuserinfoone = (List<Map>) service.saxuserinfoone(map);
		//map.put("uid", saxuserinfoone.get(0).get("userid").toString());
		service.saxupdateuid(map);
		map.put("sid", map1.get("userid").toString());
		service.lbupdateshoprole(map);
		// 修改角色
		return ToJson.toJsonArray(service.saxupdaterole(map));

	}

	// 退出登录
	@RequestMapping(value = "/saxlogout", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saxlogout(@RequestParam Map<Object, Object> map, HttpServletRequest request) {
		System.out.println("----------------saxlogout------------------");
		Subject subject = SecurityUtils.getSubject();
		boolean hasRole = subject.hasRole("dz");
		ser.delKey("username");
		System.out.println("退出成功------------");
		return ChangString.getstring(map);
	}

	// 店长
	@RequestMapping(value = "/saxselectdz", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saxselectdz(@RequestParam Map<Object, Object> map, HttpServletRequest request) {
		System.out.println("--------------saxselectdz--------------------");
		Subject subject = SecurityUtils.getSubject();
		boolean hasRole = subject.hasRole("dz");
		System.out.println("查询所有店长分店长方法:------------");
		return ToJson.toJsonArray(service.saxselectdz(map));
	}

	// 根据用户名查询本店铺的信息
	@RequestMapping(value = "/saxselectshop", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saxselectshop(@RequestParam Map<Object, Object> map, HttpServletRequest request) {
		System.out.println("---------------saxselectshop-------------------");
		Subject subject = SecurityUtils.getSubject();
		boolean hasRole = subject.hasRole("dz");
		String username = ser.getStr("username");
		map.put("username", username);
		System.out.println("进入了根据用户名查询本店铺的信息方法:------------");
		return ToJson.toJsonArray(service.saxselectshop(map));
	}

	// 根据用户名查询用户的信息
	@RequestMapping(value = "/saxselectuser", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saxselectuser(@RequestParam Map<Object, Object> map, HttpServletRequest request) {
		System.out.println("---------------saxselectuser-------------------");
		Subject subject = SecurityUtils.getSubject();
		boolean hasRole = subject.hasRole("dz");
		String username = ser.getStr("username");
		map.put("username", username);
		System.out.println("进入了根据用户名查询用户的信息方法:------------");
		return ToJson.toJsonArray(service.saxselectuser(map));
	}

	// 根据店长查询店铺下教练的个数
	@RequestMapping(value = "/saxselectshopjl", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saxselectshopjl(@RequestParam Map<Object, Object> map, HttpServletRequest request) {
		System.out.println("---------------saxselectshopjl-------------------");
		Subject subject = SecurityUtils.getSubject();
		boolean hasRole = subject.hasRole("dz");
		String username = ser.getStr("username");
		map.put("username", username);
		System.out.println("进入了根据店长查询店铺下教练的个数方法:------------");
		return ToJson.toJsonArray(service.saxselectshopjl(map));
	}

	// 根据店长查询店铺下用户的个数
	@RequestMapping(value = "/saxselectshopyh", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saxselectshopyh(@RequestParam Map<Object, Object> map, HttpServletRequest request) {
		System.out.println("---------------saxselectshopyh-------------------");
		Subject subject = SecurityUtils.getSubject();
		boolean hasRole = subject.hasRole("dz");
		String username = ser.getStr("username");
		map.put("username", username);
		System.out.println("进入了根据店长查询店铺下用户的个数方法:------------");
		return ToJson.toJsonArray(service.saxselectshopyh(map));
	}

	// 添加器材的信息
	@RequestMapping(value = "/saxinsertequipment", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saxinsertequipment(@RequestParam Map<Object, Object> map, HttpServletRequest request) {
		System.out.println("----------------saxinsertequipment------------------");
		Subject subject = SecurityUtils.getSubject();
		boolean hasRole = subject.hasRole("dz");
		String username = ser.getStr("username");
		map.put("username", username);
		System.out.println("进入了添加器材的信息方法:------------");
		Map map3 = (Map) service.saxselectusernameshopid(map).get(0);
		map.put("shopid", map3.get("shopid"));
		return ToJson.toJsonArray(service.saxinsertequipment(map));
	}

	// 删除评论
	@RequestMapping(value = "/saxdeletecomments", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saxdeletecomments(@RequestParam Map<Object, Object> map, HttpServletRequest request) {
		System.out.println("----------------saxdeletecomments------------------");
		Subject subject = SecurityUtils.getSubject();
		boolean hasRole = subject.hasRole("dz");
		String username = ser.getStr("username");
		map.put("username", username);
		System.out.println("进入了删除评论的信息方法:------------");
		return ToJson.toJsonArray(service.saxdeletecomments(map));
	}

	// 根据用户名修改uid
	@RequestMapping(value = "/saxupdateuid", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saxupdateuid(@RequestParam Map<Object, Object> map, HttpServletRequest request) {
		System.out.println("----------------saxupdateuid------------------");
		Subject subject = SecurityUtils.getSubject();
		boolean hasRole = subject.hasRole("dz");
		String username = ser.getStr("username");
		map.put("username", username);
		System.out.println("进入了根据用户名修改uid的信息方法:------------");
		return ToJson.toJsonArray(service.saxupdateuid(map));
	}

	// 删除教练
	@RequestMapping(value = "/saxdeleteusername", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saxdeleteusername(@RequestParam Map<Object, Object> map, HttpServletRequest request) {
		System.out.println("---------------saxdeleteusername-------------------");
		Subject subject = SecurityUtils.getSubject();
		boolean hasRole = subject.hasRole("dz");
		String username = ser.getStr("username");
		map.put("username", username);
		System.out.println("进入了删除教练的信息方法:------------");
		return ToJson.toJsonArray(service.saxdeleteusername(map));
	}

	// 删除器材
	@RequestMapping(value = "/saxdeletequipment", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saxdeletequipment(@RequestParam Map<Object, Object> map, HttpServletRequest request) {
		System.out.println("----------------saxdeletequipment------------------");
		Subject subject = SecurityUtils.getSubject();
		boolean hasRole = subject.hasRole("dz");
		String username = ser.getStr("username");
		map.put("username", username);
		System.out.println("进入了删除器材的信息方法:------------");
		return ToJson.toJsonArray(service.saxdeletequipment(map));
	}

	// 查询总店长的下一级...
	@RequestMapping(value = "/saxselectusernamex", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saxselectusernamex(@RequestParam Map<Object, Object> map, HttpServletRequest request) {
		System.out.println("-------------------saxselectusernamex---------------");
		Subject subject = SecurityUtils.getSubject();
		boolean hasRole = subject.hasRole("dz");
		String username = ser.getStr("username");
		map.put("username", username);
		System.out.println("进入了查询总店长的下一级...的信息方法:------------");
		return ToJson.toJsonArray(service.saxselectusernamex(map));
	}
	
	// 查询教练总个数...
	@RequestMapping(value = "/saxselectsumscach", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saxselectsumscach(@RequestParam Map<Object, Object> map, HttpServletRequest request) {
		System.out.println("--------------------saxselectsumscach--------------");
		Subject subject = SecurityUtils.getSubject();
		boolean hasRole = subject.hasRole("dz");
		String username = ser.getStr("username");
		map.put("username", username);
		System.out.println("进入了查询教练总个数...的信息方法:------------");
		return ToJson.toJsonArray(service.selectsumcstch(map));
	}
	
	// execle导出
	@RequestMapping(value = "excelout", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public void excelout(@RequestParam Map<Object, Object> map, ErrorContext context, HttpServletRequest request,
			HttpServletResponse response) {
	
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		response.setCharacterEncoding("UTF-8");
		Subject subject = SecurityUtils.getSubject();
		boolean hasRole = subject.hasRole("dz");
		String username = ser.getStr("username");
		map.put("username", username);
		List<Map> selectCoach = (List<Map>) service.saxselectuserinfo(map);
		System.out.println("进入了excelout...的信息  " + selectCoach);
		HSSFWorkbook workbook = ex.excleOut(selectCoach);
		String filename="coach.xls";
		String userAgent = request.getHeader("User-Agent"); 
		System.out.println(userAgent+"-----------------------");
		// 这里使用到FileUtils工具类进行编码
		String encodeFilename;
		ServletOutputStream outputStream=null;
		try {
			encodeFilename = FileUtils.encodeDownloadFilename(filename,userAgent);
			response.setHeader("content-disposition", "filename="+encodeFilename);
			// //文件后缀
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			// //输出流对象 ServletOutputStream
			
			outputStream = response.getOutputStream();
			workbook.write(outputStream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
																				
	}
	
	// excle导入
	@RequestMapping(value = "excelin",  produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String excelin(@RequestParam MultipartFile file,@RequestParam Map<Object, Object> map, HttpServletRequest request) {
		/*Subject subject = SecurityUtils.getSubject();
		boolean hasRole = subject.hasRole("dz");
		String username = ser.getStr("username");
		map.put("username", username);*/
		System.out.println("进入了excelin...的信息");
		System.out.println("------------------");
		System.out.println(file);
		String fileName = file.getOriginalFilename();
		System.out.println(fileName);
		try {
			ex.excleIn(file.getInputStream());
		} catch (IOException e) {
			System.out.println("----------错误------------");
			e.printStackTrace();
		}
		return "{'data':'成功'}";
	}
	
	// 修改个人信息
	@RequestMapping(value = "/saxupdatemyinfo", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saxupdatemyinfo(@RequestParam Map<Object, Object> map, HttpServletRequest request) {
		System.out.println("-----------------saxupdatemyinfo-----------------");
		Subject subject = SecurityUtils.getSubject();
		boolean hasRole = subject.hasRole("dz");
		System.out.println(map);
		System.out.println("进入了修改个人...的信息方法:------------");
		service.saxupdatemyinfo(map);
		return ToJson.toJsonArray(service.saxupdatemyinfo(map));
	}
	
	// 修改店铺信息
	@RequestMapping(value = "/saxupdateshopinfo", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saxupdateshopinfo(@RequestParam Map<Object, Object> map, HttpServletRequest request) {
		System.out.println("---------------saxupdateshopinfo-------------------");
		Subject subject = SecurityUtils.getSubject();
		boolean hasRole = subject.hasRole("dz");
		System.out.println(map);
		System.out.println("进入了修改店铺...的信息方法:------------");
		service.saxupdateshopinfo(map);
		return ToJson.toJsonArray(service.saxupdateshopinfo(map));
	}
	
	// 修改店铺图片的信息
	@RequestMapping(value = "/saxupdateshoppictureinfo", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saxupdateshoppictureinfo(@RequestParam Map<Object, Object> map, HttpServletRequest request) {
		System.out.println("--------------saxupdateshoppictureinfo--------------------");
		Subject subject = SecurityUtils.getSubject();
		boolean hasRole = subject.hasRole("dz");
		System.out.println(map);
		System.out.println("进入了修改店铺图片...的信息方法:------------");
		//service.saxupdateshopinfo(map);
		return ToJson.toJsonArray(service.saxupdateshoppictureinfo(map));
	}
	
	// 查询所有店铺的轮播
	@RequestMapping(value = "/saxselectshoplb", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saxselectshoplb(@RequestParam Map<String, String> map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("----------------saxselectshoplb------------------");
		Subject subject = SecurityUtils.getSubject();
		boolean hasRole = subject.hasRole("dz");
		System.out.println("进入了查询所有店铺的轮播方法:------------");
		System.out.println("map:" + map);
		return ToJson.toJsonArray(service.saxselectshoplb(map));

	}
	
	// 添加店铺轮播图
	@RequestMapping(value = "/saxinsertshoplb", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saxinsertshoplb(@RequestParam Map<String, String> map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("----------------saxinsertshoplb------------------");
		Subject subject = SecurityUtils.getSubject();
		boolean hasRole = subject.hasRole("dz");
		System.out.println("进入了添加店铺轮播图的方法:------------");
		System.out.println("map:" + map);
		
		Map<String, Object> map1 = new HashMap();
		map1.put("shopid", map.get("shopid"));
		for (int i = 1; i < map.size(); i++) {
			System.out.println(map.get("shoppicture"+i));
			map1.put("picture", map.get("shoppicture"+i));
			service.saxinsertshoplb(map1);
		}
//		return ToJson.toJsonArray(service.saxinsertshoplb(map));
		return null;
	}

}
