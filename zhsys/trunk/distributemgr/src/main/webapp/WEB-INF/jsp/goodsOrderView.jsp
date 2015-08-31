<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="include.jsp"%>
<style type="text/css">
	.tdh{
		height : 45px;
	}
	.td1{
		width : 200px;
	}
	.td2{
		width : 350px;
	}
	.td2_2{
		width : 250px;
	}
	
	.div_tabs{
		padding: 10px 30px;
		width: 100%;
	}
	.tables{
		border : 0px;
		bordercolor : #9899D3;
		cellspacing : 0px;
		border-collapse :　collapse;
	}
	fieldset{
		padding: 0 0 0 10px
	}
	legend : {
		font-weight: bolder;
		color:lightblue;
	}
	input : {
		height : 20px;
	}
	
</style>
</head>
<body>

	
	   <div class="easyui-tabs" data-options="border: false">
	     
	    <div title="订单基本信息" class="div_tabs" >
				<legend style="font-weight: bolder; color: lightblue"></legend>
				<table class="tables">
					<tr>
						<td class="td1">产品名称</td>
						<td class="td2"><input name="realName" id="realName"  value ="${goodsName}" style="width: 200px;height:30px;"
							readonly="readonly" /></td>
						<td class="td1">产品编号</td>
						<td class="td2"><input name="sysId" id="sysId"  value="${goodsNum}" style="width: 200px;height:30px;"
							readonly="readonly" /></td>
					</tr>
				<tr>
					<td class="td1">交易数量</td>
						<td class="td2"><input name="subjectUseToString" value="${distributeNum}" style="width: 200px;height:30px;"
							readonly="readonly" /></td>
						<td class="td1">交易金额</td>
						<td class="td2"><input name="subjectMoneyToString"  value="${businessMoney}"  style="width: 200px;height:30px;"
							readonly="readonly" /></td>
					</tr>
					<tr>
						<td class="td1">所属经销商</td>
						<td class="td2"><input name="repaymentToString"  value="${Agency}"  style="width: 200px;height:30px;"
							readonly="readonly" /></td>
						<td class="td1">${flag}</td>
						<td class="td2"><input name="raiseStandardDateToString"   value="${userName}" style="width: 200px;height:30px;"
							readonly="readonly" /></td>
					</tr>
				</table>
      
	</div>


	</div>

</body>
</html>