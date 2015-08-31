<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>商品信息</title>
<%@include file="include.jsp"%>
<script type="text/javascript" src='<c:url value="/js/pages/commodity.js"/>'></script>
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
	<input type="hidden" id="addCommodityUrl" value='<c:url value="/addCommodity.html"/>' />
<%-- 	<input type="hidden" id="checkTCommodityByCommodityNumAndName" value='<c:url value="/checkTCommodityByCommodityNumAndName.html"/>' /> --%>
	<input type="hidden" id="updateCommodityInformation" value='<c:url value="/updateCommodityRule.html"/>' />
	<%-- <input type="hidden" id="checkTgoodsNumEqualsTgoodsCount" value='<c:url value="/checkTgoodsNumEqualsTgoodsCount.html"/>' /> --%>
		<div id="tCommoditybar" style="padding:5px;height:auto" align="left">
       <form id="commodityfm" action="" method="post">
 		品种代码<input class="easyui-validatebox" type="text" name="comId" id="comId" style="width:100px">&nbsp;
 		品种名称<input class="easyui-validatebox" type="text" name="comName" id="comName" style="width:130px">&nbsp;
			<br>
		 		<a class="easyui-linkbutton" onclick="selectCommodity()">查询</a>&nbsp;&nbsp;
		 		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="addCommodity()">添加</a>&nbsp;&nbsp;
 		 		<!-- <a href="javascript:void(0)" class="easyui-linkbutton" onclick="delCommodity()">删除</a>&nbsp;&nbsp; --> 
                <a href="#" class="easyui-linkbutton" onclick="toUpdateCommodity()">修改</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="exchangeRuleQuery()">兑换规则查询</a>		
 	    </form>
 	</div>
 	
 	<table id="tCommodityList" border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
		<thead>
			<tr align="center">
			</tr>
		</thead>
	</table>
 	
 	
    <!-- 添加产品-->
	<div id="addCommodityDiv" class="easyui-dialog" style="width: 1150px; height: 580px;overflow:auto;  padding: 10px 20px" closed="true" buttons="#addCommodityDiv-buttons">
		<form id="addCommodityFm" method="post" enctype ="multipart/form-data" novalidate>
