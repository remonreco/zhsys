$(function() {
	$("#tCommodityList").datagrid({
		onLoadSuccess : function() {
			$(this).datagrid("fixRownumber");
		},
		title : '查看产品',
		fit : true,
		autoRowheight : true,
		remoteSort : false,
		singleSelect : true,
		showFooter : true,
		pagination : true,
		pagePostion : 'bottom',
		pageNumer : 1,
		pageSize : 50,// //每页显示的记录条数，默认为50
		pageList : [ 50, 100, 300, 500 ],// 可以设置每页记录条数的列表
		rownumbers : true,
		toolbar : '#tCommoditybar',
		url : ctx + 'queryCommodity.html',
		columns : [ [ {
			field : 'com_id',
			title : '品种代码',
			width : '100',
			align : 'center'
		}, {
			field : 'com_name',
			title : '品种名称',
			width : '100',
			align : 'center'
		}, {
			field : 'item_name',
			title : '项目名称',
			width : '100',
			align : 'center'
		}, {
			field : 'currency_num',
			title : '流通数量',
			width : '100',
			align : 'center'
		}, {
			field : 'exchange_state',
			title : '兑换状态',
			width : '100',
			align : 'center'
		}, {
			field : 'min_num',
			title : '最小交换数',
			width : '100',
			align : 'center'
		}, {
			field : 'start_date',
			title : '兑换开始日',
			width : '100',
			align : 'center'
		}, {
			field : 'end_date',
			title : '兑换结束日',
			width : '100',
			align : 'center'
		}, ] ]

	});

	// 设置分页控件
	var p = $("#tCommodityList").datagrid('getPager');
	$(p).pagination({
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '<span class="marrig20">共 {total} 条记录</span>',
		onBeforeRefresh : function() {
			$('#tCommodityList').datagrid('load');
			return false;
		}
	});
	
	$.extend($.fn.validatebox.defaults.rules,{
	    //数字正整数验证
		numberM:{
				validator:function(value) {
				return /^\d+$/.test(value);
			},message: "该数字不能为小数"
		},

		numberN:{
		 		validator:function(value) {
		 		return /^\d+(\.{0,1}\d+){0,1}$/.test(value);
		 	},message: "必须为数字型"
		}
	     
	});

	// 下拉框控件
	$("#itemNamecbb0").combobox({
		url : ctx + 'loadItemName.html',
		editable : false, // 不可编辑状态
		cache : false,
		contentType : "application/json",
		valueField : 'itemName',
		textField : 'itemName',

		onSelect : function() {
			$("#comNamecbb0").combobox("setValue", '');// 清空课程
			var id = $('#itemNamecbb0').combobox('getValue');
			$.ajax({
				type : "POST",
				url : ctx + "loadComName.html",
				cache : false,
				contentType : "application/json",
				dataType : "json",
				data : JSON.stringify({
					'id' : id
				}),

				success : function(data) {
					var JsonObject = eval(data);
					$("#comNamecbb0").combobox({
						dataType : "json",
						contentType : "application/json",
						editable : false, // 不可编辑状态
						cache : false,
						valueField : 'comName',
						textField : 'comName',
						
						onSelect : function() {
							var id0 = $('#comNamecbb0').combobox('getValue');
							var id1 = $('#comNamecbb1').combobox('getValue');
							var id2 = $('#comNamecbb2').combobox('getValue');
							var id3 = $('#comNamecbb3').combobox('getValue');
							var id4 = $('#comNamecbb4').combobox('getValue');
							if(id0==id1){
								alert("该品种名称已被选择，不能重复选择");
								$("#comNamecbb0").combobox("setValue", '');// 清空课程
								return;
							}
							if(id0==id2){
								alert("该品种名称已被选择，不能重复选择");
								$("#comNamecbb0").combobox("setValue", '');// 清空课程
								return;
							}
							if(id0==id3){
								alert("该品种名称已被选择，不能重复选择");
								$("#comNamecbb0").combobox("setValue", '');// 清空课程
								return;
							}
							if(id0==id4){
								alert("该品种名称已被选择，不能重复选择");
								$("#comNamecbb0").combobox("setValue", '');// 清空课程
								return;
							}
						}
						
					});

					$('#comNamecbb0').combobox('loadData', JsonObject);

				}
			});

		}
	});

	// 下拉框控件
	$("#itemNamecbb1").combobox({
		url : ctx + 'loadItemName.html',
		editable : false, // 不可编辑状态
		cache : false,
		contentType : "application/json",
		valueField : 'itemName',
		textField : 'itemName',

		onSelect : function() {
			$("#comNamecbb1").combobox("setValue", '');// 清空课程
			var id = $('#itemNamecbb1').combobox('getValue');
			$.ajax({
				type : "POST",
				url : ctx + "loadComName.html",
				cache : false,
				contentType : "application/json",
				dataType : "json",
				data : JSON.stringify({
					'id' : id
				}),

				success : function(data) {
					var JsonObject = eval(data);
					$("#comNamecbb1").combobox({
						dataType : "json",
						contentType : "application/json",
						editable : false, // 不可编辑状态
						cache : false,
						valueField : 'comName',
						textField : 'comName',
						
						
						onSelect : function() {
							var id0 = $('#comNamecbb0').combobox('getValue');
							var id1 = $('#comNamecbb1').combobox('getValue');
							var id2 = $('#comNamecbb2').combobox('getValue');
							var id3 = $('#comNamecbb3').combobox('getValue');
							var id4 = $('#comNamecbb4').combobox('getValue');
							if(id1==id0){
								alert("该品种名称已被选择，不能重复选择");
								$("#comNamecbb1").combobox("setValue", '');// 清空课程
								return;
							}
							if(id1==id2){
								alert("该品种名称已被选择，不能重复选择");
								$("#comNamecbb1").combobox("setValue", '');// 清空课程
								return;
							}
							if(id1==id3){
								alert("该品种名称已被选择，不能重复选择");
								$("#comNamecbb1").combobox("setValue", '');// 清空课程
								return;
							}
							if(id1==id4){
								alert("该品种名称已被选择，不能重复选择");
								$("#comNamecbb1").combobox("setValue", '');// 清空课程
								return;
							}
						}
					});

					$('#comNamecbb1').combobox('loadData', JsonObject);

				}
			});

		}
	});

	// 下拉框控件
	$("#itemNamecbb2").combobox({
		url : ctx + 'loadItemName.html',
		editable : false, // 不可编辑状态
		cache : false,
		contentType : "application/json",
		valueField : 'itemName',
		textField : 'itemName',

		onSelect : function() {
			$("#comNamecbb2").combobox("setValue", '');// 清空课程
			var id = $('#itemNamecbb2').combobox('getValue');
			$.ajax({
				type : "POST",
				url : ctx + "loadComName.html",
				cache : false,
				contentType : "application/json",
				dataType : "json",
				data : JSON.stringify({
					'id' : id
				}),

				success : function(data) {
					var JsonObject = eval(data);
					$("#comNamecbb2").combobox({
						dataType : "json",
						contentType : "application/json",
						editable : false, // 不可编辑状态
						cache : false,
						valueField : 'comName',
						textField : 'comName',
						
						onSelect : function() {
							var id0 = $('#comNamecbb0').combobox('getValue');
							var id1 = $('#comNamecbb1').combobox('getValue');
							var id2 = $('#comNamecbb2').combobox('getValue');
							var id3 = $('#comNamecbb3').combobox('getValue');
							var id4 = $('#comNamecbb4').combobox('getValue');
							if(id2==id0){
								alert("该品种名称已被选择，不能重复选择");
								$("#comNamecbb2").combobox("setValue", '');// 清空课程
								return;
							}
							if(id2==id1){
								alert("该品种名称已被选择，不能重复选择");
								$("#comNamecbb2").combobox("setValue", '');// 清空课程
								return;
							}
							if(id2==id3){
								alert("该品种名称已被选择，不能重复选择");
								$("#comNamecbb2").combobox("setValue", '');// 清空课程
								return;
							}
							if(id2==id4){
								alert("该品种名称已被选择，不能重复选择");
								$("#comNamecbb2").combobox("setValue", '');// 清空课程
								return;
							}
						}
					});

					$('#comNamecbb2').combobox('loadData', JsonObject);

				}
			});

		}
	});

	// 下拉框控件
	$("#itemNamecbb3").combobox({
		url : ctx + 'loadItemName.html',
		editable : false, // 不可编辑状态
		cache : false,
		contentType : "application/json",
		valueField : 'itemName',
		textField : 'itemName',

		onSelect : function() {
			$("#comNamecbb3").combobox("setValue", '');// 清空课程
			var id = $('#itemNamecbb3').combobox('getValue');
			$.ajax({
				type : "POST",
				url : ctx + "loadComName.html",
				cache : false,
				contentType : "application/json",
				dataType : "json",
				data : JSON.stringify({
					'id' : id
				}),

				success : function(data) {
					var JsonObject = eval(data);
					$("#comNamecbb3").combobox({
						dataType : "json",
						contentType : "application/json",
						editable : false, // 不可编辑状态
						cache : false,
						valueField : 'comName',
						textField : 'comName',
						
						
						onSelect : function() {
							var id0 = $('#comNamecbb0').combobox('getValue');
							var id1 = $('#comNamecbb1').combobox('getValue');
							var id2 = $('#comNamecbb2').combobox('getValue');
							var id3 = $('#comNamecbb3').combobox('getValue');
							var id4 = $('#comNamecbb4').combobox('getValue');
							if(id3==id0){
								alert("该品种名称已被选择，不能重复选择");
								$("#comNamecbb3").combobox("setValue", '');
								return;
							}
							if(id3==id1){
								alert("该品种名称已被选择，不能重复选择");
								$("#comNamecbb3").combobox("setValue", '');
								return;
							}
							if(id3==id2){
								alert("该品种名称已被选择，不能重复选择");
								$("#comNamecbb3").combobox("setValue", '');
								return;
							}
							if(id3==id4){
								alert("该品种名称已被选择，不能重复选择");
								$("#comNamecbb3").combobox("setValue", '');
								return;
							}
						}
					});

					$('#comNamecbb3').combobox('loadData', JsonObject);

				}
			});

		}
	});

	// 下拉框控件
	$("#itemNamecbb4").combobox({
		url : ctx + 'loadItemName.html',
		editable : false, // 不可编辑状态
		cache : false,
		contentType : "application/json",
		valueField : 'itemName',
		textField : 'itemName',

		onSelect : function() {
			$("#comNamecbb4").combobox("setValue", '');// 清空课程
			var id = $('#itemNamecbb4').combobox('getValue');
			$.ajax({
				type : "POST",
				url : ctx + "loadComName.html",
				cache : false,
				contentType : "application/json",
				dataType : "json",
				data : JSON.stringify({
					'id' : id
				}),

				success : function(data) {
					var JsonObject = eval(data);
					$("#comNamecbb4").combobox({
						dataType : "json",
						contentType : "application/json",
						editable : false, // 不可编辑状态
						cache : false,
						valueField : 'comName',
						textField : 'comName',
						
						onSelect : function() {
							var id0 = $('#comNamecbb0').combobox('getValue');
							var id1 = $('#comNamecbb1').combobox('getValue');
							var id2 = $('#comNamecbb2').combobox('getValue');
							var id3 = $('#comNamecbb3').combobox('getValue');
							var id4 = $('#comNamecbb4').combobox('getValue');
							if(id4==id0){
								alert("该品种名称已被选择，不能重复选择");
								$("#comNamecbb4").combobox("setValue", '');// 清空课程
								return;
							}
							if(id4==id1){
								alert("该品种名称已被选择，不能重复选择");
								$("#comNamecbb4").combobox("setValue", '');// 清空课程
								return;
							}
							if(id4==id2){
								alert("该品种名称已被选择，不能重复选择");
								$("#comNamecbb4").combobox("setValue", '');// 清空课程
								return;
							}
							if(id4==id3){
								alert("该品种名称已被选择，不能重复选择");
								$("#comNamecbb4").combobox("setValue", '');// 清空课程
								return;
							}
						}
					});

					$('#comNamecbb4').combobox('loadData', JsonObject);

				}
			});

		}
	});

});

