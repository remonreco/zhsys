<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>账号激活</title>
<meta name="description" content="">
<meta name="keywords" content="">
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<link rel="stylesheet" type="text/css" href="style/index.css" />
<link rel="stylesheet" type="text/css" href="style/registe.css">

<script type="text/javascript">
	
	function isPhone(){
		document.getElementById("phoneSpan").innerHTML="";
		var s=document.getElementById("userPhone").value;
		
		var regu =/^[1][3-8][0-9]{9}$/;

		var re = new RegExp(regu);

		if (re.test(s)) {
			document.getElementById("phoneSpan").innerHTML="<img src='images/qurenzhif.png'>";
			return true;
		}else{
			document.getElementById("phoneSpan").innerHTML="*手机号格式有误，请检查！";
			return false;
		}

			
	}
	
	function isIdNum(){
		document.getElementById("idNumSpan").innerHTML="";
		var str=document.getElementById("userIdNum").value;
		
		 var arg1 = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;
		 //18位数身份证正则表达式
		 var arg2 = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[A-Z])$/;
		 
		 var re1 = new RegExp(arg1);
		 var re2 = new RegExp(arg2);

		    if (re1.test(str)||re2.test(str)) {
		    	document.getElementById("idNumSpan").innerHTML="<img src='images/qurenzhif.png'>";
		    	return true;
		    }
		    else {
		    	document.getElementById("idNumSpan").innerHTML="*身份证格式有误，请检查！";
		    	return false;
		    }
	}
	
	function isAssetsAccount(){
		document.getElementById("assetsAccountSpan").innerHTML="";
		var s=document.getElementById("assetsAccount").value;
		if(s){
			document.getElementById("assetsAccountSpan").innerHTML="<img src='images/qurenzhif.png'>";
			return true;
		}
		else{
			document.getElementById("assetsAccountSpan").innerHTML="*资产账号不能为空！";
			return false;
		}
	}
	function isUserName(){
		document.getElementById("userNameSpan").innerHTML="";
		var s=document.getElementById("userName").value;
		if(s){
			document.getElementById("userNameSpan").innerHTML="<img src='images/qurenzhif.png'>";
			return true;
		}
		else{
			document.getElementById("userNameSpan").innerHTML="*姓名不能为空！";
			return false;
		}
	}
	
	function checkAll(){
		if(isPhone()&&isIdNum()&&isAssetsAccount()&&isUserName()){
			return true;
		}
		else{
			return false;
		}
	}
</script>
<style>
#submit-add{width: 160px;margin-left: 120px;margin-top: 20px;background-color: #FF4A00;border-radius: 6px;color: #fff;font-size: 16px;
font-weight: bold;}
</style>
</head>
<body>
	<div class="bq-logo">
		<img src="images/logo.png" />
	</div>

	<div class="bq-header-bottom">

		<div class="bq-header-nav">
			<ul class="clear">
				<li><a href="<%=request.getContextPath()%>/land.jsp">线上认购登记系统</a></li>
				<li class="first"><a
					href="<%=request.getContextPath()%>/convert.jsp">线上兑换系统</a></li>
				<li><a href="http://www.cbice.com/" target="_black">国际版权网</a></li>
				<li><a href="http://www.e-artist.cn/" target="_black">艺术+</a></li>
				<li><a href="http://www.art.cbice.com/" target="_black">投资+</a></li>
				<li><a href="http://www.piao.cbice.com/" target="_black">消费+</a></li>
				<li><a href="http://www.cbice.com/article/wsyyt/" target="_black">收藏+</a></li>

			</ul>
		</div>

	</div>

	<form name="buyerLoginOut" action="buyerLoginOut.html" method="post">
	</form>

	<div class="bq-paysucess-top1">
		<p>
			<span>线上兑换系统 > </span><span> &nbsp;账号激活 ></span>
			
		</p>
	</div>
	
	<div class="bq-paysucess-cont clear">
		<div class="bq-accountActive-content">
			<h3>账号激活</h3>
			<hr>
			<form id="accActive" action="accActiveStep1.html" method="post">
				<div>
				资产账号:<input type="text" name="assetsAccount" id="assetsAccount" value="" class="easyui-validatebox" required="required" onblur="isAssetsAccount();"/><span id="assetsAccountSpan" style="color: red; margin-left: 20px;display: inline-block;line-height: 42px;font-size: 14px;"></span><br>
				姓 &nbsp; &nbsp; &nbsp;&nbsp;名:<input type="text" name="userName" id="userName" value="" class="easyui-validatebox" required="required" onblur="isUserName();"/><span id="userNameSpan" style="color: red; margin-left: 20px;display: inline-block;line-height: 42px;font-size: 14px;"></span><br>
				身 &nbsp;份 &nbsp;证:<input type="text" name="userIdNum" id="userIdNum" value="" class="easyui-validatebox" required="required" onblur="isIdNum();" /><span id="idNumSpan" style="color: red; margin-left: 20px;display: inline-block;line-height: 42px;font-size: 14px;"></span><br>
				电 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话:<input type="text" name="userPhone" id="userPhone" value="" class="easyui-validatebox" required="required" onblur="isPhone();" /><span id="phoneSpan" style="color: red; margin-left: 20px;display: inline-block;line-height: 42px;font-size: 14px;"></span><br>
				</div>
				<input type="submit" id="submit-add" value="提交" onclick="return checkAll();" />
			</form>
		</div>
	</div>
	

	<div class="bq-footer">
		<p>
			<a>关于我们 </a>|<a> 在线咨询 </a>|<a> 法律声明 </a>|<a> 关于我们 </a>|<a> 人才招聘 </a>|<a>
				联系我们 </a>|<a> 网站地图 </a>|<a> 站长统计 </a>
		</p>
		<p>北京东方雍和国际版权交易中心版权所有 京ICP证140774号
			京ICP备13013729号京公网安备110105002453号</p>
	</div>

</body>
</html>