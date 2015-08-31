<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>经销商</title>
<%@include file="include.jsp"%>
<script type="text/javascript" src='<c:url value="/js/pages/sendGoodsInformation.js"/>'></script>
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
    <input type="hidden" id="loadoutTuserAndGoodsUrl" value='<c:url value="/loadoutTuserAndGoods.html"/>' />
		<div id="sendGoodsbar" style="padding:5px;height:auto" align="left">
       <form id="sendGoodsfm" action="" method="post">
 		产品编号<input class="easyui-validatebox" type="text" name="goodsNum" id="goodsNum" value="<%if(request.getAttribute("goodsNum")!=null ){
 				 %><%=request.getAttribute("goodsNum") %>
 				<%} %>" style="width:100px">&nbsp;
 		产品名称<input class="easyui-validatebox" type="text" name="goodsName" id="goodsName" value="<%if(request.getAttribute("goodsName")!=null ){
 				 %><%=request.getAttribute("goodsName") %>
 				<%} %>" style="width:130px">&nbsp;
 				<input class="easyui-validatebox" type="hidden" name="ids" id="ids" style="width:130px" />&nbsp;
			<br>
		 		<a href="javascript:void(0)" class="easyui-linkbutton" id="loadOutUsersGoods">导出下级会员和经销商</a>&nbsp;&nbsp;
		 		<a href="javascript:void(0)" class="easyui-linkbutton" id="loadOutAllUsersGoods">导出下级会员和经销商</a>&nbsp;&nbsp;
		 		<a class="easyui-linkbutton" class="easyui-linkbutton" onclick="upSendGoodsByUsers()">导入发货信息表</a>&nbsp;&nbsp;
 	    </form>
 	</div>
 	
 	<table id="sendGoodsList" border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
		<thead>
			<tr align="center">
			</tr>
		</thead>
	</table>
</body>
</html>