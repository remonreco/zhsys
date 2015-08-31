<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>经销商</title>
<%@include file="include.jsp"%>
<script type="text/javascript" src='<c:url value="/js/pages/tUser.js"/>'></script>
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
	<input type="hidden" id="addUserUrl" value='<c:url value="/addTuser.html"/>' />
	<input type="hidden" id="editUserUrl" value='<c:url value="/editTuser.html"/>' />
	<input type="hidden"  id="agencyId"  name ="agencyId" value ="${agencyId}">
	<input type="hidden" id="checkTuserByUserNumAndName" value='<c:url value="/checkTuserByUserNumAndName.html"/>' />
		<div id="tuserbar" style="padding:5px;height:auto" align="left">
       <form id="Userfm" action="" method="post">
 		资产帐号<input class="easyui-validatebox" type="text" name="assetsAccount" id="assetsAccount" style="width:100px">&nbsp;
 		会员名称<input class="easyui-validatebox" type="text" name="userName" id="userName" style="width:100px">&nbsp;
 		联系电话<input class="easyui-validatebox" type="text" name="tel" id="tel" style="width:100px">&nbsp;
			<br>
		 		<a class="easyui-linkbutton" onclick="selectUser()">查询</a>&nbsp;&nbsp;
		 		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="addUser()">添加</a>&nbsp;&nbsp;
		 		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="toUpdateUser()">编辑</a>&nbsp;&nbsp;
 	    </form>
 	</div>
 	
 	<table id="tuserList" border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
		<thead>
			<tr align="center">
			</tr>
		</thead>
	</table>
 	
 	
    <!-- 添加会员 -->
	<div id="addUserDiv" class="easyui-dialog" style="width: 400px; height: 380px; padding: 10px 20px" closed="true" buttons="#addUserDiv-buttons">
		<form id="addUserFm" method="post" novalidate>
			<div class="fitem">
				<label class="fitemlabel">资产帐号:</label> <input class="easyui-validatebox" type="text" id="assetsAccount2" name="assetsAccount2" data-options="required:true" validType="length[0,64]"></input>
			</div>
			<div class="fitem">
				<label class="fitemlabel">权益帐号:</label> <input class="easyui-validatebox" type="text" id="rightsAccount2" name="rightsAccount2" data-options="required:true" validType="length[0,64]"></input>
			</div>
			<div class="fitem">
				<label class="fitemlabel">会员名称:</label> <input class="easyui-validatebox" type="text" id="userName2" name="userName2" data-options="required:true" validType="length[0,64]"></input>
			</div>
			<div class="fitem">
				<label class="fitemlabel">性别:</label> 
				<select id="sex2" name="sex2" required="required" class="easyui-combobox combobox-f combo-f textbox-f" style="width: 60px; display: none;" panelheight="auto">
				<option value="0">女</option>
				<option value="1">男</option>
				</select>
			</div>
			<div class="fitem">
				<label class="fitemlabel">证件号码:</label> <input class="easyui-validatebox" type="text" id="certificateNum2" name="certificateNum2" data-options="required:true" validType="length[0,64]"></input>
			</div>
			<div class="fitem">
				<label class="fitemlabel">邮箱:</label> <input class="easyui-validatebox" type="text" id="email2" name="email2" data-options="required:true" validType="email"></input>
			</div>
			<div class="fitem">
				<label class="fitemlabel">联系方式:</label> <input class="easyui-validatebox" type="text" id="tel2" name="tel2" data-options="required:true" ></input>
			</div>
			<div class="fitem">
				<label class="fitemlabel">地址:</label> <input class="easyui-validatebox" type="text" id="address2" name="address2" data-options="required:false" validType="length[0,64]"></input>
			</div>
			<div class="fitem">
				<label class="fitemlabel">所属经销商:</label>  <input id="lowerDealer2" name="lowerDealer2" style="width:160px;" > 
			</div>
		</form>
	</div>
	<div id="addUserDiv-buttons">
		<a href="javascript:void(0)" id="addUserSave"  class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">保存</a> <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="javascript:$('#addUserDiv').dialog('close')">取消</a>
	</div>
	
	 <!-- 编辑会员 -->
	<div id="editUserDiv" class="easyui-dialog" style="width: 400px; height: 380px; padding: 10px 20px" closed="true" buttons="#editUserDiv-buttons">
		<form id="editUserFm" method="post" novalidate>
			 <input type="hidden" name="id" id="id">
			<div class="fitem">
				<label class="fitemlabel">资产帐号:</label> <input class="easyui-validatebox" type="text" id="assetsAccount3" name="assetsAccount3" data-options="required:true" validType="length[0,64]"></input>
			</div>
			<div class="fitem">
				<label class="fitemlabel">权益帐号:</label> <input class="easyui-validatebox" type="text" id="rightsAccount3" name="rightsAccount3" data-options="required:true" validType="length[0,64]"></input>
			</div>
			<div class="fitem">
				<label class="fitemlabel">会员名称:</label> <input class="easyui-validatebox" type="text" id="userName3" name="userName3" data-options="required:true" validType="length[0,64]"></input>
			</div>
			<div class="fitem">
				<label class="fitemlabel">性别:</label> 
				<select id="sex3" name="sex3"  required="required" class="easyui-combobox combobox-f combo-f textbox-f" style="width: 60px; display: none;" panelheight="auto">
				<option value="0">女</option>
				<option value="1">男</option>
				</select>
			</div>
			<div class="fitem">
				<label class="fitemlabel">证件号码:</label> <input class="easyui-validatebox" type="text" id="certificateNum3" name="certificateNum3" data-options="required:true" validType="length[0,64]"></input>
			</div>
			<div class="fitem">
				<label class="fitemlabel">邮箱:</label> <input class="easyui-validatebox" type="text" id="email3" name="email3" data-options="required:true" validType="email"></input>
			</div>
			<div class="fitem">
				<label class="fitemlabel">联系方式:</label> <input class="easyui-validatebox" type="text" id="tel3" name="tel3" data-options="required:true" ></input>
			</div>
			<div class="fitem">
				<label class="fitemlabel">地址:</label> <input class="easyui-validatebox" type="text" id="address3" name="address3" data-options="required:false" validType="length[0,64]"></input>
			</div>
			<div class="fitem">
				<label class="fitemlabel">所属经销商:</label>  <input id="lowerDealer3" name="lowerDealer3" style="width:160px;" > 
			</div>
		</form>
	</div>
	<div id="editUserDiv-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="editUser()">保存</a> <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="javascript:$('#editUserDiv').dialog('close')">取消</a>
	</div>
	
</body>
</html>