function selectCommodity() {
	$("#tCommodityList").datagrid({
		pagination : true,
		url : ctx + "queryCommodity.html",
		pageNumber : 1,
		queryParams : {
			comId : $("#comId").val(),
			comName : $("#comName").val(),
		}
	});
}

var fileNum = 0;
function addCommodity() {
	$('#addCommodityDiv').dialog('open').dialog('setTitle', '添加产品');
	$('#addCommodityFm').form('clear');
	fileNum = 0;
	imgNumdisabled();
	$("#div88 div div input").parent().parent().remove();

	for (var i = 1; i <= count; i++) {
		$('#comRole' + i).remove();
	}
	count = 0;
}

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

function saveCommodity() {
	
	var comName=$("#comNamei").val();
	var comId=$("#comIdi").val();
	var itemName=$("#itemName").val();
	var listOrganization=$("#listOrganization").val();
	var saleUnit=$("#saleUnit").val();
	var salePrice=$("#salePrice").val();
	var saleNum=$("#saleNum").val();
	var saleMoney=$("#saleMoney").val();
	var currencyNum=$("#currencyNum").val();
	var minNum=$("#minNum").val();
	var startDate=$("#startDate").datebox('getValue');
	var endDate=$("#endDate").datebox('getValue');
	var fileName=$("#fileName").val();
	if(comName==""||comId==""||itemName==""||listOrganization==""||saleUnit==""||salePrice==""||saleNum==""||saleMoney==""||currencyNum==""||minNum==""||fileNum==0){
		$.messager.alert('添加商品','您还有未填写的内容，请填写完整',"info");
		return;
	}
	
	if(startDate!="" && endDate!=""){
		if(startDate>endDate){
			alert("开始日期不能大于结束日期！");
			return;
		}
	}
	
	var introduction=$("#introduction").val();
	
	var comNamecbb0 = $('#comNamecbb0').combobox('getValue');
	var amount0=$("#amount0").val();
	if(document.getElementById("isGift0").checked){
		var isGift0="2";
	}else{
		isGift0="1";
	}
	
	var comNamecbb1 = $('#comNamecbb1').combobox('getValue');
	var amount1=$("#amount1").val();
	if(document.getElementById("isGift1").checked){
		var isGift1="2";
	}else{
		isGift1="1";
	}
	
	var comNamecbb2 = $('#comNamecbb2').combobox('getValue');
	var amount2=$("#amount2").val();
	if(document.getElementById("isGift2").checked){
		var isGift2="2";
	}else{
		isGift2="1";
	}
	
	var comNamecbb3 = $('#comNamecbb3').combobox('getValue');
	var amount3=$("#amount3").val();
	if(document.getElementById("isGift3").checked){
		var isGift3="2";
	}else{
		isGift3="1";
	}
	
	var comNamecbb4 = $('#comNamecbb4').combobox('getValue');
	var amount4=$("#amount4").val();
	if(document.getElementById("isGift4").checked){
		var isGift4="2";
	}else{
		isGift4="1";
	}
	
	if(!isLeagle(comNamecbb0,amount0,isGift0)||!isLeagle(comNamecbb1,amount1,isGift1)||!isLeagle(comNamecbb2,amount2,isGift2)||!isLeagle(comNamecbb3,amount3,isGift3)||!isLeagle(comNamecbb4,amount4,isGift4)){
		return;
	}
	

	
	
	$.ajax({
		type : "POST",
		url : ctx + "addCommodityGoods.html",
		cache : false,
		contentType : "application/json",
		dataType : "json",
		data : JSON.stringify({
			'comName':comName,
			'comId':comId,
			'itemName':itemName,
			'listOrganization':listOrganization,
			'saleUnit':saleUnit,
			'salePrice':salePrice,
			'saleNum':saleNum,
			'saleMoney':saleMoney,
			'currencyNum':currencyNum,
			'minNum':minNum,
			'startDate':startDate,
			'endDate':endDate,
			'introduction':introduction,
			'comNamecbb0' :comNamecbb0,
			'amount0':amount0,
			'isGift0':isGift0,
			'comNamecbb1' :comNamecbb1,
			'amount1':amount1,
			'isGift1':isGift1,
			'comNamecbb2' :comNamecbb2,
			'amount2':amount2,
			'isGift2':isGift2,
			'comNamecbb3' :comNamecbb3,
			'amount3':amount3,
			'isGift3':isGift3,
			'comNamecbb4' :comNamecbb4,
			'amount4':amount4,
			'isGift4':isGift4,
			'fileName':fileName
		}),

		success : function(data) {
			var JsonObject = eval(data);
			if(JsonObject[0].success=="0"){
				$('#addCommodityDiv').dialog('close');      // close the dialog  
				$('#tCommodityList').datagrid('reload');    // reload the user data 
				$.messager.show({
					title : '操作提示',
					msg : "添加成功!"
				});

			}
			if(JsonObject[0].error=="0"){
				$.messager.alert('添加产品','商品添加失败，请重新添加！','info'); 
				return false;
			}
		}
	});
}

