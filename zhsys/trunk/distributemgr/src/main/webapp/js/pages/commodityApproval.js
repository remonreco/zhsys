$(function(){
	$("#tCommodityApprovalList").datagrid({
		onLoadSuccess : function () { 
			   $(this).datagrid("fixRownumber"); 
			 },
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
		toolbar : '#tCommodityApprovalbar',
		url:ctx+'queryCommodityApprovalFirst.html',
		columns:[[
		          	{field: 'customer_name', title: '兑换者', width: '100', align: 'center'},  
		            {field: 'exchange_name', title: '兑换物名', width: '100', align: 'center'},
		            {field: 'exchange_num', title: '兑换数量', width: '100', align: 'center' },
		            {field: 'exchange_use_name', title: '兑换使用物', width: '100', align: 'center'},
		            {field: 'use_num', title: '使用数量', width: '100', align: 'center'},
		            {field: 'hold_num', title: '持有量', width: '100', align: 'center'},
		            {field: 'approval_result', title: '审核结果', width: '100', align: 'center'},
		            {field: 'approval_time', title: '审核时间', width: '170', align: 'center'},
		            ]]
		
	});
	

	//设置分页控件
	var p = $("#tCommodityApprovalList").datagrid('getPager');
	$(p).pagination({
		beforePageText: '第',//页数文本框前显示的汉字
		afterPageText: '页    共 {pages} 页',
		displayMsg: '<span class="marrig20">共 {total} 条记录</span>',
		onBeforeRefresh:function(){  
	 		$('#tCommodityApprovalList').datagrid('load');  
	 		return false;
 		} 
	});
});

function selectCommodity(){
	var approvalTimeStart=$("#approvalTimeStart").datebox('getValue');
	var approvalTimeEnd=$("#approvalTimeEnd").datebox('getValue');
	if(approvalTimeStart!="" && approvalTimeEnd!=""){
		if(approvalTimeStart>approvalTimeEnd){
			alert("开始日期不能大于结束日期！");
			return;
		}
	}
	
	$("#tCommodityApprovalList").datagrid({
		pagination: true,
		url:ctx+"queryCommodityApproval.html",
		pageNumber: 1,
		queryParams:{
			customerName:$("#customerName").val(),
			exchangeName:$("#exchangeName").val(),
			approvalTimeStart:approvalTimeStart,
			approvalTimeEnd:approvalTimeEnd,
		}
	});
}

function commodityApproval(){
	$('#commodityApprovalfm').form('submit',{   
	    url : ctx+"doCommodityApproval.html",   
	    success : function(data){
	        if(data=="1"){
                $('#tCommodityApprovalList').datagrid('reload'); 
                $.messager.show({
					title : '操作提示',
					msg : "今日审批完成!",
				});
	        }else{
	        	$.messager.alert('商品审批','审批失败，请稍后重试',"info");
	        }
	    }   
	}); 
	
	$("#tCommodityApprovalList").datagrid({
		pagination: true,
		url:ctx+"queryCommodityApproval.html",
		pageNumber: 1,
		queryParams:{
			approvalresult:"1",//证明是审批完去查询当天的数据
		}
	});
	
}



$.extend($.fn.datagrid.methods, {
	fixRownumber : function (jq) {
		return jq.each(function () {
			var panel = $(this).datagrid("getPanel");
			//获取最后一行的number容器,并拷贝一份
			var clone = $(".datagrid-cell-rownumber", panel).last().clone();
			//由于在某些浏览器里面,是不支持获取隐藏元素的宽度,所以取巧一下
			clone.css({
				"position" : "absolute",
				left : -1000
			}).appendTo("body");
			var width = clone.width("auto").width();
			//默认宽度是25,所以只有大于25的时候才进行fix
			if (width > 25) {
				//多加5个像素,保持一点边距
				$(".datagrid-header-rownumber,.datagrid-cell-rownumber", panel).width(width + 5);
				//修改了宽度之后,需要对容器进行重新计算,所以调用resize
				$(this).datagrid("resize");
				//一些清理工作
				clone.remove();
				clone = null;
			} else {
				//还原成默认状态
				$(".datagrid-header-rownumber,.datagrid-cell-rownumber", panel).removeAttr("style");
			}
		});
	}
});



//下载报表
function downLoad(){
	var approvalTimeStart=$("#approvalTimeStart").datebox('getValue');
	var approvalTimeEnd=$("#approvalTimeEnd").datebox('getValue');
	if(approvalTimeStart!="" && approvalTimeEnd!=""){
		if(approvalTimeStart>approvalTimeEnd){
			alert("开始日期不能大于结束日期！");
			return;
		}
	}
	
	$('#commodityApprovalfm').form('submit',{
		url : ctx + 'downLoadApprovalSuccess.html',//下载当日审批成功的数据
		success : function(data){
	        if(data=="1"){

	        }else{
	        	$.messager.alert('下载','今日审批成功数据数为零',"info");
	        }
	    }   
	});
	
}

//下载兑换信息
function downLoadEx(){
	var approvalTimeStart=$("#approvalTimeStart").datebox('getValue');
	var approvalTimeEnd=$("#approvalTimeEnd").datebox('getValue');
	if(approvalTimeStart!="" && approvalTimeEnd!=""){
		if(approvalTimeStart>approvalTimeEnd){
			alert("开始日期不能大于结束日期！");
			return;
		}
	}
	
	$('#commodityApprovalfm').form('submit',{
		url : ctx + 'downLoadApprovalSuccessEx.html',//下载当日审批成功的兑换数据
		success : function(data){
        if(data=="1"){
        }else{
        	$.messager.alert('兑换信息下载','今日审批成功数据数为零',"info");
        }
    }   
	});
}
