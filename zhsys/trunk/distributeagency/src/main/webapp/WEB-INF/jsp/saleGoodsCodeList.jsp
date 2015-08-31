<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>订单信息</title>
<%@include file="include.jsp"%>
<script type="text/javascript" src='<c:url value="/js/pages/saleGoodsCodeList.js"/>'></script>
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


		<div id="saleGoodsCodebar" style="padding:5px;height:auto" align="left">
       <form id="saleGoodsCodefm" action="" method="post">
 		订单编号<input class="easyui-validatebox" type="text" name="id" id="id" style="width:100px">&nbsp;
 		产品名称<input class="easyui-validatebox" type="text" name="goodsName" id="goodsName" style="width:100px">&nbsp;
 		产品编号<input class="easyui-validatebox" type="text" name="goodsNum" id="goodsNum" style="width:100px">&nbsp;
 		<br>
		 		<a class="easyui-linkbutton" onclick="selecsaleGoodsCode()" id='searchBtn'>查询</a>&nbsp;&nbsp;
		 		<a href="javascript:void(0)" class="easyui-linkbutton" id="outSaleGoodsCode">导出销售码</a>&nbsp;&nbsp;
 	    </form>

 	</div>
 	
 	<table id="saleGoodsCodeList" border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
		<thead>
			<tr align="center">
			</tr>
		</thead>
	</table>
 	
</body>
</html>