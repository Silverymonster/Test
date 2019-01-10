package com.jiale.service.imp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiale.dao.Dao;
import com.jiale.service.ServiceJ;

@Service
public class ServiceImp implements ServiceJ {
	ServiceImp() {
		System.out.println("ServiceImp 被构造");
	}

	@Autowired
	Dao dao;

	// -----------------------------------------------------------------------
	// ---后台---
	// 登录
	@Override
	public List<?> login(Map<?, ?> map) {
		return dao.select("sax.jiale.mapper.login", map);
	}

	// 列表
	@Override
	public List<?> liebiao(Map<?, ?> map) {
		return dao.select("sax.jiale.mapper.liebiao", map);
	}

	// 查询教练
	@Override
	public List<?> selectCoach(Map<?, ?> map) {
		return dao.select("sax.jiale.mapper.selectcoach", map);
	}

	// 查询用户
	@Override
	public List<?> selectuser(Map<?, ?> map) {
		return dao.select("sax.jiale.mapper.selectuser", map);
	}

	// 查询角色
	@Override
	public List<?> selectroles(Map<?, ?> map) {
		return dao.select("sax.jiale.mapper.selectroles", map);
	}

	// 查询所有店铺
	@Override
	public List<?> selectshop(Map<?, ?> map) {
		return dao.select("sax.jiale.mapper.selectshop", map);
	}

	// 查询器材
	@Override
	public List<?> saxequipment(Map<?, ?> map) {
		return dao.select("sax.jiale.mapper.selectequipment", map);
	}

	// 根据用户名查询所有店铺
	@Override
	public List<?> selectnameshop(Map<?, ?> map) {
		return dao.select("sax.jiale.mapper.selectnameshop", map);
	}

	// 根据登录名查询所对应的店铺
	@Override
	public List<?> selectloginshop(Map<?, ?> map) {
		return dao.select("sax.jiale.mapper.selectloginshop", map);
	}

	// 修改角色
	@Override
	public int saxupdaterole(Map<?, ?> map) {
		return dao.update("sax.jiale.mapper.saxupdaterole", map);
	}

	// 店长
	@Override
	public List<?> saxselectdz(Map<?, ?> map) {
		return dao.select("sax.jiale.mapper.selectdz", map);
	}

	// 根据用户名查询本店铺的信息
	@Override
	public List<?> saxselectshop(Map<?, ?> map) {
		return dao.select("sax.jiale.mapper.saxselectshop", map);
	}

	// 根据用户名查询用户的信息
	@Override
	public List<?> saxselectuser(Map<?, ?> map) {
		return dao.select("sax.jiale.mapper.saxselectuser", map);
	}

	// 根据店长查询店铺下教练的个数
	@Override
	public List<?> saxselectshopjl(Map<?, ?> map) {
		return dao.select("sax.jiale.mapper.saxselectshopjl", map);
	}

	// 根据店长查询店铺下用户的个数
	@Override
	public List<?> saxselectshopyh(Map<?, ?> map) {
		return dao.select("sax.jiale.mapper.saxselectshopyh", map);
	}

	// 添加器材的信息
	@Override
	public int saxinsertequipment(Map<?, ?> map) {
		return dao.insert("sax.jiale.mapper.saxinsertequipment", map);
	}
	
	// 根据用户名查询shopid
	@Override
	public List<?> saxselectusernameshopid(Map<?, ?> map) {
		return dao.select("sax.jiale.mapper.saxselectusernameshopid", map);
	}

	// 删除评论
	@Override
	public int saxdeletecomments(Map<?, ?> map) {
		return dao.delete("sax.jiale.mapper.saxdeletecomments", map);
	}

	// 根据用户名修改uid
	@Override
	public int saxupdateuid(Map<?, ?> map) {
		return dao.update("sax.jiale.mapper.saxupdateuid", map);
	}

	// 删除教练
	@Override
	public int saxdeleteusername(Map<?, ?> map) {
		return dao.delete("sax.jiale.mapper.saxdeleteusername", map);
	}

	// 删除器材
	@Override
	public int saxdeletequipment(Map<?, ?> map) {
		return dao.delete("sax.jiale.mapper.saxdeletequipment", map);
	}

	// 查询总店长的下一级...
	@Override
	public List<?> saxselectusernamex(Map<?, ?> map) {
		return dao.select("sax.jiale.mapper.saxselectusernamex", map);
	}

	// 查询性能日志
	@Override
	public List<?> selectperformancelog(Map<?, ?> map) {
		return dao.select("sax.jiale.mapper.selectperformancelog", map);
	}

	// 查询操作日志
	@Override
	public List<?> selectoperationlog(Map<?, ?> map) {
		return dao.select("sax.jiale.mapper.selectoperationlog", map);
	}

	// 查询报错日志
	@Override
	public List<?> selecterrorlog(Map<?, ?> map) {
		return dao.select("sax.jiale.mapper.selecterrorlog", map);
	}
	
