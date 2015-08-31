<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.util.*" %>
<%@ page import="com.buybal.epay.util.*" %>
<%@ page import="com.cbice.distribute.buyer.web.util.PropertiseUtil" %>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>提交商户订单数据至支付平台</title>
</head>
<script type="text/javascript">

   function sub(){
	document.form1.submit();
	}
	setTimeout(sub,100);
</script>
<body>
<%
SharingPayMsg sharingPayMsg = (SharingPayMsg)request.getAttribute("sharingPayMsg");
String version=(String)request.getAttribute("version");

String urlSource = PropertiseUtil.getDataFromPropertiseFile(
		"site", "site.pay");
%>
<form name="form1" id="form1"
<%if("v2.0".equals(version)){ %>
	action="<%=urlSource %>" 
<%}else{ %> 
	action="http://221.122.98.36/ppay/orderTwoAction.do" 
<%} %>
	method="post">
<table>
<!-- <tr> -->
<!-- 	<td>pageUrl:</td> -->
<%-- 	<td><%=sharingPayMsg.getPageurl() %></td> --%>
<!-- </tr> -->
<!-- <tr> -->
<!-- 	<td>bgUrl:</td> -->
<%-- 	<td><%=sharingPayMsg.getBgurl() %></td> --%>
<!-- </tr> -->
<!-- <tr> -->
<!-- 	<td>pid:</td> -->
<%-- 	<td><%=sharingPayMsg.getPid() %></td> --%>
<!-- </tr> -->
<!-- 	                                                 <br> -->
<!-- <tr> -->
<!-- 	<td>orderTime:</td> -->
<%-- 	<td><%=sharingPayMsg.getOrdertime() %></td> --%>
<!-- </tr> -->
<!-- <tr> -->
<!-- 	<td>transactionid:</td> -->
<%-- 	<td><%=sharingPayMsg.getTransactionid() %></td> --%>
<!-- </tr> -->
<!-- <tr> -->
<!-- 	<td>subtransactionid:</td> -->
<%-- 	<td><%=sharingPayMsg.getSubtransactionid() %></td> --%>
<!-- </tr> -->
<!-- <tr> -->
<!-- 	<td>orderAmount:</td> -->
<%-- 	<td><%=sharingPayMsg.getOrderamount() %></td> --%>
<!-- </tr> -->
<!-- <tr> -->
<!-- 	<td>bankId:</td> -->
<%-- 	<td><%=sharingPayMsg.getBankid() %></td> --%>
<!-- </tr> -->
<!-- <tr> -->
<!-- 	<td>taccountid:</td> -->
<%-- 	<td><%=sharingPayMsg.getTaccountid() %></td> --%>
<!-- </tr> -->
<!-- <tr> -->
<!-- 	<td>productName:</td> -->
<%-- 	<td><%=sharingPayMsg.getProductname() %></td> --%>
<!-- </tr> -->
<!-- <tr> -->
<!-- 	<td>productNum:</td> -->
<%-- 	<td><%=sharingPayMsg.getProductnum() %></td> --%>
<!-- </tr> -->
<!-- <tr> -->
<!-- 	<td>productDesc:</td> -->
<%-- 	<td><%=sharingPayMsg.getProductdesc() %></td> --%>
<!-- </tr> -->
<!-- <tr> -->
<!-- 	<td>ext1:</td> -->
<%-- 	<td><%=sharingPayMsg.getExt1() %></td> --%>
<!-- </tr> -->
<!-- <tr> -->
<!-- 	<td>ext2:</td> -->
<%-- 	<td><%=sharingPayMsg.getExt2() %></td> --%>
<!-- </tr> -->
<!-- <tr> -->
<!-- 	<td>signmsg:</td> -->
<%-- 	<td><%=sharingPayMsg.getSignmsg() %></td> --%>
<!-- </tr> -->

<!-- </table> -->