<!-- 			<div class="fitem">
				<label class="fitemlabel">产品编号:</label> <input class="easyui-validatebox" type="text" id="goodsNum2" name="goodsNum2" data-options="required:true" validType="length[0,64]"></input>
			</div> -->
			<table id="addCommodityFmTab">
				<tr>
					<td class="dataForm_fieldLabel" id="merName1"><h3>商品信息</h3></td>
					<td class="dataForm_fieldCell" id="merName2"><span id="mernamemess" style="color: red; font-size: 24"></span></td>
		  			<td class="dataForm_fieldLabel"></td>
					<td class="dataForm_fieldCell"></td>
				</tr>
				<tr>
					<td colspan="2"><span >品种名称:</span> <input class="easyui-validatebox" type="text" id="comNamei" name="comNamei" data-options="required:true" validType="length[0,60]" onblur="validcomName(this)"></input><span id="comNameiSpan" style="color:red"></span></td>
					<td colspan="2"><span >品种代码:</span> <input class="easyui-validatebox" type="text" id="comIdi" name="comIdi" data-options="required:true" validType="numberM" onblur="validcomId(this)"></input> <span id="comIdiSpan" style="color:red"></span></td>
				</tr>
				<tr>
					<td colspan="2"><span >项目名称:</span> <input class="easyui-validatebox" type="text" id="itemName" name="itemName" data-options="required:true" validType="length[0,60]"></input></td>
					<td colspan="2"><span >挂牌机构:</span> <input class="easyui-validatebox" type="text" id="listOrganization" name="listOrganization" data-options="required:true" validType="length[0,60]"></input></td>
				</tr>
				<tr>
					<td colspan="2"><span >发售单位:</span> <input class="easyui-validatebox" type="text" id="saleUnit" name="saleUnit" data-options="required:true" validType="length[0,4]"><span >(例如:个/只/条)</span></input></td>
					<td colspan="2"><span >发售单价:</span> <input class="easyui-validatebox" type="text" id="salePrice" name="salePrice" data-options="required:true" validType="numberN"></input></td>
				</tr>
				
				<tr>
					<td colspan="2"><span >发售数量:</span> <input class="easyui-validatebox" type="text" id="saleNum" name="saleNum" data-options="required:true" validType="numberM"></input></td>
					<td colspan="2"><span >发售金额:</span> <input class="easyui-validatebox" type="text" id="saleMoney" name="saleMoney" data-options="required:true" validType="numberN"></input></td>
				</tr>
				
				<tr>
					<td colspan="2"><span >流通数量:</span> <input class="easyui-validatebox" type="text" id="currencyNum" name="currencyNum" data-options="required:true" validType="numberM"></input></td>
					<td colspan="2"><span >最小兑换数:</span> <input class="easyui-validatebox" type="text" id="minNum" name="minNum" data-options="required:true" validType="numberM"></input></td>
				</tr>
				<tr>
					<td ><span >兑换开始日:</span> <input class="easyui-datebox" data-options="required:true" name="startDate" id="startDate" style="width:100px" editable="false"/></td>
					<td ><span >兑换结束日:</span><input class="easyui-datebox" data-options="required:true"  name="endDate" id="endDate" style="width:100px" editable="false"/></td>
				</tr>
				<tr>
					<td><span >商品图片:</span> <input class="easyui-validatebox" type="file" id="imgFile" name="imgFile" data-options="required:true" ></input></td>
					<td><input type="button" id = "uploadFile" onClick ="uploadPic();" value="上传"/></td>
					<td colspan="2" id="imgtd"><div id="div88"></div><td><input type="hidden" id="imgnum" value="0" /><td>
				</tr>
				<tr>
					<td colspan="4"><span >品种简介:</span><textarea class="easyui-validatebox" rows="4" cols="50" id="introduction" name="introduction" data-options="required:true" validType="length[0,200]"></textarea></td>
				</tr>
				<tr>
					<td class="dataForm_fieldLabel" id="merName1" ><h3>兑换规则</h3></td>
					<td class="dataForm_fieldCell" id="merName2"><span id="exchangeRule" style="color: red; font-size: 24"></span></td>
		  			<td class="dataForm_fieldLabel"></td>
					<td class="dataForm_fieldCell" style="text-align:right"></td>
				</tr>
				<tr id="comRole0">
					<td class="dataForm_fieldLabel" id="merName0">
						<span>项目名称：</span><input class="easyui-combobox  itemNamecbb"   id="itemNamecbb0" name="itemNamecbb0"  style="width:200px" />  
					</td>
					<td>
						<span>品种名称：</span><input class="easyui-combobox comNamecbb" id="comNamecbb0" name="comNamecbb0"  style="width:200px" />  
					</td>
					<td class="dataForm_fieldLabel" >
						<span>数量：</span><input class="easyui-validatebox" id="amount0" type="text" style="width:50px" validType="numberM" />
						<input type="checkbox" value=""  style="margin-left:20px" class="isGift" id="isGift0"/>是否为赠品 
					</td>
				</tr>
				<tr id="comRole1">
					<td class="dataForm_fieldLabel" id="merName1">
						<span>项目名称：</span><input class="easyui-combobox  itemNamecbb"   id="itemNamecbb1" name="itemNamecbb1"  style="width:200px" />  
					</td>
					<td>
						<span>品种名称：</span><input class="easyui-combobox comNamecbb" id="comNamecbb1" name="comNamecbb1"  style="width:200px" />  
					</td>
					<td class="dataForm_fieldLabel" >
						<span>数量：</span><input class="easyui-validatebox" id="amount1" type="text" style="width:50px" validType="numberM"/>
						<input type="checkbox" value=""  style="margin-left:20px" class="isGift" id="isGift1"/>是否为赠品 
					</td>
				</tr>
				
				<tr id="comRole2">
					<td class="dataForm_fieldLabel" id="merName2">
						<span>项目名称：</span><input class="easyui-combobox  itemNamecbb"   id="itemNamecbb2" name="itemNamecbb2"  style="width:200px" />  
					</td>
					<td>
						<span>品种名称：</span><input class="easyui-combobox comNamecbb" id="comNamecbb2" name="comNamecbb2"  style="width:200px" />  
					</td>
					<td class="dataForm_fieldLabel" >
						<span>数量：</span><input class="easyui-validatebox" id="amount2" type="text" style="width:50px" validType="numberM"/>
						<input type="checkbox" value=""  style="margin-left:20px" class="isGift" id="isGift2"/>是否为赠品 
					</td>
				</tr>
				
				
				<tr id="comRole3">
					<td class="dataForm_fieldLabel" id="merName3">
						<span>项目名称：</span><input class="easyui-combobox  itemNamecbb"   id="itemNamecbb3" name="itemNamecbb3"  style="width:200px" />  
					</td>
					<td>
						<span>品种名称：</span><input class="easyui-combobox comNamecbb" id="comNamecbb3" name="comNamecbb3"  style="width:200px" />  
					</td>
					<td class="dataForm_fieldLabel" >
						<span>数量：</span><input class="easyui-validatebox" id="amount3" type="text" style="width:50px" validType="numberM"/>
						<input type="checkbox" value=""  style="margin-left:20px" class="isGift" id="isGift3"/>是否为赠品 
					</td>
				</tr>
				
				
				<tr id="comRole4">
					<td class="dataForm_fieldLabel" id="merName4">
						<span>项目名称：</span><input class="easyui-combobox  itemNamecbb"   id="itemNamecbb4" name="itemNamecbb4"  style="width:200px" />  
					</td>
					<td>
						<span>品种名称：</span><input class="easyui-combobox comNamecbb" id="comNamecbb4" name="comNamecbb4"  style="width:200px" />  
					</td>
					<td class="dataForm_fieldLabel" >
						<span>数量：</span><input class="easyui-validatebox" id="amount4" type="text" style="width:50px" validType="numberM"/>
						<input type="checkbox" value=""  style="margin-left:20px" class="isGift" id="isGift4"/>是否为赠品 
					</td>
				</tr>
				
			</table>
			
		</form>
	</div>
	<div id="addCommodityDiv-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveCommodity()">保存</a> <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
			 onclick="javascript:$('#addCommodityDiv').dialog('close')">取消</a>
	</div>
	
	
	    <!-- 修改产品-->
	<div id="updateCommodityDiv" class="easyui-dialog" style="width: 1150px; height: 580px;overflow:auto;  padding: 10px 20px" closed="true" buttons="#updateCommodityDiv-buttons">
		<form id="updateCommodityFm" method="post" novalidate>
			<input type="hidden" id="upComNameiHide" name="upComNameiHide" />
			<input type="hidden" id="upfreezeNum" name="upfreezeNum" />
			<input type="hidden" id="version" name="version" />
