<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>支付选择页面</title>
<meta name="description" content="">
<meta name="keywords" content="">
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/pages/banquan.js"></script>
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
			<span style="width: 200px">欢迎您，${username}<a
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
		<li><a href="http://www.cbice.com/" target="_black">国际版权网</a></li>
                <li><a href="http://www.e-artist.cn/" target="_black">艺术+</a></li>
                <li><a href="http://www.art.cbice.com/" target="_black">投资+</a></li>
                <li><a href="http://www.piao.cbice.com/" target="_black">消费+</a></li>
                <li><a href="http://www.cbice.com/article/wsyyt/" target="_black">收藏+</a></li>
				<p>${username}</p>
			</ul>
		</div>
	</div>
	<div class="bq-paycheck">
		<div class="bq-paysucess-top">
			<p>
				<span>线上认购登记系统 > </span><span> &nbsp;支付</span>
			</p>
		</div>
	<form name="buyerLoginOut" action="buyerLoginOut.html" method="post">
	</form>
		<form id="payform" action="paymentOrder.html" method="post">
			<div class="bq-aycheck-cont">
				<div class="bq-aycheck-conttop">
					<input type="hidden" id="goodsName" name="goodsName"
						value="<%=request.getAttribute("name")%>" /> <input type="hidden"
						id="goodsNum" name="goodsNum"
						value="<%=request.getParameter("goodsNum")%>" /> <input
						type="hidden" id="singlePrice" name="singlePrice"
						value="<%=request.getParameter("singlePrice")%>" /> <input
						type="hidden" id="totalAmt" name="totalAmt"
						value="<%=request.getParameter("totalAmt")%>" /> <input
						type="hidden" id="distributeNum" name="distributeNum"
						value="<%=request.getParameter("distributeNum")%>" /> <input
						type="hidden" id="agencyName" name="agencyName"
						value="<%=request.getAttribute("agencyName")%>" />
					<p class="clear" style="border-bottom: 1px dashed #c7d6e0;">
						<b>商品清单</b><strong> <span>产品名称：</span><em><%=request.getAttribute("name")%></em></strong><strong>
					</p>
					<p class="clear">
						<strong><span>单价：</span><em class="bq-big"
							style="font-size: 18px;">￥<%=request.getParameter("singlePrice")%></em></strong>
						<strong><span>购买数量：</span><i class="bq-big"><%=request.getParameter("distributeNum")%>&nbsp;&nbsp;&nbsp;</i><em>件</em></strong>
						<strong><span>总价：</span><em class="bq-big"
							style="font-size: 18px; color: #ff4a00;">￥<%=request.getParameter("totalAmt")%></em></strong>
					</p>
				</div>
				<div class="bq-aycheck-contmid">
					<p style="font-size: 20px;">选择支付方式</p>
					<p>
						<input type="radio" name="pay" id="yue-pay" /><label
							for="yue-pay">余额支付</label>
					</p>
					<p>
						<input type="radio" name="pay" id="bank-pay" /><label
							for="bank-pay">使用银行卡支付</label>
					</p>
					<ul class="clear user-bank">
						<li><input type="radio" name="bank" id="gongshagn" /><label
							for="gongshagn"><img src="images/banquan-bank.png" /></label></li>
						<li><input type="radio" name="bank" id="nongye" /><label
							for="nongye"><img src="images/banquan-bank.png" /></label></li>
						<li><input type="radio" name="bank" id="gongshagn" /><label
							for="gognshagn"><img src="images/banquan-bank.png" /></label></li>
						<li><input type="radio" name="bank" id="gongshagn" /><label
							for="gognshagn"><img src="images/banquan-bank.png" /></label></li>
						<li><input type="radio" name="bank" id="gongshagn" /><label
							for="gognshagn"><img src="images/banquan-bank.png" /></label></li>
						<li><input type="radio" name="bank" id="gongshagn" /><label
							for="gognshagn"><img src="images/banquan-bank.png" /></label></li>
						<li><input type="radio" name="bank" id="gongshagn" /><label
							for="gognshagn"><img src="images/banquan-bank.png" /></label></li>
						<li><input type="radio" name="bank" id="gongshagn" /><label
							for="gognshagn"><img src="images/banquan-bank.png" /></label></li>
						<li><input type="radio" name="bank" id="gongshagn" /><label
							for="gognshagn"><img src="images/banquan-bank.png" /></label></li>
						<li><input type="radio" name="bank" id="gongshagn" /><label
							for="gognshagn"><img src="images/banquan-bank.png" /></label></li>
						<li class="bq-jiantou">展开更多银行</li>
					</ul>
					<h6>
						<input type="button" onclick="next()" value="下一步" />
					</h6>
				</div>

			</div>
		</form>
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
