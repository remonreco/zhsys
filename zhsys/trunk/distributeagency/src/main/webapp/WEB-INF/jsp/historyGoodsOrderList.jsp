<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>订单信息</title>
<%@include file="include.jsp"%>
<script type="text/javascript" src='<c:url value="/js/pages/historyGoodsOrder.js"/>'></script>
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
		<div id="thistoryGoodsOrderbar" style="padding:5px;height:auto" align="left">
       <form id="historyGoodsOrderfm" action="" method="post">
 		批次号<input class="easyui-validatebox" type="text" name="batchId" id="batchId" style="width:100px">&nbsp;
 		产品名称<input class="easyui-validatebox" type="text" name="goodsName" id="goodsName" style="width:100px">&nbsp;&nbsp;
 		订单有效期:<input class="easyui-datebox" type="text" name="orderTimeStart" id="orderTimeStart" style="width:80px">
 		至:<input class="easyui-datebox" type="text" name="orderTimeEnd" id="orderTimeEnd" style="width:90px">&nbsp;<br>
		 		<a class="easyui-linkbutton" onclick="selecthistorygoodsorder()">查询</a>&nbsp;&nbsp;
		 		<a class="easyui-linkbutton"  id="searchDetail">查询批次详情</a>&nbsp;&nbsp;
 	    </form>
 	</div>
 	
 	<table id="thistoryGoodsOrderList" border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
		<thead>
			<tr align="center">
			</tr>
		</thead>
	</table>
 	<div id="viewDlg" >  	</div>
 	

	
</body>
</html>