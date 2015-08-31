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

</head>
<body>
	<div class="bq-logo">
		<img src="images/logo.png" />
	</div>

	<div class="bq-header-bottom">

		<div class="bq-header-nav">
			<ul class="clear">
				<li class="two"><a href="<%=request.getContextPath()%>/land.jsp">线上认购登记系统</a></li>
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
			<span>线上兑换系统 > </span><span> &nbsp;账号激活 > </span><span> &nbsp;确认密码 > </span><span> &nbsp;完成 > </span>
			
		</p>
	</div>
	
	<div class="bq-paysucess-cont clear">
		<div class="bq-accountActive-content">
			<span><img src="images/sucess.jpg" /><h2>账号激活成功</h2></span>
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