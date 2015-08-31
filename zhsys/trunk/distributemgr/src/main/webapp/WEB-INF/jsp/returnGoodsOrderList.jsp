<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>退货申请管理</title>
<%@include file="include.jsp"%>
<script type="text/javascript" src='<c:url value="/js/pages/returnGoodsOrder.js"/>'></script>
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

   <div id="returnGoodsOrderbar" style="padding:5px;height:auto" align="left">
       <form id="returnGoodsOrderfm" action="" method="post">
 		产品名称<input class="easyui-validatebox" type="text" name="goodsName" id="goodsName" style="width:100px">&nbsp;
 		产品编号<input class="easyui-validatebox" type="text" name="goodsNum" id="goodsNum" style="width:100px">&nbsp;
 		所属经销商名称<input class="easyui-validatebox" type="text" name="dealerName" id="dealerName" style="width:100px">&nbsp;
 		审核状态<select id="return_goods_state" style="width: 100px;"  class="easyui-combobox" data-options="editable:false,panelHeight: 'auto'">
					<option value="">全部</option>
					<option value="0">未审核</option>
					<option value="1">审核通过</option>
					<option value="2">审核不通过</option>
				</select>&nbsp;
 		<br>
		 		<a class="easyui-linkbutton" onclick="selectreturngoodsorder()">查询</a>&nbsp;&nbsp;
		 		<a type="button" class="easyui-linkbutton" data-options="iconCls : 'icon-edit', plain : true," id='checkReturnGoodsOrder'>退货清单审核</a>&nbsp;&nbsp;
 	    </form>
 	</div>
 	
 	<table id="returnGoodsOrderList" border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
		<thead>
			<tr align="center">
			</tr>
		</thead>
	</table>
 	<!-- 退货申请审核 -->
	<div id="check_goodsOrder"  style="display: none;">
		<form action="" id="checkGoodsOrderForm" method="post">
			<table id="checkTable">
			<tr>
				<td>经销商名称：</td>
				<td><input id="check_dealerName" name="dealer_name" type="text" readonly="readonly"/></td>
				<td><input id="id" name="id" type="hidden" />
				<input id="higer_dealer_id" name="higer_dealer_id" type="hidden" />
				<input id="agency_id" name="agency_id" type="hidden" />
				</td>
				
			</tr>
			<tr>
				<td>产品名称：</td>
				<td><input id="check_goodsName" name="goods_name" type="text" readonly="readonly"/></td>
			</tr>
			<tr>
				<td>产品编号：</td>
				<td><input id="check_goodsNum" name="goods_num" type="text" readonly="readonly"></td>
			</tr>
			<tr>
				<td>产品数量：</td>
				<td><input id="check_distributeNum" name="distribute_num" type="text" readonly="readonly"/></td>
			</tr>
			<tr>
				<td>审核原因：</td>
				<td>
					<textarea id="check_returnGoodsOptions" name="return_goods_options" rows="5" cols="50" class="text"></textarea>
				</td>
			</tr>
			</table>
		</form>
	</div>

	
</body>
</html>