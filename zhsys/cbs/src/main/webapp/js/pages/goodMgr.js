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
		selectOnCheck :true,
		ctrlSelect:true,
		singleSelect: false,
		url:ctx+'queryVGoodsOrder.html'+"?idForGto=" +VbatchId,
		columns:[[
		           {field: 'id', title: '订单编号', width: '100', align: 'center',checkbox:true},  
		            {field: 'userName', title: '会员名称', width: '100', align: 'center' },
		            {field: 'goodsName', title: '产品名称', width: '100', align: 'center'},
		            {field: 'goodsNum', title: '产品编号', width: '100', align: 'center'},
		            {field: 'distributeNum', title: '交易数量', width: '100', align: 'center'},
		            {field: 'businessMoneyDto', title: '交易金额', width: '100', align: 'center'},
		            {field: 'dealerName', title: '所属经销商', width: '100', align: 'center'},
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
	
	/*//同意绑定事件
	$("#agree").bind('click' ,function(){
		 var row = $('#goodList').datagrid('getChecked');
	       if(row.length==0 ){
				$.messager.alert("操作提示", "请先选择一条记录！");
				return ;
			}
		var str ="";
		for(var i=0;i<row.length;i++){
			str +=row[i].id+",";
		}
		var reason =$("#reason").val();
		reason = encodeURI(reason); 
		reason = encodeURI(reason); 
		$.post(ctx+"agreeOrder.html?ids="+str+"&reason="+reason, 
				   function(data){
			 		if(data=="1"){
	                $('#goodList').datagrid('reload');    // reload the user data
	               window.parent.reloadGrid("产品贸易查看"); //刷新产品管理页面
	                $.messager.show({
						title : '操作提示',
						msg : "批量同意成功!"
					});
		        }else{
		        	$.messager.alert('批量同意','添加失败，请稍后重试',"info");
		        }
				   });
	});
	
	//驳回绑定事件
	$("#disAgree").bind('click' ,function(){
		 var row = $('#goodList').datagrid('getChecked');
	       if(row.length==0 ){
				$.messager.alert("操作提示", "请先选择一条记录！");
				return ;
			}
		var str ="";
		for(var i=0;i<row.length;i++){
			str +=row[i].id+",";
		}
		var reason =$("#reason").val();
		reason = encodeURI(reason); 
		reason = encodeURI(reason); 
		$.post(ctx+"disAgreeOrder.html?ids="+str+"&reason="+reason, 
				   function(data){
			 		if(data=="1"){
	                $('#goodList').datagrid('reload');    // reload the user data
	                $.messager.show({
						title : '操作提示',
						msg : "批量驳回成功!"
					});
		        }else{
		        	$.messager.alert('批量驳回','添加失败，请稍后重试',"info");
		        }
				   });
	});
	
	
	
	//查看详细绑定事件
	$("#viewOrder").bind('click' ,function(){
		 var row = $('#goodList').datagrid('getSelected');
	       if(row==null ){
				$.messager.alert("操作提示", "请先选择一条记录！");
				return ;
			}
	       var id =row.id;
	       $('#viewDlg').dialog({    
	    	    title: '查看订单详细',    
	    	    width: 700,    
	    	    height: 480,    
	    	    closed: false,    
	    	    cache: false,    
	    	    href: ctx+'goodOrderView.html?id='+id,    
	    	    modal: true   
	    	});    
	    	$('#dd').dialog('refresh', ctx+'goodOrderView.html?id='+id);  
	});*/
});

//查询
function selectGoodsOrder(){
	
	$("#goodList").datagrid({
		pagination: true,
		url:ctx+"queryVGoodsOrder.html",
		pageNumber: 1,
		queryParams:{
			assetsAccount:$("#assetsAccount").val(),
			userName:$("#userName").val(),
			idForGto:$("#VbatchId").val(),
		}
	});
	
}
//订单批量审核
function goodsOrderAudit(){
	var row = $('#goodList').datagrid('getChecked');
    if(row.length==0 ){
			$.messager.alert("操作提示", "请先选择一条记录！");
			return ;
		}
    $('#goodsOrderAuditDiv').dialog('open').dialog('setTitle','批量审核');  
    $('#goodsOrderAuditDiv').form('clear');
}
//同意订单
function agreeGoodsOrder(){
	var row = $('#goodList').datagrid('getChecked');
	var str ="";
	for(var i=0;i<row.length;i++){
		str +=row[i].id+",";
	}
	var reason =$("#reason").val();
	reason = encodeURI(reason); 
	reason = encodeURI(reason); 
	$.post(ctx+"agreeOrder.html?ids="+str+"&reason="+reason, 
			   function(data){
		 		if(data=="1"){
		 	$('#goodsOrderAuditDiv').dialog('close');      // close the dialog 
            $('#goodList').datagrid('reload');    // reload the user data
            window.parent.reloadGrid("产品贸易查看"); //刷新产品管理页面
             $.messager.show({
					title : '操作提示',
					msg : "批量同意成功!"
				});
	        }else{
	        	$.messager.alert('批量同意','添加失败，请稍后重试',"info");
	        }
			   });
}
//驳回订单
function rejectGoodsOrder(){
	 var row = $('#goodList').datagrid('getChecked');
       if(row.length==0 ){
			$.messager.alert("操作提示", "请先选择一条记录！");
			return ;
		}
	var str ="";
	for(var i=0;i<row.length;i++){
		str +=row[i].id+",";
	}
	var reason =$("#reason").val();
	reason = encodeURI(reason); 
	reason = encodeURI(reason); 
	$.post(ctx+"disAgreeOrder.html?ids="+str+"&reason="+reason, 
			   function(data){
		 		if(data=="1"){
		 		$('#goodsOrderAuditDiv').dialog('close');      // close the dialog 
                $('#goodList').datagrid('reload');    // reload the user data
                $.messager.show({
					title : '操作提示',
					msg : "批量驳回成功!"
				});
	        }else{
	        	$.messager.alert('批量驳回','添加失败，请稍后重试',"info");
	        }
			   });
}

