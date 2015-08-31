$(function(){
	$("#thistoryGoodsOrderList").datagrid({
		title:'已发产品管理',
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
		toolbar : '#thistoryGoodsOrderbar',
		url:ctx+'queryHistoryGoodsOrder.html',
		columns:[[
		            {field: 'batchId', title: '批次号', width: '100', align: 'center'},  
		            {field: 'goodsName', title: '产品名称', width: '100', align: 'center'},  
		            {field: 'orderTime', title: '订单有效时间', width: '100', align: 'center' },
		            {field: 'agencyName', title: '经销商名称', width: '100', align: 'center'}, 
		            {field: 'sumd', title: '分发数量', width: '100', align: 'center'},  
		            {field: 'summ', title: '价值', width: '100', align: 'center'},  
		            ]]
	
	
		
	});
	
	//设置分页控件
	var p = $("#thistoryGoodsOrderList").datagrid('getPager');
	$(p).pagination({
		beforePageText: '第',//页数文本框前显示的汉字
		afterPageText: '页    共 {pages} 页',
		displayMsg: '<span class="marrig20">共 {total} 条记录</span>',
		onBeforeRefresh:function(){  
	 		$('#thistoryGoodsOrderList').datagrid('load');  
	 		return false;
 		} 
	});
	$("#searchDetail").bind('click',function(){
		   var row = $('#thistoryGoodsOrderList').datagrid('getSelected');
	       if(row ==null ){
				$.messager.alert("操作提示", "请先选择一条记录！");
				return ;
			}
	       //var id =row.batchId;
	       $("#batchId2").val(row.batchId);
	       $("#goodsName2").val(row.goodsName);
	       $("#orderTime").val(row.orderTime);
	       $("#agencyName").val(row.agencyName);
	       $("#sumd").val(row.sumd);
	       $("#summ").val(row.summ);
	       $('#viewDlg').dialog({    
	    	    title: '查看订单详细',    
	    	    fit : true,
				doSize : true,
				modal : true,
				cache : false,
				resizable : true,
				maximizable : true,
				closed : true,  
	    	    href: ctx+"historyOrderView.html?id="+row.batchId,    
	    	    modal: true, 
	    	});   
	       
//	    	$.post(ctx+"historyOrderView.html?id="+row.batchId,
//	    			
//	    			function(data){
//					//循环输出
//	    		alert(data.rows);
//	    		//altter(data.rows);
//	    		   for(var i=0;i<data.rows.length;i++){
//	    			   alert(data.rows[i]);
//	    		   }
////	    		$(data.rows).each(function(){
////	    			alter($(this).userName)
////	    		});
////					$.each(data.rows,function(i,item){
////						alter(item.userName);
////						alter(item.goodsName);
////					var row =document.getElementById("auth").insertRow(document.getElementById("auth").rows.length);
////				    var cell=row.insertCell(0);
////				    cell.innerHTML=item.userName;
////				    var cell=row.insertCell(1);
////				    cell.innerHTML=item.userName;
////				    var cell=row.insertCell(2);
////				    cell.innerHTML=item.userName;
////				    var cell=row.insertCell(3);
////				    cell.innerHTML=item.userName;	
//						
////					});
//	    			
//	    		
//	    			})	      
	    			$('#viewDlg').dialog("open");
	});
});
function selecthistorygoodsorder(){
	$("#thistoryGoodsOrderList").datagrid({

		pagination: true,
		url:ctx+"queryHistoryGoodsOrder.html",
		pageNumber: 1,
		queryParams:{
			batchId:$("#batchId").val(),
			goodsName:$("#goodsName").val(),
			orderTimeStart:$("#orderTimeStart").datebox('getValue'),
			orderTimeEnd:$("#orderTimeEnd").datebox('getValue'),
		}
	});
}


