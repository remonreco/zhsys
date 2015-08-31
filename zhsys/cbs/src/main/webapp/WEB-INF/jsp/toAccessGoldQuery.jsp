<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>经销商管理</title>
<%@include file="include.jsp"%>
<script type="text/javascript" src='<c:url value="/js/pages/accessGold.js"/>'></script>
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
	<!-- <input type="hidden" id="checkTAgencyBydealerNumAndName" value='<c:url value="/checkAccessByaccountAnddeveloper.html"/>' /> -->
		<div id="accessGoldbar" style="padding:5px;height:auto" align="left">
       <form id="accessGoldfm" action="" method="post">
 		经销商编码<input class="easyui-validatebox" type="text" name="developer" id="developer" style="width:130px">&nbsp;
 		席位号<input class="easyui-validatebox" type="text" name="clientId" id="clientId" style="width:130px">&nbsp;
 		开始日期<input class="easyui-datebox"  name="initDateStart" id="initDateStart" style="width:100px">&nbsp;
 		结束日期<input class="easyui-datebox"  name="initDateEnd" id="initDateEnd" style="width:100px">&nbsp;
			<br>
		 		<a class="easyui-linkbutton" onclick="selectAccessGold()">查询</a>&nbsp;&nbsp;
		 		<a class="easyui-linkbutton"  onclick="outAccessGoldDetail()">导出出入金明细表</a>&nbsp;&nbsp;
 	    </form>
 	</div>
 	
 	<table id="accessGoldList" border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
		<thead>
			<tr align="center">
			</tr>
		</thead>
	</table>
 	
 	
	
</body>
</html>