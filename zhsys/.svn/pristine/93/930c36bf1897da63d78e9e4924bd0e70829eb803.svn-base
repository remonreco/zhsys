$(function(){
	$("#saleGoodsCodeList").datagrid({
		title:'查看产品',
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
		toolbar : '#saleGoodsCodebar',
		url:ctx+'saleGoodsCode.html',
		columns:[[
                    {field: 'id', title: '批次号', width: '100', align: 'center'}, 
		            {field: 'goodsNum', title: '产品编号', width: '100', align: 'center'},  
		            {field: 'goodsName', title: '产品名称', width: '100', align: 'center' },
		            {field: 'creatTimeStr', title: '订单创建时间', width: '230', align: 'center'},
		            {field: 'goodsPriceStr', title: '产品价格', width: '100', align: 'center'},
		            ]]
		
	});
	
	//导出报表
	$("#outSaleGoodsCode").click(function() {
		var row = $('#saleGoodsCodeList').datagrid('getSelected');
		if(row ==null ){
			$.messager.alert("操作提示", "请先选择一条记录！");
			return ;
		}
		$('#saleGoodsCodefm').form('submit',{  
			url : ctx + 'loadoutSaleCode.html?id='+row.id
		});
		
	});

	//设置分页控件
	var p = $("#saleGoodsCodeList").datagrid('getPager');
	$(p).pagination({
		beforePageText: '第',//页数文本框前显示的汉字
		afterPageText: '页    共 {pages} 页',
		displayMsg: '<span class="marrig20">共 {total} 条记录</span>',
		onBeforeRefresh:function(){  
	 		$('#saleGoodsCodeList').datagrid('load');  
	 		return false;
 		} 
	});
});
function selecsaleGoodsCode(){
	$("#saleGoodsCodeList").datagrid({
		pagination: true,
		url:ctx+"saleGoodsCode.html",
		pageNumber: 1,
		queryParams:{
			goodsNum:$("#goodsNum").val(),
			goodsName:$("#goodsName").val(),
		}
	});
	
}


