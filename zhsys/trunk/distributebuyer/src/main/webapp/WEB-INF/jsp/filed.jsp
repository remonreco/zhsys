<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>支付失败页面</title>
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
				href="javascript:document.buyerLoginOut.submit();">退出</a></span>
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
				<li><a href="http://www.cbice.com/" target="_black">国际版权网</a><>
                <li><a href="http://www.e-artist.cn/" target="_black">艺术+</a><>
                <li><a href="http://www.art.cbice.com/" target="_black">投资+</a><>
                <li><a href="http://www.piao.cbice.com/" target="_black">消费+</a><>
                <li><a href="http://www.cbice.com/article/wsyyt/" target="_black">收藏+</a><>
				<p>${username}</p>
			</ul>
		</div>
	</div>
	<form name="buyerLoginOut" action="buyerLoginOut.html" method="post">
	</form>
	<div class="bq-paysucess">
		<div class="bq-paysucess-top">
			<p>
				<span>线上认购登记系统 > </span><span> &nbsp;支付 ></span><span>&nbsp;支付失败
				</span>
			</p>
		</div>
		<div class="bq-paysucess-cont clear">
			<div class="bq-paysucess-contleft clear">
				<span><img src="images/flied.jpg" /></span> <b>支付失败！</b>
			</div>
			<div class="bq-paysucess-contright">
				<div class="bq-pysuc-cotri-top">
					<h4>订单详情</h4>
					<p style="border-bottom: 1px solid #e7e6e6; line-height: 46px;">
						<strong>订单号：</strong><em style="margin-right: 20px;">${record.orderSerial}</em><strong>订单状态：</strong><em>失败</em>
					</p>

					<h4>商品清单</h4>
					<p>
						<strong>产品名称：</strong><em>${record.goodsName}</em>
					</p>
					<p>
						<strong>产品代码：</strong><em>${record.goodsNum}</em>
					</p>
					<p>
						<strong>经销商名称：</strong><em>${record.agencyName}</em>
					</p>
					<p>
						<strong>购买数量：</strong><em>${record.distributeNum}</em><i>件</i>
					</p>
					<p>
						<strong>总价：</strong><em style="color: #333333; font-size: 18px;">￥${record.totalAmt}</em>
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