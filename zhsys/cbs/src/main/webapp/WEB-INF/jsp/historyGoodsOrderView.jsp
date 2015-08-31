<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>订单信息</title>
<%@include file="include.jsp"%>
<script type="text/javascript" src='<c:url value="/js/pages/historyGoodsOrder.js"/>'></script>
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
<div id="viewDlg" > 
 	 <div title="批次基本信息" class="div_tabs" >
	      <form id="subjectAudit_Form" method="post">
	        <input type="hidden" name="id" />
	      <fieldset>
				<legend style="font-weight: bolder; color: lightblue">批次基本信息</legend>
				<table class="tables">
					<tr>
						<td class="td1">批次号</td>
						<td class="td2"><input name="realName" id="batchId2" style="width: 200px;height:30px;"
							readonly="readonly"  value="${batch.id}" /></td>
						<td class="td1">产品名称</td>
						<td class="td2"><input name="sysId" id="goodsName2" style="width: 200px;height:30px;"
							readonly="readonly" value="${batch.goodsName}"  /></td>
					</tr>
				</table>
	     </fieldset>
		        <fieldset>
					<legend style="font-weight: bolder; color: lightblue">订单详情</legend>
				    <table class="tables" id="auth" border="1" border="1" cellpadding="0" cellspacing="0" 
				           style="border-color: #ccc;width: 99%;text-align: center;">
				        <tr>
				          <td class="td1" style="width: 200px;height:30px;">用户类型</td>
						  <td class="td1" style="width: 200px;height:30px;">用户名称</td>
						  <td class="td1" style="width: 200px;height:30px;">总金额</td>
						  <td class="td1" style="width: 200px;height:30px;">产品名称</td>
						  <td class="td1" style="width: 200px;height:30px;">产品编码</td>
						  <td class="td1" style="width: 200px;height:30px;">分发数量</td>
						  <td class="td1" style="width: 200px;height:30px;">是否支付</td>
						  <td class="td1" style="width: 200px;height:30px;">有效时间</td>
						  <td class="td1" style="width: 200px;height:30px;">订单状态</td>
				       </tr>
				       	 <c:forEach var="record" items="${rows}" > 
	                  <tr>
	                 	<td>${record.userName} </td>   
	                 	<td> ${record.agencyName} </td>  
	                 	<td> ${record.totalAmt}</td>   
	                 	<td> ${record.goodsName}</td>  
	                 	<td> ${record.goodsNum}</td>  
	                 	<td> ${record.distributeNum}</td> 
	                 	<td> ${record.payStateDto}</td>  
	                 	<td> ${record.sendDate}</td>   
	                 	<td> ${record.orderStateDto}</td>  
	                 </tr>
                </c:forEach>
				    </table>
				</fieldset>
				
				   
		 
		 
     </form>
	</div>
 	</div>
 	
 	
 	</body>
</html>