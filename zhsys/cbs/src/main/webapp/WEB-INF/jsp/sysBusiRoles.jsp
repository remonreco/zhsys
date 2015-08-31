<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>角色管理</title>
<%@include file="include.jsp"%>
<script type="text/javascript" src='<c:url value="/js/pages/sysBusiRoles.js"/>'></script>
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
	<input type="hidden" id="addSysBusiRoleUrl" value='<c:url value="/addSysBusiRole.html"/>' />
	<input type="hidden" id="allSysRolesUrl" value='<c:url value="/allSysRoles.html"/>' />
	<input type="hidden" id="checkDuplicateSysBusiRoleNameUrl" value='<c:url value="/checkDuplicateSysBusiRoleName.html"/>' />
	<input type="hidden" id="updateSysBusiRoleUrl" value='<c:url value="/updateSysBusiRole.html"/>' />
	<input type="hidden" id="querySysBusiRolesUrl" value='<c:url value="/querySysBusiRoles.html"/>' />
	<table id="agrList_data" border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
		<thead>
			<tr align="center">
			</tr>
		</thead>
	</table>
	<!-- 添加用户 -->
	<div id="addRoleDiv" class="easyui-dialog" style="width: 400px; height: 380px; padding: 10px 20px" closed="true" buttons="#addRoleDiv-buttons">
		<form id="addRoleFm" method="post" novalidate>
			<div class="fitem">
				<label class="fitemlabel">角色名:</label> <input class="easyui-validatebox" type="text" id="name1" name="name" data-options="required:true" validType="length[1,10]"></input>
			</div>
			<div class="fitem">
				<label class="fitemlabel">备注:</label> <input class="easyui-validatebox" type="text" id="remark1" name="remark" data-options="required:false" validType="length[0,64]"></input>
			</div>
			<div class="fitem">
				<label class="fitemlabel">功能:</label> <span id="rolespan1"></span>
			</div>
		</form>
	</div>
	<div id="addRoleDiv-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveRole()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#addRoleDiv').dialog('close')">取消</a>
	</div>
	<!-- 修改 -->
	<div id="updateRoleDiv" class="easyui-dialog" style="width: 420px; height: 380px; padding: 10px 20px" closed="true" buttons="#upateRoleDiv-buttons">
		<form id="updateRoleFm" method="post" novalidate>
			<div class="fitem">
				<label class="fitemlabel">角色名:</label> <span id="updateRoleId"></span> <span id="roName"></span>
			</div>
			<div class="fitem">
				<label class="fitemlabel">备注</label> <input class="easyui-validatebox" type="text" name="remark" data-options="required:false" validType='remark1'></input>
			</div>
			<div class="fitem">
				<label class="fitemlabel">功能:</label><span id="rolespan2"></span>
			</div>
		</form>
	</div>
	<div id="upateRoleDiv-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="updateRole()">修改</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#updateRoleDiv').dialog('close')">取消</a>
	</div>
	<div id="tb" style="padding: 5px; height: auto">
		<div>
			角色名:<input class="easyui-validatebox" type="text" name="name" id="name" style="width: 100px"> &nbsp;&nbsp;&nbsp;
			功能:<label><input type="checkbox" id="all" checked="checked" value="" />全部</label>
            <c:forEach items="${roles}" var="role">
                <label><input type="checkbox" name="roles" value="${role.id}" />${role.name}</label>
            </c:forEach>
            &nbsp;&nbsp;&nbsp;
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="queryRoles()">查询</a>|
 	<!-- 		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="toAddRole()">添加</a>|
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="toUpdateRole()">修改</a>  -->
		</div>
	</div>
</body>
</html>