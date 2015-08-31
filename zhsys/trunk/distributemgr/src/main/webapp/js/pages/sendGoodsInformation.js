$(function(){
	$("#sendGoodsList").datagrid({
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
		toolbar : '#sendGoodsbar',
		selectOnCheck :true,
		ctrlSelect:true,
		singleSelect: false,
		url:ctx+'queryLowerUser.html',
		columns:[[
		            {field: 'id', title: 'id', width: '100', align: 'center',checkbox:true},  
		            {field: 'name', title: '下级人员名称', width: '200', align: 'center'},  
				    {field: 'tel', title: '联系方式', width: '100', align: 'center'},
				    {field: 'higerAgency', title: '所属经销商', width: '100', align: 'center'},
				    {field: 'userIdenty', title: '下级人员标识', width: '100', align: 'center'},  
		            ]]
		
	});
	
	//导出所选数据信息
	$("#loadOutUsersGoods").click(function() {
		 var row = $('#sendGoodsList').datagrid('getChecked');
	       if(row.length==0 ){
				$.messager.alert("操作提示", "请先选择一条记录！");
				return ;
			}
		var str ="";
		for(var i=0;i<row.length;i++){
			str +=row[i].id+",";
		}
		   $("#ids").val(str);
			$('#sendGoodsfm').form('submit',{  
				url : $("#loadoutTuserAndGoodsUrl").val()
			});
	});
	//导出所有数据信息
	$("#loadOutAllUsersGoods").click(function() {
			$('#sendGoodsfm').form('submit',{  
				url : $("#loadoutTuserAndGoodsUrl").val()
			});
	});

	//设置分页控件
	var p = $("#sendGoodsList").datagrid('getPager');
	$(p).pagination({
		beforePageText: '第',//页数文本框前显示的汉字
		afterPageText: '页    共 {pages} 页',
		displayMsg: '<span class="marrig20">共 {total} 条记录</span>',
		onBeforeRefresh:function(){  
	 		$('#sendGoodsList').datagrid('load');  
	 		return false;
 		} 
	});
});

function upSendGoodsByUsers(){
	window.location=ctx+"toUpSendGoodsByUsers.html";
};

