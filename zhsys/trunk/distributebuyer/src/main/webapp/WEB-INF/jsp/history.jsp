<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>历史购买记录</title>
<meta name="description" content="">
<meta name="keywords" content="">
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>

<script type="text/javascript" src="js/pages/banquan.js"></script>
<!-- <script type="text/javascript" src="js/pages/tcal.js"></script>  -->
<script type='text/javascript' src='js/My97DatePicker/WdatePicker.js'></script>
<script type="text/javascript" src="js/pages/history.js"></script>
<script src="js/pages/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="js/pages/jquery.pager.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="style/tcal.css" />
<link rel="stylesheet" type="text/css" href="style/index.css" />
<link rel="stylesheet" href="style/calendar.css" />
<style type="text/css">
body{font-size:12px;}
h1,p{margin:0;}
ul{padding:0;margin:0;list-style:none;}

/* demo */
.demo{width:800px;margin:30px;}
#result{font-size:24px;font-family:'微软雅黑','宋体';color:#333;margin:0;font-weight:normal;}
#pager ul.pages{padding-top:20px;height:40px;}
#pager ul.pages li{float:left;border:1px solid #ddd;background:#fff; margin:0 8px 0 0;padding:5px 8px;}
#pager ul.pages li:hover{border:1px solid #FF4B01;}
#pager ul.pages li.pgEmpty{border:1px solid #eee;color:#999;}
#pager ul.pages li.pgCurrent{border:1px solid #FF4B01;color:#555;font-weight:700;}

.txt{padding-top:5px;font-size:14px;font-family:Arial,Times New Roman;}
.txt b{margin-left:5px;color:#f60;}
</style>
</head>
<body>
	<form name="historyfm" action="history.html" method="post">
		<input type="hidden" id="begintimehid" name="begintimehid" class="tcal" value="" />
		<input type="hidden" id="endtimehid" name="endtimehid" class="tcal" value="" />
		<input type="hidden" id="www" name="endtimehid" class="tcal" value="222" />
	</form>
	<input type="hidden" id="historyUrl" value="<c:url value="/history.html"/>"/>
	<div class="bq-header">
		<div class="bq-headerc clear">
			<p>
				<a href="/">网站地图 </a> | <a href="/"> 帮助中心</a>
			</p>
			<span>欢迎您，${username}<a
				href="javascript:document.buyerLoginOut.submit();">退出</a></span>
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
	<div class="bq-histroy">
		<div class="bq-paysucess-top">
			<p>
				<span>线上认购登记系统 > </span><span> &nbsp;历史购买记录</span>
			</p>
		</div>
		<div class="bq-histroy-cont">
			<h4>
				<span>历史记录</span>
			</h4>
			<div class="bq-time clear">
				<p>起止日期:&nbsp;</p>
				 <input id="begintime" class="Wdate" name="begintime"
							type="text" size="15"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',startDate:'%y-{%M-1}-%d',maxDate:'%y-%M-%d'})"
							value="${defaultDate }" readonly="readonly" />
				<p>&nbsp;-&nbsp;&nbsp;</p>
				<input id="endtime"
							class="Wdate" name="endtime" type="text" size="15"
							onfocus="WdatePicker({minDate:'#F{$dp.$D(\'begintime\')}',dateFmt:'yyyy-MM-dd',startDate:'%y-{%M-1}-%d',maxDate:'%y-%M-%d'})"
							value="${defaultDate }" readonly="readonly"	readonly="readonly" />
				<input type="button" value="查询" onclick="innerTable(1);"  />
			</div>
			<!-- <h2 class="clear">
				<em>购买分类:&nbsp;</em><i class="bq-addii">全部</i><i>茶叶</i><i>古玩字画</i>
			</h2> -->
		<input type="hidden" id="historyfm" value='<c:url value="/historyfm.html"/>' />
		 <span > 
			<table cellspacing="0" id="hisTabel">
				
			</table>
			 </span>
		</div>
		<div class="demo">
	<div id="pager" ></div>
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
