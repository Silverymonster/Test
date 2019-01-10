<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="http://unpkg.com/element-ui@2.4.8/lib/theme-chalk/index.css">
		<!-- <link rel="stylesheet" type="text/css" href="css/index.css"> -->
		<script type="text/javascript" src="js/vue.min.js"></script>
		<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
		<script src="js/index.js"></script>
		<script type="text/javascript" src="js/q.js"></script>
		<script type="text/javascript" src="sui-build/.package/js/sui.min.js"></script>
		<link rel="stylesheet" type="text/css" href="sui-build/.package/css/sui.min.css" /> 
		<link rel="stylesheet" type="text/css" href="sui-build/.package/css/sui-append.min.css" />
		<link rel="stylesheet" href="css/style1.css">
		<link rel="stylesheet" href="css/dingtou.css">
		<link rel="stylesheet" href="layui/css/layui.css" media="all">
		<script src="layui/layui.js" charset="utf-8" type="text/javascript"></script>
<title>嘉乐俱乐部</title>
</head>
<script type="text/javascript">
$(function(){
	$.ajax({
		url:"http://172.20.10.11:8088/JiaLe/lbselectpic?shopid=1",
		type:"post",
		async:false,
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		success:function(data){
			var jw = JSON.parse(data);
				$($(".i1").find("img")[0]).attr("src",jw[0].picture);
				$($(".i1").find("img")[1]).attr("src",jw[1].picture);
				$($(".i1").find("img")[2]).attr("src",jw[2].picture);
				$($(".i1").find("img")[3]).attr("src",jw[3].picture);
		},error:function(data){//错误，返回回来的错误数据
		}
	})
$("#tuichu").click(function(){
	$.ajax({
		url:"http://172.20.10.11:8088/JiaLe/lblogout",
		type:"post",
		async:false,
		datatype:"jsonp",
		jsonp:"callback",
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		success:function(data){
			window.location.href = 'home.jsp';
		},error:function(data){//错误，返回回来的错误数据
		}
	});
  })
   $.ajax({
		url:"http://172.20.10.11:8088/JiaLe/selectshop",
		type:"post",
		async:false,
		datatype:"jsonp",
		jsonp:"callback",
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		success:function(data){
			   var jw = JSON.parse(data);
			   for(var i=1;i<jw.length+1;i++){
				$("#dm"+i+"").html(jw[i-1].shopname);
				$("#dm"+i+"").attr("href",jw[i-1].template+"?uid=10"+i+"&shopid="+jw[i-1].shopid);
				$("#f"+i+"").attr("src",jw[i].shoppicture);
				$($(".fen"+i+"").find("span")[0]).html(jw[i].shopname);
				$($(".fen"+i+"").find("span")[1]).html(jw[i].shopspace);
				//$($(".fen"+i+"").find("a")[0]).attr("href",jw[i].template+"?shopid=");
			   }
		},error:function(data){//错误，返回回来的错误数据
		}
	})
	$.ajax({
		url:"http://172.20.10.11:8088/JiaLe/lbselectuserinfo",
		type:"post",
		async:false,
		datatype:"jsonp",
		jsonp:"callback",
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		success:function(data){
			var jw = JSON.parse(data);
				$($(".jiaolian1").find("img")[0]).attr("src",jw[0].userpicture);
				$($(".jiaolian1").find("p")[0]).html(jw[0].username);
				$($(".jiaolian1").find("p")[3]).html(jw[0].remarks);
				$($(".jiaolian1").find("a")[0]).attr("href","coach1.html?userid="+jw[0].userid);
				
				
				$($(".jiaolian2").find("img")[0]).attr("src",jw[1].userpicture);
				$($(".jiaolian2").find("p")[0]).html(jw[1].username);
				$($(".jiaolian2").find("p")[3]).html(jw[1].remarks);
				$($(".jiaolian2").find("a")[0]).attr("href","coach1.html?userid="+jw[1].userid);
				
				
				$($(".jiaolian3").find("img")[0]).attr("src",jw[2].userpicture);
				$($(".jiaolian3").find("p")[0]).html(jw[2].username);
				$($(".jiaolian3").find("p")[3]).html(jw[2].remarks);
				$($(".jiaolian3").find("a")[0]).attr("href","coach1.html?userid="+jw[2].userid);
				
				
				$($(".jiaolian4").find("img")[0]).attr("src",jw[3].userpicture);
				$($(".jiaolian4").find("p")[0]).html(jw[3].username);
				$($(".jiaolian4").find("p")[3]).html(jw[3].remarks);
				$($(".jiaolian4").find("a")[0]).attr("href","coach1.html?userid="+jw[3].userid);
				
				
				$($(".jiaolian5").find("img")[0]).attr("src",jw[4].userpicture);
				$($(".jiaolian5").find("p")[0]).html(jw[4].username);
				$($(".jiaolian5").find("p")[3]).html(jw[4].remarks);
				$($(".jiaolian5").find("a")[0]).attr("href","coach1.html?userid="+jw[4].userid);
				
				
				$($(".jiaolian6").find("img")[0]).attr("src",jw[5].userpicture);
				$($(".jiaolian6").find("p")[0]).html(jw[5].username);
				$($(".jiaolian6").find("p")[3]).html(jw[5].remarks);
				$($(".jiaolian6").find("a")[0]).attr("href","coach1.html?userid="+jw[5].userid);
				
		},error:function(data){//错误，返回回来的错误数据
			alert("");
		}
	})
	$("#gr").click(function () {		
		$(location).attr('href',
				encodeURI(encodeURI("userinfo.html?username="+$("#u").html()))
				)
	});
	$("#gm").click(function () {		
		$(location).attr('href',
				encodeURI(encodeURI("jmeng.html?username="+$("#u").html()))
				)
	});
	$.ajax({
		url:"http://172.20.10.11:8088/JiaLe/selectshop",
		type:"post",
		async:false,
		datatype:"jsonp",
		jsonp:"callback",
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		success:function(data){
			var jw = JSON.parse(data);
			for(let i=0;i<jw.length+1;i++){
				$($(".fen"+(i+1)).find("a")[0]).attr("href",jw[i+1].template+"?shopid="+jw[i+1].shopid+"&uid="+jw[i+1].sid);
			}
		},error:function(data){
		}
	})
})
</script>
<div id="body">
<!-- 导航前 -->
	<header style="position:fixed;top:0;">
		<div id="dh" style="margin-left:40px;">
			<ul class="ul1">
				<li><a href="index.html"><img src="img/jl.jpg" style="width:50px;height:50px;border-radius:200px; "></a></li>
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
  		<el-menu-item index="1"><a href="index1.html">首页</a></el-menu-item>
  		<el-menu-item index="3"><span id="gm">加盟嘉乐</span></el-menu-item>
  		<el-submenu index="2">
    		<template slot="title">关于门店</template>
    		<el-menu-item index="2-1"><a href=""><font style="color:#FFFFFF">器材分类</font></a></el-menu-item>
    		<el-menu-item index="2-2"><a href="#fen"><font style="color:#FFFFFF">门店风采</font></a></el-menu-item>
    		<el-menu-item index="2-3"><a href="#jiaolian"><font style="color:#FFFFFF">专业教练</font></a></el-menu-item>
   	 </el-submenu>
  	</el-submenu>
  		<el-menu-item index="4"><span id="gr">个人中心</span></el-menu-item>
		<% 
		    String username=(String)session.getAttribute("username");
		    if(username==null){
				out.print("<el-menu-item index='5'><a href='denglu.html' target='blank'>在线登录/注册</a></el-menu-item>");
			}else{
				out.print("<el-menu-item index='5'>你好&nbsp;<a href='' id='u' target='blank'>"+username+"</a></el-menu-item>");
				out.print("<el-menu-item index='5'><a id='tuichu' href='session'  target='blank'>退出</a></el-menu-item>");
		    }
		%>
  		<el-menu-item index="6"><a href="about.html" target="blank">关于我们</a></el-menu-item>
  		<el-menu-item index="7"><i class="el-icon-phone"></i>热线:400-002-1516</el-menu-item>
