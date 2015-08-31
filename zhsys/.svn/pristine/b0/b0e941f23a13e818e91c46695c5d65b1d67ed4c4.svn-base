	$(function(){
		userTable();
		
		$.extend($.fn.validatebox.defaults.rules, {   
		    equals: {   
		        validator: function(value,param){ 
		            return value == $(param[0]).val();   
		        },   
		        message: '两次密码不一致'
		    },
		    mobile: {   
		        validator: function(value,param){   
		            return /^([0-9]{11})?$/.test(value);   
		        },   
		        message: '手机号格式不正确'  
		    }
		});
		
		
		$("#tb input[type='checkbox'][name='roles']").click(function(){
			var len = $("#tb input[type='checkbox'][name='roles']:checked").length;
			if(len ==0){
				$("#all").prop("checked",true);
			}else{
				$("#all").prop("checked",false);
			}
		});
		$("#all").click(function(){
			if($("#all").prop("checked")){
				$("#tb input[type='checkbox'][name='roles']").prop("checked",false);
			}
		});
		
	});
	
	function userTable(){
		$('#agrList_data').datagrid({
			title:'操作员管理',
			striped: true,
			remoteSort: false,
			idField: 'id',
			fit : true,
			showFooter: false,
			singleSelect: true,
			pagination : true,
		    pagePosition : 'bottom',
		    pageNumber : 1,
			pageSize: 50,////每页显示的记录条数，默认为50
			pageList: [50,100,300,500],//可以设置每页记录条数的列表
			rownumbers: true,
			toolbar : '#tb',
			url: $("#querySysUsersUrl").val(),
			columns:[[
	            {field: 'dealerName', title: '登录名', width: '90', align: 'center'},
	            {field: 'userName', title: '真实姓名', width: '80', align: 'center'},
			    {field: 'tel', title: '手机号', width: '110', align: 'center'},
			    {field: 'tAgencyBusiRoles', title: '角色', width: '235', align: 'center',
			    	formatter:function(value, rowData, index){
			    		var roles = "";
			    		$.each(value,function(i,value){
			    			if(i!=0){
				    			roles += "," + value.name;
			    			}else{
			    				roles += value.name;
			    			}
			    		});
		    			return roles;
			    	}},
			    {field: 'accountStatus', title: '状态', width: '60', align: 'center',
			    	formatter: function(value, rowData, index){
			    		if(value == 1){
			    			return '正常';
			    		}else if(value == 0){
			    			return '禁用';
			    		}
			    	}
			    }
			    ] ]
		});
		//设置分页控件
		var p = $('#agrList_data').datagrid('getPager');
		$(p).pagination({
			beforePageText: '第',//页数文本框前显示的汉字
			afterPageText: '页    共 {pages} 页',
			displayMsg: '<span class="marrig20">共 {total} 条记录</span>',
			onBeforeRefresh:function(){  
		 		$('#agrList_data').datagrid('load');  
		 		return false;
	 		} 
		});
	}

	function queryUsers(){
		var roles = "";
		$("#tb input[type='checkbox'][name='roles']:checked").each(function(){
			roles += $(this).val()+",";
		});
	    $('#agrList_data').datagrid({//重载datagrid
	    	pagination: true,
	    	url: $("#querySysUsersUrl").val(),
			pageNumber : 1,
			//向底层传递参数
			queryParams : {
				loginName: $("#loginName").val(),
				status: $("#status").combobox('getValue'),
				roles: roles
			}
	    });
	}
	
	function toAddUser(){  
        $.ajax({
        	type: 'POST',
        	url: $("#allSysBusiRolesUrl").val(),
        	dataType : 'json',
        	async: true,
        	cache: false,
        	traditional: true,
        	success: function(data) {
        		var role = "";
        		$.each(data, function(i,value){
        			role+='<label><input type="checkbox" name="roles" value="'+value.id+'"/>'+value.name+'</label>';
        		});
        		$("#rolespan1").html(role);
        	}
        });
        $('#addUserDiv').dialog('open').dialog('setTitle','添加操作员');  
        $('#addUserFm').form('clear');
    } 
	
	function saveUser(){
		$('#addUserFm').form('submit',{   
		    url:$("#addSysUserUrl").val(),   
		    onSubmit: function(){   
		       return $(this).form('validate')&&checkName()&&checkRole();
		    },   
		    success:function(data){
		    	var obj = eval('(' + data + ')');
		        if(obj.resCode=="0000"){
		        	$('#addUserDiv').dialog('close');      // close the dialog  
                    $('#agrList_data').datagrid('reload');    // reload the user data
                    $.messager.show({
						title : '操作提示',
						msg : "添加成功!"
					});
		        }else{
		        	$.messager.alert('添加操作员','添加失败'+obj.msg+'，请稍后重试',"info");
		        }
		    }   
		}); 
	}
	
	function checkRole(){
		if($("#addUserFm input[type='checkbox'][name='roles']:checked").length == 0){
			$.messager.alert('添加操作员','请选择角色');   
			return false;
		}
		return true;
	}
	
	function checkName(){
		var result = "";
		$.ajax({
			type: 'POST',
			url: $("#checkDuplicateSysUserLoginNameUrl").val(),
			data: {
				loginName: $("#loginName1").val()
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
			$.messager.alert('添加账号','帐户名已存在','info'); 
			return false;
		}
		return true;
	}
	
	function toUpdateUser(){
		var row = $('#agrList_data').datagrid('getSelected'); 
        if (row){  
            $('#updateUserFm').form('load',row);
            $("#updateUserFm #loginPwd2").val("");
            $("#updateUserFm #loginPwd2_2").val("");
            $("#updateUserId").html(row.dealerName+"<input type='hidden' name='id' value='"+row.id+"'/>");
            $.ajax({
            	type : 'POST',
            	url : $("#allSysBusiRolesUrl").val(),
            	dataType : 'json',
            	async: true,
            	cache: false,
            	traditional : true,
            	success: function(data) {
            		var role = "";
            		$.each(data,function(i,value){
            			role+='<label><input type="checkbox" name="roles" value="'+value.id+'"';
            			$.each(row.tAgencyBusiRoles,function(i,val){
                        	if(val.id == value.id){
                        		role+=' checked="checked"';
                        	}
                        });
            			role+='/>'+value.name+'</label>';
            		});
            		$("#rolespan2").html(role);
            	}
            });            
            $('#upateUserDiv').dialog('open').dialog('setTitle','修改操作员');  
        }else{
        	$.messager.alert('修改操作员','请先选择要修改的操作员','info'); 
        }
	}
	
	function updateCheckRole(){
		if($("#updateUserFm input[type='checkbox'][name='roles']:checked").length == 0){
			$.messager.alert('修改操作员','请选择角色');   
			return false;
		}
		return true;
	}
	
	function checkPWD(){
		var pwd = $('#loginPwd2').val();
		var pwdr = $('#loginPwd2_2').val();
		if(pwd!=pwdr){
			$.messager.alert('修改操作员','两次密码不一致');   
			return false;	
		}
		return true;
	}
	
	function updateUser(){
		$('#updateUserFm').form('submit',{   
			url:$("#updateSysUserUrl").val(),
			onSubmit: function(){  
				return $(this).form('validate')&&updateCheckRole()&&checkPWD();
			},   
			success:function(data){
				var obj = eval('(' + data + ')');
				if(obj.resCode=="0000"){
					$('#upateUserDiv').dialog('close');      // close the dialog  
					$('#agrList_data').datagrid('reload');    // reload the user data 
					$.messager.show({
						title : '操作提示',
						msg : "修改成功!"
					});
				}else{
					$.messager.alert('修改账号',obj.msg,'info');
				}
			}   
		}); 
	}