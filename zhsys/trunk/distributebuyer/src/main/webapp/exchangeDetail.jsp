<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>兑换页面</title>
<meta name="description" content="">
<meta name="keywords" content="">

<script type="text/javascript" src="js/pages/banquan.js"></script>
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/pages/jquery-1.3.2.min.js" ></script>
<script type="text/javascript" src="js/pages/jquery.pager.js" ></script>
<link rel="stylesheet" type="text/css" href="style/index.css" />
<link rel="stylesheet" type="text/css" href="style/tcal.css" />
    <link rel="stylesheet" type="text/css" href="style/buqn.css" />
<link rel="stylesheet" href="style/calendar.css" />
<script type="text/javascript" src='<c:url value="/js/pages/exchangeDetail.js"/>'></script>
<script type="text/javascript">
</script>


</head>
<body style="background-color:#f9f9f9;" id="exBody">
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
	<div class="bq-histroy">
		<div class="bq-paysucess-top">
			<p>
				<span>线上兑换系统 > </span><span> &nbsp;商品兑换</span>
			</p>
		</div>
	
		
	</div>
	<input type="hidden" id="comName"  name="comName"/>
	<input type="hidden" id="comId"  name="comId"/>
	<input type="hidden" id="currencyNum" name="currencyNum" />
	<input type="hidden" id="introduction" name="introduction"/>
	<input type="hidden" id="inputNum" name="inputNum"/>
	<input type="hidden" id="exchangeNum" name="exchangeNum"/>
	<input type="hidden" id="exchangeName"   name="exchangeName"/>
	<input type="hidden" id="costNum" name="costNum"/>
	<input type="hidden" id="giveName" name="giveName"/>
	<input type="hidden" id="giveSum" name="giveSum"/>
	<input type="hidden" id="freezeNum" name="freezeNum" />
	<input type="hidden" id="saleUnit" name="saleUnit" />
	<input type="hidden" id="exSaleUnit" name="exSaleUnit" />
	<input type="hidden" id="costSum" name="costSum" />
	<input type="hidden" id="resultFlag" name="resultFlag" />
	<input type="hidden" id="test" name="test" />
	<input type="hidden" id="exId" name="exId" />
	<input type="hidden" id="minNum" name="minNum" />
	<input type="hidden" id="comPicture" name="comPicture" />
	<div id="exchangeGoods" class="ex-changtwo" style="z-index:100;">

	</div>
	<div class="op-sucss-check" id="bakcGround" style="display:none;">
	<div id="submitDiv" class="submitDiv-class">
             <p ><strong>您需要花 费  ：</strong> <em class='currencyNum1'></em></p>
            <p id="giveIdp"><strong id="test">并&nbsp;&nbsp;&nbsp;&nbsp;赠&nbsp;&nbsp;&nbsp;&nbsp;送 ：</strong><i class='giveName1' ></i></p>
            <h6><input type="button" value="确认" onclick="submitForm(1);" />
            <input type="button" value="取消" onclick="submitForm(2);" /></h6>
        </div>

	</div>
	
	<div id='putText' class='op-sucss' style="display:none" ><div  class='op-sucss-cont1'><p>请您输入发货地址</p><h5><a  onclick='closeDiv()' >确定</a></h5></div></div>
	
	<div id='putText1' class='op-sucss' style="display:none"><div class='op-sucss-cont1'><p>请您选择兑换物</p><h5><a  onclick='closeDiv()' >确定</a></h5></div></div>
	
	<div id='putText2' class='op-sucss' style="display:none"><div  class='op-sucss-cont1'><p>该物品存货不足</p><h5><a  onclick='closeDiv()' >确定</a></h5></div></div>
	
	<div id='putText3' class='op-sucss' style="display:none"><div  class='op-sucss-cont1'><p id="shopSum">  </p><h5><a  onclick='closeDiv()' >确定</a></h5></div></div>
	
	<div id='putText4' class='op-sucss' style="display:none"><div  class='op-sucss-cont1'><p>兑换数量必须是数字</p><h5><a  onclick='closeDiv()' >确定</a></h5></div></div>
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