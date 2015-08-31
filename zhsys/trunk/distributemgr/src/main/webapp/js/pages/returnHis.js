$(function(){
	$("#goodList").datagrid({
		title:'产品管理',
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
		toolbar : '#tuserbar',
		ctrlSelect:true,
		url:ctx+'returnHistoryList.html',
		columns:[[
		            {field: 'userName', title: '归属用户类型', width: '100', align: 'center' },
		            {field: 'agencyName', title: '产品归属', width: '100', align: 'center'},
		            {field: 'goodsName', title: '产品名称', width: '100', align: 'center'},
		            {field: 'goodsNum', title: '产品编码', width: '100', align: 'center'},
		            {field: 'orderReturnStateDto', title: '退货状态', width: '100', align: 'center' },
		            {field: 'returnGoodsOptions', title: '退货审核意见', width: '100', align: 'center'},
		            {field: 'returnGoodsReason', title: '退货原因', width: '100', align: 'center'},
		            {field: 'distributeNum', title: '退货数量', width: '100', align: 'center'},
		            
		            ]],
	
		
	});
	
	


	//设置分页控件
	var p = $("#goodList").datagrid('getPager');
	$(p).pagination({
		beforePageText: '第',//页数文本框前显示的汉字
		afterPageText: '页    共 {pages} 页',
		displayMsg: '<span class="marrig20">共 {total} 条记录</span>',
		onBeforeRefresh:function(){  
	 		$('#goodList').datagrid('load');  
	 		return false;
 		} 
	});
	
});

//查询
function selectGoodsOrder(){
	
	$("#goodList").datagrid({
		pagination: true,
		url:ctx+"returnHistoryList.html",
		pageNumber: 1,
		queryParams:{
			goodsName:$("#goodsName").val(),
			goodsNum:$("#goodsNum").val(),
		}
	});
	
}

