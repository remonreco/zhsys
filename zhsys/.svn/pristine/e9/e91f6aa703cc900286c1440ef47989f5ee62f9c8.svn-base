<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>订单查询</title>
<meta name="description" content="">
<meta name="keywords" content="">

<script type="text/javascript" src="js/pages/banquan.js"></script>
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/pages/jquery-1.3.2.min.js" ></script>
<script type="text/javascript" src="js/pages/jquery.pager.js" ></script>
<link rel="stylesheet" type="text/css" href="style/index.css" />
<link rel="stylesheet" type="text/css" href="style/registe.css">
<link rel="stylesheet" type="text/css" href="style/tcal.css" />
    <link rel="stylesheet" type="text/css" href="style/index.css" />
    <link rel="stylesheet" type="text/css" href="style/buqn.css" />
<link rel="stylesheet" href="style/calendar.css" />

<script type="text/javascript" src="js/pages/exQuery.js" ></script>
	<script>
            $(function(){
                $('.hist-new table tr:even').addClass('table-chang');
                 if ($('.hist-new table tr:first').hasClass('table-chang'));
                 {
                    $('.hist-new table tr:first').removeClass('table-chang').addClass('ta-first');
                 }
            })
</script>
    <style>
        .hist-new table tr td {width:133px !important;}
        .hist-new table tr.table-chang{background-color: #f6f6f6; }
        .hist-new table tr td a{width:133px !important; cursor: pointer; font-size: 16px;}
        .hist-new table tr td a.sub{ color:#ff4a00; font-size: 14px;}
        .hist-new table tr td a:link{color:#ff4a00; font-size: 14px;}
        .hist-new table tr td a:visited{color:#ff4a00; font-size: 14px;}
        .hist-new strong span{ width:50px; display: inline-block; text-align: center; margin-right: 10px !important;}
        .hist-new strong span a{display: inline-block; width:50px; border-radius: 6px; background-color:#d16b41; color:#fff; cursor: pointer;}
        .hist-new strong span a:link{background-color:#d16b41; color:#fff; cursor: pointer;}
        .hist-new strong span a:visited{ background-color: #ff4a00; color: #fff; cursor: pointer;}
    </style>
</head>
<body id="exBody">
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
				<span>线上兑换系统 > </span><span> &nbsp;订单查询</span>
			</p>
		</div>
		<div id="pager" ></div>
	</div>	
	
	<form name="ExchangeAction" action="exQueryList.html" method="post"></form>
	
	<div class="bq-histroy">
		<div class="bq-histroy-cont hist-new">
			<table cellspacing="0" id="dataList">
				
			</table>
			<strong><span id="pageNum"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="totalCount"></span><span><a  onclick="firstPage()">首页</a></span><span><a  onclick="previousPage()">上一页</a></span><span><a  onclick="nextPage()">下一页</a></span><span><a onclick="lastPage()">尾页</a></span></strong>
			<input type="hidden" value="1" name="page" id="page"/>
			<input type="hidden" name="totalPage" id="totalPage"/>
			<input type="hidden" name="orderId" id="orderId"/>
			<input type="hidden" name="pageNums" id="pageNums" value="1"/>
		</div>
	</div>
	
	<div id='putText' style="display:none" class='op-sucss' >
		<div id='putText1' class='op-sucss-cont1'>
			<p>确认撤销吗?</p>
			<h5><a  onclick="closeDiv();" >确定</a>&nbsp;&nbsp;&nbsp;&nbsp;<a  onclick='closeDiv1()' >取消</a></h5>
		</div>
	</div>
	
	<div id='putText2' style="display:none" class='op-sucss' >
		<div id='putText1' class='op-sucss-cont1'>
			<p>对不起，您无法进行撤单操作</p>
			<h5><a  onclick="closeDiv2();" >确定</a></h5>
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