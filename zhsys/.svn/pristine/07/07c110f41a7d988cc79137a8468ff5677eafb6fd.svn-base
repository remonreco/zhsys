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
<style>
#submit-add{width: 160px;margin-left: 120px;margin-top: 20px;background-color: #FF4A00;border-radius: 6px;color: #fff;font-size: 16px;
font-weight: bold;}
</style>
<script type="text/javascript">
	function isUserPwd(){
		document.getElementById("userPwdSpan").innerHTML="";
		var s=document.getElementById("userPwd").value;
		var s2=document.getElementById("userPwd2").value;

		if (s&&!s2) {
			document.getElementById("userPwdSpan").innerHTML="<img src='images/qurenzhif.png'>";
			return true;
		}else if(s&&s2&&s==s2){
			document.getElementById("userPwdSpan").innerHTML="<img src='images/qurenzhif.png'>";
			return true;
		}else if(s&&s2&&s!=s2){
			document.getElementById("userPwdSpan").innerHTML="*密码不一致，请重新输入！";
			return false;
			}
		else{
			document.getElementById("userPwdSpan").innerHTML="*密码不能为空！";
			return false;
		}

	}
	function isUserPwd2(){
		document.getElementById("userPwd2Span").innerHTML="";
		var s=document.getElementById("userPwd2").value;
		var s1=document.getElementById("userPwd").value;
		
		var regu =/^[1][3-8][0-9]{9}$/;

		var re = new RegExp(regu);

		if (s&&s==s1) {
			document.getElementById("userPwd2Span").innerHTML="<img src='images/qurenzhif.png'>";
			return true;
		}else{
			document.getElementById("userPwd2Span").innerHTML="密码不一致，请重新输入！";
			return false;
		}

	}
	
	function checkAll(){
		if(isUserPwd()&&isUserPwd2()){
			return true;
		}
		else{
			return false;
		}
	}
</script>

</head>
<body>
	<div class="bq-logo">
		<img src="images/logo.png" />
	</div>

	<div class="bq-header-bottom">

		<div class="bq-header-nav">
			<ul class="clear">
				<li><a href="<%=request.getContextPath()%>/land.jsp">线上认购登记系统</a></li>
				<li class="first"><a href="<%=request.getContextPath()%>/convert.jsp">线上兑换系统</a></li>
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
			<span>线上兑换系统 > </span><span> &nbsp;账号激活 > </span><span> &nbsp;确认密码 > </span>
			
		</p>
	</div>
	
	<div class="bq-paysucess-cont clear">
		<div class="bq-accountActive-content">
			<h3>确认密码</h3>
			<hr>
			<form action="accActiveStep2.html" method="post">
				<div>
				资产账号:<input type="text" name="assetsAccount"  value="<%=(String)session.getAttribute("assetsAccount")%>" /><br>
				密 &nbsp; &nbsp; &nbsp;&nbsp;码:<input type="password" name="userPwd" id="userPwd" class="easyui-validatebox" required="required" value="" onblur="isUserPwd();"/><span id="userPwdSpan" style="color: red; margin-left: 20px;display: inline-block;line-height: 42px;font-size: 14px;"></span><br>
				确认密码:<input type="password" name="userPwd2" id="userPwd2" class="easyui-validatebox" required="required" value="" onblur="isUserPwd2();"/><span id="userPwd2Span" style="color: red; margin-left: 20px;display: inline-block;line-height: 42px;font-size: 14px;"></span><br>
				</div>
				<input type="submit" id="submit-add" value="提交" onclick="return checkAll();"/>
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