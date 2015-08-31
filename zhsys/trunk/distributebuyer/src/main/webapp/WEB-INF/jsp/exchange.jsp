<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>商品页面</title>
<meta name="description" content="">
<meta name="keywords" content="">

<script type="text/javascript" src="js/pages/banquan.js"></script>
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/pages/jquery-1.3.2.min.js" ></script>
<script type="text/javascript" src="js/pages/jquery.pager.js" ></script>
<link rel="stylesheet" type="text/css" href="style/index.css" />
<link rel="stylesheet" type="text/css" href="style/registe.css">
<link rel="stylesheet" type="text/css" href="style/tcal.css" />
    <link rel="stylesheet" type="text/css" href="style/buqn.css" />
<link rel="stylesheet" href="style/calendar.css" />

<script type="text/javascript" src="js/pages/exchange.js" ></script>
<script type="text/javascript">
	
function EnterPress(e){ //传入 event
	var e = e || window.event;
	if(e.keyCode == 13){
	document.getElementById("textVal").focus();
	innerTable("");
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
				<li class="first">
				<c:if test="${not empty sessionScope.username}">
                	<a href="<%=request.getContextPath()%>/convertList.jsp">线上兑换系统</a>
                	</c:if>
                	<a href="<%=request.getContextPath()%>/convert.jsp">线上兑换系统</a>
				</li>
				<li><a href="http://www.cbice.com/" target="_black">国际版权网</a></li>
				<li><a href="http://www.e-artist.cn/" target="_black">艺术+</a></li>
				<li><a href="http://www.art.cbice.com/" target="_black">投资+</a></li>
				<li><a href="http://www.piao.cbice.com/" target="_black">消费+</a></li>
				<li><a href="http://www.cbice.com/article/wsyyt/" target="_black">收藏+</a></li>

			</ul>
		</div>

	</div>
	<div class="bq-histroy">
		<div class="bq-paysucess-top">
			<p>
				<span>线上兑换系统 > </span><span> &nbsp;物品列表</span>
			</p>
		</div>
	<div id="pager" ></div>
	
	<form name="ExchangeAction" action="exchangeInfo.html" method="post"></form>
	
	<input type="hidden" id="exchangefm" value='<c:url value=""/>' />
	<div class="ex-changcot">
	    <div class="ex-changcot-top" id="productType">
           
        </div>		
		<div class='ex-changcot-mid' id="exchange">
			
		
		</div>
		 <div class="ex-changcot-bot">
            <p >
            	<b>
            		<select id="selectVal"  onchange="selectChange()">	
	            		<option >5</option>
	            		<option >10</option>
	            		<option >20</option>
	            		<option>50</option>
            		</select>
            	</b>
            	<b style="cursor:pointer">
            		<i>
            			<a onclick="firstPage()"><img src="images/ic1.png" /></a>
            		</i>
            		<i>
            			<a onclick="previousPage()"><img src="images/ic2.png" /></a>
            		</i>
            	</b>
            	<span>第<input type="text"  id="textVal" value="1" onkeypress="EnterPress(event)" onkeydown="EnterPress()" />共<em id="totalPage"></em>页</span>
            	<b style="cursor:pointer">
            		<i>
            			<a onclick="nextPage()"><img src="images/ic3.png" /></a>
            		</i>
            		<i>
            			<a onclick="lastPage()"><img src="images/ic4.png" /></a>
            		</i>
            	</b>
            	<del style="cursor:pointer">
            		<a onclick="refresh()"><img src="images/ic5.png" /></a>
            	</del> 
            	<strong id="total"></strong>
            </p>
            <p style="background-color:#fff; border-top:0"></p>
        </div>
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