function isLeagle(comNamecbb,amount,isGift){
	if(comNamecbb==null||comNamecbb==("")){
		if(amount!=("")){
			alert("未选择兑换商品不能填写数量！");
			return false;
		}
		if(isGift==("2")){
			alert("未选择兑换商品不能选择是否为赠品！");
			return false;
		}
		
	}else{
		if(amount==("")){
			alert("兑换数量不能为空！");
			return false;
		}
	}
	
	return true;
}

function validcomId(obj){
	var va=obj.id;
	var id=document.getElementById(va).value;
	$.ajax({
		type : "POST",
		url : ctx + "isComIdUseful.html",
		cache : false,
		contentType : "application/json",
		dataType : "json",
		data : JSON.stringify({
			'comId' : id
		}),

		success : function(data){
			var JsonObject = eval(data);
			if(JsonObject[0].error=="0"){
				document.getElementById("comIdiSpan").innerHTML="该品种代码已被占用，请更换！";
			}
			if(JsonObject[0].success=="0"){
				document.getElementById("comIdiSpan").innerHTML="";
			}
		}
	});
}


function validcomName(obj){
	var va=obj.id;
	var str="comNameiSpan";
	valicomName(va,str);
}

function validUpcomName(obj){
	var va=obj.id;
	var str="upComNameiSpan";
	if(document.getElementById(va).value!=document.getElementById("upComNameiHide").value){
		valicomName(va,str);
	}
	else{
		return;
	}
	
}

