<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>经销商线下配售——业务管理系统</title>
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
		
		<%-- <sec:authorize access="hasAnyRole('ROLE_DEALER_QUERY','ROLE_TUSER_QUERY','ROLE_DEALER_IMPORT')">--%>
			<sec:authorize access="">
				<li iconCls="icon-base"><span>经销商会员管理</span>
					<ul>
					<%--<sec:authorize access="hasAnyRole('ROLE_DEALER_QUERY')"> --%>
					<sec:authorize access="">
						<li iconCls="icon-gears"><a href="#" onclick="open1('经销商管理','<c:url value="/dealerList.html"/>')">经销商管理</a></li>
					</sec:authorize>
					<%--<sec:authorize access="hasAnyRole('ROLE_TUSER_QUERY')"> --%>
					<sec:authorize access="">
						<li iconCls="icon-gears"><a href="#" onclick="open1('会员管理','<c:url value="/dealerUserList.html"/>')">会员管理</a></li>
					</sec:authorize>
					</ul>
				</li>
			</sec:authorize>
			<%--<sec:authorize access="hasAnyRole('ROLE_GOODS_TRADE','ROLE_REVIEW_ORDERS','ROLE_OUT_GOING_GOODS','ROLE_ALL_GOODS_INFORMATION','ROLE_SERD_GOODS_MINUTE','ROLE_SENDED_GOODS_MANAGE','ROLE_RETURN_MANAGERMENT','ROLE_TRANCATION_HISTORY','ROLE_TRANSATION_REPORT_OUT','ROLE_HISTORY_RETURN_GOODS','ROLE_QUERY_COMMISION')"> --%>
		<sec:authorize access="hasAnyRole('ROLE_QUERY_COMMISION','ROLE_ACCESS_GOLD_QUERY')">
				<li iconCls="icon-base"><span>产品贸易</span>
					<ul>
					<%--<sec:authorize access="hasAnyRole('ROLE_OUT_GOING_GOODS')"> --%>
					<sec:authorize access="">
						<li iconCls="icon-gears"><a href="#" onclick="open1('待发产品','<c:url value="/toBeSendGoodsList.html"/>')">待发产品</a></li>
					    </sec:authorize>
					    <%--<sec:authorize access="hasAnyRole('ROLE_SERD_GOODS_MINUTE')"> --%>
					<sec:authorize access="">
						<li iconCls="icon-gears"><a href="#" onclick="open1('发货信息页', '<c:url value="/toQueryLowerUser.html" />')">分发信息页</a></li>
					</sec:authorize>
					<%--<sec:authorize access="hasAnyRole('ROLE_ALL_GOODS_INFORMATION')"> --%>
					<sec:authorize access="">
						<li iconCls="icon-gears"><a href="#" onclick="open1('剩余产品信息','<c:url value="/tolastGoods.html"/>')">剩余产品信息</a></li>
					</sec:authorize>
					<%--<sec:authorize access="hasAnyRole('ROLE_RETURN_MANAGERMENT')"> --%>
					<sec:authorize access="">
						<li iconCls="icon-gears"><a href="#" onclick="open1('本级退货管理', '<c:url value="toReturnsGoods.html" />')">本级退货管理</a></li>
					</sec:authorize>
					<%--<sec:authorize access="hasAnyRole('ROLE_GOODS_TRADE')"> --%>
					<sec:authorize access="">
						<li iconCls="icon-gears"><a href="#" onclick="open1('产品贸易审核','<c:url value="/showBatch.html"/>')">产品贸易审核</a></li>
					</sec:authorize>
					<%-- <sec:authorize access="hasAnyRole('ROLE_RETURN_GOODS_MANAGE')">--%>
					<sec:authorize access="">
						<li iconCls="icon-gears"><a href="#" onclick="open1('下级退货管理', '<c:url value="/toLowReturnsGoods.html" />')">下级退货管理</a></li>
					</sec:authorize>
					<%--<sec:authorize access="hasAnyRole('ROLE_SENDED_GOODS_MANAGE')"> --%>
					<sec:authorize access="">
						<li iconCls="icon-gears"><a href="#" onclick="open1('撤销产品管理', '<c:url value="/goodsOrderList.html" />')">撤销产品管理</a></li>
					</sec:authorize>
					<%--<sec:authorize access="hasAnyRole('ROLE_TRANCATION_HISTORY')"> --%>
					<sec:authorize access="">
						<li iconCls="icon-gears"><a href="#" onclick="open1('历史交易记录', '<c:url value="/historyGoodsOrderList.html" />')">历史交易记录</a></li>
					</sec:authorize>
					<%-- <sec:authorize access="hasAnyRole('ROLE_HISTORY_RETURN_GOODS')">--%>
					<sec:authorize access="">
						<li iconCls="icon-gears"><a href="#" onclick="open1('历史退货记录', '<c:url value="/returnHistory.html" />')">历史退货记录</a></li>
					</sec:authorize>
					<%-- <sec:authorize access="hasAnyRole('ROLE_TRANSATION_REPORT_OUT')">--%>
					<sec:authorize access="">
						<li iconCls="icon-gears"><a href="#" onclick="open1('交易报表导出', '<c:url value="/trancationReportList.html" />')">交易报表导出</a></li>
					</sec:authorize>
					<%--<sec:authorize access="hasAnyRole('ROLE_OUT_SALE_CODE')"> --%>
					<sec:authorize access="">
						<li iconCls="icon-gears"><a href="#" onclick="open1('销售码导出', '<c:url value="/toSaleGoodsCodeList.html" />')">销售码导出</a></li>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ROLE_QUERY_COMMISION')">
						<li iconCls="icon-gears"><a href="#" onclick="open1('每日佣金明细查询', '<c:url value="/commision.html" />')">每日佣金明细查询</a></li>
					</sec:authorize>
					
					<sec:authorize access="hasAnyRole('ROLE_ACCESS_GOLD_QUERY')">
						<li iconCls="icon-gears"><a href="#" onclick="open1('出入金明细查询', '<c:url value="/toAccessGoldQuery.html" />')">出入金明细查询</a></li>
					</sec:authorize>
					
					<sec:authorize access="hasAnyRole('ROLE_SUM_COMMISION_QUERY')">
						<li iconCls="icon-gears"><a href="#" onclick="open1('每日佣金汇总查询', '<c:url value="/commisionSum.html" />')">每日佣金汇总查询</a></li>
					</sec:authorize>
					
					<sec:authorize access="hasAnyRole('ROLE_GOLD_SUM_QUERY')">
						<li iconCls="icon-gears"><a href="#" onclick="open1('出入金汇总查询', '<c:url value="/toAccessGoldSumQuery.html" />')">出入金汇总查询</a></li>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ROLE_QUERY_COMMISION')">
						<li iconCls="icon-gears"><a href="#" onclick="open1('产品持有者查询', '<c:url value="/toAgencyGoods.html" />')">产品持有者查询</a></li>
					</sec:authorize>
					</ul>
				</li>
			</sec:authorize>
		<%--<sec:authorize access="hasAnyRole('ROLE_OPERATOR_MANAGE','ROLE_BUSI_ROLE_MANAGE','ROLE_ROLE_MANAGE')"> --%>
			<sec:authorize access="">
				<li iconCls="icon-base"><span>系统管理</span>
					<ul>
					<%--<sec:authorize access="hasAnyRole('ROLE_OPERATOR_MANAGE')"> --%>
					<sec:authorize access="">
						<li iconCls="icon-gears"><a href="#" onclick="open1('操作员管理','<c:url value="/toSysUsers.html"/>')">操作员管理</a></li>
					</sec:authorize>
					<%--	<sec:authorize access="hasAnyRole('ROLE_BUSI_ROLE_MANAGE')"> --%>
					<sec:authorize access="">
						<li iconCls="icon-gears"><a href="#" onclick="open1('角色管理', '<c:url value="/toSysBusiRoles.html" />')">角色管理</a></li>
					</sec:authorize>
					<%-- <sec:authorize access="hasAnyRole('ROLE_ROLE_MANAGE')">--%>
					<sec:authorize access="">
						<li iconCls="icon-gears"><a href="#" onclick="open1('功能管理', '<c:url value="/toSysRoles.html" />')">功能管理</a></li>
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