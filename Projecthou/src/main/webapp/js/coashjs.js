$(function() {
	/*function jiazai() {
		var coachdata;
		 $.ajax({
				async : false,
				url : "selectcoach?username=小二",
				type : "GET",
				contentType : "application/x-www-form-urlencoded; charset=UTF-8",
				success : function(data) {
					alert(data);
					coachdata=JSON.parse(data);					
				}
			});
		var Main = {			
			    data() {
			      return {	    	
			    	 tableData:coachdata	    		
			      }
			    }
			  };
			var Ctor = Vue.extend(Main);
			new Ctor().$mount('#app');	
	}*/


	
	
	/*$("#querycoach").click(function () {
		jiazai();
	});
	$("#yanzheng").click(function () {
		alert("000")
		 $.ajax({
				async : false,
				url : "yanzheng",
				data:{phone:$("#phone").val()},
				type : "post",
				contentType : "application/x-www-form-urlencoded; charset=UTF-8",
				success : function(data) {
					alert(data);
					var obj=JSON.parse(data);
					if(obj.respCode==00000){
						alert("发送成功");
					}else{
						alert("发送失败");
					}
				}
			});
	})*/
})
