<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>经销商管理</title>
<%@include file="include.jsp"%>
<script type="text/javascript" src='<c:url value="/js/pages/dealer.js"/>'></script>
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
	<input type="hidden" id="addDealerUrl" value='<c:url value="/addDealer.html"/>' />
	<input type="hidden" id="checkTAgencyBydealerNumAndName" value='<c:url value="/checkTAgencyBydealerNumAndName.html"/>' />
	<input type="hidden" id="editDealerUrl" value='<c:url value="/editDealer.html"/>' />
		<div id="tuserbar" style="padding:5px;height:auto" align="left">
       <form id="dealerfm" action="" method="post">
 		经销商编号<input class="easyui-validatebox" type="text" name="dealerNum" id="dealerNum" style="width:100px">&nbsp;
 		经销商名称<input class="easyui-validatebox" type="text" name="dealerName" id="dealerName" style="width:130px">&nbsp;
			<br>
		 		<a class="easyui-linkbutton" onclick="selectdealer()">查询</a>&nbsp;&nbsp;
		 		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="addDealer()">添加</a>&nbsp;&nbsp;
		 		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="toUpdateUser()">编辑</a>&nbsp;&nbsp;
		 		<a class="easyui-linkbutton" class="easyui-linkbutton" onclick="upAllUsers()">导入所有经销商会员</a>&nbsp;&nbsp;
 	    </form>
 	</div>
 	
 	<table id="tuserList" border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
		<thead>
			<tr align="center">
			</tr>
		</thead>
	</table>
 	
 	
    <!-- 添加经销商 -->
	<div id="addDealerDiv" class="easyui-dialog" style="width: 400px; height: 380px; padding: 10px 20px" closed="true" buttons="#addDealerDiv-buttons">
		<form id="addDealerFm" method="post" novalidate>
			<div class="fitem">
				<label class="fitemlabel">经销商编号:</label> <input class="easyui-validatebox" type="text" id="dealerNum2" name="dealerNum2" data-options="required:true" validType='dealerNum'></input>
			</div>
			<div class="fitem">
				<label class="fitemlabel">经销商名称:</label> <input class="easyui-validatebox" type="text" id="dealerName2" name="dealerName2" data-options="required:false" validType="length[0,64]"></input>
			</div>
		</form>
	</div>
	<div id="addDealerDiv-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveDealer()">保存</a> <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="javascript:$('#addDealerDiv').dialog('close')">取消</a>
	</div>
	
	    <!-- 编辑 -->
	<div id="editDealerDiv" class="easyui-dialog" style="width: 400px; height: 380px; padding: 10px 20px" closed="true" buttons="#editDealerDiv-buttons">
		<form id="editDealerFm" method="post" novalidate>
			<input type="hidden" name="id" id="id">
			<div class="fitem">
				<label class="fitemlabel">经销商名称:</label> <input class="easyui-validatebox" type="text" id="dealerName3" name="dealerName3" data-options="required:false" validType="length[0,64]"></input>
			</div>
			<div class="fitem">
				<label class="fitemlabel">经销商编号:</label> <input class="easyui-validatebox" type="text" id="dealerNum3" name="dealerNum3" data-options="required:true" validType="dealerNum"></input>
			</div>
			<div class="fitem">
				<label class="fitemlabel">经销商账号:</label> <input class="easyui-validatebox" type="text" id="userName3" name="userName3" readonly="readonly" data-options="required:false" validType="length[0,64]"></input>(不可修改)
			</div>
		</form>
	</div>
	<div id="editDealerDiv-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="editDealer()">保存</a> <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="javascript:$('#editDealerDiv').dialog('close')">取消</a>
	</div>
	
</body>
</html>