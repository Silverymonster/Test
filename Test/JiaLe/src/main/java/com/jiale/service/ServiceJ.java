package com.jiale.service;

import java.util.List;
import java.util.Map;

public interface ServiceJ {

	// -----------------------------------------------------------------------
	// ---后台---
	// 登录
	public List<?> login(Map<?, ?> map);

	// 用户信息一
	public List<?> saxuserinfoone(Map<?, ?> map);

	// 列表
	public List<?> liebiao(Map<?, ?> map);

	// 查询教练
	public List<?> selectCoach(Map<?, ?> map);

	// 查询用户
	public List<?> selectuser(Map<?, ?> map);

	// 查询角色
	public List<?> selectroles(Map<?, ?> map);

	// 查询所有店铺
	public List<?> selectshop(Map<?, ?> map);

	// 查询器材
	public List<?> saxequipment(Map<?, ?> map);

	// 查询所有店长和分店长
	public List<?> saxselectdz(Map<?, ?> map);

	// 根据用户名查询店铺
	public List<?> selectnameshop(Map<?, ?> map);

	// 修改角色
	public int saxupdaterole(Map<?, ?> map);

	// 根据登录名查询所对应的店铺
	public List<?> selectloginshop(Map<?, ?> map);

	// 根据用户名查询本店铺的信息
	public List<?> saxselectshop(Map<?, ?> map);

	// 根据用户名查询用户的信息
	public List<?> saxselectuser(Map<?, ?> map);

	// 根据店长查询店铺下教练的个数
	public List<?> saxselectshopjl(Map<?, ?> map);

	// 根据店长查询店铺下用户的个数
	public List<?> saxselectshopyh(Map<?, ?> map);

	// 添加器材的信息
	public int saxinsertequipment(Map<?, ?> map);
	
	// 根据用户名查询shopid
	public List<?> saxselectusernameshopid(Map<?, ?> map);

	// 删除评论
	public int saxdeletecomments(Map<?, ?> map);

	// 根据用户名修改uid
	public int saxupdateuid(Map<?, ?> map);

	// 删除教练
	public int saxdeleteusername(Map<?, ?> map);

	// 删除器材
	public int saxdeletequipment(Map<?, ?> map);

	// 查询总店长的下一级...
	public List<?> saxselectusernamex(Map<?, ?> map);

	// 查询性能日志
	public List<?> selectperformancelog(Map<?, ?> map);

	// 查询操作日志
	public List<?> selectoperationlog(Map<?, ?> map);

	// 查询报错日志
	public List<?> selecterrorlog(Map<?, ?> map);
	
	// 查询总教练条数
	public List<?> selectsumcstch(Map<?, ?> map);

	// 查询所有
	public List<?> saxselectuserinfo(Map<?, ?> map);

	// Excel导入
	public int exclein(Map<?, ?> map);

	// 修改个人信息
	public int saxupdatemyinfo(Map<?, ?> map);
	
	// 修改店铺的信息
	public int saxupdateshopinfo(Map<?, ?> map);
	
	// 修改店铺土图片的信息
	public int saxupdateshoppictureinfo(Map<?, ?> map);
	
	// 查询所有店铺的轮播
	public List<?> saxselectshoplb(Map<?, ?> map);
	
	// 添加店铺轮播图
	public int saxinsertshoplb(Map<?, ?> map);
	
	// -----------------------------------------------------------------------
	// ---首页---
	// 登录
	public List<?> lblogin(Map<?, ?> map);

	// 注册用户
	public int lbinsertuser(Map<?, ?> map);
	
	// 添加用户与角色的中间表
	public int lbinsertuserrole(Map<?, ?> map);
	
	// 查询用户与角色的中间表
	public List<?> lbselectuserrole(Map<?, ?> map);
	
	// 更新用户与角色的中间表 
	public int lbupdateuserrole(Map<?, ?> map);
	
	// 注册店长
	public int lbinsertdz(Map<?, ?> map);

	// 总店页面
	public List<?> lbselectpic(Map<?, ?> map);

	// 发送短信
	public boolean send(String phone, String code);

	// 查询用户表下的教练
	public List<?> lbselectuserinfo(Map<?, ?> map);

	// 用户和角色插入
	public int lbinsertrole(Map<?, ?> map);
	
	// 模糊查询搜索用户教练店铺
	public List<?> lbselectuser(Map<?, ?> map);
	
	// 查询模板店铺下的信息
	public List<?> lbselectshopinfo(Map<?, ?> map);
	
	// 查询模板店铺下的教练
	public List<?> lbselectshopcoach(Map<?, ?> map);
	
	// 查询模板店铺下的器材
	public List<?> lbselectshopequipment(Map<?, ?> map);

	// -----------------------------------------------------------------------
	// ---分店页面---
	// 查询店铺一下的图片
	public List<?> wxyselectpic(Map<?, ?> map);

	// 店铺一下的教练
	public List<?> wxyselectshop(Map<?, ?> map);

	// 查询店铺一教练下的用户
	public List<?> wxyselectuser(Map<?, ?> map);

	// 查询店铺一教练下用户的评论
	public List<?> wxyselectcomments(Map<?, ?> map);

	// 查询店铺二下的图片
	public List<?> wxyselectpictwo(Map<?, ?> map);

	// 店铺二下的器材
	public List<?> wxyselectequipmenttwo(Map<?, ?> map);
	
	//根据username查userid
	public List<?> saxselectuserinfouserid(Map<?, ?> map);
	// 添加店铺信息
	public int jminsertshop(Map<?, ?> map);
	
	// 添加店铺与用户中间表
	public int jminsertshoprole(Map<?, ?> map);
	
	// 根据用户名查询用户id
	public List<?> jmselectuserid(Map<?, ?> map);
	
	// 更新用户与角色中间表
	public int jmupdateuserrole(Map<?, ?> map);

	// -----------------------------------------------------------------------
	// ---个人中心页面---
	// 查询个人信息
	public List<?> myxselect(Map<?, ?> map);

	// 上传图片
	public int myxupdatesc(Map<?, ?> map);

	// 修改用户信息
	public int myxupdateyh(Map<?, ?> map);

	// 性能日志
	public int performancelog(Map<?, ?> map);
	
	// 根据用户名修改Uid
	public int lbupdateuid(Map<?, ?> map);  
	
	//修改角色店铺中间表
	public int lbupdateshoprole(Map<?, ?> map);
}