</el-menu>
	</div>
</div>
</header>
<!-- 图片轮播 -->
<div id="divbody">
		<!-- 图片轮播 -->
		<div class="layui-carousel" id="test10">
			<div carousel-item="" style="height:115%;">
				<div >
					<a class="i1" href=""><img src="" class="lunbo"
						style="width: 100%; height: 100%;"></a>
				</div>
				<div>
					<a class="i1" href=""><img src="" class="lunbo"
						style="width: 100%; height: 100%;"></a>
				</div>
				<div>
					<a class="i1" href=""><img src="" class="lunbo"
						style="width: 100%; height: 100%;"></a>
				</div>
				<div>
					<a class="i1" href=""><img src="" class="lunbo"
						style="width: 100%; height: 100%;"></a>
				</div>
			</div>
		</div>
		<div>
			<div id="div0"></div>
		</div>
	</div>
	<!-- 托管 -->
	<div class="tuoguan">
		<div style="margin-left:38%;margin-top:2%;">
			<a href="#" style="font-size:30px;font-weight:bold;">品牌加盟&nbsp;&nbsp;一站式服务</a>
		</div>
		<div class="q7"><a href="#" class="i2"><img src="img/q7.png"></a></div>
		<div class="q1">
			<div><a href="#" class="i2"><img src="img/q1.png" onmouseout="qiu1_none()" onmouseover="qiu1_block()"></a></div>
			<div style="float:left;margin-top:5%;font-size:170%;margin-left:10%;">健身休闲区</div>
			<img id="q1" src="img/q101.jpg">
		</div>
		<div class="q2">
			<a href="#" class="i2"><img src="img/q2.png"></a>
		</div>
		<div class="q3">
			<div style="margin-left:11%;margin-top:-0.5%">
				<a href="#" class="i2"><img src="img/q3.png" onmouseout="qiu3_none()" onmouseover="qiu3_block()"></a></div>
			<div style="float:right;margin-top:18%;margin-right:35%;font-size:170%;">专业教练团队</div>
			<img id="q3" src="img/q301.jpg">
		</div>
		<div class="q4">
			<div ><a href="#" class="i2"><img src="img/q4.png" onmouseout="qiu4_none()" onmouseover="qiu4_block()"></a></div>
			<div style="float:right;font-size:150%;margin-top:-20%;margin-right:10%;">教练健身风采</div>
			<img id="q4" src="img/q401.gif">
		</div>
		<div class="q5">
			<div style="float:left;margin-left:15%;margin-top:-10%;font-size:150%;">专业的选址</div>
			<div ><a href="#" class="i2"><img src="img/q5.png" onmouseout="qiu5_none()" onmouseover="qiu5_block()"></a></div>
			<img id="q5" src="img/q501.jpg">
		</div>
		<div class="q6">
			<div style="float:left;margin-left:37%;margin-top:10%;font-size:150%;">健身器材风采</div>
			<div ><a href="#" class="i2"><img src="img/q6.png" onmouseout="qiu6_none()" onmouseover="qiu6_block()"></a></div>
			<img id="q6" src="img/q601.jpg">
		</div>
	</div>
	<!-- 教练 -->
	<div class="jiaolian"  id="jiaolian">
		<div style="margin-left:620px;margin-top:40px;">
			<a href="#" style="font-size:30px;font-weight:bold;color:white;">教&nbsp;练</a>
			<hr style="width:65px;height:2px;background-color:Gold;" >
		</div>
		<div class="jiaolian1" onmouseout="zhe1_none(1)" onmouseover="zhe1_block(1)">
			<img src="">
			<div id="zhezhao1">
			<a>
				<p class="pl"></p>
				<p class="pl">年龄:22岁</p>
				<p class="pl">国家一级健身运动员</p>
				<p class="pl1"></p>
			</a>
			</div>
		</div>
		
		
		<div class="jiaolian2" onmouseout="zhe1_none(2)" onmouseover="zhe1_block(2)">
			<img src="">
			<div id="zhezhao2">
			<a>
				<p class="pl"></p>
				<p class="pl">年龄:26岁</p>
				<p class="pl">AFAI国际私教</p>
				<p class="pl1"></p>
				</a>
			</div>
		</div>
		<div class="jiaolian3" onmouseout="zhe1_none(3)" onmouseover="zhe1_block(3)">
			<img src="">			
			<div id="zhezhao3">
			<a>
				<p class="pl"></p>
				<p class="pl">年龄:24岁</p>
				<p class="pl">泰拳认证教练</p>
				<p class="pl1"></p>
				</a>
			</div>
		</div>
		
		
		<div class="jiaolian4" onmouseout="zhe1_none(4)" onmouseover="zhe1_block(4)">
		<img src="">
			<div id="zhezhao4">
			<a>
				<p class="pl"></p>
				<p class="pl">年龄:22岁</p>
				<p class="pl">私人教练</p>
				<p class="pl1"></p>
				</a>
			</div>
		</div>
		
		
		<div class="jiaolian5" onmouseout="zhe1_none(5)" onmouseover="zhe1_block(5)">
		<img src="">
			<div id="zhezhao5">
			<a>
				<p class="pl"></p>
				<p class="pl">年龄:23岁</p>
				<p class="pl">精英私人教练</p>
				<p class="pl1"></p>
				</a>
			</div>
		</div>
		
		<div class="jiaolian6" id="jiaolian6" onmouseout="zhe1_none(6)" onmouseover="zhe1_block(6)">
		<img src="">
		<div id="zhezhao6">
		<a>
				<p class="pl"></p>
				<p class="pl">年龄:22岁</p>
				<p class="pl">中国五星级认证好教练</p>
				<p class="pl1"></p>
				</a>
			</div>
			
		</div>
		
	</div>
	<!-- 课程 -->
	<div class="kecheng">
		<div style="margin-left:650px;margin-top:40px;">
			<a href="#" style="font-size:30px;font-weight:bold;color:white;">私教&nbsp;&nbsp;课程</a>
			<hr style="width:135px;height:2px;background-color:Gold;" >
		</div>
		<div class="kecheng1">
			<p class="px1">嘉乐健身 格斗健身训练课程</p>
			<hr style="width:120px;height:3px;background-color:Peru;" />
			<p class="px">嘉乐格斗健身训练课程融入了:散打 拳击 柔道 防身术 和 综合格斗同时结合了健身的元素</p>
			<p class="px">可以对 反应能力心肺功能 柔韧性 爆发力 耐力 协调性 等有很大程度的提高，可以更好更快的达到燃脂塑形的效果</p>
			<p class="px">课程内容丰富，多元化，多角度，多方向训练，课程互动性和趣味性更强。目前热度很高的特色课程</p>
		</div>
		<div class="kecheng2">
			<p class="px1">嘉乐健身体系—特色拉伸课程</p>
			<hr style="width:130px;height:4px;background-color:Peru;" />
			<p class="px">以定位静态拉伸为核心技术来提高肌肉的延展性.缓解肌肉酸痛.提高运动表现</p>
			<p class="px">辅助扳机点精准松解技术，快速让目标肌肉放松.结合本体感觉神经肌肉促进疗法来提高肌肉力量，达到一定康复疗效</p>
			<p class="px">再通过专用小工具.手法压揉技术松解深层肌肉来缓解腰背痛.提高生活舒适度</p>
		</div>
		<div class="kecheng3">
			<p class="px1">嘉乐 儿童体适能课程</p>
			<hr style="width:130px;height:4px;background-color:Peru;" />
			<p class="px">——为成长助力！为健康加分！</p>
			<p class="px">嘉乐儿童体适能课程是一项以3-16岁儿童动作发展和运动窗口期为依据，开展的全面趣味性体适能训练课程</p>
			<p class="px">本课程将围绕儿童生长发育过程中所出现的身高、体重、体态、感统失调、体育达标等问题制定具体化解决方案，帮助孩子改善不良习惯，身心全面发展!</p>
			<p class="px">运动让我快乐，健康成就未来！给父母一份安心，让孩子健康成长！嘉乐儿童体适能课程，您孩子成长之路上的不二之选！</p>
		</div>
		<div class="kecheng4">
			<p class="px1">嘉乐健身-多功能训练地垫</p>
			<hr style="width:130px;height:4px;background-color:Peru;" />
			<p class="px">以定位静态拉伸为核心技术来提高肌肉的延展性.缓解肌肉酸痛.提高运动表现</p>
			<p class="px">辅助扳机点精准松解技术，快速让目标肌肉放松.结合本体感觉神经肌肉促进疗法来提高肌肉力量，达到一定康复疗效</p>
			<p class="px">再通过专用小工具.手法压揉技术松解深层肌肉来缓解腰背痛.提高生活舒适度</p>
		</div>
	</div>
	<!-- 分店进入 -->
	<div class="fen" id="fen">
		<div style="margin-top:40px;width: 100%;height: 100px;">
			<p  style="margin-left:670px;font-size:30px;font-weight:bold;color:white;">门店风采展示</p>
			<hr style="margin-left:670px;width:180px;height:2px;background-color:Gold;" >
			<div style="margin-top: -50px;margin-left:1050px;">
			<span class="sui-dropdown dropdown-bordered"><span class="dropdown-inner">
			<a role="button" data-toggle="dropdown" href="#" class="dropdown-toggle"><i class="caret"></i> 嘉乐健身俱乐部(总店) </a>
		    <ul role="menu" aria-labelledby="drop1" class="sui-dropdown-menu">
		      <li role="presentation"  class="active"><a id="dm1" role="menuitem" tabindex="-1" href=""></a></li>
		      <li role="presentation"><a id="dm2" role="menuitem" tabindex="-1" href=""></a></li>
		      <li role="presentation"><a id="dm3" role="menuitem" tabindex="-1" href=""></a></li>
		      <li role="presentation"><a id="dm4" role="menuitem" tabindex="-1" href=""></a></li>
		      <li role="presentation"><a id="dm5" role="menuitem" tabindex="-1" href=""></a></li>
		      <li role="presentation"><a id="dm6" role="menuitem" tabindex="-1" href=""></a></li>
		      <li role="presentation"><a id="dm7" role="menuitem" tabindex="-1" href=""></a></li>
		      <li role="presentation"><a id="dm8" role="menuitem" tabindex="-1" href=""></a></li>
		      <li role="presentation"><a id="dm9" role="menuitem" tabindex="-1" href=""></a></li>
		    </ul>
		    </span></span>
		    </div>
		</div> 
		<div class="fen1">
			 <a href=""><img id="f1"  src="" style="width:600px;height:450px;border-radius:20px;"></a>
	            <div style="position:absolute;z-indent:2;left:0;bottom:0;background-color:rgba(18,36,203,0.5);width: 100%;">
	                <span style="font-size:18px;margin-left:20px;color: white;">地址:</span>
				    <span style="font-size:18px;margin-left:20px;color: white;"></span>
			    </div>
		</div>
		<div class="fen2">
			 <a href=""><img id="f2" src="" style="width:600px;height:450px;border-radius:20px;"></a>
	            <div style="position:absolute;z-indent:2;left:0;bottom:0;background-color:rgba(18,36,203,0.5);width: 100%;">
	                <span style="font-size:18px;margin-left:20px;color: white;">地址:</span>
				    <span style="font-size:18px;margin-left:20px;color: white;"></span>
			    </div>
		</div>
		      <div class="fen3">
	            <a href=""><img id="f3" src="" style="width:400px;height:300px;border-radius:20px;"></a>
	            <div style="position:absolute;z-indent:2;left:0;bottom:0;background-color:rgba(18,36,203,0.5);width: 100%;">
	                <span style="font-size:18px;margin-left:20px;color: white;">地址:</span>
				    <span style="font-size:18px;margin-left:20px;color: white;"></span>
			    </div>
		      </div>
		      <div class="fen4">
		        <a href=""><img id="f4" src="" style="width:400px;height:300px;border-radius:20px;"></a>
		        <div style="position:absolute;z-indent:2;left:0;bottom:0;background-color:rgba(18,36,203,0.5);width: 100%;">
		            <span style="font-size:18px;margin-left:20px;color: white;">地址:</span>
			        <span style="font-size:18px;margin-left:20px;color: white;"></span>
		        </div>
			  </div>
		      <div class="fen5">
		        <a href=""><img id="f5" src="" style="width:400px;height:300px;border-radius:20px;"></a>
		        <div style="position:absolute;z-indent:2;left:0;bottom:0;background-color:rgba(18,36,203,0.5);width: 100%;">
			        <span style="font-size:18px;margin-left:20px;color: white;">地址:</span>
				    <span style="font-size:18px;margin-left:20px;color: white;"></span>
		        </div>
			  </div>
		<div id="db1" class="p3">
			<img style="width:60px;height:50px;border-radius:20px;z-index:5000;position:relative;" src="img/3.jpg">
		</div>
	</div>
	<!-- 底部 -->
	<iframe src="dibu.html" style="display:block;width:1520px;height:410px;border: 0px;"></iframe>
</div>
</body>
<script type="text/javascript">
layui.use([ 'carousel', 'form' ], function() {
	var carousel = layui.carousel, form = layui.form;
	//图片轮播
	carousel.render({
		elem : '#test10',
		width : '100%',
		height : '600px',
		interval : 5000
	});
	//其它示例
	$('.demoTest .layui-btn').on('click', function() {
		var othis = $(this), type = othis.data('type');
		active[type] ? active[type].call(this, othis) : '';
	});
});
var Main = {
	    data() {
	      return{
	        activeIndex: '1',
	        activeIndex2: '1'
	      }
	    },
	    methods: {
	      handleSelect(key, keyPath) {
	        console.log(key, keyPath);
	      }
	    }
	  }
	var Ctor = Vue.extend(Main)
	new Ctor().$mount('#ap')
	
	
$(function(){   
    $(window).scroll(function() {      
        if($(window).scrollTop() >= 100){
            $('#db1').fadeIn(300); 
        }else{    
            $('#db1').fadeOut(300);    
        }  
    });
    $('#db1').click(function(){
    $('html,body').animate({scrollTop: '0px'}, 800);});   
});
</script>
</html>