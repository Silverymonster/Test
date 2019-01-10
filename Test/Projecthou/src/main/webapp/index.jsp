<%@ page language="java" contentType="text/html; charset=Utf-8"
	pageEncoding="Utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Utf-8">
<style type="text/css">
#allmap {
	width: 66%;
	height: 85%;
	font-family: "微软雅黑";
}
</style>
<script type="text/javascript" src="http://api.map.baidu.com/getscript?v=2.0&ak=mK55PnxMQ79ff5TAzWNdvQufGSTiy5n1&services=&t=20171014112628">
</script>
<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>
<script src="js/jquery-3.3.1.js"></script>
<title>index3</title>
<link rel="stylesheet" href="css/2.css">
<link rel="stylesheet" href="css/index1.css">
<link rel="stylesheet" href="css/index.css">
 
  <script type="text/javascript">
        $(function () {
            $("#test img").click(function () {
                $("#test img").addClass("imgopacity");
                $(this).removeClass("imgopacity");
            })
        });
    </script>
     <style type="text/css">
        .imgopacity{
            filter: alpha(opacity=50);
            -moz-opacity: 0.5;
            -khtml-opacity: 0.5;
            opacity: 0.5;
        }
    </style>
</head>
<body>
	<div style="width:300px; height:800px; float: right;">
		<div style="width: 300px; height: 100px;margin-top: 40px;">
			<div id="r-result">
				<input type="text" id="startId" name="startId" size="20" placeholder="起始位置"
					style="width: 280px; color: black; background: write; border: 1px solid #ccc;" />
				<input type="text" id="destinationId" name="destinationId" size="20" placeholder="目的地位置"
					style="width: 280px; color: black; background: write; border: 1px solid #ccc;" />
			<input type="button" id="result" value="查询"
				style="width: 270px; height: 30px; color: black; background: #f0f0f0;" />
			</div>
			<div id="resultstep"
				style="float: right; width: 300px; height: 400px; overflow: auto; margin-top: 20px; display: none;">

			</div>
			<div id="searchResultPanel"
				style="border: 1px solid #C0C0C0; width: 150px; height: auto; display: none;"></div>
			</div>
			</br>
			<div id="fade" class="black_overlay"></div>
		</div>
	<div id="allmap" style="width:900px;height:600px;float:right;"></div>

