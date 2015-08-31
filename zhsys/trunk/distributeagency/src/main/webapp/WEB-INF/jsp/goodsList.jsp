<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>产品信息</title>
<%@include file="include.jsp"%>
<script type="text/javascript" src='<c:url value="/js/pages/goods.js"/>'></script>
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
	<input type="hidden" id="addgoodsUrl" value='<c:url value="/addgoods.html"/>' />
	<input type="hidden" id="checkTgoodsBygoodsNumAndName" value='<c:url value="/checkTgoodsBygoodsNumAndName.html"/>' />
	<input type="hidden" id="updateGoodsInformation" value='<c:url value="/updateGoods.html"/>' />
		<div id="tgoodsbar" style="padding:5px;height:auto" align="left">
       <form id="goodsfm" action="" method="post">
 		产品编号<input class="easyui-validatebox" type="text" name="goodsNum3" id="goodsNum3" style="width:100px">&nbsp;
 		产品名称<input class="easyui-validatebox" type="text" name="goodsName3" id="goodsName3" style="width:130px">&nbsp;
			<br>
		 		<a class="easyui-linkbutton" onclick="selectgoods()">查询</a>&nbsp;&nbsp;
		 		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="addgoods()">添加</a>&nbsp;&nbsp;
 		 		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="delgoods()">删除</a>&nbsp;&nbsp; 
                <a href="#" class="easyui-linkbutton" onclick="toUpdateUser()">修改</a>

 	    </form>
 	</div>
 	
 	<table id="tgoodsList" border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
		<thead>
			<tr align="center">
			</tr>
		</thead>
	</table>
 	
 	
    <!-- 添加产品-->
	<div id="addgoodsDiv" class="easyui-dialog" style="width: 400px; height: 380px; padding: 10px 20px" closed="true" buttons="#addgoodsDiv-buttons">
		<form id="addgoodsFm" method="post" novalidate>
			<div class="fitem">
				<label class="fitemlabel">产品编号:</label> <input class="easyui-validatebox" type="text" id="goodsNum2" name="goodsNum2" data-options="required:true" validType="length[0,64]"></input>
			</div>
			<div class="fitem">
				<label class="fitemlabel">产品名称:</label> <input class="easyui-validatebox" type="text" id="goodsName2" name="goodsName2" data-options="required:false" validType="length[0,64]"></input>
			</div>
			<div class="fitem">
				<label class="fitemlabel">产品单价:</label> <input class="easyui-validatebox" type="text" id="goodsPrice2" name="goodsPrice2" data-options="required:false" validType="numberV"></input>
			</div>
		</form>
	</div>
	<div id="addgoodsDiv-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="savegoods()">保存</a> <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="javascript:$('#addgoodsDiv').dialog('close')">取消</a>
	</div>
	
	<!-- 修改产品 -->
	
		<div id="updategoodsDiv" class="easyui-dialog" style="width: 400px; height: 380px; padding: 10px 20px" closed="true" buttons="#updategoodsDiv-buttons">
		<form id="updategoodsFm" method="post" novalidate>
		   <input type="hidden" name="id" id="id">
			<div class="fitem">
				<label class="fitemlabel">产品编号:</label> <input class="easyui-validatebox" type="text" id="goodsNum" name="goodsNum" data-options="required:false" validType="length[0,64]"></input>
			</div>
			<div class="fitem">
				<label class="fitemlabel">产品名称:</label> <input class="easyui-validatebox" type="text" id="goodsName" name="goodsName" data-options="required:false" validType="length[0,64]"></input>
			</div>
			<div class="fitem">
				<label class="fitemlabel">产品单价:</label> <input class="easyui-validatebox" type="text" id="goodsPrice" name="goodsPrice" data-options="required:false" validType="numberV"></input>
			</div>
		</form>
	</div>
	<div id="updategoodsDiv-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="updategoods()">保存</a> <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="javascript:$('#updategoodsDiv').dialog('close')">取消</a>
	</div>
</body>
</html>