<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>下级退货申请管理</title>
<%@include file="include.jsp"%>
<script type="text/javascript" src='<c:url value="/js/pages/lowReturnsGoods.js"/>'></script>
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

tr{
	margin-top: 20px;
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
		 		<a class="easyui-linkbutton" id='agreeBtn'>同意</a>&nbsp;&nbsp;
		 		<a class="easyui-linkbutton" id='unAgreeBtn'>驳回</a>&nbsp;&nbsp;
 	    </form>
 	</div>
 	
 	<table id="remainderGoodsOrderList" border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
		<thead>
			<tr align="center">
			</tr>
		</thead>
	</table>
	
	<!-- 驳回退货申请界面 -->
	<div id="unAgreeReturnGoodsOrder" style="display: none;">
       <form id="unAgreeForm" action="" method="post">
	       	<table>
	       	<tr>
	       		<td>经销商名称:</td>
	       		<td>
	       			<span name="dealerName2" id="dealerName2" style="width:100px">&nbsp;</span>
	       			<input type="hidden" name="id" id="id"/>
	       			<input id="user_id" name="user_id" type="hidden" />
					<input id="agency_id" name="agency_id" type="hidden" />
					<input id="check_goodsNum" name="goodsNum" type="hidden" />
					<input id="check_distributeNum" name="distributeNum" type="hidden" />
	       		</td>
	       	</tr>
	       	<tr>
	       		<td>驳回原因&nbsp;</td>
	       		<td>
	       			<textarea  name="goodsReason" id="goodsReason" style="width:100px" rows="4" cols="200" maxlength="32"></textarea>
	       		</td>
	       	</tr>
	       	</table>
   		</form>
	</div>
</body>
</html>