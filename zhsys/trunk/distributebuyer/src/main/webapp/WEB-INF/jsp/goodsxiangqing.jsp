<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>商品详情</title>
<meta name="description" content="">
<meta name="keywords" content="">
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/pages/banquan.js"></script>
<link rel="stylesheet" type="text/css" href="style/index.css" />
</head>
<body>
	<div class="bq-header">
		<div class="bq-headerc clear">
			<p>
				<a href="/">网站地图 </a> | <a href="/"> 帮助中心</a>
			</p>
			<span style="width: 20%; float: right;" >欢迎您，${username} <a href="javascript:document.buyerLoginOut.submit();" >退出</a></span>
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
	<form name="historyAction" action="history.html" method="post">
	</form>
	<form name="buyerLoginOut" action="buyerLoginOut.html" method="post">
	</form>
	<!-- 我要退单 -->
	<form id="returnform" action="returnOrderList2.html" method="post">

	</form>
	<form id="myform" action="choicePay.html" method="post">
		<input type="hidden" id="orderId" name="orderId" value="${goodRecord.id}"/>
		<div class="bq-goodsxq">
			<div class="bq-paysucess-top">
				<p>
					<span>线上认购登记系统 > </span><span> &nbsp;商品详情</span>
				</p>
				<strong><img src="images/hery.png" /><a
					href="javascript:document.historyAction.submit();">历史记录</a></strong>
			</div>
			<div class="bq-goodsxq-cont">
				<div class="bq-goodsxq-contmid">
					<div class="bq-goods-contmd-top">
						<h3>${goodRecord.goodsName}</h3>
					</div>

					<div class="bq-goods-contmd-mid">
						<input type="hidden" id="goodsName" name="goodsName"
							value="${goodRecord.goodsName}" /> 
							<!-- 订单有效时间 -->
							<input type="hidden" id="orderTime" name="orderTime"value="${goodRecord.sendDate}" /> 
							<!-- 订单流水号 -->
							<input type="hidden" id="orderSerial" name="orderSerial" value="${goodRecord.orderSerial}" /> 
							<input type="hidden" id="distributeNum" name="distributeNum" value="${goodRecord.distributeNum}" /> 
							<input type="hidden" id="goodsNum" name="goodsNum" value="${goodRecord.goodsNum}" />
						    <input type="hidden" id="agencyName" name="agencyName"value="${goodRecord.agencyName}" /> 
						    <input type="hidden"id="singlePrice" name="singlePrice"value="${goodRecord.singlePrice}" /> 
							<input type="hidden" id="distributeNum" name="distributeNum"  value="${goodRecord.distributeNum}" />
						    <input type="hidden" id="totalAmt" name="totalAmt" value="${goodRecord.totalAmt}" />
						<p>
							<strong>产品名称：</strong><em>${goodRecord.goodsName }</em>
						</p>
						<p>
							<strong>产品代码：</strong><em>${goodRecord.goodsNum}</em>
						</p>
						<p>
							<strong>经销商名称：</strong><em>${goodRecord.agencyName}</em>
						</p>
						<p>
							<strong>购买数量：</strong><em class="bq-big"
								style="font-size: 20px; color: #ff4a00;">&nbsp;&nbsp;${goodRecord.distributeNum}&nbsp;&nbsp;</em><i>件</i>
						</p>
						<p>
							<strong>总价：</strong><em class="bq-big"
								style="color: #333333; font-size: 18px;">￥${goodRecord.totalAmt}</em>
						</p>
					</div>
					<c:if test="${goodRecord.payState==0}">
						<div class="bq-goods-contmd-boot">
							<p>
								<input type="checkbox" id="intruedice" onchange="checkBox();" /><a
									href="javascript:void(0)">《平台支付说明》</a><em>您尚未同意平台支付说明</em>
							</p>
							<h5 class="chang-button">
								<input type="button" value="立即购买" onclick="nowBuy()"
									class="bq-backgrdto" /><input type="button"
									class="bq-backgrdto" onclick="notBuy()" value="我要退单" />
							</h5>
						</div>
					</c:if>
					<c:if test="${goodRecord.payState==1}">
						<div class="bq-paysucess-contleft clear">
							<span><img src="images/sucess.jpg" /></span> <b>支付成功！</b>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</form>
	<div class="bq-footer">
		<p>
			<a>关于我们 </a>|<a> 在线咨询 </a>|<a> 法律声明</a>|<a> 关于我们 </a>|<a> 人才招聘 </a>|<a>
				联系我们 </a>|<a> 网站地图 </a>|<a> 站长统计 </a>
		</p>
		<p>北京东方雍和国际版权交易中心版权所有 京ICP证140774号 京ICP备13013729号
			京公网安备110105002453号</p>
	</div>
	<div class="bq-zhezhao">
		<div class="bq-zhezhaocont">
			<div class="bq-zhezhaocont-top">
				<img src="images/fuzhi.png" />
				<h4>平台支付说明</h4>
			</div>
			<div class="bq-zhezhaocont-cont">
				<div class="bq-zhezhaocont-contxieyi">
					<h6>一、总则</h6>
					<p>快捷支付服务协议（以下简称“本协议”）是拉卡拉支付有限公司（以下简称“拉卡拉”）与拉卡拉注册用户（以下简称
						“用户”或“您”）就快捷支付服务（以下简称“本服务”）的使用等相关所订立的有效合约。用户在开启此功能之前，均应仔
						细阅读本协议，用户通过点击确认或以其他方式选择接受本协议，即表示用户与拉卡拉已达成协议并同意接受本协议的全部约定 内容。</p>
					<p>快捷支付服务协议（以下简称“本协议”）是拉卡拉支付有限公司（以下简称“拉卡拉”）与拉卡拉注册用户（以下简称
						“用户”或“您”）就快捷支付服务（以下简称“本服务”）的使用等相关所订立的有效合约。用户在开启此功能之前，均应仔
						细阅读本协议，用户通过点击确认或以其他方式选择接受本协议，即表示用户与拉卡拉已达成协议并同意接受本协议的全部约定 内容。</p>
					<p>快捷支付服务协议（以下简称“本协议”）是拉卡拉支付有限公司（以下简称“拉卡拉”）与拉卡拉注册用户（以下简称
						“用户”或“您”）就快捷支付服务（以下简称“本服务”）的使用等相关所订立的有效合约。用户在开启此功能之前，均应仔
						细阅读本协议，用户通过点击确认或以其他方式选择接受本协议，即表示用户与拉卡拉已达成协议并同意接受本协议的全部约定 内容。</p>
					<p>快捷支付服务协议（以下简称“本协议”）是拉卡拉支付有限公司（以下简称“拉卡拉”）与拉卡拉注册用户（以下简称
						“用户”或“您”）就快捷支付服务（以下简称“本服务”）的使用等相关所订立的有效合约。用户在开启此功能之前，均应仔
						细阅读本协议，用户通过点击确认或以其他方式选择接受本协议，即表示用户与拉卡拉已达成协议并同意接受本协议的全部约定 内容。</p>
					<p>快捷支付服务协议（以下简称“本协议”）是拉卡拉支付有限公司（以下简称“拉卡拉”）与拉卡拉注册用户（以下简称
						“用户”或“您”）就快捷支付服务（以下简称“本服务”）的使用等相关所订立的有效合约。用户在开启此功能之前，均应仔
						细阅读本协议，用户通过点击确认或以其他方式选择接受本协议，即表示用户与拉卡拉已达成协议并同意接受本协议的全部约定 内容。</p>
					<h6>二、用户的权利、义务</h6>
					<p>1. 您自愿申请使用拉卡拉快捷支付服务。</p>
					<p>1. 您自愿申请使用拉卡拉快捷支付服务。</p>
					<p>1. 您自愿申请使用拉卡拉快捷支付服务。</p>
				</div>
			</div>
			<div class="bq-zhezhaocont-fot">
				<h2>
					<input type="button" value="确认" />
				</h2>
			</div>
		</div>
	</div>
</body>
</html>