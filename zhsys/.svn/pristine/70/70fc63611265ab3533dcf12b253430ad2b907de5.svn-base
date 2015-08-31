<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>每日佣金查询</title>
<%@include file="include.jsp"%>
<script type="text/javascript" src='<c:url value="/js/pages/queryCommision.js"/>'></script>
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

   <div id="commisionOrderbar" style="padding:5px;height:auto" align="left">
       <form id="commissionOrderfm" action="" method="post">
 		席位号<input class="easyui-validatebox" type="text" name="clientId" id="clientId" style="width:100px">&nbsp;
 		权益代码<input class="easyui-validatebox" type="text" name="otcCode" id="otcCode" style="width:100px">&nbsp;
 		经销商编号<input class="easyui-validatebox" type="text" name="developer" id="developer" style="width:100px">&nbsp;
 		<!-- 所属经销商代码<input class="easyui-validatebox" type="text" name="developer" id="developer" style="width:100px">&nbsp; -->
 		开始日期<input class="easyui-datebox"  name="initDateStart" id="initDateStart" style="width:100px">&nbsp;
 		结束日期<input class="easyui-datebox"  name="initDateEnd" id="initDateEnd" style="width:100px">&nbsp;
 		<br>
		 		<a class="easyui-linkbutton" onclick="selectCommisionApplication()">查询</a>&nbsp;&nbsp;
		 		<!-- <a href="javascript:void(0)" class="easyui-linkbutton" id="outCommisionDetail">导出佣金明细表</a>&nbsp;&nbsp; -->
		 		<a class="easyui-linkbutton"  onclick="outCommisionDetail()">导出佣金明细表</a>&nbsp;&nbsp;
 	    </form>
 	</div>
 	<table id="commisionList" border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
		<thead>
			<tr align="center">
			</tr>
		</thead>
	</table>
</body>
</html>