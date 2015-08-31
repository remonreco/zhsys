$(function(){
	$("#sonSysList").datagrid({
		title:'查看产品',
		fit:true,
		autoRowheight:true,
		remoteSort:false,
		singleSelect:true,
		showFooter:true,
		pagination:true,
		pagePostion:'bottom',
		pageNumer: 1,
		pageSize: 50,// //每页显示的记录条数，默认为50
		pageList: [50,100,300,500],// 可以设置每页记录条数的列表
		rownumbers: true,
		toolbar : '#tgoodsbar',
		url:ctx+'querySonSys.html',
		columns:[[
		          	{field: 'sys_id', title: '系统编号', width: '100', align: 'center'},  
		            {field: 'sys_name', title: '系统名称', width: '100', align: 'center'},  
		            {field: 'edit_time', title: '录入时间', width: '200', align: 'center' },
		            {field: 'schedule_time', title: '定时任务时间', width: '200',align: 'center'},
		            ]]
		
	});
	

	// 设置分页控件
	var p = $("#tgoodsList").datagrid('getPager');
	$(p).pagination({
		beforePageText: '第',// 页数文本框前显示的汉字
		afterPageText: '页    共 {pages} 页',
		displayMsg: '<span class="marrig20">共 {total} 条记录</span>',
		onBeforeRefresh:function(){  
	 		$('#tgoodsList').datagrid('load');  
	 		return false;
 		} 
	});
});



$.extend($.fn.datagrid.methods, {
	fixRownumber : function(jq) {
		return jq.each(function() {
			var panel = $(this).datagrid("getPanel");
			// 获取最后一行的number容器,并拷贝一份
			var clone = $(".datagrid-cell-rownumber", panel).last().clone();
			// 由于在某些浏览器里面,是不支持获取隐藏元素的宽度,所以取巧一下
			clone.css({
				"position" : "absolute",
				left : -1000
			}).appendTo("body");
			var width = clone.width("auto").width();
			// 默认宽度是25,所以只有大于25的时候才进行fix
			if (width > 25) {
				// 多加5个像素,保持一点边距
				$(".datagrid-header-rownumber,.datagrid-cell-rownumber", panel)
						.width(width + 5);
				// 修改了宽度之后,需要对容器进行重新计算,所以调用resize
				$(this).datagrid("resize");
				// 一些清理工作
				clone.remove();
				clone = null;
			} else {
				// 还原成默认状态
				$(".datagrid-header-rownumber,.datagrid-cell-rownumber", panel)
						.removeAttr("style");
			}
		});
	}
});

function selectSonSys(){
	$("#sonSysList").datagrid({
		pagination: true,
		url:ctx+"querySonSys.html",
		pageNumber: 1,
		queryParams:{
			sysId:$("#sysId1").val(),
			sysName:$("#sysName1").val(),
		}
	});
}

function addSonSys(){  
	document.getElementById("ssSpan").style.color="gray";
	document.getElementById("sysName2Span").innerHTML="";
    $('#addSonSysDiv').dialog('open').dialog('setTitle','添加子系统');  
    $('#addSonSysFm').form('clear');
} 




function saveSonSys(){
	
	var sysName=$("#sysName2").val();
	// var approvalTime=$("#approvalTime2").datebox('getValue');
	var scheduleTime=$("#ss").val();
	
	var valitime=/^((1|0?)[0-9]|2[0-3]):([0-5][0-9])$/;
	
	if(!valitime.test(scheduleTime)){
		document.getElementById("ssSpan").style.color="red";
		return;
	}
	var str=document.getElementById("sysName2Span").innerHTML;
	if(str!=""){
		return;
	}
		$.ajax({
			type : "POST",
			url : ctx + "addSonSys.html",
			cache : false,
			contentType : "application/json",
			dataType : "json",
			data : JSON.stringify({
				'sysName':sysName,
				'scheduleTime':scheduleTime,
			}),

			success : function(data) {
				var JsonObject = eval(data);
				if(JsonObject[0].success=="0"){
					$('#addSonSysDiv').dialog('close');      // close the
																// dialog
					$('#sonSysList').datagrid('reload');    // reload the user
															// data
					$.messager.show({
						title : '操作提示',
						msg : "添加成功!"
					});

				}
				if(JsonObject[0].error=="0"){
					$.messager.alert('添加产品','商品添加子系统失败，请重新添加！','info'); 
					return false;
				}
			}
		});
}

