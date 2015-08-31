$(function(){
	$("#goodsBatchList").datagrid({
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
		url:ctx+'queryBatch.html',
		columns:[[
					{field: 'id', title: '产品批次号', width: '100', align: 'center'},  
					{field: 'goodsName', title: '产品名称', width: '100', align: 'center' },
					{field: 'goodsNum', title: '产品编号', width: '100', align: 'center' },
					{field: 'showTime', title: '发货时间', width: '100', align: 'center'},
					{field: 'agencyName', title: '经销商名称', width: '100', align: 'center'},
		            ]],


	});
	
	


	//设置分页控件
	var p = $("#goodsBatchList").datagrid('getPager');
	$(p).pagination({
		beforePageText: '第',//页数文本框前显示的汉字
		afterPageText: '页    共 {pages} 页',
		displayMsg: '<span class="marrig20">共 {total} 条记录</span>',
		onBeforeRefresh:function(){  
	 		$('#goodsBatchList').datagrid('load');  
	 		return false;
 		} 
	});
	
	$("#searchDetail").bind('click',function(){
		   var row = $('#goodsBatchList').datagrid('getSelected');
	       if(row ==null ){
				$.messager.alert("操作提示", "请先选择一条记录！");
				return ;
			}
	       window.parent.open1('订单审核', ctx+"/goodsMgr.html?id="+row.id);
	})

});

//查询
function selectGoodsBatch(){
	$("#goodsBatchList").datagrid({
		pagination: true,
		url:ctx+"queryBatch.html?goodsName="+$("#goodsName").val(),
		pageNumber: 1,
		queryParams:{
			id:$("#id").val(),
		}
	});

}