<!-- 			<div class="fitem">
				<label class="fitemlabel">产品编号:</label> <input class="easyui-validatebox" type="text" id="goodsNum2" name="goodsNum2" data-options="required:true" validType="length[0,64]"></input>
			</div> -->
			<table id="updateCommodityFmTab">
				<tr>
					<td class="dataForm_fieldLabel" id="merName1"><h3>商品信息</h3></td>
					<td class="dataForm_fieldCell" id="merName2"><span id="mernamemess" style="color: red; font-size: 24"></span></td>
		  			<td class="dataForm_fieldLabel"></td>
					<td class="dataForm_fieldCell"></td>
				</tr>
				<tr>
					<td colspan="2" style="width:450px"><span>品种名称:</span> <input class="easyui-validatebox" type="text" id="upComNamei" name="updateComNamei" data-options="required:true" validType="length[0,60]" onblur="validUpcomName(this)" ></input><span id="upComNameiSpan" style="color:red"></span></td>
					<td colspan="2"><span>品种代码:</span> <input class="easyui-validatebox" type="text" id="upComIdi" name="upComIdi" data-options="required:true" validType="numberM"  readOnly="readOnly"></input> </td>
				</tr>
				<tr>
					<td colspan="2"><span >项目名称:</span> <input class="easyui-validatebox" type="text" id="upItemName" name="upItemName" data-options="required:true" validType="length[0,60]"></input></td>
					<td colspan="2"><span >挂牌机构:</span> <input class="easyui-validatebox" type="text" id="upListOrganization" name="upListOrganization" data-options="required:true" validType="length[0,60]"></input></td>
				</tr>
				<tr>
					<td colspan="2"><span >发售单位:</span> <input class="easyui-validatebox" type="text" id="upSaleUnit" name="upSaleUnit" data-options="required:true" validType="length[0,4]"><span >(例如:个/只/条)</span></input></td>
					<td colspan="2"><span >发售单价:</span> <input class="easyui-validatebox" type="text" id="upSalePrice" name="upSalePrice" data-options="required:true" validType="numberN"></input></td>
				</tr>
				
				<tr>
					<td colspan="2"><span >发售数量:</span> <input class="easyui-validatebox" type="text" id="upSaleNum" name="upSaleNum" data-options="required:true" validType="numberM"></input></td>
					<td colspan="2"><span >发售金额:</span> <input class="easyui-validatebox" type="text" id="upSaleMoney" name="upSaleMoney" data-options="required:true" validType="numberN"></input></td>
				</tr>
				
				<tr>
					<td colspan="2"><span >流通数量:</span> <input class="easyui-validatebox" type="text" id="upCurrencyNum" name="upCurrencyNum" data-options="required:true" validType="numberM"></input></td>
					<td colspan="2"><span >最小兑换数:</span> <input class="easyui-validatebox" type="text" id="upMinNum" name="upMinNum" data-options="required:true" validType="numberM"></input></td>
				</tr>
				<tr>
					<td colspan="4"><span >兑换状态:</span>
					<select class="easyui-validatebox" type="text" id="upExchangeState" name="upExchangeState" data-options="required:true" >
							<option value="启动">启动</option>
							<option value="不启动">不启动</option>
							<option value="暂停">暂停</option>							
					</select>
					</td>
				</tr>
				<tr>
					<td ><span >兑换开始日:</span> <input class="easyui-datebox" data-options="required:true" name="upStartDate" id="upStartDate" style="width:100px" editable="true"/></td>
					<td ><span >兑换结束日:</span><input class="easyui-datebox" data-options="required:true"  name="upEndDate" id="upEndDate" style="width:100px" editable="true"/></td>
				</tr>
				<tr>
					<!-- <td><span >商品图片:</span> <input class="easyui-validatebox" type="file" id="comPicture" name="comPicture" data-options="required:true" ></input></td> -->
					<td colspan="4"><input type="hidden"   id="comRoleCount" name="comRoleCount" value=""/><td>
				</tr>
				<tr>
					<td colspan="4"><span >品种简介:</span><textarea class="easyui-validatebox" rows="4" cols="50" id="upIntroduction" name="upIntroduction" data-options="required:true" validType="length[0,200]"></textarea></td>
				</tr>
			</table>
			
		</form>
	</div>
	<div id="updateCommodityDiv-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="updateCommodity()">保存</a> <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
			 onclick="javascript:$('#updateCommodityDiv').dialog('close')">取消</a>
	</div>
	
	<!-- 兑换商品规则查询div-->
	<div id="exchangeRuleDiv" class="easyui-dialog" style="width: 1150px; height: 580px;overflow:auto;  padding: 10px 20px" closed="true" buttons="#exchangeRuleDiv-buttons">
		<form id="exchangeRuleFm" method="post" novalidate>
			<table id="exchangeRuleFmTab">
				<tr>
					<td class="dataForm_fieldLabel" id=""><h5>被兑换商品名：<span id = "beComName"></span>&nbsp;&nbsp;&nbsp;商品编号：<span id = "beComId"></span>&nbsp;&nbsp;&nbsp;最小兑换数量：<span id = "beMinNum"></span></h5></td>
					<td class="dataForm_fieldCell" id=""><span id="mernamemess" style="color: red; font-size: 24"></span></td>
		  			<td class="dataForm_fieldLabel"></td>
					<td class="dataForm_fieldCell"></td>
				</tr>
			</table>
		</form>
		<div id="exchangeRulebar" style="padding:5px;height:auto" align="left">
	       <form id="exchangeRulefm" action="" method="post">
				<br>
			 		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="addRule();">添加</a>&nbsp;&nbsp;
	 		 		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="toModifyRule();">修改</a>&nbsp;&nbsp; 
	 		 		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="toDeleteRule();">删除</a>&nbsp;&nbsp; 
	 	    </form>
 		</div>
 		<table id="exchangeRuleList" border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
			<thead>
				<tr align="center">
				</tr>
			</thead>
		</table>
	</div>
	<div id="exchangeRuleDiv-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
			 onclick="javascript:$('#exchangeRuleDiv').dialog('close')">取消</a>
	</div>
	
	    <!-- 添加规则-->
	<div id="addRuleDiv" class="easyui-dialog" style="width: 950px; height: 450px;overflow:auto;  padding: 15px 30px" closed="true" buttons="#addRuleDiv-buttons">
		<form id="addRuleFm" method="post" novalidate>
		<input type="hidden" name="comId" id="comId">
		<input type="hidden" name="comName" id="comName">
			<table id="addRuleFmTab">
				<tr id="RcomRole0">
					<td class="dataForm_fieldLabel" id="RmerName0">
						<span>项目名称：</span><input class="easyui-combobox  itemNamecbb"   id="RitemNamecbb0" name="RitemNamecbb0"  style="width:200px" />  
					</td>
					<td>
						<span>品种名称：</span><input class="easyui-combobox comNamecbb" id="RcomNamecbb0" name="RcomNamecbb0"  style="width:200px" />  
					</td>
					<td class="dataForm_fieldLabel" >
						<span>数量：</span><input class="easyui-validatebox" id="Ramount0" type="text" style="width:50px" validType="numberM" />
						<input type="checkbox" value=""  style="margin-left:20px" class="isGift" id="RisGift0"/>是否为赠品 
					</td>
				</tr>
				<tr id="RcomRole1">
					<td class="dataForm_fieldLabel" id="RmerName1">
						<span>项目名称：</span><input class="easyui-combobox  itemNamecbb"   id="RitemNamecbb1" name="RitemNamecbb1"  style="width:200px" />  
					</td>
					<td>
						<span>品种名称：</span><input class="easyui-combobox comNamecbb" id="RcomNamecbb1" name="RcomNamecbb1"  style="width:200px" />  
					</td>
					<td class="dataForm_fieldLabel" >
						<span>数量：</span><input class="easyui-validatebox" id="Ramount1" type="text" style="width:50px" validType="numberM"/>
						<input type="checkbox" value=""  style="margin-left:20px" class="isGift" id="RisGift1"/>是否为赠品 
					</td>
				</tr>
				
				<tr id="RcomRole2">
					<td class="dataForm_fieldLabel" id="RmerName2">
						<span>项目名称：</span><input class="easyui-combobox  itemNamecbb"   id="RitemNamecbb2" name="RitemNamecbb2"  style="width:200px" />  
					</td>
					<td>
						<span>品种名称：</span><input class="easyui-combobox comNamecbb" id="RcomNamecbb2" name="RcomNamecbb2"  style="width:200px" />  
					</td>
					<td class="dataForm_fieldLabel" >
						<span>数量：</span><input class="easyui-validatebox" id="Ramount2" type="text" style="width:50px" validType="numberM"/>
						<input type="checkbox" value=""  style="margin-left:20px" class="isGift" id="RisGift2"/>是否为赠品 
					</td>
				</tr>
				
				
				<tr id="RcomRole3">
					<td class="dataForm_fieldLabel" id="RmerName3">
						<span>项目名称：</span><input class="easyui-combobox  itemNamecbb"   id="RitemNamecbb3" name="RitemNamecbb3"  style="width:200px" />  
					</td>
					<td>
						<span>品种名称：</span><input class="easyui-combobox comNamecbb" id="RcomNamecbb3" name="RcomNamecbb3"  style="width:200px" />  
					</td>
					<td class="dataForm_fieldLabel" >
						<span>数量：</span><input class="easyui-validatebox" id="Ramount3" type="text" style="width:50px" validType="numberM"/>
						<input type="checkbox" value=""  style="margin-left:20px" class="isGift" id="RisGift3"/>是否为赠品 
					</td>
				</tr>
				
				
				<tr id="RcomRole4">
					<td class="dataForm_fieldLabel" id="RmerName4">
						<span>项目名称：</span><input class="easyui-combobox  itemNamecbb"   id="RitemNamecbb4" name="RitemNamecbb4"  style="width:200px" />  
					</td>
					<td>
						<span>品种名称：</span><input class="easyui-combobox comNamecbb" id="RcomNamecbb4" name="RcomNamecbb4"  style="width:200px" />  
					</td>
					<td class="dataForm_fieldLabel" >
						<span>数量：</span><input class="easyui-validatebox" id="Ramount4" type="text" style="width:50px" validType="numberM"/>
						<input type="checkbox" value=""  style="margin-left:20px" class="isGift" id="RisGift4"/>是否为赠品 
					</td>
				</tr>
				
			</table>
		</form>
	</div>
	<div id="addRuleDiv-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveRule()">保存</a> <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
			 onclick="javascript:$('#addRuleDiv').dialog('close')">取消</a>
	</div>
	
	<!-- 修改商品规则 -->
	<div id="updateRuleDiv" class="easyui-dialog" style="width: 400px; height: 380px; padding: 10px 20px" closed="true" buttons="#updateRuleDiv-buttons">
		<form id="updateRuleFm" method="post" novalidate>
		   <input type="hidden" name="comId" id="comId">
		   <input type="hidden" name="minNum2" id="minNum2">
		   <input type="hidden" name="comType1" id="comType1" value="1" />
		   <input type="hidden" name="fileName" id="fileName" value="1" />
			<div class="fitem">
				<label class="fitemlabel">商品编号:</label> <input class="easyui-validatebox" type="text" id="exchangeId" name="exchangeId" data-options="required:true" validType="numberM" readonly="true"></input>
			</div>
			<div class="fitem">
				<label class="fitemlabel">商品名称:</label> <input class="easyui-validatebox" type="text" id="exchangeName" name="exchangeName" data-options="required:true" validType="length[0,60]"></input>
			</div>
			<div class="fitem">
				<label class="fitemlabel">兑换规则:</label> <input class="easyui-validatebox" type="text" id="exchangeNum" name="exchangeNum" data-options="required:true" validType="numberV"></input>
			</div>
			<div class="fitem">
				<label class="fitemlabel">是否为赠品:</label><input type="checkbox" value="1" name="comType"  style="margin-left:20px" class="easyui-validatebox" id="comType"/> 
			</div>
		</form>
	</div>
	<div id="updateRuleDiv-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="updateRuleById();">更新</a> <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="javascript:$('#updateRuleDiv').dialog('close')">取消</a>
	</div>