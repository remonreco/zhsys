<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>错误信息</title>
<%@include file="include.jsp" %>
<script type="text/javascript" src='<c:url value="/js/pages/repayment.js"/>'></script>
<script type="text/javascript">
	function onBack(){
		window.parent.open1($("#urlName").val(), ctx + $("#urlAddr").val());
		return false;
	}
</script>
</head>
<body>
	<div style="padding: 50px 0 10px 50px" align="left">
			导入失败:<br>
<%-- 			<c:forEach var="error" items="${memo}"> --%>
				${memo }<br>
<%-- 			</c:forEach> --%>
			<a href="toUpSendGoodsByUsers.html" onclick="" class="easyui-linkbutton">返回</a>
		</div>
		<input type="hidden" value="${urlName }" id="urlName"/>
		<input type="hidden" value="${urlAddr }" id="urlAddr"/>
</body>
</html>
