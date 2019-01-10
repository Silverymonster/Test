function qiu1_none(){
	document.getElementById("q1").style.display="none";
}
function qiu1_block(){
	document.getElementById("q1").style.display="block";
}
function qiu3_none(){
	document.getElementById("q3").style.display="none";
}
function qiu3_block(){
	document.getElementById("q3").style.display="block";
}
function qiu4_none(){
	document.getElementById("q4").style.display="none";
}
function qiu4_block(){
	document.getElementById("q4").style.display="block";
}
function qiu5_none(){
	document.getElementById("q5").style.display="none";
}
function qiu5_block(){
	document.getElementById("q5").style.display="block";
}
function qiu6_none(){
	document.getElementById("q6").style.display="none";
}
function qiu6_block(){
	document.getElementById("q6").style.display="block";
}
function zhe1_none(a){
	
	var s="zhezhao"+a;
	//console.log(s);
	document.getElementById(s).style.display="none";
}
function zhe1_block(a){
	var s="zhezhao"+a;
	//console.log(document.getElementById(s));
	document.getElementById(s).style.display="block";
	//document.getElementById("zhezhao"+a).style.display="block";
}

$(function(){   
    $(window).scroll(function() {      
        if($(window).scrollTop() >= 100){
            $('.actGotop').fadeIn(300); 
        }else{    
            $('.actGotop').fadeOut(300);    
        }  
    });
    $('.actGotop').click(function(){
    $('html,body').animate({scrollTop: '0px'}, 800);});   
});
