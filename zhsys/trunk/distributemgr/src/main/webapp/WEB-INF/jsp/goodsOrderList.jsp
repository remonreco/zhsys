<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>撤销产品管理</title>
<%@include file="include.jsp"%>
<script type="text/javascript" src='<c:url value="/js/pages/goodsOrder.js"/>'></script>
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
		<div id="tgoodsOrderbar" style="padding:5px;height:auto" align="left">
       <form id="goodsOrderfm" action="" method="post">
 		订单编号<input class="easyui-validatebox" type="text" name="id" id="id" style="width:100px">&nbsp;
 		产品名称<input class="easyui-validatebox" type="text" name="goodsName" id="goodsName" style="width:100px">&nbsp;
 		产品编号<input class="easyui-validatebox" type="text" name="goodsNum" id="goodsNum" style="width:100px">&nbsp;
 		<br>
 	<!-- 	所属订单编号<input class="easyui-validatebox" type="text" name="higherOrderId" id="higherOrderId" style="width:100px">&nbsp;
 		所属经销商名称<input class="easyui-validatebox" type="text" name="agencyId" id="agencyId" style="width:100px">&nbsp;
 		是否过期<select id="isExpired" name="isExpired" ><option value="yes">已过期</option><option value="no">未过期</option></select>  -->
 		<br>
		 		<a class="easyui-linkbutton" onclick="selectgoodsorder()" id='searchBtn'>查询</a>&nbsp;&nbsp;
		 		<a class="easyui-linkbutton" id="accessBtn">撤销</a>&nbsp;&nbsp;
 	    </form>

 	</div>
 	
 	<table id="tgoodsOrderList" border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
		<thead>
			<tr align="center">
			</tr>
		</thead>
	</table>
 	
	<div id="tgoodsOrderAudit_Div"></div>
	
</body>
</html>