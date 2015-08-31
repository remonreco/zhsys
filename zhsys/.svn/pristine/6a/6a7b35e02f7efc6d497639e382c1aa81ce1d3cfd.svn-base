<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>产品管理</title>
<%@include file="include.jsp"%>
<script type="text/javascript" src='<c:url value="/js/pages/returnHis.js"/>'></script>
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
 		产品名称&nbsp;&nbsp;<input class="easyui-validatebox" type="text" name="goodsName" id="goodsName" style="width:100px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 		产品编号&nbsp;&nbsp;<input class="easyui-validatebox" type="text" name="goodsNum" id="goodsNum" style="width:130px">&nbsp;
			<br>
		 		<a class="easyui-linkbutton" onclick="selectGoodsOrder()">查询</a>&nbsp;&nbsp;&nbsp;
	  </form>
 	</div>
	
	<table id="goodList" border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
		<thead>
			<tr align="center">
			</tr>
		</thead>
	</table>
	<div id ="viewDlg"></div>
</body>
</html>