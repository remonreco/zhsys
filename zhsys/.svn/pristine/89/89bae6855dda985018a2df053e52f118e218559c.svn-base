<%-- <%@ page session="false"%> --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="stylesheet" type="text/css" href='<c:url value="/css/easyui/default/easyui.css"/>' />
<link rel="stylesheet" type="text/css" href='<c:url value="/css/easyui/icon.css"/>' />
<script type="text/javascript" src='<c:url value="/js/jquery-1.11.2.min.js"/>'></script>
<script type="text/javascript" src='<c:url value="/js/jquery.easyui.min.js"/>'></script>
<script type="text/javascript" src='<c:url value="/js/easyui-lang-zh_CN.js"/>'></script>
<style type="text/css">
#centerdiv {
	position: fixed;
	top: 50%;
	left: 50%;
	margin-top: -9em; /*set to a negative number 1/2 of your height*/
	margin-left: -15em; /*set to a negative number 1/2 of your width*/
	border: 1px solid #ccc;
	background-color: #f3f3f3;
}
</style>
</head>
<body onkeypress="KeyDown()">
	<div id="centerdiv">
		<div class="easyui-panel" title="经销商系统——业务管理" style="width: 400px">
			<div style="padding: 10px 0 10px 60px">
				<form id="loginForm" action="<c:url value="/login.html"/>" method="post">
					<table>
						<tr>
							<td>用户名:</td>
							<td><input class="easyui-validatebox" type="text" name="username" data-options="required:true" value="admin"></input></td>
						</tr>
						<tr>
							<td>密码:</td>
							<td><input class="easyui-validatebox" type="password" name="password" data-options="required:true" value="111111"></input></td>
						</tr>
					</table>
				</form>
			</div>  
			
			<c:if test="${not empty sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}">
				<span style="color:red;text-align: center; padding: 5px">
                   	登录失败：
					${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
				</span>
			</c:if>
			
			<div style="text-align: center; padding: 5px">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">登录</a> <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">清空</a>
			</div>
		</div>
	</div>
	<script>
		function submitForm() {
			if ($('#loginForm').form('validate')) {
				$('#loginForm').submit();
			}
		}
		function clearForm() {
			$('#loginForm').form('clear');
		}
		  if   (top   !=   window){   
		      alert("您已超时，需重新登录");
		      top.location.href   =   window.location.href;  
		    }
		//回车登陆
		  function KeyDown()
		  {
		    if (event.keyCode == 13)
		    {
		      event.returnValue=false;
		      event.cancel = true;
		      $('#loginForm').submit();
		    }
		  }  
	</script>
</body>
</html>
