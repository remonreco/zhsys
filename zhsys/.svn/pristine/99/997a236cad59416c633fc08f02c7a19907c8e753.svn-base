<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>经销商系统——业务管理</title>
<%@include file="include.jsp"%>
<script type="text/javascript">
    function open1(plugin, url){
        if ($('#tt').tabs('exists', plugin)){
            $('#tt').tabs('select', plugin);
            var tab = $('#tt').tabs('getSelected');
            $('#tt').tabs('update', {
            	 tab: tab,
                 options: {
                	 title:plugin,
                     content: '<iframe src="'+ url+ '" width="100%" height="100%" frameborder="0" scrolling="auto"></iframe>'
                 }
            });
            
        } else {
        	$('#tt').tabs('add',{
                title:plugin,
                content: '<iframe src="'+ url+ '" width="100%" height="100%" frameborder="0" scrolling="auto"></iframe>',
                closable:true,
                extractor:function(data){
                    return data;
                }
            });
        }
    }
    
    /**
    * 时间对象的格式化
    */
	Date.prototype.format = function(format) {
		format="yyyy-MM-dd hh:mm:ss";
		var o = {
			"M+" : this.getMonth() + 1,
			"d+" : this.getDate(),
			"h+" : this.getHours(),
			"m+" : this.getMinutes(),
			"s+" : this.getSeconds(),
			"q+" : Math.floor((this.getMonth() + 3) / 3),
			"S" : this.getMilliseconds()
		};

		if (/(y+)/.test(format)) {
			format = format.replace(RegExp.$1, (this.getFullYear() + "")
					.substr(4 - RegExp.$1.length));
		}

		for ( var k in o) {
			if (new RegExp("(" + k + ")").test(format)) {
				format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
						: ("00" + o[k]).substr(("" + o[k]).length));
			}
		}
		return format;
	}
    
    /**
    * 刷新指定列表
    **/
    function reloadGrid(plugin){
    	var tab= $('#tt').tabs('getTab', plugin);
    	var frame = tab.find('iframe')[0].contentWindow;
    	frame.$("#goodsBatchList").datagrid('reload');
    	
    }
</script>

</head>
<body class="easyui-layout" style="text-align: left">
	<div data-options="region:'north'" style="background: #666; text-align: center; height: 75px;">
		<div id="header-inner">
			<table cellpadding="0" cellspacing="0" style="width: 100%;">
				<tr>
					<td rowspan="2" style="width: 20px;"></td>
					<td style="height: 52px;">
						<div style="color: #fff; font-size: 22px; font-weight: bold;">
							<a href="<c:url value="/main.html"/>" style="color: #fff; font-size: 22px; font-weight: bold; text-decoration: none">经销商系统——业务管理</a>
						</div>
						<div style="color: #fff">
							<a href="<c:url value="/main.html"/>" style="color: #fff; text-decoration: none">致力于发展成为中国文化产权交易第一品牌</a>
						</div>
					</td>
					<td style="padding-right: 5px; text-align: right; vertical-align: bottom;">
						<div id="topmenu" style="color: #fff">
							您好，${username}!<a href="<c:url value="/logout.html"/>">[退出]</a><a href="#" onclick="open1('修改密码', '<c:url value="/modPwdInput.html" />')">修改密码</a>
						</div>
					</td>
				</tr>
			</table>
		</div>

	</div>

	<div data-options="region:'west',split:true,title:'功能导航'" style="width: 200px; padding: 5px;">
		<ul class="easyui-tree">
		
		
			<sec:authorize access="hasAnyRole('ROLE_DEALER_QUERY','ROLE_USER_QUERY','ROLE_DEALER_IMPORT')">
				<li iconCls="icon-base"><span>经销商</span>
					<ul>
					<sec:authorize access="hasAnyRole('ROLE_DEALER_QUERY')">
						<li iconCls="icon-gears"><a href="#" onclick="open1('经销商管理','<c:url value="/dealerList.html"/>')">经销商管理</a></li>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ROLE_USER_QUERY')">
						<li iconCls="icon-gears"><a href="#" onclick="open1('会员管理','<c:url value="/tUserList.html"/>')">会员管理</a></li>
					</sec:authorize>
					</ul>
				</li>
			</sec:authorize>
			
			<sec:authorize access="hasAnyRole('ROLE_GOODS_TRADE','ROLE_REVIEW_ORDERS','ROLE_SERD_GOODS_MINUTE','ROLE_SENDED_GOODS_MANAGE','ROLE_TRANSATION_REPORT_OUT','ROLE_GOODS_INFORMATION','ROLE_OUT_SALE_CODE','ROLE_HISTORY_RETURN_GOODS')">
				<li iconCls="icon-base"><span>产品贸易</span>
					<ul>
					<sec:authorize access="hasAnyRole('ROLE_GOODS_INFORMATION')">
						<li iconCls="icon-gears"><a href="#" onclick="open1('产品信息录入', '<c:url value="/goodsList.html" />')">产品信息录入</a></li>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ROLE_SERD_GOODS_MINUTE')">
						<li iconCls="icon-gears"><a href="#" onclick="open1('发货信息页', '<c:url value="/toQueryLowerUser.html" />')">发货信息页</a></li>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ROLE_GOODS_TRADE')">
						<li iconCls="icon-gears"><a href="#" onclick="open1('产品贸易审核','<c:url value="/showBatch.html"/>')">产品贸易审核</a></li>
					</sec:authorize>
