$(function(){
	$("#tgoodsList").datagrid({
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
		toolbar : '#tgoodsbar',
		url:ctx+'queryGoods.html',
		columns:[[
		          	{field: 'id', title: '产品编号', width: '100', align: 'center',hidden:true},  
		            {field: 'goodsNum', title: '产品编号', width: '100', align: 'center'},  
		            {field: 'goodsName', title: '产品名称', width: '100', align: 'center' },
		            {field: 'goodsPriceStr', title: '产品价格', width: '100', align: 'center'},
		            {field: 'goodsCount', title: '分发数量', width: '100', align: 'center'},
		            {field: 'remainderGoodsCount', title: '剩余产品数量', width: '100', align: 'center'},
		            ]]
		
	});
	

	//设置分页控件
	var p = $("#tgoodsList").datagrid('getPager');
	$(p).pagination({
		beforePageText: '第',//页数文本框前显示的汉字
		afterPageText: '页    共 {pages} 页',
		displayMsg: '<span class="marrig20">共 {total} 条记录</span>',
		onBeforeRefresh:function(){  
	 		$('#tgoodsList').datagrid('load');  
	 		return false;
 		} 
	});
});
function selectgoods(){
	$("#tgoodsList").datagrid({
		pagination: true,
		url:ctx+"queryGoods.html",
		pageNumber: 1,
		queryParams:{
			goodsNum:$("#goodsNum3").val(),
			goodsName:$("#goodsName3").val(),
		}
	});
}

function addgoods(){  
    $('#addgoodsDiv').dialog('open').dialog('setTitle','添加产品');  
    $('#addgoodsFm').form('clear');
} 




function savegoods(){
	//货物编号验证是否重复
		var result = "";
	$('#addgoodsFm').form('submit',{   
	    url:$("#addgoodsUrl").val(),   
	    onSubmit: function(){   
	       return $(this).form('validate')&&checkName()&&$('#addgoodsDiv').dialog('close');
	    },   
	    success:function(data){
	        if(data=="1"){
                $('#tgoodsList').datagrid('reload');    // reload the goods data
                $.messager.show({
					title : '操作提示',
					msg : "添加成功!"
				});
	        }else{
	        	$.messager.alert('添加产品','添加失败，请稍后重试',"info");
	        }
	    }   
	}); 
}

function delgoods(){
	var row = $('#tgoodsList').datagrid('getSelected');
	if(row ==null ){
		$.messager.alert("操作提示", "请先选择一条记录");
		return ;
	}
	$('#goodsfm').form('submit',{  
		url : ctx + 'deleteGoods.html?id='+row.id,
		success : function(data) {
			if (data == 1) {
				$('#tgoodsList').datagrid('reload');  
				$('#tgoodsbar').dialog('close'); 
				$.messager.show({
					title : '操作提示',
					msg : "删除产品信息操作成功!",
				});
			} else if(data == 0){
				$.messager.alert("操作提示", "该产品已下发，不能删除！", "info");
			}else{
				$.messager.alert("操作提示", "删除产品信息失败，请稍后修改！", "info");
			}
		}
     });
}

function checkName(){
	var result = "";
	$.ajax({
		type: 'POST',
		url: $("#checkTgoodsBygoodsNumAndName").val(),
		data: {
			goodsNum2: $("#goodsNum2").val()
			//goodsName2: $("#goodsName2").val()
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
		$.messager.alert('添加账号','产品已存在','info'); 
		return false;
	}
	return true;
}


function toUpdateUser(){
	var row = $('#tgoodsList').datagrid('getSelected');
	//判断产品基本信息录入中发货数量是否和剩余产品数量相等
	//防止两用户同时操作同一条数据
	var result="";
	$.ajax({
		type: 'POST',
		url: $("#checkTgoodsNumEqualsTgoodsCount").val(),
		data: {
			id: row.id
		},
		dataType: 'text',
		async: false,
		cache : false,
		traditional: true,
		success: function(data) {
			result = data;
		}
	});
	if(result.indexOf("1")>=1){
		$.messager.alert('修改产品','产品已分发不能修改数量！','info'); 
		return false;
	}
	else{
	
	if (row){  
    	 $("#updategoodsFm #id").val(row.id);
    	 $("#updategoodsFm #goodsNum").val(row.goodsNum);
    	 $("#updategoodsFm #goodsName").val(row.goodsName);
    	 $("#updategoodsFm #goodsPrice").val(row.goodsPriceStr);
        $('#updategoodsDiv').dialog('open').dialog('setTitle','修改产品');  
    }else{
    	$.messager.alert('修改产品信息','请先选择要修改的产品信息','info'); 
    }
	}
}


function updategoods(){
	$('#updategoodsFm').form('submit',{   
		url:$("#updateGoodsInformation").val(),
		onSubmit: function(){   
		       return $(this).form('validate')&&checkName()&&$('#addgoodsDiv').dialog('close');
		    },
		success:function(data){
			if(data=="1"){
				$('#updategoodsDiv').dialog('close');      // close the dialog  
				$('#tgoodsList').datagrid('reload');    // reload the user data 
				$.messager.show({
					title : '操作提示',
					msg : "修改成功!"
				});
			}
			else if(data=="-2"){
				$.messager.alert('修改产品','产品已存在！','info'); 
				return false;
			}
			
			else{
				$.messager.alert('修改产品信息','修改失败，请稍后重试','info');
			}
		}   
	}); 
}
function toSendGoods(){
	var row = $("#tgoodsList").datagrid("getSelected");
	if(row==""||row==null){
		$.messager.alert("操作提示", "请选择要发货的数据！");
		return;
	}
	
	goodsName = encodeURI(encodeURI(row.goodsName));   
	goodsName = encodeURI(encodeURI(row.goodsName)); 
	window.parent.open1("发货信息页","toSendGoods.html?goodsNum="+row.goodsNum+"&goodsName="+goodsName);
	
}