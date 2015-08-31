$(function(){
	$("#accessGoldList").datagrid({
		
		onLoadSuccess : function () { 
			   $(this).datagrid("fixRownumber"); 
			 },
		
		title:'查看经销商',
		fit:true,
		autoRowheight:true,
		remoteSort:false,
		singleSelect:true,
		showFooter:true,
		pagination:true,
		pagePostion:'bottom',
		pageNumer: 1,
		pageSize: 50,////每页显示的记录条数，默认为50
		pageList: [50,100,300,500],//可以设置每页记录条数的列表
		rownumbers: true,
		toolbar : '#accessGoldbar',
		url:ctx+'queryAccessGold.html',
		columns:[[
		          	{field: 'developer', title: '经销商', width: '100', align: 'center'}, 
		          	{field: 'clientId', title: '客户账号', width: '100', align: 'center'}, 
				    {field: 'clientName', title: '客户姓名', width: '100', align: 'center'},
				    {field: 'inBalance', title: '转入金额', width: '100', align: 'center'},
				    {field: 'outBalance', title: '转出金额', width: '100', align: 'center'},
				    {field: 'diffBalance', title: '轧差', width: '100', align: 'center'},
				    {field: 'currentBalance', title: '持仓资金', width: '100', align: 'center'}
		            ]]
	});
	

	$.extend($.fn.datagrid.methods, {
		fixRownumber : function (jq) {
			return jq.each(function () {
				var panel = $(this).datagrid("getPanel");
				//获取最后一行的number容器,并拷贝一份
				var clone = $(".datagrid-cell-rownumber", panel).last().clone();
				//由于在某些浏览器里面,是不支持获取隐藏元素的宽度,所以取巧一下
				clone.css({
					"position" : "absolute",
					left : -1000
				}).appendTo("body");
				var width = clone.width("auto").width();
				//默认宽度是25,所以只有大于25的时候才进行fix
				if (width > 25) {
					//多加5个像素,保持一点边距
					$(".datagrid-header-rownumber,.datagrid-cell-rownumber", panel).width(width + 5);
					//修改了宽度之后,需要对容器进行重新计算,所以调用resize
					$(this).datagrid("resize");
					//一些清理工作
					clone.remove();
					clone = null;
				} else {
					//还原成默认状态
					$(".datagrid-header-rownumber,.datagrid-cell-rownumber", panel).removeAttr("style");
				}
			});
		}
	});

	
	
	//设置分页控件
	var p = $("#accessGoldList").datagrid('getPager');
	$(p).pagination({
		beforePageText: '第',//页数文本框前显示的汉字
		afterPageText: '页    共 {pages} 页',
		displayMsg: '<span class="marrig20">共 {total} 条记录</span>',
		onBeforeRefresh:function(){  
	 		$('#accessGoldList').datagrid('load');  
	 		return false;
 		} 
	});
});




function selectAccessGold(){
	var startDate=$("#initDateStart").datebox('getValue');
	var endDate=$("#initDateEnd").datebox('getValue');
	if(startDate!="" && endDate!=""){
		if(startDate>endDate){
			alert("开始日期不能大于结束日期！");
			return;
		}
	}
	
	$("#accessGoldList").datagrid({
		pagination: true,
		url:ctx+"queryAccessGold.html",
		pageNumber: 1,
		queryParams:{
			developer:$("#developer").val(),
			clientId:$("#clientId").val(),
			initDateStart:$("#initDateStart").val(),
			initDateEnd:$("#initDateEnd").val(),
		}
	});
	
}	

