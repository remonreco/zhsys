$(function(){
	$("#tgoodsOrderList").datagrid({
		title:'撤销产品管理',
		fit:true,
		autoRowheight:true,
		remoteSort:false,
		singleSelect:true,
		showFooter:true,
		pagination:true,
		pagePostion:'bottom',
		pageSize: config.pageSize, //当设置了 pagination 特性时，初始化页码尺寸
		pageList: config.pageList,//当设置了 pagination 特性时，初始化页面尺寸的选择列表。
		pageList: [50,100,300,500],//可以设置每页记录条数的列表
		rownumbers: true,
		toolbar : '#tgoodsOrderbar',
		url:ctx+'queryGoodsOrder.html',
		columns:[[
		            {field: 'id', title: '订单编号', width: '100', align: 'center' },
		            {field: 'goods_name', title: '产品名称', width: '100', align: 'center'},  
		            {field: 'goods_num', title: '产品编号', width: '100', align: 'center' },
		            {field: 'distribute_num', title: '产品数量', width: '100', align: 'center'}, 
		            {field: 'dealer_name', title: '上级经销商', width: '100', align: 'center'}, 
		            {field: 'order_state', title: '订单状态', width: '100', align: 'center',
		            	formatter : function(value,rowData,index){
				        	  if(value == 0){
				        		  return "未审核";
				        	  }else if(value == 1){
				        		  return "审核通过";
				        	  }else if(value == 2){
				        		  return "审核不通过";
				        	  }else if(value == 3){
				        		  return "成功领取";
				        	  }else if(value == 4){
				        		  return "订单过期";
				        	  }
		            	}  
		            },
		            {field: 'userType', title: '用户类型', width: '100', align: 'center'}, 
		            {field: 'userName', title: '用户名称', width: '100', align: 'center'}, 
		            ]]
	});
	
	//设置分页控件
	var p = $("#tgoodsOrderList").datagrid('getPager');
	$(p).pagination({
		beforePageText: '第',//页数文本框前显示的汉字
		afterPageText: '页    共 {pages} 页',
		displayMsg: '<span class="marrig20">共 {total} 条记录</span>',
		onBeforeRefresh:function(){  
	 		$('#tgoodsOrderList').datagrid('load');  
	 		return false;
 		} 
	});
	
	$('#searchBtn').linkbutton({
		iconCls : 'icon-search',
		plain : true,
		text : '查询',
	});
	//审核
	$('#accessBtn').linkbutton({
		iconCls : 'icon-redo',
		plain : true,
		text : '撤销',
	});
	
	$("#accessBtn").bind("click",function(){
		var row = $('#tgoodsOrderList').datagrid('getSelected');
		if (row) {
			$.messager.confirm("系统提示", "是否确认撤销该产品订单？", function(r) {
				if (r) {
					$.ajax({
						type : 'POST',
						url : ctx + 'cancelGoodsOrder.html',
						data : {id : row.id},
						cache : false,
						traditional : true,
						success : function(data) {
							if (data == 1) {
								$.messager.alert("系统提示", "撤销成功！");
							} else {
								$.messager.alert('系统提示', '撤销失败', 'info');
							}
							$('#tgoodsOrderList').datagrid('reload');// 重新加载
							$('#tgoodsOrderList').datagrid('clearSelections');// 取消选中项
						}
					});
				} else {
					window.close();
					$('#tgoodsOrderList').datagrid('clearSelections');// 取消选中项
				}
			});
		} else {
			$.messager.alert('系统提示', '请选择要撤销的订单', 'info');
			return;
		}
	});
});
	
function selectgoodsorder(){
	$("#tgoodsOrderList").datagrid({
		pagination: true,
		url:ctx+"queryGoodsOrder.html",
		pageNumber: 1,
		queryParams:{
			id:$("#id").val(),
			goodsName:$("#goodsName").val(),
			goodsNum:$("#goodsNum").val(),
			higherOrderId:$("#higherOrderId").val(),
			agencyName:$("#agencyId").val(),
		}
	});
}



