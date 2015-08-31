<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>子系统信息</title>
<%@include file="include.jsp"%>
<script type="text/javascript" src='<c:url value="/js/pages/sonsys.js"/>'></script>
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
		<div id="tgoodsbar" style="padding:5px;height:auto" align="left">
       <form id="goodsfm" action="" method="post">
 		系统编号<input class="easyui-validatebox" type="text" name="sysId1" id="sysId1" style="width:100px">&nbsp;
 		系统名称<input class="easyui-validatebox" type="text" name="sysName1" id="sysName1" style="width:130px">&nbsp;
			<br>
		 		<a class="easyui-linkbutton" onclick="selectSonSys()">查询</a>&nbsp;&nbsp;
		 		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="addSonSys()">添加</a>&nbsp;&nbsp;
                <a href="#" class="easyui-linkbutton" onclick="toUpdateSys()">修改</a>
 	    </form>
 	</div>
 	
 	<table id="sonSysList" border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
		<thead>
			<tr align="center">
			</tr>
		</thead>
	</table>
 	
 	
    <!-- 添加产品-->
	<div id="addSonSysDiv" class="easyui-dialog" style="width: 550px; height: 380px; padding: 10px 20px" closed="true" buttons="#addSonSysDiv-buttons">
		<form id="addSonSysFm" method="post" novalidate>
			
			<div class="fitem">
				<label class="fitemlabel">系统名称:</label> <input class="easyui-validatebox" type="text" id="sysName2" name="sysName2" data-options="required:true" validType="length[0,64]" onblur="validsysName(this)"></input><span id="sysName2Span" style="color:red" ></span></td>
			</div>
			
			<label class="fitemlabel" style="width:120px;">定时任务执行时间:</label><input class="easyui-validatebox" id="ss"  style="width:120px;" data-options="required:true" onfocus="timeS()"><span id="ssSpan" style="color:gray">(请输入24小时制的时间，例如23:00)</span></td>
			<!--  <div class="fitem">
				<label class="fitemlabel">审核时间:</label> <input class="easyui-datebox" data-options="required:true" name="approvalTime2" id="approvalTime2" style="width:155px" editable="false"/></td>
			</div>-->
		</form>
	</div>
	<div id="addSonSysDiv-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveSonSys()" id="saveHerf" >保存</a> <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="javascript:$('#addSonSysDiv').dialog('close')">取消</a>
	</div>
	
	<!-- 修改产品 -->
	
		<div id="updateSonSysDiv" class="easyui-dialog" style="width: 550px; height: 380px; padding: 10px 20px" closed="true" buttons="#updateSonSysDiv-buttons">
		<form id="updateSonSysFm" method="post" novalidate>
		   <input type="hidden" name="sysId3" id="sysId3">
		   <input type="hidden" name="sysName4" id="sysName4">
			<div class="fitem">
				<label class="fitemlabel">系统名称:</label> <input class="easyui-validatebox" type="text" id="sysName3" name="sysName3" data-options="required:true" validType="length[0,64]"></input><span id="sysName3Span" style="color:red" ></span>
			</div>
		</form>
	</div>
	<div id="updateSonSysDiv-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="updateSonSys()">更新</a> <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="javascript:$('#updateSonSysDiv').dialog('close')">取消</a>
	</div>
</body>
</html>