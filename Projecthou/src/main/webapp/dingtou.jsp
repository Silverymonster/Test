<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="http://unpkg.com/element-ui@2.4.8/lib/theme-chalk/index.css">
		<script src="js/vue.min.js"></script>
		<script src="js/index.js"></script>
		<link rel="stylesheet" href="css/dingtou.css">
		<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
		<script type="text/javascript">
		$("#gr").click(function () {		
			$(location).attr('href',
					encodeURI(encodeURI("userinfo.html?username="+$("#u").html()))
					)
		})
		</script>
</head>
<body>
	<header style="position:fixed;top:0;">
		<div id="dh" style="margin-left:40px;">
			<ul class="ul1">
				<li><a href="index.html" target='blank'><img src="img/jl.jpg" style="width:50px;height:50px;border-radius:200px; "></a></li>
			</ul>
		</div>
		<div class="sousuo">
			<form id="fm">
				<input type="text" name="Search" placeholder="搜索器材/分店/教练" required="">
				<font style="vertical-align: inherit;">
					<font style="vertical-align: inherit;">
					<input type="submit" value="搜索"></font>
				</font>
			</form>
		</div>
		<!-- 导航 -->
<div style="margin-left:-50px;">
		<div id="ap">
	<el-menu :default-active="activeIndex2" class="el-menu-demo" mode="horizontal" @select="handleSelect" 
		background-color="black" text-color="#FFFFFF" active-text-color="SkyBlue">
  		<el-menu-item index="1"><a href="home.jsp" target="_Top">首页</a></el-menu-item>
  		<el-menu-item index="3"><a href="jmeng.html" target="_Top">加盟嘉乐</a></a></el-menu-item>
  		<el-submenu index="2">
    		<template slot="title">关于门店</template>
    		<el-menu-item index="2-1"><a href="" target="_Top"><font style="color:#FFFFFF">器材分类</font></a></el-menu-item>
    		<el-menu-item index="2-2"><a href="home.jsp#fen" target="_Top"><font style="color:#FFFFFF">门店风采</font></a></el-menu-item>
    		<el-menu-item index="2-3"><a href="home.jsp#jiaolian" target="_Top"><font style="color:#FFFFFF">专业教练</font></a></el-menu-item>
   	 </el-submenu>
  	</el-submenu>
  		<el-menu-item index="4"><span id="gr">个人中心</span></el-menu-item>
		<% 
		    String username=(String)session.getAttribute("username");
			if(username==null){
				out.print("<el-menu-item index='5'><a href='denglu.html' target='_Top'>在线登录/注册</a></el-menu-item>");
			}else{
				out.print("<el-menu-item index='5'><a href=''  target='_Top'>你好&nbsp;"+username+"</a></el-menu-item>");
				out.print("<el-menu-item index='5'><a id='tuichu' href='session' target='blank'>退出</a></el-menu-item>");
			}
		%>
  		<el-menu-item index="6"><a href="about.html" target="_Top">关于我们</a></el-menu-item>
  		<el-menu-item index="7"><i class="el-icon-phone"></i>热线:400-002-1516</el-menu-item>
</el-menu>
	</div>
</div>
</header>
</body>
<script type="text/javascript">
var Main = {
	    data(){
	      return{
	        activeIndex: '1',
	        activeIndex2: '1'
	      };
	    },
	    methods: {
	      handleSelect(key, keyPath) {
	        console.log(key, keyPath);
	      }
	    }
	  }
	var Ctor = Vue.extend(Main)
	new Ctor().$mount('#ap')
</script>
</html>