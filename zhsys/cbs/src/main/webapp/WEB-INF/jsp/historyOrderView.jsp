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
	     
	    <div title="历史交易记录详细" class="div_tabs" >
				<legend style="font-weight: bolder; color: lightblue"></legend>
				<table class="tables">
					<tr>
						<td class="td1">批次号</td>
						<td class="td2"><input name="batchId" id="batchId"  value ="${batchId}" style="width: 200px;height:30px;"
							readonly="readonly" /></td>
						<td class="td1">产品编号</td>
						<td class="td2"><input name="goodsNum" id="goodsNum"  value="${goodsNum}" style="width: 200px;height:30px;"
							readonly="readonly" /></td>
					</tr>
				<tr>
					<td class="td1">产品名称</td>
						<td class="td2"><input name="goodsName" value="${goodsName}" style="width: 200px;height:30px;"
							readonly="readonly" /></td>
						<td class="td1">产品数量</td>
						<td class="td2"><input name="distributeNum"  value="${distributeNum}"  style="width: 200px;height:30px;"
							readonly="readonly" /></td>
					</tr>
					<tr>
						<td class="td1">产品总价值</td>
						<td class="td2"><input name="businessMoney"  value="${businessMoney}"  style="width: 200px;height:30px;"
							readonly="readonly" /></td>
						<td class="td1">所属经销商</td>
						<td class="td2"><input name="agencyName"  value="${agencyName}"  style="width: 200px;height:30px;"
							readonly="readonly" /></td>
					</tr>
					<tr>
						<td class="td1">订单明细:</td>
					</tr>
				</table>
	<table id="thistoryGoodsOrderList" border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
		<thead>
			<tr align="center">
			</tr>
		</thead>
	</table>
      
	</div>


	</div>

</body>
</html>