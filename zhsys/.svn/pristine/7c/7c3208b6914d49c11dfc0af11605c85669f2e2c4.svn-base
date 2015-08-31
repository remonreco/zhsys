<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>订单信息</title>
<%@include file="include.jsp"%>
<script type="text/javascript" src='<c:url value="/js/pages/toBeSendGoodsList.js"/>'></script>
<style type="text/css">
#fm {
	margin: 0;
	padding: 10px 30px;
}

.ftitle {
	font-size: 14px;
	font-weight: bold;
	padding: 5px 0;
	margin-bottom: 10px;
	border-bottom: 1px solid #ccc;
}

.fitem {
	margin-bottom: 5px;
}

.fitemlabel {
	display: inline-block;
	width: 80px;
}
</style>
</head>
<body>

<%-- 		<input type="hidden" id="addDealerUrl" value='<c:url value="/addDealer.html"/>' />
		<input type="hidden" id="checkGoodsOrder" value='<c:url value="/checkGoodsOrder.html"/>' /> --%>
		<input type="hidden" id="updateOrderStateById" value='<c:url value="/updateOrderStateById.html"/>' />
		<div id="toBeSendGoodsListbar" style="padding:5px;height:auto" align="left">
       <form id="goodsOrderfm" action="" method="post">
 		产品名称<input class="easyui-validatebox" type="text" name="goodsName" id="goodsName" style="width:100px">&nbsp;
 		产品编号<input class="easyui-validatebox" type="text" name="goodsNum" id="goodsNum" style="width:100px">&nbsp;
 		订单下发日期<input class="easyui-datebox"  name="startDate" id="startDate" style="width:100px">&nbsp;
 		至<input class="easyui-datebox"  name="endDate" id="endDate" style="width:100px">&nbsp;
		 <a class="easyui-linkbutton" onclick="selectToBeSendGoodsList()">查询</a>&nbsp;&nbsp;
 	    </form>
 	           <form id="goodsOrderfm" action="" method="post">
<!-- 		 		<a class="easyui-linkbutton" onclick="sendGoods()">发货</a>&nbsp;&nbsp; -->
		 		<a class="easyui-linkbutton" onclick="receiveGoods()">领取产品</a>&nbsp;&nbsp;
 	    </form>
 	</div>
 	
 	<table id="toBeSendGoodsList" border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
		<thead>
			<tr align="center">
			</tr>
		</thead>
	</table>
 	
	<div id="sendGoodsDiv"></div>
</body>
</html>