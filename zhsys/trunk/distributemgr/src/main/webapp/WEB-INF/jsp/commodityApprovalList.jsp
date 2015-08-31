<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>商品信息</title>
<%@include file="include.jsp"%>
<script type="text/javascript" src='<c:url value="/js/pages/commodityApproval.js"/>'></script>
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
	<input type="hidden" id="commodityApprovalUrl" value='<c:url value="/doCommodityApproval.html"/>' />
	<div id="tCommodityApprovalbar" style="padding:5px;height:auto" align="left">
       <form id="commodityApprovalfm" action="" method="post">
 		兑请者<input class="easyui-validatebox" type="text" name="customerName" id="customerName" style="width:100px">&nbsp;
 		兑换物名<input class="easyui-validatebox" type="text" name="exchangeName" id="exchangeName" style="width:130px">&nbsp;
 		开始日期<input class="easyui-datebox"  name="approvalTimeStart" id="approvalTimeStart" style="width:100px">&nbsp;
 		结束日期<input class="easyui-datebox"  name="approvalTimeEnd" id="approvalTimeEnd" style="width:100px">&nbsp;
			<br>
		 		<a class="easyui-linkbutton" onclick="selectCommodity()" id='searchBtn'>查询</a>&nbsp;&nbsp;
		 		<a type="button" class="easyui-linkbutton" onclick="commodityApproval()" data-options="iconCls : 'icon-edit', plain : true," >兑换商品审核</a>&nbsp;&nbsp;
		 		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="downLoad()">下载</a>&nbsp;&nbsp;
		 		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="downLoadEx()">兑换信息下载</a>&nbsp;&nbsp;
 	    </form>
 	</div>
 	
 	<table id="tCommodityApprovalList" border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
		<thead>
			<tr align="center">
			</tr>
		</thead>
	</table>
 	
</body>
</html>