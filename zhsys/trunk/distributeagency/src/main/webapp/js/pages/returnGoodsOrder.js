$(function(){
	$("#returnGoodsOrderList").datagrid({
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
		toolbar : '#returnGoodsOrderbar',
		url:ctx+'queryReturnGoodsOrder.html',
		columns:[[
		          	{field: 'user_identy', hidden:true }, 
					{field: 'dealer_name', title: '经销商名称/会员', width: '100', align: 'center'},             
					{field: 'goods_name', title: '产品名称', width: '100', align: 'center'},  
					{field: 'goods_num', title: '产品编号', width: '100', align: 'center' },
					{field: 'distribute_num', title: '产品数量', width: '100', align: 'center'},  
					{field: 'return_goods_reason', title: '退货原因', width: '100', align: 'center'}, 
					{field: 'return_goods_state', title: '退货审核状态', width: '100', align: 'center',
						formatter : function(value,rowData,index){
				        	  if(value == 0){
				        		  return "未审核";
				        	  }else if(value == 1){
				        		  return "审核通过";
				        	  }else if(value == 2){
				        		  return "审核不通过";
				        	  }
						}
				     }
		            ]]
		

	});

	//设置分页控件
	var p = $("#returnGoodsOrderList").datagrid('getPager');
	$(p).pagination({
		beforePageText: '第',//页数文本框前显示的汉字
		afterPageText: '页    共 {pages} 页',
		displayMsg: '<span class="marrig20">共 {total} 条记录</span>',
		onBeforeRefresh:function(){  
	 		$('#returnGoodsOrderList').datagrid('load');  
	 		return false;
 		} 
	});

	$("#checkReturnGoodsOrder").bind("click",function(){
		var row = $('#returnGoodsOrderList').datagrid('getSelected');
		$("#checkGoodsOrderForm").form("clear");
		if(row){
			$('#checkGoodsOrderForm').form('load',row);
			 if(row.user_identy==0){
			  bindDialog(true);
			 }
			 else if(row.user_identy==2)
				 {
				 bindDialog(false);
				 }
			if(row.return_goods_state != 0){
				$.messager.alert('系统提示','只有未审核的才能进行审核','info');
			}else{	
				$("#check_goodsOrder").dialog('open').dialog('setTitle', '审核');
			}
		}else{
			$.messager.alert('系统提示','请选择要审核的产品','info');
		}
	});
	
	
	
	
});
//查询
function selectreturngoodsorder(){
	$("#returnGoodsOrderList").datagrid({
		pagination: true,
		url:ctx+"queryReturnGoodsOrder.html",
		pageNumber: 1,
		queryParams:{
			goodsName:$("#goodsName").val(),
			goodsNum:$("#goodsNum").val(),
			dealerName:$("#dealerName").val(),
			returnGoodsState:$("#return_goods_state").combobox("getValue")
		}
	});
}

function bindDialog(flag){
//审核退货订单
	if(flag){
$('#check_goodsOrder').show().dialog({
	iconCls: 'icon-edit',   
    closed: true,
    cache: false,   
    modal: true ,
    buttons:[{
		text:'审核通过',
		iconCls: 'icon-ok',
		handler:function(){
			$('#checkGoodsOrderForm').form('submit', {   
				url: ctx + "checkGoodsOrder.html?returnGoodsState=1",
			    onSubmit:function(){
			    	var returnOrderOptions = $('#check_returnGoodsOptions').val();
			    	if(returnOrderOptions==null || returnOrderOptions==''){
			    		$.messager.alert('系统提示', '审核原因不能为空', 'info');
			    		return false;
					}
			    },
			    success:function(data){
			    	if(data="1"){
			        	$.messager.alert("操作提示", "审核成功!", "info");
			        	$('#check_goodsOrder').dialog('close');
				    	$('#returnGoodsOrderList').datagrid('reload');
				    	$('#returnGoodsOrderList').datagrid('unselectAll');
			        }else{
			        	$.messager.alert("操作提示", "审核失败，请稍候重新审核!", "info");
			        	$('#check_goodsOrder').dialog('close');
				    	$('#returnGoodsOrderList').datagrid('reload');
				    	$('#returnGoodsOrderList').datagrid('unselectAll');
			        }
			    } 
			});
			$("#check_goodsOrder").dialog('close');
			$('#returnGoodsOrderList').datagrid('reload');
			$('#returnGoodsOrderList').datagrid('unselectAll');
		}
	 },{
		text:'取消',
		iconCls: 'icon-no',
		handler:function(){
			$('#check_goodsOrder').dialog('close');
		}
	}]
});
	}
	else{


//审核退货订单
$('#check_goodsOrder').show().dialog({
	iconCls: 'icon-edit',   
    closed: true,
    cache: false,   
    modal: true ,
    buttons:[{
		text:'审核通过',
		iconCls: 'icon-ok',
		handler:function(){
			$('#checkGoodsOrderForm').form('submit', {   
				url: ctx + "checkGoodsOrder.html?returnGoodsState=1",
			    onSubmit:function(){
			    	var returnOrderOptions = $('#check_returnGoodsOptions').val();
			    	if(returnOrderOptions==null || returnOrderOptions==''){
			    		$.messager.alert('系统提示', '审核原因不能为空', 'info');
			    		return false;
					}
			    },
			    success:function(data){
			    	if(data="1"){
			        	$.messager.alert("操作提示", "审核成功!", "info");
			        	$('#check_goodsOrder').dialog('close');
				    	$('#returnGoodsOrderList').datagrid('reload');
				    	$('#returnGoodsOrderList').datagrid('unselectAll');
			        }else{
			        	$.messager.alert("操作提示", "审核失败，请稍候重新审核!", "info");
			        	$('#check_goodsOrder').dialog('close');
				    	$('#returnGoodsOrderList').datagrid('reload');
				    	$('#returnGoodsOrderList').datagrid('unselectAll');
			        }
			    } 
			});
			$("#check_goodsOrder").dialog('close');
			$('#returnGoodsOrderList').datagrid('reload');
			$('#returnGoodsOrderList').datagrid('unselectAll');
		}
	 },{
			text:'审核不通过',
			iconCls: 'icon-no',
			handler:function(){
				$('#checkGoodsOrderForm').form('submit', {   
					url: ctx + "checkGoodsOrder.html?returnGoodsState=2",
				    onSubmit:function(){
				    	var returnOrderOptions = $('#check_returnGoodsOptions').val();
				    	if(returnOrderOptions==null || returnOrderOptions==''){
				    		$.messager.alert('系统提示', '审核原因不能为空', 'info');
				    		return false;
						}
				    },
				    success:function(data){
				    	if(data="1"){
				        	$.messager.alert("操作提示", "审核不通过成功!", "info");
				        	$('#check_goodsOrder').dialog('close');
					    	$('#returnGoodsOrderList').datagrid('reload');
					    	$('#returnGoodsOrderList').datagrid('unselectAll');
				        }else{
				        	$.messager.alert("操作提示", "审核不通过失败，请稍候重新审核!", "info");
				        	$('#check_goodsOrder').dialog('close');
					    	$('#returnGoodsOrderList').datagrid('reload');
					    	$('#returnGoodsOrderList').datagrid('unselectAll');
				        }
				    } 
				});
				$("#check_goodsOrder").dialog('close');
				$('#returnGoodsOrderList').datagrid('reload');
				$('#returnGoodsOrderList').datagrid('unselectAll');
			}
		},{
		text:'取消',
		iconCls: 'icon-no',
		handler:function(){
			$('#check_goodsOrder').dialog('close');
		}
	}]
});
	}
}
