$(function(){
	$("#toBeSendGoodsList").datagrid({
		title:'待发产品',
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
		toolbar : '#toBeSendGoodsListbar',
		url:ctx+'toBeSendGoods.html',
		columns:[[
		            {field: 'batchId', title: '产品批次', width: '100', align: 'center'},  
		            {field: 'goodsName', title: '产品名称', width: '100', align: 'center'},  
		            {field: 'goodsNum', title: '产品编号', width: '100', align: 'center' },
		            {field: 'agencyName', title: '所属经销商', width: '100', align: 'center'},  
		            {field: 'agencyId', title: '所属经销商Id', width: '100', align: 'center',hidden:true},  
		            {field: 'distributeNum', title: '分发数量', width: '100', align: 'center'}, 
		            {field: 'totalAmt', title: '商品总价值', width: '100', align: 'center'},  
		            {field: 'sendDate', title: '订单下发日期', width: '100', align: 'center'}, 
		            {field: 'overdueState', title: '订单状态', width: '100', align: 'center'},  
		            {field: 'orderState', title: '订单状态', width: '100', align: 'center',hidden:true},  
		            {field: 'id', title: '订单ID', width: '100', align: 'center',hidden:true},  
		            ]]
	});
	
	//设置分页控件
	var p = $("#toBeSendGoodsList").datagrid('getPager');
	$(p).pagination({
		beforePageText: '第',//页数文本框前显示的汉字
		afterPageText: '页    共 {pages} 页',
		displayMsg: '<span class="marrig20">共 {total} 条记录</span>',
		onBeforeRefresh:function(){  
	 		$('#toBeSendGoodsList').datagrid('load');  
	 		return false;
 		} 
	});
});

function selectToBeSendGoodsList(){
	var startDate=$("#startDate").datebox('getValue');
	var endDate=$("#endDate").datebox('getValue');
	if(startDate!="" && endDate!=""){
		if(startDate>endDate){
			alert("开始日期不能大于结束日期！");
			return;
		}
	}
	$("#toBeSendGoodsList").datagrid({
		pagination: true,
		url:ctx+"toBeSendGoods.html",
		pageNumber: 1,
		queryParams:{
			goodsName:$("#goodsName").val(),
			goodsNum:$("#goodsNum").val(),
			startDate:$("#startDate").datebox('getValue'),
			endDate:$("#endDate").datebox('getValue'),
		}
	});
}

	function sendGoods(){
		var row = $("#toBeSendGoodsList").datagrid("getSelected");
		if(row==""||row==null){
			$.messager.alert("操作提示", "请选择要发货的数据！");
			return;
		}
		//先领产品在发货
		if(row.orderState==3){
		goodsName = encodeURI(encodeURI(row.goodsName));   
		goodsName = encodeURI(encodeURI(row.goodsName)); 
		window.parent.open1("发货信息页","toSendGoodsPage.html?goodsNum="+row.goodsNum+"&goodsName="+goodsName);
		}
		else{
			$.messager.alert("操作提示", "请先领取产品！");
			return;
		}
	}
	
	//领取产品
	function receiveGoods()
	{
		var row = $("#toBeSendGoodsList").datagrid("getSelected");
		if(row==""||row==null){
			$.messager.alert("操作提示", "请选择要领取的数据！");
			return;
		}
		if(row.orderState!=1){
			$.messager.alert("操作提示", "该批产品不能领取");
			return;
		}
			var result="";
			$.ajax({
				type: 'POST',
				url: $("#updateOrderStateById").val(),
				data: {
					id: row.id
				},
				dataType: 'text',
				async: false,
				cache : false,
				traditional: true,
				success: function(data) {
					if(data==1){
						$.messager.alert("操作提示", "领取产品成功!", "info");
				    	$('#toBeSendGoodsList').datagrid('reload');
				    	$('#toBeSendGoodsList').datagrid('unselectAll');
					}
				}
			});
			if(result.indexOf("1")>=1){
				$.messager.alert('修改产品','产品领取失败！','info'); 
				return false;
			}
	}


