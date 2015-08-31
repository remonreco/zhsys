<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>功能列表</title>
<%@include file="include.jsp"%>
</head>
<body>
	<table class="easyui-datagrid" title="功能列表" style="width: 550px;" data-options="fit:true,rownumbers:true,singleSelect:true,url:'<c:url value="/queryAgencyRoles.html"/>'">
		<thead>
			<tr>
				<th data-options="field:'name',width:400">功能名称</th>
				<th data-options="field:'remark',width:400">备注</th>
			</tr>
		</thead>
	</table>
</body>
</html>
