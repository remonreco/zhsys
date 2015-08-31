<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>本级退货申请管理</title>
<%@include file="include.jsp"%>
<script type="text/javascript" src='<c:url value="/js/pages/remainderGoodsApplication.js"/>'></script>
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

   <div id="remainderGoodsOrderbar" style="padding:5px;height:auto" align="left">
       <form id="remainderGoodsOrderfm" action="" method="post">
 		产品名称<input class="easyui-validatebox" type="text" name="goodsName" id="goodsName" style="width:100px">&nbsp;
 		产品编号<input class="easyui-validatebox" type="text" name="goodsId" id="goodsId" style="width:100px">&nbsp;
 		所属经销商名称<input class="easyui-validatebox" type="text" name="dealerName" id="dealerName" style="width:100px">&nbsp;
 		<br>
		 		<a class="easyui-linkbutton" onclick="selectReturnGoodsApplication()">查询</a>&nbsp;&nbsp;
		 		<a type="button" class="easyui-linkbutton" data-options="iconCls : 'icon-edit', plain : true," id='remReturnGoodsOrder'>退货申请</a>&nbsp;&nbsp;
 	    </form>
 	</div>
 	
 	<table id="remainderGoodsOrderList" border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
		<thead>
			<tr align="center">
			</tr>
		</thead>
	</table>
	
	<!-- 退货申请 -->
	<div id="rem_goodsOrder"  style="display: none;">
		<form action="" id="remGoodsOrderForm" method="post">
			<table id="remTable">
			<tr>
				<td>经销商名称：</td>
				<td><input id="rem_dealerName" name="dealer_name" type="text" readonly="readonly"/></td>
				<td><input id="rem_user_id" name="user_id" type="hidden"></td>
				
				
			</tr>
			<tr>
				<td>产品名称：</td>
				<td><input id="rem_goodsName" name="goods_name" type="text" readonly="readonly"/></td>
			</tr>
			<tr>
				<td>产品编号：</td>
				<td><input id="rem_goodsId" name="t_goods_num" type="text" readonly="readonly"></td>
			</tr>
			<tr>
				<td>产品数量：</td>
				<td><input id="rem_goodsNum" name="goods_num" type="text" readonly="readonly"/></td>
			</tr>
			<tr>
				<td>退货原因：</td>
				<td>
					<textarea id="rem_returnGoodsReason" name="returnGoodsReason" rows="5" cols="50" class="text"></textarea>
				</td>
			</tr>
			</table>
		</form>
	</div>

</body>
</html>