</body>
<!-- 地图 -->
<script type="text/javascript">
	//我所在位置119.98186101,31.77139674
	// 百度地图API功能
	var map = new BMap.Map("allmap"); // 创建Map实例
	map.centerAndZoom(new BMap.Point(113.807778,34.800157), 17); // 初始化地图,设置中心点坐标和地图级别
	map.addControl(new BMap.MapTypeControl()); //添加地图类型控件
	map.setCurrentCity("郑州市"); // 设置地图显示的城市 此项是必须设置的
	map.enableScrollWheelZoom(true); //开启鼠标滚轮缩放

	function G(id) {
		return document.getElementById(id);
	}

	var ac = new BMap.Autocomplete( //建立一个自动完成的对象
	{
		"input" : "startId",
		"location" : map
	});

	var bc = new BMap.Autocomplete( //建立一个自动完成的对象
	{
		"input" : "destinationId",
		"location" : map
	});

	ac.addEventListener("onhighlight", function(e) { //鼠标放在下拉列表上的事件
		var str = "";
		var _value = e.fromitem.value;
		var value = "";
		if (e.fromitem.index > -1) {
			value = _value.province + _value.city + _value.district
					+ _value.street + _value.business;
		}
		str = "FromItem<br />index = " + e.fromitem.index + "<br />value = "
				+ value;

		value = "";
		if (e.toitem.index > -1) {
			_value = e.toitem.value;
			value = _value.province + _value.city + _value.district
					+ _value.street + _value.business;
		}
		str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = "
				+ value;
		G("searchResultPanel").innerHTML = str;
	});

	bc.addEventListener("onhighlight", function(e) { //鼠标放在下拉列表上的事件
		var str = "";
		var _value = e.fromitem.value;
		var value = "";
		if (e.fromitem.index > -1) {
			value = _value.province + _value.city + _value.district
					+ _value.street + _value.business;
		}
		str = "FromItem<br />index = " + e.fromitem.index + "<br />value = "
				+ value;

		value = "";
		if (e.toitem.index > -1) {
			_value = e.toitem.value;
			value = _value.province + _value.city + _value.district
					+ _value.street + _value.business;
		}
		str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = "
				+ value;
		G("searchResultPanel").innerHTML = str;
	});

	var myValue;
	ac.addEventListener("onconfirm", function(e) { //鼠标点击下拉列表后的事件
		var _value = e.item.value;
		myValue = _value.province + _value.city + _value.district
				+ _value.street + _value.business;
		G("searchResultPanel").innerHTML = "onconfirm<br />index = "
				+ e.item.index + "<br />myValue = " + myValue;

		setPlace();
	});

	var myValue;
	bc.addEventListener("onconfirm", function(e) { //鼠标点击下拉列表后的事件
		var _value = e.item.value;
		myValue = _value.province + _value.city + _value.district
				+ _value.street + _value.business;
		G("searchResultPanel").innerHTML = "onconfirm<br />index = "
				+ e.item.index + "<br />myValue = " + myValue;

		setPlace();
	});

	function setPlace() {
		map.clearOverlays(); //清除地图上所有覆盖物
		function myFun() {
			var pp = local.getResults().getPoi(0).point; //获取第一个智能搜索的结果
			map.centerAndZoom(pp, 18);
			map.addOverlay(new BMap.Marker(pp)); //添加标注
		}
		var local = new BMap.LocalSearch(map, { //智能搜索
			onSearchComplete : myFun
		});
		local.search(myValue);
	}

	//三种驾车策略：最少时间，最短距离，避开高速
	var routePolicy = [ BMAP_DRIVING_POLICY_LEAST_TIME,
			BMAP_DRIVING_POLICY_LEAST_DISTANCE,
			BMAP_DRIVING_POLICY_AVOID_HIGHWAYS ];
	$("#result").click(function() {
		$("#resultstep").css("display", "block");
		var start = $("#startId").val();
		var end = $("#destinationId").val();
		//alert(start+"--"+end);
		map.clearOverlays();
		var i = $("#driving_way select").val();
		search(start, end, routePolicy[i]);
		function search(start, end, route) {
			var driving = new BMap.DrivingRoute(map, {renderOptions: {map: map, panel: "resultstep", autoViewport: true}});
			driving.search(start,end);
		}

		//驾车计算时间和距离
		var output = "从" + start + "到" + end + "驾车需要";
		var searchComplete = function(results) {
			if (transit.getStatus() != BMAP_STATUS_SUCCESS) {
				return;
			}
			var plan = results.getPlan(0);
			output += plan.getDuration(true) + "\n"; //获取时间
			output += "总路程为：";
			output += plan.getDistance(true) + "\n"; //获取距离
		}
		var transit = new BMap.DrivingRoute(map, {
			renderOptions : {
				map : map
			},
			onSearchComplete : searchComplete,
			onPolylinesSet : function() {
				setTimeout(function() {

				}, "1000");
				//$("#resultCount").html(output);
			}
		});
		transit.search(start, end);

		//驾车步骤
		var transit = new BMap.DrivingRoute(map, {
			renderOptions : {
				map : map,
				panel : "",
				enableDragging : true
			//起终点可进行拖拽
			},
		});
		transit.search(start, end);
	});

	//比例尺控件
	var top_left_control = new BMap.ScaleControl({
		anchor : BMAP_ANCHOR_TOP_LEFT
	});// 左上角，添加比例尺
	var top_left_navigation = new BMap.NavigationControl(); //左上角，添加默认缩放平移控件
	var top_right_navigation = new BMap.NavigationControl({
		anchor : BMAP_ANCHOR_TOP_RIGHT,
		type : BMAP_NAVIGATION_CONTROL_SMALL
	}); //右上角，仅包含平移和缩放按钮
	/*缩放控件type有四种类型:
	BMAP_NAVIGATION_CONTROL_SMALL：仅包含平移和缩放按钮；BMAP_NAVIGATION_CONTROL_PAN:仅包含平移按钮；BMAP_NAVIGATION_CONTROL_ZOOM：仅包含缩放按钮*/
	//默认加载地图add比例尺控件
	map.addControl(top_left_control);
	map.addControl(top_left_navigation);
	map.addControl(top_right_navigation);
</script>
</html>