	// 用户信息一
	@Override
	public List<?> saxuserinfoone(Map<?, ?> map) {
		return dao.select("sax.jiale.mapper.saxuserinfoone", map);
	}
	
	// 查询教练总个数...
	@Override
	public List<?> selectsumcstch(Map<?, ?> map) {
		return dao.select("sax.jiale.mapper.sumcoach", map);
	}

	// 查询所有用户
	@Override
	public List<?> saxselectuserinfo(Map<?, ?> map) {
		return  dao.select("sax.jiale.mapper.saxselectuserinfo", map);
	}

	// excle导入
	@Override
	public int exclein(Map<?, ?> map) {
		return dao.insert("sax.jiale.mapper.exclein", map);
	}

	// 修改个人信息
	@Override
	public int saxupdatemyinfo(Map<?, ?> map) {
		return dao.update("sax.jiale.mapper.saxupdatemyinfo", map);
	}
	
	// 修改店铺的信息
	@Override
	public int saxupdateshopinfo(Map<?, ?> map) {
		return dao.update("sax.jiale.mapper.saxupdateshopinfo", map);
	}
	
	// 修改店铺土图片的信息
	@Override
	public int saxupdateshoppictureinfo(Map<?, ?> map) {
		return dao.update("sax.jiale.mapper.saxupdateshoppictureinfo", map);
	}
	
	// 查询所有店铺的轮播
	@Override
	public List<?> saxselectshoplb(Map<?, ?> map) {
		return dao.select("sax.jiale.mapper.saxselectshoplb", map);
	}
	
	// 添加店铺轮播图
	@Override
	public int saxinsertshoplb(Map<?, ?> map) {
		return dao.insert("sax.jiale.mapper.saxinsertshoplb", map);
	}

	// -----------------------------------------------------------------------
	// ---首页---

	// 登录
	public List<?> lblogin(Map<?, ?> map) {
		return dao.select("sax.jiale.mapper.login", map);
	}

	// 注册用户
	public int lbinsertuser(Map<?, ?> map) {
		return dao.insert("lb.jiale.mapper.lbinsertuser", map);
	}
	
	// 添加用户与角色的中间表
	public int lbinsertuserrole(Map<?, ?> map) {
		return dao.insert("lb.jiale.mapper.lbinsertuserrole", map);
	}
	
	// 查询用户与角色的中间表
	public List<?> lbselectuserrole(Map<?, ?> map) {
		return dao.select("lb.jiale.mapper.lbselectuserrole", map);
	}
	
	// 更新用户与角色的中间表 
	public int lbupdateuserrole(Map<?, ?> map) {
		return dao.insert("lb.jiale.mapper.lbupdateuserrole", map);
	}

	// 注册店长
	@Override
	public int lbinsertdz(Map<?, ?> map) {
		return dao.insert("lb.jiale.mapper.lbinsertdz", map);
	}

	// 用户和角色插入
	public int lbinsertrole(Map<?, ?> map) {
		return dao.insert("lb.jiale.mapper.lbinsertrole", map);
	}

	// 发送短信
	public boolean send(String phone, String code) {
		String line = null;
		HttpURLConnection connection = null;
		BufferedReader bf = null;
		StringBuilder sb = null;
		try {
			URL url = new URL("https://api.miaodiyun.com/20150822/industrySMS/sendSMS");
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
			connection.connect();

			Date dt = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			String dttime = df.format(dt);
			// 随机生成的验证码
			String appId = "4e4f4e5f9a6d49bd98696d23a2c6d584";
			String token = "89fe18762517442da00d19540f3b87ef";
			Object sigs = new Md5Hash(appId + token + dttime);

			DataOutputStream dataout = new DataOutputStream(connection.getOutputStream());
			String accountSid = "accountSid=" + URLEncoder.encode("4e4f4e5f9a6d49bd98696d23a2c6d584", "utf-8");
			String smsContent = "&templateid=" + URLEncoder.encode("924273373", "utf-8");
			String parm = "&param=" + URLEncoder.encode(code, "utf-8");
			String phoneNum = "&to=" + URLEncoder.encode(phone, "utf-8");
			String time = "&timestamp=" + URLEncoder.encode(dttime, "utf-8");
			String sig = "&sig=" + URLEncoder.encode(sigs.toString(), "utf-8");
			String respDataType = "&respDataType=" + URLEncoder.encode("JSON", "utf-8");
			String parms = accountSid + smsContent + parm + phoneNum + time + sig + respDataType;
			// 将参数输出到连接
			dataout.writeBytes(parms);
			// 输出完成后刷新并关闭流
			dataout.flush();
			dataout.close(); // 重要且易忽略步骤 (关闭流,切记!)
			System.out.println(connection.getResponseCode());

			bf = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

			sb = new StringBuilder();
			while ((line = bf.readLine()) != null) {
				sb.append(line).append(System.getProperty("line.separator"));
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bf.close();
			} catch (IOException e) {
				e.printStackTrace();
				connection.disconnect(); // 销毁连接
			}

		}
		String backcode = sb.toString();// 获取短信接口的返回状态码
		System.out.println(backcode);
		return true;
	}

