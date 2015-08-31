<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>退单成功页面</title>
<meta name="description" content="">
<meta name="keywords" content="">
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<link rel="stylesheet" type="text/css" href="style/index.css" />
</head>
<body>
	<form name="userQuit" action="buyerLoginOut.html" method="post">
	</form>
	<div class="bq-header">
		<div class="bq-headerc clear">
			<p>
				<a href="/">网站地图 </a> | <a href="/"> 帮助中心</a>
			</p>
			<span style="width: 200px">欢迎您，${username} <a
				href="javascript:document.buyerLoginOut.submit();">退出</a>
			</span>
		</div>
	</div>
	
	<div class="bq-logo">
		<img src="images/logo.png" />
	</div>
	<div class="bq-header-bottom">
		<div class="bq-header-nav">
			<ul class="clear">
				<li class="first"><a href="/">线上认购登记系统</a></li>
			<li><a href="http://www.cbice.com/" target="_black">国际版权网</a></li>
                <li><a href="http://www.e-artist.cn/" target="_black">艺术+</a></li>
                <li><a href="http://www.art.cbice.com/" target="_black">投资+</a></li>
                <li><a href="http://www.piao.cbice.com/" target="_black">消费+</a></li>
                <li><a href="http://www.cbice.com/article/wsyyt/" target="_black">收藏+</a></li>
				<p>${username}</p>
			</ul>
		</div>
	</div>
	<form name="buyerLoginOut" action="buyerLoginOut.html" method="post">
	</form>
	<div class="bq-paysucess">
		<div class="bq-paysucess-top">
			<p>
				<span>线上认购登记系统 > </span><span> &nbsp;支付 ></span><span>&nbsp;退单成功
				</span>
			</p>
		</div>
		<div class="bq-paysucess-cont clear">
			<div class="bq-paysucess-contleft clear">
				<span><img src="images/sucess.jpg" /></span> <b>退单成功！</b>
			</div>

			<div class="bq-paysucess-contright">
				<div class="bq-pysuc-cotri-top">
					<h4>订单详情</h4>
					<p>
						<strong>订单号：</strong><em style="margin-right: 20px;">${id }</em> <strong>订单状态：</strong><em>退单成功</em>
					</p>
					<p>
						<strong>下单时间：</strong><em>${issued_date}</em> <i></i>
					</p>
					<p style="border-bottom: 1px solid #e7e6e6; line-height: 46px;">
						<strong>支付方式：</strong><em style="color: red;">XXXXX[需求确认再改]</em>
					</p>
					<h4>商品清单</h4>
					<p>
						<strong>产品名称：</strong><em>${goodsName }</em>
					</p>
					<p>
						<strong>产品代码：</strong><em>${goodsNum }</em>
					</p>
					<p>
						<strong>经销商名称：</strong><em>${agencyName }</em>
					</p>
					<p>
						<strong>购买数量：</strong><em>${distributeNum }</em><i>件</i>
					</p>
					<p>
						<strong>总价：</strong><em style="color: #333333; font-size: 18px;">￥${totalAmt}</em>
					</p>
				</div>
				<div class="bq-background"></div>
			</div>

		</div>

	</div>
	<div class="bq-footer">
		<p>
			<a>关于我们 </a>|<a> 在线咨询 </a>|<a> 法律声明 </a>|<a> 关于我们 </a>|<a> 人才招聘 </a>|<a>
				联系我们 </a>|<a> 网站地图 </a>|<a> 站长统计 </a>
		</p>
		<p>北京东方雍和国际版权交易中心版权所有 京ICP证140774号 京ICP备13013729号
			京公网安备110105002453号</p>
	</div>

</body>
</html>