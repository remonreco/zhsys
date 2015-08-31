<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="include.jsp"%>
<script type="text/javascript"
	src='<c:url value="/js/pages/fullSubjectListFirst.js"/>'></script>
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
	     
	    <div title="满标初审基本信息" class="div_tabs" >
	      <form id="subjectAudit_Form" method="post">
	        <input type="hidden" name="id" />
	      <fieldset>
				<legend style="font-weight: bolder; color: lightblue">标的基本信息</legend>
				<table class="tables">
					<tr>
						<td class="td1">真实姓名</td>
						<td class="td2"><input name="realName" id="realName" style="width: 200px;height:30px;"
							readonly="readonly" /></td>
						<td class="td1">签约ID</td>
						<td class="td2"><input name="sysId" id="sysId" style="width: 200px;height:30px;"
							readonly="readonly" /></td>
						<td class="td1">身份证号</td>
						<td class="td2"><input name="ids" id="ids" style="width: 200px;height:30px;"
							readonly="readonly" /></td>
						<td class="td1">借款标题</td>
						<td class="td2"><input name="subjectName" id="subjectName" style="width: 200px;height:30px;"
							readonly="readonly" /></td>
					</tr>
				<tr>
					<td class="td1">借款用途</td>
						<td class="td2"><input name="subjectUseToString" style="width: 200px;height:30px;"
							readonly="readonly" /></td>
						<td class="td1">借款金额</td>
						<td class="td2"><input name="subjectMoneyToString"  style="width: 200px;height:30px;"
							readonly="readonly" /></td>
						<td class="td1">月利率</td>
						<td class="td2"><input name="monthRate" id="monthRate" style="width: 200px;height:30px;"
							readonly="readonly" /></td>
						<td class="td1">年利率</td>
						<td class="td2"><input name="yearRate" id="yearRate" style="width: 200px;height:30px;"
							readonly="readonly" /></td>
					</tr>
					<tr>
						<td class="td1">还款方式</td>
						<td class="td2"><input name="repaymentToString"  style="width: 200px;height:30px;"
							readonly="readonly" /></td>
						<td class="td1">筹标期限</td>
						<td class="td2"><input name="raiseStandardDateToString"  style="width: 200px;height:30px;"
							readonly="readonly" /></td>
						<td class="td1">月还本息</td>
						<td class="td2"><input name="monthPayMoney"  style="width: 200px;height:30px;"
							readonly="readonly" value=""/></td>
						<td class="td1">借款管理费</td>
						<td class="td2"><input name="loanManagementFeeToString" style="width: 200px;height:30px;"
							readonly="readonly" /></td>
					</tr>
					<tr>
						<td class="td1">借款服务费</td>
						<td class="td2"><input name="loanServiceFeeToString"  style="width: 200px;height:30px;"
							readonly="readonly" /></td>
						<td class="td1">状态</td>
						<td class="td2" colspan="5"><input name="subjectStatusToString"  style="width: 200px;height:30px;"
							readonly="readonly" /></td>
					</tr>
					<tr>
					<td class="td1">借款描述</td>
				    <td colspan="7"><textarea name="loanDes" rows="3"
								style="width: 445px" readonly="readonly"></textarea>
					</td>		
					</tr>
				</table>
	     </fieldset>
	      
		  <fieldset>
				<legend style="font-weight: bolder; color: lightblue">信用档案</legend>
			    <table class="tables">
			       <tr>
			          <td class="td1">信用等级</td>
			          <td class="td2"><input name="p2pLevel" style="width: 200px;height:30px;"
							readonly="readonly" /></td>
					  <td class="td1">信用额度</td>
			          <td class="td2"><input name="p2pCredit" style="width: 200px;height:30px;"
							readonly="readonly" /></td>
					  <td class="td1">待还本息</td>
			          <td class="td2"><input name="totalReplaymentSumMoneyToString" style="width: 200px;height:30px;"
							readonly="readonly" /></td>
			       </tr>
			       <tr>
			          <td class="td1">申请借款笔数</td>
			          <td class="td2"><input name="applyMoneyCount" style="width: 200px;height:30px;"
							readonly="readonly" /></td>
					  <td class="td1">成功借款笔数</td>
			          <td class="td2"><input name="successApplyMoneyCount" style="width: 200px;height:30px;"
							readonly="readonly" /></td>
					  <td class="td1">已结清借款笔数</td>
			          <td class="td2"><input name="repayedMoneyCount" style="width: 200px;height:30px;"
							readonly="readonly" /></td>
			       </tr>
			       <tr>
			          <td class="td1">累计逾期次数</td>
			          <td class="td2"><input name="yuQiCount" style="width: 200px;height:30px;"
							readonly="readonly" /></td>
					  <td class="td1">累计逾期金额</td>
			          <td class="td2"><input name="yuQiMoneyToString" style="width: 200px;height:30px;"
							readonly="readonly" /></td>
					  <td class="td1">当前逾期金额</td>
			          <td class="td2"><input name="currentYuQiMoneyToString" style="width: 200px;height:30px;"
							readonly="readonly" /></td>
			       </tr>
			    </table>
			</fieldset>
		  <fieldset>
				<legend style="font-weight: bolder; color: lightblue">认证审核情况</legend>
			    <table class="tables" id="auth" border="1" border="1" cellpadding="0" cellspacing="0" 
			           style="border-color: #ccc;width: 99%;text-align: center;">
			        <tr>
			          <td class="td1" style="width: 200px;height:30px;">认证项目</td>
					  <td class="td1" style="width: 200px;height:30px;">状态</td>
					  <td class="td1" style="width: 200px;height:30px;">通过时间</td>
					  <td class="td1" style="width: 200px;height:30px;">认证有效期</td>
			       </tr>
			    </table>
			</fieldset>
		  <fieldset>
		    <legend style="font-weight: bolder; color: lightblue">满标初审</legend>
			    <table class="tables">
			        <tr>
			          <td class="td1" rowspan="3" >审核意见</td>
					  <td class="td1" colspan="3"  rowspan="3"  ><textarea 
					   name="fullSubjectTrialMark"  id="fullSubjectTrialMark" rows="3"
								style="width: 445px"></textarea></td>
			       </tr>
			    </table>
		</fieldset>
     </form>
	</div>

		<div title="投标记录 " class="div_tabs">
			<div style="width: 100%; height: 100%; padding: 0px; margin: 0px;">

				<div id="tb">
					<form align="right">
						总投标人数: <input type="text" id="totalBidUserCount" /> 
						总投标金额: <input type="text" id="totalBidSumMoney" />
					</form>
				</div>
				<table id="subjectAuditList" border="0" cellpadding="0"
					cellspacing="0" width="100%" height="100%">
					<thead>
						<tr align="center">
						</tr>
					</thead>
				</table>

			</div>
		</div>

	</div>

</body>
</html>