function timeS(){
	document.getElementById("ssSpan").style.color="gray";
}

function delSys(){
	var row = $('#sonSysList').datagrid('getSelected');
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

function validsysName(obj){
	var va=obj.id;
	var str="sysName2Span";
	var id=document.getElementById(va).value;
	
	if(id==""){
		document.getElementById(str).innerHTML="请输入系统名称！";
		return;
	}
	
	$.ajax({
		type : "POST",
		url : ctx + "isSysNameUseful.html",
		cache : false,
		contentType : "application/json",
		dataType : "json",
		data : JSON.stringify({
			'sysName' : id
		}),

		success : function(data){
			var JsonObject = eval(data);
			if(JsonObject[0].error=="0"){
				document.getElementById(str).innerHTML="该系统名称已存在，不能重复添加！";
			}
			if(JsonObject[0].success=="0"){
				document.getElementById(str).innerHTML="";
			}
		}
	});
	
}


function toUpdateSys(){
	var row = $('#sonSysList').datagrid('getSelected');
	if(row ==null ){
		$.messager.alert("操作提示", "请先选择一条记录！");
		return ;
	}
	
	document.getElementById("sysName3").value=row.sys_name;
	document.getElementById("sysId3").value=row.sys_id;
	document.getElementById("sysName4").value=row.sys_name;
	$('#updateSonSysDiv').dialog('open').dialog('setTitle','修改系统信息');  
}

function updateSonSys(){
	
	var sysName=document.getElementById("sysName3").value;
	var sysId=document.getElementById("sysId3").value;
	var flag=valSysName();
	alert(flag);
	var str=document.getElementById("sysName3Span").innerHTML;
	if(str!=""){
		return;
	}
	
		$.ajax({
			type : "POST",
			url : ctx + "updateSonSys.html",
			cache : false,
			contentType : "application/json",
			dataType : "json",
			data : JSON.stringify({
				'sysName' : sysName,
				'sysId':sysId
			}),

			success : function(data){
				var JsonObject = eval(data);
				if(JsonObject[0].error=="0"){
					$.messager.alert('修改系统信息','修改失败，请重新确认！','info'); 
					return false;
				}
				if(JsonObject[0].success=="0"){
					$('#updateSonSysDiv').dialog('close');      // close the
																// dialog
					$('#sonSysList').datagrid('reload');    // reload the
															// user data
					$.messager.show({
						title : '操作提示',
						msg : "修改成功!"
					});
				}
			}
		});
	
}



function valSysName(){
	
	var sysName=document.getElementById("sysName3").value;
	var sysName4=document.getElementById("sysName4").value;
	var flag=true;
	if(sysName!=sysName4){
		$.ajax({
			type : "POST",
			url : ctx + "isSysNameUseful.html",
			cache : false,
			async:false,
			contentType : "application/json",
			dataType : "json",
			data : JSON.stringify({
				'sysName' : sysName,
			}),

			success : function(data){
				var JsonObject = eval(data);
				if(JsonObject[0].error=="0"){
					document.getElementById("sysName3Span").innerHTML="该系统名称已存在，不能重复添加！";
					flag=false;
				}
				if(JsonObject[0].success=="0"){
					document.getElementById("sysName3Span").innerHTML="";
					flag=true;
				}
			}
		});
	}
	
	return flag;
	
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


$('#ss').timespinner({
	showDates:false,
	showHours:true,
	showMinutes:true
	}); 