function valicomName(va,str){
	var id=document.getElementById(va).value;
	$.ajax({
		type : "POST",
		url : ctx + "iscomNameUseful.html",
		cache : false,
		contentType : "application/json",
		dataType : "json",
		data : JSON.stringify({
			'comName' : id
		}),

		success : function(data){
			var JsonObject = eval(data);
			if(JsonObject[0].error=="0"){
				document.getElementById(str).innerHTML="该品种名称已存在，不能重复添加！";
			}
			if(JsonObject[0].success=="0"){
				document.getElementById(str).innerHTML="";
			}
		}
	});
	
}

function toUpdateCommodity(){
	var row = $('#tCommodityList').datagrid('getSelected');
	if(row ==null ){
		$.messager.alert("操作提示", "请先选择一条记录！");
		return ;
	}
	
	var id=row.com_id;
	
	$.ajax({
		type : "POST",
		url : ctx + "updateCommodity.html",
		cache : false,
		contentType : "application/json",
		dataType : "json",
		data : JSON.stringify({
			'comId' : id
		}),

		success : function(data){
			var JsonObject = eval(data);
			if(JsonObject[0].error=="0"){
				
			}
			if(JsonObject[0].success=="0"){
				document.getElementById("upComNamei").value=JsonObject[0].comName;
				document.getElementById("upComNameiHide").value=JsonObject[0].comName;
				document.getElementById("upComIdi").value=JsonObject[0].comId;
				document.getElementById("upItemName").value=JsonObject[0].itemName;
				document.getElementById("upListOrganization").value=JsonObject[0].listOrganization;
				document.getElementById("upSaleUnit").value=JsonObject[0].saleUnit;
				document.getElementById("upSalePrice").value=JsonObject[0].salePrice;
				document.getElementById("upSaleNum").value=JsonObject[0].saleNum;
				document.getElementById("upSaleMoney").value=JsonObject[0].saleMoney;
				document.getElementById("upCurrencyNum").value=JsonObject[0].currencyNum;
				document.getElementById("upMinNum").value=JsonObject[0].minNum;
				document.getElementById("upfreezeNum").value=JsonObject[0].freezeNum;
				document.getElementById("version").value=JsonObject[0].version;
				document.getElementById("fileName").value=JsonObject[0].fileName;
				$("#upStartDate").datebox("setValue", JsonObject[0].startDate); 
				$("#upEndDate").datebox("setValue", JsonObject[0].endDate); 
				document.getElementById("upIntroduction").value=JsonObject[0].introduction;
				
				if(document.getElementById("upExchangeState").options[0].text==JsonObject[0].exchangeState){
					document.getElementById("upExchangeState").options[0].selected=true;
				}
				if(document.getElementById("upExchangeState").options[1].text==JsonObject[0].exchangeState){
					document.getElementById("upExchangeState").options[1].selected=true;
				}
				if(document.getElementById("upExchangeState").options[2].text==JsonObject[0].exchangeState){
					document.getElementById("upExchangeState").options[2].selected=true;
				}
			}
		}
	});
	
	$('#updateCommodityDiv').dialog('open').dialog('setTitle', '兑换商品修改');
}

