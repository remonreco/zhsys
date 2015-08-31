$(function(){
	//初始化添加dialog
	$('#unAgreeReturnGoodsOrder').show().dialog({
		width: 800,   
	    height:500,   
		cache : false,
		doSize : true,
		modal : true,
		resizable : true,
		closed : true,
		border:false,
		//href:ctx+'toSelectMegAdd.html',
		buttons : [{
			id : 'save',
			text : '驳回',
			iconCls : 'icon-cancel',
			handler : function() {
				$.ajax({
					type : 'POST',
					url : ctx + 'unAgreeReturnGoodsOrder.html?returnGoodsState=2',
					/*data : {id : $('#id').val(),
						    reason : $('#goodsReason').val()},*/
					cache : false,
					traditional : true,
					success : function(data) {
						if (data == 1) {
							$.messager.alert("系统提示", "驳回成功！");
						} else {
							$.messager.alert('系统提示', '驳回失败', 'info');
						}
						$('#remainderGoodsOrderList').datagrid('reload');// 重新加载
						$('#remainderGoodsOrderList').datagrid('clearSelections');// 取消选中项
					}
				});
			}
		},{
			id : 'cancel',
			text : '取消',
			iconCls : 'icon-redo',
			handler : function() {
				$('#unAgreeReturnGoodsOrder').dialog('close');
			}
		}]
	});
	
	$("#remainderGoodsOrderList").datagrid({
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
		toolbar : '#remainderGoodsOrderbar',
		url:ctx+'queryRemainderGoodsLow.html',
		columns:[[
		          	
					{field: 'dealer_name', title: '经销商名称', width: '100', align: 'center'},             
					{field: 'goods_name', title: '产品名称', width: '100', align: 'center'},  
					{field: 'goods_id', title: '产品编号', width: '100', align: 'center' },
					{field: 'goods_num', title: '产品数量', width: '100', align: 'center'},
					{field: 'return_goods_reason', title: '申请原因', width: '100', align: 'center'},
		            ]]

	});

	//设置分页控件
	var p = $("#remainderGoodsOrderList").datagrid('getPager');
	$(p).pagination({
		beforePageText: '第',//页数文本框前显示的汉字
		afterPageText: '页    共 {pages} 页',
		displayMsg: '<span class="marrig20">共 {total} 条记录</span>',
		onBeforeRefresh:function(){  
	 		$('#remainderGoodsOrderList').datagrid('load');  
	 		return false;
 		} 
	});
	
	$("#agreeBtn").bind("click",function(){
		var row = $('#remainderGoodsOrderList').datagrid('getSelected');
		if (row) {
			$.messager.confirm("系统提示", "是否确认同意该订单退货？", function(r) {
				if (r) {
					$.ajax({
						type : 'POST',
						url : ctx + 'agreeReturnGoodsOrder.html?returnGoodsState=1',
						/*data : {id : row.id},*/
						cache : false,
						traditional : true,
						success : function(data) {
							if (data == 1) {
								$.messager.alert("系统提示", "操作成功！");
							} else {
								$.messager.alert('系统提示', '操作失败', 'info');
							}
							$('#remainderGoodsOrderList').datagrid('reload');// 重新加载
							$('#remainderGoodsOrderList').datagrid('clearSelections');// 取消选中项
						}
					});
				} else {
					window.close();
					$('#remainderGoodsOrderList').datagrid('clearSelections');// 取消选中项
				}
			});
		} else {
			$.messager.alert('系统提示', '请选择要同意的订单', 'info');
			return;
		}
	});
	
	$("#unAgreeBtn").bind("click",function(){
		$('#id').val('');
		$('#dealerName2').html('');
		$('#goodsReason').val('');
			var row = $('#remainderGoodsOrderList').datagrid('getSelected');
			if(row){
				$('#id').val(row.bid);
				$('#dealerName2').html(row.dealer_name);
				$('#unAgreeReturnGoodsOrder').dialog('open').dialog('setTitle', '驳回退货申请');
			}else{
				$.messager.alert('系统提示', '请选择要驳回的退货申请订单', 'info');
			}
		});
});

function selectReturnGoodsApplication(){
	$("#remainderGoodsOrderList").datagrid({
		pagination: true,
		url:ctx+"queryRemainderGoodsLow.html",
		pageNumber: 1,
		queryParams:{
			goodsName:$("#goodsName").val(),
			goodsId:$("#goodsId").val(),
			dealerName:$("#dealerName").val(),
		}
	});
}