<%-- 					<sec:authorize access="hasAnyRole('REVIEW_ORDERS')"> --%>
<!-- 						<li iconCls="icon-gears"><a href="#" onclick="open1('订单审核', '<c:url value="/goodsMgr.html" />')">订单审核</a></li>  -->
<%-- 					</sec:authorize> --%>
					
					<sec:authorize access="hasAnyRole('ROLE_SENDED_GOODS_MANAGE')">
						<li iconCls="icon-gears"><a href="#" onclick="open1('撤销产品管理', '<c:url value="/goodsOrderList.html" />')">撤销产品管理</a></li>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ROLE_RETURN_GOODS_MANAGE')">
						<li iconCls="icon-gears"><a href="#" onclick="open1('下级退货管理', '<c:url value="/returnGoodsOrderList.html" />')">下级退货管理</a></li>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ROLE_TRANSATION_REPORT_OUT')">
						<li iconCls="icon-gears"><a href="#" onclick="open1('交易报表导出', '<c:url value="/trancationReportList.html" />')">交易报表导出</a></li>
					</sec:authorize>
					
					<sec:authorize access="hasAnyRole('ROLE_TRANCATION_HISTORY')">
						<li iconCls="icon-gears"><a href="#" onclick="open1('历史交易记录', '<c:url value="/historyGoodsOrderList.html" />')">历史交易记录</a></li>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ROLE_HISTORY_RETURN_GOODS')">
						<li iconCls="icon-gears"><a href="#" onclick="open1('历史退货记录', '<c:url value="/returnHistory.html" />')">历史退货记录</a></li>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ROLE_OUT_SALE_CODE')">
						<li iconCls="icon-gears"><a href="#" onclick="open1('销售码导出', '<c:url value="/toSaleGoodsCodeList.html" />')">销售码导出</a></li>
					</sec:authorize>
					
<%-- 					<sec:authorize access="hasAnyRole('ROLE_QUERY_COMMISION')">
						<li iconCls="icon-gears"><a href="#" onclick="open1('每日佣金查询', '<c:url value="/commision.html" />')">每日佣金查询</a></li>
					</sec:authorize> --%>
					
					<sec:authorize access="hasAnyRole('ROLE_EXCHANGE_COMMODITY')">
						<li iconCls="icon-gears"><a href="#" onclick="open1('兑换商品录入', '<c:url value="/commodityList.html" />')">兑换商品录入</a></li>
					</sec:authorize>
					
					<sec:authorize access="hasAnyRole('ROLE_EXCHANGE_APPROVAL')">
						<li iconCls="icon-gears"><a href="#" onclick="open1('兑换商品审核', '<c:url value="/commodityApproval.html" />')">兑换商品审核</a></li>
					</sec:authorize>
					</ul>
				</li>
			</sec:authorize>
		
			<sec:authorize access="hasAnyRole('ROLE_OPERATOR_MANAGE','ROLE_BUSI_ROLE_MANAGE','ROLE_ROLE_MANAGE','ROLE_SonSys_MANAGE')">
				<li iconCls="icon-base"><span>系统管理</span>
					<ul>
					<sec:authorize access="hasAnyRole('ROLE_OPERATOR_MANAGE')">
						<li iconCls="icon-gears"><a href="#" onclick="open1('操作员管理','<c:url value="/toSysUsers.html"/>')">操作员管理</a></li>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ROLE_BUSI_ROLE_MANAGE')">
						<li iconCls="icon-gears"><a href="#" onclick="open1('角色管理', '<c:url value="/toSysBusiRoles.html" />')">角色管理</a></li>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ROLE_ROLE_MANAGE')">
						<li iconCls="icon-gears"><a href="#" onclick="open1('功能管理', '<c:url value="/toSysRoles.html" />')">功能管理</a></li>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ROLE_SonSys_MANAGE')">
						<li iconCls="icon-gears"><a href="#" onclick="open1('子系统管理', '<c:url value="/toSonSysRoles.html" />')">子系统管理</a></li>
					</sec:authorize>
					</ul>
				</li>
			</sec:authorize>
			
			<sec:authorize access="hasAnyRole('ROLE_AGENCY_ROLE')">
				<li iconCls="icon-base"><span>经销商系统管理</span>
					<ul>
					<sec:authorize access="hasAnyRole('ROLE_BUSI_ROLE_MANAGE')">
						<li iconCls="icon-gears"><a href="#" onclick="open1('经销商角色管理', '<c:url value="/toAgencyBusiRoles.html" />')">经销商角色管理</a></li>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ROLE_ROLE_MANAGE')">
						<li iconCls="icon-gears"><a href="#" onclick="open1('经销商功能管理', '<c:url value="/toAgencyRoles.html" />')">功能管理</a></li>
					</sec:authorize>
					</ul>
				</li>
			</sec:authorize>
			
		
		
		</ul>
	</div>

	<div data-options="region:'south',border:false" style="height: 25px; background: #A9FACD; padding: 5px;">CopyRight：国际版权交易中心</div>
	<div data-options="region:'center'">
		<div id="tt" class="easyui-tabs" fit="true" border="false" plain="true"></div>
	</div>
</body>
</html>