function updateCommodity(){
	var comName=$("#upComNamei").val();
	var comId=$("#upComIdi").val();
	var itemName=$("#upItemName").val();
	var listOrganization=$("#upListOrganization").val();
	var saleUnit=$("#upSaleUnit").val();
	var salePrice=$("#upSalePrice").val();
	var saleNum=$("#upSaleNum").val();
	var saleMoney=$("#upSaleMoney").val();
	var currencyNum=$("#upCurrencyNum").val();
	var minNum=$("#upMinNum").val();
	var startDate=$("#upStartDate").datebox('getValue');
	var endDate=$("#upEndDate").datebox('getValue');
	var introduction=$("#upIntroduction").val();
	var freezeNum=$("#upfreezeNum").val();
	var version=$("#version").val();
	var exchangeState=document.getElementById("upExchangeState").value;
	var fileName=$("#fileName").val();
	$.ajax({
		type : "POST",
		url : ctx + "updateCommodityGoods.html",
		cache : false,
		contentType : "application/json",
		dataType : "json",
		data : JSON.stringify({
			'comName':comName,
			'comId':comId,
			'itemName':itemName,
			'listOrganization':listOrganization,
			'saleUnit':saleUnit,
			'salePrice':salePrice,
			'saleNum':saleNum,
			'saleMoney':saleMoney,
			'currencyNum':currencyNum,
			'minNum':minNum,
			'startDate':startDate,
			'endDate':endDate,
			'introduction':introduction,
			'exchangeState':exchangeState,
			'freezeNum':freezeNum,
			'version':version,
			'fileName':fileName
		}),

		success : function(data) {
			var JsonObject = eval(data);
			if(JsonObject[0].success=="0"){
				$('#updateCommodityDiv').dialog('close');      // close the dialog  
				$('#tCommodityList').datagrid('reload');    // reload the user data 
				$.messager.show({
					title : '操作提示',
					msg : "修改成功!"
				});

			}
			if(JsonObject[0].error=="0"){
				$.messager.alert('添加产品','修改失败，请重新确认！','info'); 
				return false;
			}
		}
	});
}

