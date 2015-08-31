$(function(){
	$("#dealerUserList").datagrid({
		title:'查看经销商的会员',
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
		toolbar : '#userbar',
		url:ctx+'queryDealerUser.html',
		columns:[[
		            {field: 'userName', title: '会员名称', width: '7%', align: 'center' },
		            {field: 'assetsAccount', title: '资产账号', width: '7%', align: 'center' },
		            {field: 'rightsAccount', title: '权益账号', width: '7%', align: 'center' },
		            {field: 'sex', title: '性别', width: '7%', align: 'center',
		            	formatter: function(value,row,index){
		            		if(row.sex =='0'){
		            			return '女';
		            		}else{
		            			return '男';
		            		}
		    			}},
		            {field: 'certificateNum', title: '证件号码', width: '7%', align: 'center' },
		            {field: 'email', title: '邮箱', width: '7%', align: 'center' },
		            {field: 'tel', title: '联系方式', width: '8%', align: 'center'},
		            {field: 'address', title: '联系地址', width: '40%', align: 'center'}
		            ]]
		
	});

	//设置分页控件
	var p = $("#dealerUserList").datagrid('getPager');
	$(p).pagination({
		beforePageText: '第',//页数文本框前显示的汉字
		afterPageText: '页    共 {pages} 页',
		displayMsg: '<span class="marrig20">共 {total} 条记录</span>',
		onBeforeRefresh:function(){  
	 		$('#dealerUserList').datagrid('load');  
	 		return false;
 		} 
	});
	
	

	//初始化选择经销商下拉列表
 	/*$('#lowerDealer2').combobox({    
 	    url:ctx+'selectLowerDealerList.html',    
 		valueField:'id',    
 	    textField:'dealerName',
 	    
 	}); */
	/*$('#lowerDealer3').combobox({    
 	    url:ctx+'selectLowerList.html',    
 		valueField:'id',    
 	    textField:'dealerName',
 	}); */
 	
});
function selectDealerUser(){
	$("#dealerUserList").datagrid({
		pagination: true,
		url:ctx+"queryDealerUser.html",
		pageNumber: 1,
		queryParams:{
			assetsAccount:$("#assetsAccount").val(),
			tel:$("#tel").val(),
			userName:$("#userName").val()
		}
	});
}
function addDealerUser(){  
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
    $('#addDealerUserDiv').dialog('open').dialog('setTitle','添加会员');  
    $('#addDealerUserFm').form('clear');
    /*$('#lowerDealer2').combobox('select',$("#userId").val());*/
   /* $('#lowerDealer2').combobox();*/
} 
function saveDealerUser(){
	$('#addDealerUserFm').form('submit',{   
	    url:$("#addDealerUserUrl").val(),   
	    onSubmit: function(){   
	       return $(this).form('validate')&&$('#addDealerUserDiv').dialog('close');
	    },   
	    success:function(data){
	        if(data=="1"){
                $('#dealerUserList').datagrid('reload');    // reload the user data
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
			id:$("#editDealerUserDiv #id").val(),
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
		$.messager.alert('编辑会员','会员已存在','info'); 
		return false;
	}
	return true;
}

function toUpdateDealerUser(){
	var row = $('#dealerUserList').datagrid('getSelected'); 
	
    if (row){  
    	 $("#editDealerUserDiv #id").val(row.id);
    	 $("#editDealerUserDiv #assetsAccount3").val(row.assetsAccount);
    	 $("#editDealerUserDiv #rightsAccount3").val(row.rightsAccount);
    	 $("#editDealerUserDiv #tel3").val(row.tel);
    	 $("#editDealerUserDiv #userName3").val(row.userName);
    	 $("#editDealerUserDiv #address3").val(row.address);
    	 if(row.sex=='0'){
    		 $('#sex3').combobox('setValue','0');
    	 }else{
    		 $('#sex3').combobox('setValue','1');
    	 }
    	 $("#editDealerUserDiv #certificateNum3").val(row.certificateNum);
    	 $("#editDealerUserDiv #email3").val(row.email);
/*    	 $('#lowerDealer3').combobox('select',$("#userId").val());*/
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
        $('#editDealerUserDiv').dialog('open').dialog('setTitle','修改会员');
       
    }else{
    	$.messager.alert('修改会员','请先选择要修改的会员信息','info'); 
    }
}

function editDealerUser(){
	$('#editDealerUserFm').form('submit',{   
	    url:$("#editDealerUserUrl").val(),   
	    onSubmit: function(){   
	       return $(this).form('validate')&&checkName2()&&$('#editDealerUserDiv').dialog('close'); 
	    },   
	    success:function(data){
	    	
	        if(data=="1"){
                $('#dealerUserList').datagrid('reload');    // reload the user data
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
