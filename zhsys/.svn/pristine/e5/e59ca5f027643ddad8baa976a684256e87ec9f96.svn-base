<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>产品持有明细</title>
<%@include file="include.jsp"%>
<script type="text/javascript" src='<c:url value="/js/pages/queryAgencyGoods.js"/>'></script>
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

   <div id="anencyGoods" style="padding:5px;height:auto" align="left">
       <form id="angencyGoodsForm" action="" method="post">
 		席位号<input class="easyui-validatebox" type="text" name="clientId" id="clientId" style="width:100px">&nbsp;
 		开始日期<input class="easyui-datebox"  name="initDateStart" id="initDateStart" style="width:100px">&nbsp;
 		结束日期<input class="easyui-datebox"  name="initDateEnd" id="initDateEnd" style="width:100px">&nbsp;
 		产品<input type='text' name="goodId" id="goodId"><br>
		 		<a class="easyui-linkbutton" onclick="selectAngencyGoods()">查询</a>&nbsp;&nbsp;
		 		<a class="easyui-linkbutton"  onclick="outGoodsDetail()">导出明细表</a>&nbsp;&nbsp;
 	    </form>
 	</div>
 	<table id="anencyGoodsList" border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
		<thead>
			<tr align="center">
			</tr>
		</thead>
	</table>
</body>
</html>