//兑换规则查询
function exchangeRuleQuery(){
	var row = $('#tCommodityList').datagrid('getSelected');
	if(row ==null ){
		$.messager.alert("操作提示", "请先选择一条记录");
		return ;
	}
	$("#exchangeRuleFm #beComId").html(row.com_id);
	$("#exchangeRuleFm #beComName").html(row.com_name);
	$("#exchangeRuleFm #beMinNum").html(row.min_num);
	$('#exchangeRuleDiv').dialog('open').dialog('setTitle', '兑换规则查询');
	$('#exchangeRuleFm').form('clear');
	$("#exchangeRuleList").datagrid({
		title : '兑换规则',
		fit : true,
		autoRowheight : true,
		remoteSort : false,
		singleSelect : true,
		showFooter : true,
		rownumbers : true,
		toolbar : '#exchangeRulebar',
		url : ctx + 'exchangeRuleQuery.html?comId='+row.com_id,
		columns : [ [ {
			field : 'exchange_id',
			title : '品种编号',
			width : '100',
			align : 'center'
		}, {
			field : 'exchange_name',
			title : '品种名称',
			width : '100',
			align : 'center'
		}, {
			field : 'exchange_num',
			title : '兑换规则',
			width : '100',
			align : 'center'
		}, {
			field : 'com_type',
			title : '品种类型',
			width : '100',
			align : 'center'
		} ] ]

	});
	
	// 下拉框控件
	$("#RitemNamecbb0").combobox({
		url : ctx + 'loadItemName.html',
		editable : false, // 不可编辑状态
		cache : false,
		contentType : "application/json",
		valueField : 'itemName',
		textField : 'itemName',

		onSelect : function() {
			$("#RcomNamecbb0").combobox("setValue", '');// 清空课程
			var id = $('#RitemNamecbb0').combobox('getValue');
			$.ajax({
				type : "POST",
				url : ctx + "loadComName.html",
				cache : false,
				contentType : "application/json",
				dataType : "json",
				data : JSON.stringify({
					'id' : id
				}),

				success : function(data) {
					var JsonObject = eval(data);
					$("#RcomNamecbb0").combobox({
						dataType : "json",
						contentType : "application/json",
						editable : false, // 不可编辑状态
						cache : false,
						valueField : 'comName',
						textField : 'comName',
						onSelect : function() {
							var id0 = $('#RcomNamecbb0').combobox('getValue');
							var id1 = $('#RcomNamecbb1').combobox('getValue');
							var id2 = $('#RcomNamecbb2').combobox('getValue');
							var id3 = $('#RcomNamecbb3').combobox('getValue');
							var id4 = $('#RcomNamecbb4').combobox('getValue');

							if(id1==id0||id2==id0||id3==id0||id4==id0){
								alert("该品种名称已被选择，不能重复选择");
								$("#RcomNamecbb0").combobox("setValue", '');// 清空课程
								return;
							}
						}
					});

					$('#RcomNamecbb0').combobox('loadData', JsonObject);

				}
			});

		}
	});

	// 下拉框控件
	$("#RitemNamecbb1").combobox({
		url : ctx + 'loadItemName.html',
		editable : false, // 不可编辑状态
		cache : false,
		contentType : "application/json",
		valueField : 'itemName',
		textField : 'itemName',

		onSelect : function() {
			$("#RcomNamecbb1").combobox("setValue", '');// 清空课程
			var id = $('#RitemNamecbb1').combobox('getValue');
			$.ajax({
				type : "POST",
				url : ctx + "loadComName.html",
				cache : false,
				contentType : "application/json",
				dataType : "json",
				data : JSON.stringify({
					'id' : id
				}),

				success : function(data) {
					var JsonObject = eval(data);
					$("#RcomNamecbb1").combobox({
						dataType : "json",
						contentType : "application/json",
						editable : false, // 不可编辑状态
						cache : false,
						valueField : 'comName',
						textField : 'comName',
						onSelect : function() {
							var id0 = $('#RcomNamecbb0').combobox('getValue');
							var id1 = $('#RcomNamecbb1').combobox('getValue');
							var id2 = $('#RcomNamecbb2').combobox('getValue');
							var id3 = $('#RcomNamecbb3').combobox('getValue');
							var id4 = $('#RcomNamecbb4').combobox('getValue');
	
							if(id0==id1||id2==id1||id3==id1||id4==id1){
								alert("该品种名称已被选择，不能重复选择");
								$("#RcomNamecbb1").combobox("setValue", '');// 清空课程
								return;
							}
						}
					});

					$('#RcomNamecbb1').combobox('loadData', JsonObject);

				}
			});

		}
	});

	// 下拉框控件
	$("#RitemNamecbb2").combobox({
		url : ctx + 'loadItemName.html',
		editable : false, // 不可编辑状态
		cache : false,
		contentType : "application/json",
		valueField : 'itemName',
		textField : 'itemName',

		onSelect : function() {
			$("#RcomNamecbb2").combobox("setValue", '');// 清空课程
			var id = $('#RitemNamecbb2').combobox('getValue');
			$.ajax({
				type : "POST",
				url : ctx + "loadComName.html",
				cache : false,
				contentType : "application/json",
				dataType : "json",
				data : JSON.stringify({
					'id' : id
				}),

				success : function(data) {
					var JsonObject = eval(data);
					$("#RcomNamecbb2").combobox({
						dataType : "json",
						contentType : "application/json",
						editable : false, // 不可编辑状态
						cache : false,
						valueField : 'comName',
						textField : 'comName',
						onSelect : function() {
							var id0 = $('#RcomNamecbb0').combobox('getValue');
							var id1 = $('#RcomNamecbb1').combobox('getValue');
							var id2 = $('#RcomNamecbb2').combobox('getValue');
							var id3 = $('#RcomNamecbb3').combobox('getValue');
							var id4 = $('#RcomNamecbb4').combobox('getValue');

							if(id0==id2||id1==id2||id3==id2||id4==id2){
								alert("该品种名称已被选择，不能重复选择");
								$("#RcomNamecbb2").combobox("setValue", '');// 清空课程
								return;
							}
						}
					});

					$('#RcomNamecbb2').combobox('loadData', JsonObject);

				}
			});

		}
	});

	// 下拉框控件
	$("#RitemNamecbb3").combobox({
		url : ctx + 'loadItemName.html',
		editable : false, // 不可编辑状态
		cache : false,
		contentType : "application/json",
		valueField : 'itemName',
		textField : 'itemName',

		onSelect : function() {
			$("#RcomNamecbb3").combobox("setValue", '');// 清空课程
			var id = $('#RitemNamecbb3').combobox('getValue');
			$.ajax({
				type : "POST",
				url : ctx + "loadComName.html",
				cache : false,
				contentType : "application/json",
				dataType : "json",
				data : JSON.stringify({
					'id' : id
				}),

				success : function(data) {
					var JsonObject = eval(data);
					$("#RcomNamecbb3").combobox({
						dataType : "json",
						contentType : "application/json",
						editable : false, // 不可编辑状态
						cache : false,
						valueField : 'comName',
						textField : 'comName',
						onSelect : function() {
							var id0 = $('#RcomNamecbb0').combobox('getValue');
							var id1 = $('#RcomNamecbb1').combobox('getValue');
							var id2 = $('#RcomNamecbb2').combobox('getValue');
							var id3 = $('#RcomNamecbb3').combobox('getValue');
							var id4 = $('#RcomNamecbb4').combobox('getValue');
							if(id0==id3||id1==id3||id2==id3||id4==id3){
								alert("该品种名称已被选择，不能重复选择");
								$("#RcomNamecbb3").combobox("setValue", '');// 清空课程
								return;
							}
						}
					});

					$('#RcomNamecbb3').combobox('loadData', JsonObject);

				}
			});

		}
	});

	// 下拉框控件
	$("#RitemNamecbb4").combobox({
		url : ctx + 'loadItemName.html',
		editable : false, // 不可编辑状态
		cache : false,
		contentType : "application/json",
		valueField : 'itemName',
		textField : 'itemName',

		onSelect : function() {
			$("#RcomNamecbb4").combobox("setValue", '');// 清空课程
			var id = $('#RitemNamecbb4').combobox('getValue');
			$.ajax({
				type : "POST",
				url : ctx + "loadComName.html",
				cache : false,
				contentType : "application/json",
				dataType : "json",
				data : JSON.stringify({
					'id' : id
				}),

				success : function(data) {
					var JsonObject = eval(data);
					$("#RcomNamecbb4").combobox({
						dataType : "json",
						contentType : "application/json",
						editable : false, // 不可编辑状态
						cache : false,
						valueField : 'comName',
						textField : 'comName',
						onSelect : function() {
							var id0 = $('#RcomNamecbb0').combobox('getValue');
							var id1 = $('#RcomNamecbb1').combobox('getValue');
							var id2 = $('#RcomNamecbb2').combobox('getValue');
							var id3 = $('#RcomNamecbb3').combobox('getValue');
							var id4 = $('#RcomNamecbb4').combobox('getValue');
							if(id0==id4||id1==id4||id2==id4||id3==id4){
								alert("该品种名称已被选择，不能重复选择");
								$("#RcomNamecbb4").combobox("setValue", '');// 清空课程
								return;
							}
						}
					});

					$('#RcomNamecbb4').combobox('loadData', JsonObject);

				}
			});

		}
	});

}