	// 总店页面获取图片
	@Override
	public List<?> lbselectpic(Map<?, ?> map) {
		return dao.select("lb.jiale.mapper.lbselectpic", map);
	}

	// 查询用户表下的教练
	@Override
	public List<?> lbselectuserinfo(Map<?, ?> map) {
		return dao.select("lb.jiale.mapper.lbselectuserinfo", map);
	}
	
	// 模糊查询搜索用户教练店铺
	@Override
	public List<?> lbselectuser(Map<?, ?> map) {
		return dao.select("lb.jiale.mapper.lbselectuser", map);
	}
	
	// 查询模板店铺下的信息
	@Override
	public List<?> lbselectshopinfo(Map<?, ?> map) {
		return dao.select("lb.jiale.mapper.lbselectshopinfo", map);
	}
	
	// 查询模板店铺下的教练
	@Override
	public List<?> lbselectshopcoach(Map<?, ?> map) {
		return dao.select("lb.jiale.mapper.lbselectshopcoach", map);
	}
		
	// 查询模板店铺下的器材
	@Override
	public List<?> lbselectshopequipment(Map<?, ?> map) {
		return dao.select("lb.jiale.mapper.lbselectshopequipment", map);
	}

	// -----------------------------------------------------------------------
	// ---分店页面---
	// 查询店铺一下的图片
	@Override
	public List<?> wxyselectpic(Map<?, ?> map) {
		return dao.select("wxy.jiale.mapper.wxyselectpic", map);

	}

	// 店铺一下的教练
	@Override
	public List<?> wxyselectshop(Map<?, ?> map) {
		return dao.select("wxy.jiale.mapper.wxyselectshop", map);
	}

	// 查询店铺一教练下的用户
	@Override
	public List<?> wxyselectuser(Map<?, ?> map) {
		return dao.select("wxy.jiale.mapper.wxyselectuser", map);
	}

	// 查询店铺一教练下用户的评论
	@Override
	public List<?> wxyselectcomments(Map<?, ?> map) {
		return dao.select("wxy.jiale.mapper.wxyselectcomments", map);
	}

	// 查询店铺二下的图片
	@Override
	public List<?> wxyselectpictwo(Map<?, ?> map) {
		return dao.select("wxy.jiale.mapper.wxyselectpictwo", map);
	}

	// 店铺二下的器材
	@Override
	public List<?> wxyselectequipmenttwo(Map<?, ?> map) {
		return dao.select("wxy.jiale.mapper.wxyselectequipmenttwo", map);
	}
	
	// 添加店铺信息
	@Override
	public int jminsertshop(Map<?, ?> map) {
		return dao.update("wxy.jiale.mapper.jminsertshop", map);
	}
	
	// 添加店铺与用户中间表
	@Override
	public int jminsertshoprole(Map<?, ?> map) {
		return dao.update("wxy.jiale.mapper.jminsertshoprole", map);
	}
	
	// 根据用户名查询用户id
	@Override
	public List<?> jmselectuserid(Map<?, ?> map) {
		return dao.select("wxy.jiale.mapper.jmselectuserid", map);
	}
	
	// 更新用户与角色中间表
	@Override
	public int jmupdateuserrole(Map<?, ?> map) {
		return dao.update("wxy.jiale.mapper.jmupdateuserrole", map);
	}

	// -----------------------------------------------------------------------
	// ---个人中心页面---
	// 查询个人信息
	@Override
	public List<?> myxselect(Map<?, ?> map) {
		return dao.select("myx.jiale.mapper.myxselect", map);
	}

	// 上传图片
	@Override
	public int myxupdatesc(Map<?, ?> map) {
		return dao.update("myx.jiale.mapper.myxupdatesc", map);
	}

	// 修改个人信息
	@Override
	public int myxupdateyh(Map<?, ?> map) {
		return dao.update("myx.jiale.mapper.myxupdateyh", map);
	}

	// 性能日志
	@Override
	public int performancelog(Map<?, ?> map) {
		return dao.insert("myx.jiale.mapper.performancelog", map);
	}

	@Override
	public int lbupdateuid(Map<?, ?> map) {
		return dao.update("lb.jiale.mapper.lbupdateuid", map);
	}

	@Override
	public int lbupdateshoprole(Map<?, ?> map) {
		// TODO Auto-generated method stub
		return dao.update("lb.jiale.mapper.lbupdateshoprole", map);
	}

	@Override
	public List<?> saxselectuserinfouserid(Map<?, ?> map) {
		// TODO Auto-generated method stub
		return dao.select("sax.jiale.mapper.saxselectuserinfouserid", map);
	}
}
