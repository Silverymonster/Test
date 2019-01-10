package Controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import myx.Service.service;

@Controller
@Scope("prototype")
public class myx {
	@Autowired
	service s;

	@RequestMapping(value="/upload")
	@ResponseBody
    public ModelAndView  upload(@RequestParam("img") CommonsMultipartFile file,@RequestParam Map map,HttpServletRequest request) throws Exception {
         long  startTime=System.currentTimeMillis();
        System.out.println("---------id："+map.get("userid"));
        System.out.println("fileName："+file.getOriginalFilename());
        shangchuan(file);
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        long  endTime=System.currentTimeMillis();
        System.out.println("方法二的运行时间："+String.valueOf(endTime-startTime)+"ms");
        Map<String, String> map2=new HashMap();
        map2.put("userpicture", "img/"+file.getOriginalFilename());
        map2.put("userid", map.get("userid").toString());
        System.out.println("img/"+file.getOriginalFilename());
        s.doPost("http://172.20.10.11:8088/JiaLe/myxupdatesc", map2);
        return mv(map); 
    }

	@RequestMapping(value = "/updateshopinfo", produces = "text/html;charset=UTF-8")
	public ModelAndView updateshopinfo(@RequestParam("img") CommonsMultipartFile file, @RequestParam Map map)
			throws Exception {
		long startTime = System.currentTimeMillis();
		System.out.println(map);
		System.out.println("---------id��" + map.get("userid"));
		System.out.println("fileName��" + file.getOriginalFilename());
		shangchuan(file);
		long endTime = System.currentTimeMillis();
		System.out.println("������������ʱ�䣺" + String.valueOf(endTime - startTime) + "ms");
		System.out.println("img/" + file.getOriginalFilename());
		System.out.println(map);
		Map map1 = new HashMap<>();
		map1.put("shoppicture", "img/" + file.getOriginalFilename());
		map1.put("shopid", map.get("shopid"));
		System.out.println(map1);
		s.doPost("http://172.20.10.11:8088/JiaLe/saxupdateshoppictureinfo", map1);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("shop.html");
		return modelAndView;
	}

	@RequestMapping(value = "/lunbotu", produces = "text/html;charset=UTF-8")
	public ModelAndView lunbotu(@RequestParam("img") CommonsMultipartFile file[], @RequestParam Map map)
			throws Exception {
		System.out.println(map);
		System.out.println(file);
		System.out.println(file[0] + "    " + file[0].getOriginalFilename());
		System.out.println(file[1] + "    " + file[1].getOriginalFilename());
		System.out.println(file[2] + "    " + file[2].getOriginalFilename());
		shangchuan(file[0]);
		shangchuan(file[1]);
		shangchuan(file[2]);
		map.put("shoppicture1", "img/" + file[0].getOriginalFilename());
		map.put("shoppicture2", "img/" + file[1].getOriginalFilename());
		map.put("shoppicture3", "img/" + file[2].getOriginalFilename());
		System.out.println(map);
		s.doPost("http://172.20.10.11:8088/JiaLe/saxinsertshoplb", map);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("shop.html");
		return modelAndView;
	}
	
	@RequestMapping(value = "/qicai", produces = "text/html;charset=UTF-8")
	public ModelAndView qicai(@RequestParam("img") CommonsMultipartFile file, @RequestParam Map map)
			throws Exception {
		System.out.println(map);
		System.out.println(file);
		map.put("equipmentpicture", "img/" + file.getOriginalFilename());
		System.out.println(map);
		s.doPost("http://172.20.10.11:8088/JiaLe/saxinsertequipment", map);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("equipment.html");
		return modelAndView;
	}
	
	@RequestMapping(value = "/sss", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String sss(@RequestParam("img") CommonsMultipartFile file, @RequestParam Map map)
			throws Exception {
		System.out.println("------------------");
		System.out.println(file);
		String fileName = file.getOriginalFilename();
		System.out.println(fileName);
		return "111";
	}
	public static void shangchuan(CommonsMultipartFile file) {
		File file3 = new File(myx.class.getResource("/").getPath());
		String str = file3.toString();
		System.out.println(file3 + "    file3");
		String[] split = str.split("WEB-INF");
		System.out.println(split[0]);
		String path = split[0] + "img\\" + file.getOriginalFilename();
		File newFile = new File(path);
		// ͨ��CommonsMultipartFile�ķ���ֱ��д�ļ���ע�����ʱ��
		try {
			file.transferTo(newFile);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * @RequestMapping(value = "excelout", method = RequestMethod.POST, produces =
	 * "text/html;charset=UTF-8")
	 * 
	 * @ResponseBody public String excelout(@RequestParam Map map,
	 * HttpServletRequest request) { Subject subject = SecurityUtils.getSubject();
	 * boolean hasRole = subject.hasRole("dz"); String username =
	 * ser.getStr("username"); map.put("username", username); List<Map> selectCoach
	 * = (List<Map>) service.saxselectuserinfo(map); try {
	 * s.doPost("http://172.20.10.11:8088/JiaLe/saxupdateshopinfo", map); } catch
	 * (Exception e) { // TODO Auto-generated catch block e.printStackTrace(); }
	 * System.out.println("������excelout...����Ϣ  "+selectCoach);
	 * ex.excleOut(selectCoach); return "{'data':'�ɹ�'}"; }
	 * 
	 * @RequestMapping(value = "excelin", method = RequestMethod.POST, produces =
	 * "text/html;charset=UTF-8")
	 * 
	 * @ResponseBody public String excelin(@RequestParam Map map, HttpServletRequest
	 * request) { Subject subject = SecurityUtils.getSubject(); boolean hasRole =
	 * subject.hasRole("dz"); String username = ser.getStr("username");
	 * map.put("username", username); System.out.println("������excelin...����Ϣ");
	 * ex.excleIn(); try {
	 * s.doPost("http://172.20.10.11:8088/JiaLe/saxupdateshopinfo", map); } catch
	 * (Exception e) { // TODO Auto-generated catch block e.printStackTrace(); }
	 * return "{'data':'�ɹ�'}"; }
	 */

	public ModelAndView mv(Map map) {
		ModelAndView mv = new ModelAndView();
		System.out.println("�ϴ��ɹ�");
		mv.setViewName("home.jsp");
		return mv;
	}
	
	@RequestMapping(value = "/session")
	public ModelAndView session(HttpServletRequest request) throws Exception {
         HttpSession session=request.getSession();
         session.removeAttribute("username");
         System.out.println("退出成功");
         session.invalidate();
         ModelAndView mv = new ModelAndView();
         mv.setViewName("home.jsp");
 		 return mv;
	}
	
	@RequestMapping(value = "/setsession")
	public ModelAndView setsession(HttpServletRequest request) throws Exception {
         HttpSession session=request.getSession();
         String username=request.getParameter("username");
         System.out.println("登录成功");
         session.setAttribute("username", username);
         ModelAndView mv = new ModelAndView();
         mv.setViewName("home.jsp");
 		 return mv;
	}

}