function addRule() {
	$('#addRuleDiv').dialog('open').dialog('setTitle', '添加商品规则');
	$('#addRuleFm').form('clear');
   	$("#addRuleDiv #comId").val($("#exchangeRuleFm #beComId").html());
   	$("#addRuleDiv #comName").val($("#exchangeRuleFm #beComName").html());
}

function saveRule(){
	var comName = $("#addRuleDiv #comName").val();
	var comId = $("#addRuleDiv #comId").val();
	if(comId==null){
		$.messager.alert('添加产品','获取被兑换商品编号失败，请重新尝试！','info'); 
		return false;
	}
	
	var comNamecbb0 = $('#RcomNamecbb0').combobox('getValue');
	var amount0=$("#Ramount0").val();
	if(document.getElementById("RisGift0").checked){
		var isGift0="2";
	}else{
		isGift0="1";
	}
	
	var comNamecbb1 = $('#RcomNamecbb1').combobox('getValue');
	var amount1=$("#Ramount1").val();
	if(document.getElementById("RisGift1").checked){
		var isGift1="2";
	}else{
		isGift1="1";
	}
	
	var comNamecbb2 = $('#RcomNamecbb2').combobox('getValue');
	var amount2=$("#Ramount2").val();
	if(document.getElementById("RisGift2").checked){
		var isGift2="2";
	}else{
		isGift2="1";
	}
	
	var comNamecbb3 = $('#RcomNamecbb3').combobox('getValue');
	var amount3=$("#Ramount3").val();
	if(document.getElementById("RisGift3").checked){
		var isGift3="2";
	}else{
		isGift3="1";
	}
	
	var comNamecbb4 = $('#RcomNamecbb4').combobox('getValue');
	var amount4=$("#Ramount4").val();
	if(document.getElementById("RisGift4").checked){
		var isGift4="2";
	}else{
		isGift4="1";
	}
	
   	var row = $('#exchangeRuleList').datagrid("getData");
   	for(var i=0;i<row.rows.length;i++){
   		if(row.rows[i].exchange_name==comNamecbb0||row.rows[i].exchange_name==comNamecbb1||row.rows[i].exchange_name==comNamecbb2||row.rows[i].exchange_name==comNamecbb3||row.rows[i].exchange_name==comNamecbb4){
   			$.messager.alert('添加产品',row.rows[i].exchange_name+'该条商品规则已存在，请重新填写！','info'); 
			return false;
   		}
   	}
   	
	if(!isLeagle(comNamecbb0,amount0,isGift0)||!isLeagle(comNamecbb1,amount1,isGift1)||!isLeagle(comNamecbb2,amount2,isGift2)||!isLeagle(comNamecbb3,amount3,isGift3)||!isLeagle(comNamecbb4,amount4,isGift4)){
		return;
	}
	
	$.ajax({
		type : "POST",
		url : ctx + "addCommodityRules.html",
		cache : false,
		contentType : "application/json",
		dataType : "json",
		data : JSON.stringify({
			'comName':comName,
			'comId':comId,
			'comNamecbb0' :comNamecbb0,
			'amount0':amount0,
			'isGift0':isGift0,
			'comNamecbb1' :comNamecbb1,
			'amount1':amount1,
			'isGift1':isGift1,
			'comNamecbb2' :comNamecbb2,
			'amount2':amount2,
			'isGift2':isGift2,
			'comNamecbb3' :comNamecbb3,
			'amount3':amount3,
			'isGift3':isGift3,
			'comNamecbb4' :comNamecbb4,
			'amount4':amount4,
			'isGift4':isGift4,
		}),

		success : function(data) {
			var JsonObject = eval(data);
			if(JsonObject[0].success=="0"){
				$('#addRuleDiv').dialog('close');      // close the dialog  
				$('#exchangeRuleList').datagrid('reload');    // reload the user data 
				$.messager.show({
					title : '操作提示',
					msg : "添加成功!"
				});

			}
			if(JsonObject[0].error=="0"){
				$.messager.alert('添加产品','商品添加失败，请重新添加！','info'); 
				return false;
			}
		}
	});
}

function toModifyRule(){
	var row = $('#exchangeRuleList').datagrid('getSelected');
	if(row ==null ){
		$.messager.alert("操作提示", "请先选择一条记录");
		return ;
	}
	if (row){  
    	 $("#updateRuleFm #comId").val($("#exchangeRuleFm #beComId").html());
    	 $("#updateRuleFm #minNum2").val($("#exchangeRuleFm #beMinNum").html());
    	 $("#updateRuleFm #exchangeId").val(row.exchange_id);
    	 $("#updateRuleFm #exchangeName").val(row.exchange_name);
    	 $("#updateRuleFm #exchangeNum").val(row.exchange_num);
    	 if(row.com_type=="赠品"){
    		 $("#updateRuleFm #comType").attr("checked", true);
    	 }else{
    		 $("#updateRuleFm #comType").attr("checked", false);
    	 }
         $('#updateRuleDiv').dialog('open').dialog('setTitle','修改兑换规则');  
    }else{
    	$.messager.alert('修改兑换规则','请先选择要修改的商品信息','info'); 
    }
}

