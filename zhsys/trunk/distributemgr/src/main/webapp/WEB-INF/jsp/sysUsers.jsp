<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>操作员管理</title>
<%@include file="include.jsp"%>
<script type="text/javascript" src='<c:url value="/js/pages/sysUsers.js"/>'></script>
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
	<input type="hidden" id="addSysUserUrl" value='<c:url value="/addSysUser.html"/>' />
	<input type="hidden" id="allSysBusiRolesUrl" value='<c:url value="/allSysBusiRoles.html"/>' />
	<input type="hidden" id="checkDuplicateSysUserLoginNameUrl" value='<c:url value="/checkDuplicateSysUserLoginName.html"/>' />
	<input type="hidden" id="updateSysUserUrl" value='<c:url value="/updateSysUser.html"/>' />
	<input type="hidden" id="querySysUsersUrl" value='<c:url value="/querySysUsers.html"/>' />
	<table id="agrList_data" border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
		<thead>
			<tr align="center">
			</tr>
		</thead>
	</table>
	<!-- 添加用户 -->
	<div id="addUserDiv" class="easyui-dialog" style="width: 400px; height: 380px; padding: 10px 20px" closed="true" buttons="#addUserDiv-buttons">
		<form id="addUserFm" method="post" novalidate>
			<div class="fitem">
				<label class="fitemlabel">登录名:</label> <input class="easyui-validatebox" type="text" id="loginName1" name="loginName" data-options="required:true" validType="length[1,10]"></input>
			</div>
			<div class="fitem">
                <label class="fitemlabel">真实姓名:</label> <input class="easyui-validatebox" type="text" id="realName1" name="realName" data-options="required:true" validType="length[1,10]"></input>
            </div>
			<div class="fitem">
				<label class="fitemlabel">密码:</label> <input class="easyui-validatebox" type="password" id="loginPwd1" name="loginPwd" data-options="required:true" validType="length[6,20]"></input>
			</div>
			<div class="fitem">
				<label class="fitemlabel">确认密码:</label> <input class="easyui-validatebox" type="password" name="loginPwd2" data-options="required:true" validType="equals['#addUserFm #loginPwd1']"></input>
			</div>
			<div class="fitem">
				<label class="fitemlabel">角色:</label> <span id="rolespan1"></span>
			</div>
			<div class="fitem">
				<label class="fitemlabel">邮箱:</label> <input class="easyui-validatebox" type="text" name="email" data-options="required:true" validType="email"></input>
			</div>
			<div class="fitem">
				<label class="fitemlabel">手机号:</label> <input class="easyui-validatebox" type="text" name="mobile" data-options="required:true" validType='mobile'></input>
			</div>
		</form>
	</div>
	<div id="addUserDiv-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">保存</a> <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="javascript:$('#addUserDiv').dialog('close')">取消</a>
	</div>
	<!-- 修改 -->
	<div id="upateUserDiv" class="easyui-dialog" style="width: 420px; height: 380px; padding: 10px 20px" closed="true" buttons="#upateUserDiv-buttons">
		<form id="updateUserFm" method="post" novalidate>
			<div class="fitem">
				<label class="fitemlabel">登录名:</label> <span id="updateUserId"></span>
			</div>
			<div class="fitem">
				<label class="fitemlabel">密码:</label> <input class="easyui-validatebox" type="password" id="loginPwd2" name="loginPwd" validType="length[6,20]"></input> <span style="color: red;">填写则重置密码</span>
			</div>
			<div class="fitem">
				<label class="fitemlabel">确认密码:</label> <input class="easyui-validatebox" type="password" id="loginPwd2_2" name="loginPwd2" validType="equals['#updateUserFm #loginPwd2']"></input>
			</div>
			<div class="fitem">
				<label class="fitemlabel">角色:</label> <span id="rolespan2"></span>
			</div>
			<div class="fitem">
				<label class="fitemlabel">邮箱:</label> <input class="easyui-validatebox" type="text" name="email" data-options="required:false" validType="email"></input>
			</div>
			<div class="fitem">
				<label class="fitemlabel">手机号:</label> <input class="easyui-validatebox" type="text" name="mobile" data-options="required:false" validType='mobile'></input>
			</div>
			<div class="fitem">
				<label class="fitemlabel">状态:</label> <label><input class="easyui-validatebox" type="radio" name="status" value="1"></input>正常 </label><label><input class="easyui-validatebox" type="radio" name="status" value="0"></input>禁用</label>
			</div>
		</form>
	</div>
	<div id="upateUserDiv-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="updateUser()">修改</a> <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="javascript:$('#upateUserDiv').dialog('close')">取消</a>
	</div>
	<div id="tb" style="padding: 5px; height: auto">
		<div>
			登录名:<input class="easyui-validatebox" type="text" name="loginName" id="loginName" style="width: 100px">
			&nbsp;角色:<label><input type="checkbox" id="all" checked="checked" value="" />全部</label>
			<c:forEach items="${roles}" var="role">
				<label><input type="checkbox" name="roles" value="${role.id}" />${role.name}</label>
			</c:forEach>
			&nbsp;状态:<select id="status" class="easyui-combobox" panelHeight="auto" style="width: 60px">
				<option value="">全部</option>
				<option value="1">正常</option>
				<option value="0">禁用</option>
			</select>
			&nbsp;&nbsp;&nbsp;
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="queryUsers()">查询</a>|
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="toAddUser()">添加</a>|
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="toUpdateUser()">修改</a>
		</div>
	</div>
</body>
</html>