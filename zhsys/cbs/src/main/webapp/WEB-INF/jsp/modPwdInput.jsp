<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="include.jsp"%>
<title>修改密码</title>
<script type="text/javascript">
	// extend the 'equals' rule  
	$.extend($.fn.validatebox.defaults.rules, {
		equals : {
			validator : function(value, param) {
				return value == $(param[0]).val();
			},
			message : '密码输入不一致'
		}
	});
	function submitForm() {
		$('#loginForm').form('submit', {
			onSubmit : function() {
				return $(this).form('validate');
			},
			success : function(data) {
				$.messager.alert('Info', data, 'info');
				if('"密码修改成功"' == data){
					clearForm();
					$('#w').window('close');
				}
			}
		});
	}
	function clearForm() {
		$('#loginForm').form('clear');
	}
	$('#w').window('center');
</script>
</head>
<body>
	<div id="w" class="easyui-window" title="修改密码" data-options="iconCls:'icon-save',collapsible:false,minimizable:false,maximizable:false,closable:false"
		style="width: 500px; height: 200px; padding: 10px;">
		<div style="padding: 0 0 0 0">
			<form id="loginForm" action="<c:url value="/modPwd.html"/>" method="post">
				<table>
					<tr>
						<td>原密码：</td>
						<td><input class="easyui-validatebox" type="password" id="orginPwd" name="orginPwd" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>输入新密码：</td>
						<td><input class="easyui-validatebox" type="password" id="newPwd" name="newPwd" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>再次输入新密码：</td>
						<td><input class="easyui-validatebox" type="password" id="newPwd2" name="newPwd2" required="required" validType="equals['#newPwd']"></input></td>
					</tr>
				</table>
			</form>
		</div>
		<div style="text-align: center; padding: 10px">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">修改</a>
		</div>
	</div>
</body>
</html>