$(function(){
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
		url:ctx+'queryRemainderGoods.html',
		columns:[[
		          	
					{field: 'dealer_name', title: '经销商名称', width: '100', align: 'center'},             
					{field: 'goods_name', title: '产品名称', width: '100', align: 'center'},  
					{field: 't_goods_num', title: '产品编号', width: '100', align: 'center' },
					{field: 'goods_num', title: '剩余产品数量', width: '100', align: 'center'},  
					{field: 'goods_count', title: '原始数量', width: '100', align: 'center'}
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
	
	
	$('#rem_goodsOrder').show().dialog({
		iconCls: 'icon-edit',   
	    closed: true,
	    cache: false,   
	    modal: true ,
	    buttons:[{
			text:'退货',
			iconCls: 'icon-ok',
			handler:function(){
				$('#remGoodsOrderForm').form('submit', {   
					url: ctx + "remainderGoodsApplication.html",
				    onSubmit:function(){
				    	var returnGoodsReason = $('#rem_returnGoodsReason').val();
				    	if(returnGoodsReason==null || returnGoodsReason==''){
				    		$.messager.alert('系统提示', '退货申请原因不能为空', 'info');
				    		return false;
						}
				    },
				    success:function(data){
				    	if(data="1"){
				        	$.messager.alert("操作提示", "申请成功!", "info");
				        	$('#rem_goodsOrder').dialog('close');
					    	$('#remainderGoodsOrderList').datagrid('reload');
					    	$('#remainderGoodsOrderList').datagrid('unselectAll');
				        }else{
				        	$.messager.alert("操作提示", "申请失败，请稍候重新申请!", "info");
				        	$('#rem_goodsOrder').dialog('close');
					    	$('#remainderGoodsOrderList').datagrid('reload');
					    	$('#remainderGoodsOrderList').datagrid('unselectAll');
				        }
				    } 
				});
				$("#rem_goodsOrder").dialog('close');
				$('#remainderGoodsOrderList').datagrid('reload');
				$('#remainderGoodsOrderList').datagrid('unselectAll');
			}
		 },{
				text:'取消',
				iconCls: 'icon-no',
				handler:function(){
					$('#rem_goodsOrder').dialog('close');
				}
			}]
		});
	
	
});
function selectReturnGoodsApplication(){
	$("#remainderGoodsOrderList").datagrid({
		pagination: true,
		url:ctx+"queryRemainderGoods.html",
		pageNumber: 1,
		queryParams:{
			goodsName:$("#goodsName").val(),
			goodsId:$("#goodsId").val(),
			dealerName:$("#dealerName").val(),
		}
	});
}

function toReplacementGoods(){
	var row = $("#remainderGoodsOrderList").datagrid("getSelected");
	if(row==""||row==null){
		$.messager.alert("操作提示", "请选择要补发货的数据！");
		return;
	}
	goodsName = encodeURI(encodeURI(row.goods_name));   
	goodsName = encodeURI(encodeURI(row.goods_name)); 
	window.parent.open1("发货信息页","toReplacementGoods.html?goodsNum="+row.t_goods_num+"&goodsName="+goodsName);
}