<!-- <div style="display:none;"> -->
<!-- <table> -->
<!-- <tr> -->
<!-- 	<td>payeeAmount:</td> -->
<%-- 	<td><%=sharingPayMsg.getPayeeamount() %></td> --%>
<!-- </tr> -->
<!-- <tr> -->
<!-- 	<td>payType:</td> -->
<%-- 	<td><%=sharingPayMsg.getPaytype() %></td> --%>
<!-- </tr> -->
<!-- <tr> -->
<!-- 	<td>sharingFeeCalcType:</td> -->
<%-- 	<td><%=sharingPayMsg.getSharingfeecalctype() %></td> --%>
<!-- </tr> -->
<!-- <tr> -->
<!-- 	<td>sharingFeeType:</td> -->
<%-- 	<td><%=sharingPayMsg.getSharingfeetype() %></td> --%>
<!-- </tr> -->
<!-- <tr> -->
<!-- 	<td>hasPayeeFee:</td> -->
<%-- 	<td><%=sharingPayMsg.getHaspayeefee() %></td> --%>
<!-- </tr> -->
<!-- <tr> -->
<!-- 	<td>sharingData:</td> -->
<%-- 	<td><%=sharingPayMsg.getSharingdata() %></td> --%>
<!-- </tr> -->
<!-- <tr> -->
<!-- 	<td>sharingPayFlag:</td> -->
<%-- 	<td><%=sharingPayMsg.getSharingpayflag() %></td> --%>
<!-- </tr> -->
</table>
</div>

	<input type="hidden" name="pageurl" value="<%=sharingPayMsg.getPageurl() %>">
	<input type="hidden" name="bgurl" value="<%=sharingPayMsg.getBgurl() %>">
	<input type="hidden" name="transactionid" value="<%=sharingPayMsg.getTransactionid() %>">
	<input type="hidden" name="subtransactionid" value="<%=sharingPayMsg.getSubtransactionid() %>">
	<input type="hidden" name="taccountid" value="<%=sharingPayMsg.getTaccountid() %>">
	<input type="hidden" name="orderamount" value="<%=sharingPayMsg.getOrderamount() %>">
	<input type="hidden" name="payeeamount" value="<%=sharingPayMsg.getPayeeamount() %>">
	<input type="hidden" name="ordertime" value="<%=sharingPayMsg.getOrdertime() %>">
	<input type="hidden" name="productname" value="<%=sharingPayMsg.getProductname() %>">
	<input type="hidden" name="productnum" value="<%=sharingPayMsg.getProductnum() %>">
	<input type="hidden" name="productdesc" value="<%=sharingPayMsg.getProductdesc() %>">
	<input type="hidden" name="ext1" value="<%=sharingPayMsg.getExt1() %>">
	<input type="hidden" name="ext2" value="<%=sharingPayMsg.getExt2() %>">
	<input type="hidden" name="paytype" value="<%=sharingPayMsg.getPaytype() %>">
	<input type="hidden" name="bankid" value="<%=sharingPayMsg.getBankid() %>">
	<input type="hidden" name="pid" value="<%=sharingPayMsg.getPid() %>">
	<input type="hidden" name="sharingfeecalctype" value="<%=sharingPayMsg.getSharingfeecalctype() %>">
	<input type="hidden" name="sharingfeetype" value="<%=sharingPayMsg.getSharingfeetype() %>">
	<input type="hidden" name="haspayeefee" value="<%=sharingPayMsg.getHaspayeefee() %>">
	<input type="hidden" name="sharingdata" value="<%=sharingPayMsg.getSharingdata() %>">
	<input type="hidden" name="sharingpayflag" value="<%=sharingPayMsg.getSharingpayflag() %>">
	<input type="hidden" name="signmsg" value="<%=sharingPayMsg.getSignmsg() %>">
<!-- 	<input type="button"  name="提交" value="提交支付" onclick="document.form1.submit()"> -->
	</form>
<!-- 	<script type="text/javascript">document.form1.submit();</script> -->
</body>
</html>