function updateRuleById(){
	if ($("#updateRuleFm #comType").prop("checked")) {
		$("#updateRuleFm #comType1").val("2");
	}
	$('#updateRuleFm').form('submit',{   
		url:$("#updateCommodityInformation").val(),
		onSubmit: function(){   
		       return $(this).form('validate')&&$('#updateRuleDiv').dialog('close');
		    },
		success:function(data){
			if(data=="1"){
				$('#updateRuleDiv').dialog('close');      // close the dialog  
				$('#exchangeRuleList').datagrid('reload');    // reload the user data 
				$.messager.show({
					title : '操作提示',
					msg : "修改成功!"
				});
			}
			else if(data=="-2"){
				$.messager.alert('修改产品','商品已存在！','info'); 
				return false;
			}
			
			else{
				$.messager.alert('修改商品规则','修改失败，请稍后重试','info');
			}
		}   
	}); 
	$("#updateRuleFm #comType").attr("checked", false);
	$("#updateRuleFm #comType1").val("1");//默认comType1为1；
}

//function delCommodity(){
//	var row = $('#tCommodityList').datagrid('getSelected');
//	if(row ==null ){
//		$.messager.alert("操作提示", "请先选择一条记录");
//		return ;
//	}
//	return confirm("是否要删除该条信息！");
//
//	$('#commodityfm').form('submit',{  
//		url : ctx + 'deleteCommodity.html?comId='+row.com_id,
//		success : function(data) {
//			if (data == 1) {
//				$('#tCommodityList').datagrid('reload');  
//				$('#tCommoditybar').dialog('close'); 
//				$.messager.show({
//					title : '操作提示',
//					msg : "删除商品信息操作成功!",
//				});
//			}else{
//				$.messager.alert("操作提示", "删除产品信息失败，请稍后修改！", "info");
//			}
//		}
//     });
//}

//删除兑换规则
function toDeleteRule(){
	var row = $('#exchangeRuleList').datagrid('getSelected');
	if(row ==null ){
		$.messager.alert("操作提示", "请先选择一条记录");
		return ;
	}
	$('#exchangeRulefm').form('submit',{  
		url : ctx + 'deleteRule.html?exchangeId='+row.exchange_id+'&comId='+$("#exchangeRuleFm #beComId").html(),
		success : function(data) {
			if (data == 1) {
				$('#exchangeRuleList').datagrid('reload');  
				$('#exchangeRulebar').dialog('close'); 
				$.messager.show({
					title : '操作提示',
					msg : "删除商品规则操作成功!",
				});
			}else{
				$.messager.alert("操作提示", "删除产品规则失败，请稍后修改！", "info");
			}
		}
	});
}

function uploadPic(){
//	document.getElementById("addCommodityFm").enctype="multipart/form-data";
	var fileNull = $("#imgFile").val();
	if(fileNull==""){
		$.messager.alert('上传图片','请先选择需要上传的图片',"info");
		return;
	}
	if(fileNum!=0){
		return;
	}
	$('#addCommodityFm').form('submit',{   
	    url:ctx + 'uploadPicture.html',   
	    onSubmit: function(){   
	       return true;
	    },   
	    success:function(data){
//	        if(data=="0"){
//                $.messager.show({
//					title : '操作提示',
//					msg : "添加成功!"
//				});
//	        }else{
//	        	$.messager.alert('添加产品','添加失败，请稍后重试',"info");
//	        }
	    	data = eval(data);
	    	if(data =="picTypeError"){
	    		$.messager.alert('上传图片','上传图片不是jpg文件',"info");
	    	}else if(data == "fileNotBig"){
	    		$.messager.alert('上传图片','上传文件需要介于50kb到1M之间',"info");
			}else {
				if(data != "fail"){
					var arr= new Array(); //定义一数组
					arr=data.split("^"); //字符分割 
					var fileFileName = arr[0];//上传的文件名
					var filePath = arr[1];//文件路径
					var fileName = arr[2];//自己生成的文件名
//					$("#div88").append("<div id=\""+fileName+"\"><label style='display:none'></label><div  style='text-align:center;color: red;'>"+fileFileName+"<img src='images/delete.png' style='padding-left: 25px;cursor: pointer;' width='18px;' align='middle' alt='' onclick='deletePicture(\""+fileName+"\",\""+fileName+"\""+");'/><input type='hidden' id='fileName'  name='fileName'  value='"+fileName+"'/></div></div>");
					$("#div88").append("<div id=\""+fileName+"\"><label style='display:none'></label><div  style='text-align:center;color: red;'>"+fileFileName+"<span  style='padding-left: 25px;cursor: pointer;' width='18px;' align='middle'  onclick='deletePicture(\""+fileName+"\",\""+fileName+"\""+");'>删除</span><input type='hidden' id='fileName'  name='fileName'  value='"+fileName+"'/></div></div>");
					//$("#fileError").html(data);
					fileNum = fileNum+1;
					imgNumdisabled();
				}else{
					alert("上传图片失败");
				} 
			}
	    }   
	}); 
}

//最多只能上传一张
function imgNumdisabled(){
	if(fileNum>0){
		$("#uploadFile").attr('disabled',true);
		$("#imgFile").attr('disabled',true);
	} else{
		$("#uploadFile").attr('disabled',false);
		$("#imgFile").attr('disabled',false);
	}
}

function deletePicture(filePath,fileName){
	 if(confirm("是否要删除？")){
			$.ajax({
				  type: "post",
				  url: ctx + 'deletePicture.html?fileName='+fileName,
				  success: function(data){
					  data = eval(data);
					  if(data == "success"){
						$("#div88 div div input").each(function(index){
							 if($(this).val()==fileName){
								 $(this).parent().parent().remove();
							 }
						});
						var imgnum = parseInt($("#imgnum").val());
						fileNum = fileNum-1;
						imgNumdisabled();
					  }else if(data == "fail"){
						  alert("删除失败");
					  }
	        	}
			}); 
		}
}
