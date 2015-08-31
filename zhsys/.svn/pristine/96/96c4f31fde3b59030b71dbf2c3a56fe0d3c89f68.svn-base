<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>产品管理</title>
<%@include file="include.jsp"%>
<script type="text/javascript" src='<c:url value="/js/pages/goodMgr.js"/>'></script>
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
<script type="text/javascript">
var VbatchId='${id}';
</script>
</head>
<body>
	
		<div id="tuserbar" style="padding:5px;height:auto" align="left">
       <form id="dealerfm" action="" method="post">
       		 <input type="hidden" name="VbatchId" id="VbatchId" value ='${id}'>
 		会员名称&nbsp;&nbsp;<input class="easyui-validatebox" type="text" name="userName" id="userName" style="width:130px">&nbsp;
 		<!-- <a class="easyui-linkbutton" name="viewOrder" id="viewOrder">查看详情</a>&nbsp;&nbsp;&nbsp; -->
			<br>
		 		<a class="easyui-linkbutton" onclick="selectGoodsOrder()">查询</a>&nbsp;&nbsp;&nbsp;
		 		<!-- <a class="easyui-linkbutton" name="agree" id="agree">批量同意</a>&nbsp;&nbsp;&nbsp;
		 		<a class="easyui-linkbutton"  name="disAgree" id="disAgree">批量驳回</a>&nbsp;&nbsp;&nbsp;
		 		审核意见&nbsp;&nbsp;<input class="easyui-validatebox" type="text" name="reason" id="reason" style="width:130px">&nbsp; -->
		 		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="goodsOrderAudit()">批量审核</a>
	  </form>
 	</div>
	
	<table id="goodList" border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
		<thead>
			<tr align="center">
			</tr>
		</thead>
	</table>
	<div id ="viewDlg"></div>
	<div id ="goodsOrderAuditDlg"></div>
	<div id="goodsOrderAuditDiv" class="easyui-dialog" style="width: 400px; height: 380px; padding: 10px 20px" closed="true" buttons="#goodsOrderAuditDiv-buttons">
		<form id="goodsOrderAuditFm" method="post" novalidate>
		审核意见&nbsp;&nbsp;<br><br>
		<!-- <input class="easyui-validatebox" type="text" name="reason" id="reason" style="width:130px">&nbsp; -->
		<input class="easyui-textbox easyui-validatebox" id="reason" validType="length[1,64]" data-options="multiline:true" style="width:300px;height:100px">
		</form>
	</div>
	<div id="goodsOrderAuditDiv-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="agreeGoodsOrder()">同意</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="rejectGoodsOrder()">驳回</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#goodsOrderAuditDiv').dialog('close')">取消</a>
	</div>
</body>
</html>