$(function(){
	$("#tuserList").datagrid({
		title:'查看经销商',
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
		url:ctx+'queryTuser.html',
		columns:[[
		            {field: 'dealerNum', title: '经销商编号', width: '200', align: 'center'},  
		            {field: 'dealerName', title: '经销商名称', width: '200', align: 'center' },
		            {field: 'userName', title: '经销商账号', width: '200', align: 'center' },
				    {field: 'stringDealerLevel', title: '经销商级别', width: '200', align: 'center'},
				    //{field: 'tel', title: '联系方式', width: '100', align: 'center'},
				    {field: 'higerDealer', title: '所属经销商', width: '200', align: 'center'},
		            ]]
		
	});
	

	//设置分页控件
	var p = $("#tuserList").datagrid('getPager');
	$(p).pagination({
		beforePageText: '第',//页数文本框前显示的汉字
		afterPageText: '页    共 {pages} 页',
		displayMsg: '<span class="marrig20">共 {total} 条记录</span>',
		onBeforeRefresh:function(){  
	 		$('#tuserList').datagrid('load');  
	 		return false;
 		} 
	});
});
function selectdealer(){
	$("#tuserList").datagrid({
		pagination: true,
		url:ctx+"queryTuser.html",
		pageNumber: 1,
		queryParams:{
			dealerNum:$("#dealerNum").val(),
			dealerName:$("#dealerName").val(),
		}
	});
}

function addDealer(){  
    $('#addDealerDiv').dialog('open').dialog('setTitle','添加经销商');  
    $('#addDealerFm').form('clear');
} 




function saveDealer(){
	$('#addDealerFm').form('submit',{   
	    url:$("#addDealerUrl").val(),   
	    onSubmit: function(){   
	       return $(this).form('validate')&&checkName()&&$('#addDealerDiv').dialog('close');      
	    },   
	    success:function(data){
	        if(data=="1"){
                $('#tuserList').datagrid('reload');    // reload the user data
                $.messager.show({
					title : '操作提示',
					msg : "添加成功!"
				});
	        }else{
	        	$.messager.alert('添加经销商','添加失败，请稍后重试',"info");
	        }
	    }   
	}); 
}

function checkName(){
	var result = "";
	$.ajax({
		type: 'POST',
		url: $("#checkTAgencyBydealerNumAndName").val(),
		data: {
			dealerNum2: $("#dealerNum2").val(),
			dealerName2: $("#dealerName2").val()
		},
		dataType: 'text',
		async: false,
		cache : false,
		traditional: true,
		success: function(data) {
			result = data;
		}
	});
	if(result.indexOf("1") >= 0){
		$.messager.alert('添加账号','经销商已存在','info'); 
		return false;
	}
	return true;
}

function checkName2(){
	var result = "";
	$.ajax({
		type: 'POST',
		url: $("#checkTAgencyBydealerNumAndName").val(),
		data: {
			id:$("#editDealerDiv #id").val(),
			dealerNum2: $("#dealerNum3").val(),
			dealerName2: $("#dealerName3").val(),
		},
		dataType: 'text',
		async: false,
		cache : false,
		traditional: true,
		success: function(data) {
			result = data;
		}
	});
	if(result.indexOf("1") >= 0){
		$.messager.alert('编辑经销商','经销商已存在','info'); 
		return false;
	}
	return true;
}


function toUpdateDealer(){
	var row = $('#tuserList').datagrid('getSelected'); 
    if (row){  
    	 $("#editDealerDiv #id").val(row.id);
    	 $("#editDealerDiv #dealerName3").val(row.dealerName);
    	 $("#editDealerDiv #userName3").val(row.userName);
    	 $("#editDealerDiv #dealerNum3").val(row.dealerNum);
        $('#editDealerDiv').dialog('open').dialog('setTitle','修改经销商');  
    }else{
    	$.messager.alert('修改经销商','请先选择要修改的经销商信息','info'); 
    }
}


function editDealer(){
	$('#editDealerFm').form('submit',{   
	    url:$("#editDealerUrl").val(),   
	    onSubmit: function(){   
	       return $(this).form('validate')&&checkName2()&&$('#editDealerDiv').dialog('close');    
	    },   
	    success:function(data){
	        if(data=="1"){
	        	
                $('#tuserList').datagrid('reload');    // reload the user data
                $.messager.show({
					title : '操作提示',
					msg : "编辑成功!"
				});
	        }else{
	        	$.messager.alert('编辑经销商','编辑失败，请稍后重试',"info");
	        }
	    }   
	}); 
}