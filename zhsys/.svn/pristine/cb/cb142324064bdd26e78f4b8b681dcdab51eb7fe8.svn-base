$(function(){
	$("#tuserList").datagrid({
		title:'查看会员',
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
		url:ctx+'queryTuser2.html',
		columns:[[	
		          	{field: 'id', title: 'id', hidden:true}, 
		            {field: 'assetsAccount', title: '资产帐号', width: '100', align: 'center'},  
		            {field: 'rightsAccount', title: '权益帐号', width: '100', align: 'center' },
				    {field: 'userName', title: '用户名称', width: '100', align: 'center'},
				    {field: 'tel', title: '联系方式', width: '100', align: 'center'},
				    {field: 'sex', title: '性别', width: '100', align: 'center',
		            	formatter: function(value,row,index){
		            		if(row.sex =='0'){
		            			return '女';
		            		}else{
		            			return '男';
		            		}
		    			}},
		            {field: 'certificateNum', title: '证件号码', width: '100', align: 'center' },
		            {field: 'email', title: '邮箱', width: '100', align: 'center' },
				    /*{field: 'higerDealer', title: '上级经销商', width: '100', align: 'center'},*/
				    {field: 'address', title: '地址', width: '100', align: 'center'},
				    {field: 'tDealerName', title: '所属经销商', width: '100', align: 'center'},
				    {field: 'tDealerLevel', title: '经销商级别', width: '100', align: 'center',
				    	formatter: function(value,row,index){
		            		if(row.tDealerLevel =='0'){
		            			return '总部';
		            		}else if(row.tDealerLevel =='1'){
		            			return '一级经销商';
		            		}else if(row.tDealerLevel =='2'){
		            			return '二级经销商';
		            		}else if(row.tDealerLevel =='3'){
		            			return '三级经销商';
		            		}else if(row.tDealerLevel =='4'){
		            			return '四级经销商';
		            		}else if(row.tDealerLevel =='5'){
		            			return '五级经销商';
		            		}
		    			}}
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
function selectUser(){
	$("#tuserList").datagrid({
		pagination: true,
		url:ctx+"queryTuser2.html",
		pageNumber: 1,
		queryParams:{
			assetsAccount:$("#assetsAccount").val(),
			rightsAccount:$("#rightsAccount").val(),
			userName:$("#userName").val(),
			tel:$("#tel").val()
		}
	});
}

function addUser(){
	$('#lowerDealer2').combobox({    
 	    url:ctx+'selectLowerDealerList.html',    
 		valueField:'id',    
 	    textField:'dealerName',
 	   onLoadSuccess: function (data) {
           if (data) {
               $('#lowerDealer2').combobox('setValue',$('#agencyId').val());
           }
         }
 	    
 	});
    $('#addUserDiv').dialog('open').dialog('setTitle','添加会员');  
    $('#addUserFm').form('clear');
} 




function saveUser(){
	
	$('#addUserFm').form('submit',{   
	    url:$("#addUserUrl").val(),   
	    onSubmit: function(){
		      return $(this).form('validate')&&$('#addUserDiv').dialog('close'); 
	    },   
	    success:function(data){
	        if(data=="1"){
                $('#tuserList').datagrid('reload');    // reload the user data
                $.messager.show({
					title : '操作提示',
					msg : "添加成功!"
				});
	        }else if(data=="2"){
	        	$.messager.alert('添加会员','会员已存在','info'); 
	    		return false;
	        }else{
	        	$.messager.alert('添加会员','添加失败，请稍后重试',"info");
	        }
	    }   
	}); 
}

function checkName(){
	var result = "";
	$.ajax({
		type: 'POST',
		url: $("#checkTuserByUserNumAndName").val(),
		data: {
			assetsAccount: $("#assetsAccount2").val(),
			rightsAccount: $("#rightsAccount2").val(),
			tel: $("#tel2").val(),
			userName: $("#userName2").val(),
			address: $("#address2").val(),
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
		$.messager.alert('添加会员','会员已存在','info'); 
		return false;
	}
	return true;
}

function checkName2(){
	var result = "";
	$.ajax({
		type: 'POST',
		url: $("#checkTuserByUserNumAndName").val(),
		data: {
			id:$("#editUserDiv #id").val(),
			assetsAccount: $("#assetsAccount3").val(),
			rightsAccount: $("#rightsAccount3").val(),
			tel: $("#tel3").val(),
			userName: $("#userName3").val(),
			address: $("#address3").val(),
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
		$.messager.alert('添加会员','会员已存在','info'); 
		return false;
	}
	return true;
}




function toUpdateUser(){
	var row = $('#tuserList').datagrid('getSelected');
	if(row.tDealerLevel != '0'){
		$.messager.alert('修改会员','不能修改此会员信息','info'); 
		return;
	}
    if (row){  
    	 $("#editUserDiv #id").val(row.id);
    	 $("#editUserDiv #assetsAccount3").val(row.assetsAccount);
    	 $("#editUserDiv #rightsAccount3").val(row.rightsAccount);
    	 $("#editUserDiv #tel3").val(row.tel);
    	 $("#editUserDiv #userName3").val(row.userName);
    	 $("#editUserDiv #address3").val(row.address);
    	 if(row.sex=='0'){
    		 $('#sex3').combobox('setValue','0');
    	 }else{
    		 $('#sex3').combobox('setValue','1');
    	 }
    	 $("#editUserDiv #certificateNum3").val(row.certificateNum);
    	 $("#editUserDiv #email3").val(row.email);
    	 var agencyId = row.agencyId;
    	 $('#lowerDealer3').combobox({    
      	    url:ctx+'selectLowerDealerList.html',    
      		valueField:'id',    
      	    textField:'dealerName',
      	    onLoadSuccess: function (data) {
              if (data) {
                  $('#lowerDealer3').combobox('setValue',agencyId);
              }
            }
      	});
        $('#editUserDiv').dialog('open').dialog('setTitle','修改会员');  
    }else{
    	$.messager.alert('修改会员','请先选择要修改的会员信息','info'); 
    }
}

function editUser(){
	$('#editUserFm').form('submit',{   
	    url:$("#editUserUrl").val(),   
	    onSubmit: function(){   
	       return $(this).form('validate')&&checkName2()&&$('#editUserDiv').dialog('close');
	    },   
	    success:function(data){
	        if(data=="1"){
                $('#tuserList').datagrid('reload');    // reload the user data
                $.messager.show({
					title : '操作提示',
					msg : "编辑成功!"
				});
	        }else{
	        	$.messager.alert('编辑会员','编辑失败，请稍后重试',"info");
	        }
	    }   
	}); 
}
