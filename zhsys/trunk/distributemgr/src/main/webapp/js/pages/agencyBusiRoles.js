	$(function(){
		roleTable();
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
	
	function roleTable(){
		$('#agrList_data').datagrid({
			title:'角色管理',
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
			url: $("#queryAgencyBusiRolesUrl").val(),
			columns:[[
	            {field: 'name', title: '角色名', width: '150', align: 'center'},
	            {field: 'tAgencyLevel', title: '经销商等级', width: '50', align: 'center'},
			    {field: 'remark', title: '备注', width: '400', align: 'center'},
			    {field: 'tAgencyRoles', title: '功能', width: '500', align: 'center',
			    	formatter:function(value, rowData, index){
			    		var tSysRoles = "";
			    		$.each(value,function(i,value){
			    			if(i!=0){
			    				tSysRoles += "," + value.name;
			    			}else{
			    				tSysRoles += value.name;
			    			}
			    		});
		    			return tSysRoles;
			    	}
			    }
			    ]]
			    
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

	function queryRoles(){
		var roles = "";
		$("#tb input[type='checkbox'][name='roles']:checked").each(function(){
			roles += $(this).val()+",";
		});
	    $('#agrList_data').datagrid({//重载datagrid
	    	pagination: true,
	    	url: $("#querySysBusiRolesUrl").val(),
			pageNumber : 1,
			//向底层传递参数
			queryParams : {
				name: $("#name").val(),
				roles: roles,
				agencyLevel : $("#agencyl option:selected").val()
				
			}
	    });
	}

	function toAddRole(){
        $.ajax({
        	type: 'POST',
        	url: $("#allAgencyRolesUrl").val(),
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
        $('#addRoleDiv').dialog('open').dialog('setTitle','添加角色');  
        $('#addRoleFm').form('clear');  
    } 
	
	function saveRole(){
		$('#addRoleFm').form('submit',{   
		    url:$("#addAgencyBusiRoleUrl").val(),
		    data:{},
		    onSubmit: function(){   
		       return $(this).form('validate')&&checkName()&&checkRole();
		    },   
		    success:function(data){
		    	var obj = eval('(' + data + ')');
				if(obj.resCode=="0000"){
		        	$('#addRoleDiv').dialog('close');      // close the dialog  
                    $('#agrList_data').datagrid('reload');    // reload the user data
                    $.messager.show({
						title : '操作提示',
						msg : "添加成功!"
					});
		        }else{
		        	$.messager.alert('添加角色',obj.msg,"info");
		        }
		    }   
		}); 
	}
	
	function checkRole(){
		if($("#addRoleFm input[type='checkbox'][name='roles']:checked").length==0){
			$.messager.alert('添加角色','请选择功能');   
			return false;
		}
		return true;
	}
	
	function checkName(){
		var result = "";
		$.ajax({
			type: 'POST',
			url: $("#checkDuplicateAgencyBusiRoleNameUrl").val(),
			data: {
				name: $("#name1").val(),
				agencyLevel : $("#agencyLevel1").val()
			},
			dataType: 'text',
			async: false,//设成同步
			cache: false,
			traditional: true,
			success: function(data) {
				result = data;
			}
		});
		if(result.indexOf("1") >= 0){
			$.messager.alert('添加角色','角色名已存在','info'); 
			return false;
		}
		return true;
	}
	
	function toUpdateRole(){
		var row = $('#agrList_data').datagrid('getSelected'); 
        if (row){  
            $('#updateRoleFm').form('load',row);
            $("#updateRoleId").html(" <input type='hidden' id='id2' name='id' value='"+row.id+"'/> ");
            $("#level").html(row.tAgencyLevel+"级经销商");
            $("#roName").html(row.name);
            $("#agencyLevel2").val(row.tAgencyLevel);
            $.ajax({
            	type : 'POST',
            	url : $("#allAgencyRolesUrl").val(),
            	dataType : 'json',
            	async: true,
            	cache: false,
            	traditional : true,
            	success: function(data) {
            		var role = "";
            		$.each(data,function(i,value){
            			role+='<label><input type="checkbox" name="roles" value="'+value.id+'"';
            			$.each(row.tAgencyRoles,function(i,val){
                        	if(val.id == value.id){
                        		role+=' checked="checked"';
                        	}
                        });
            			role+='/>'+value.name+'</label>';
            		});
            		$("#rolespan2").html(role);
            	}
            });            
            $('#updateRoleDiv').dialog('open').dialog('setTitle','修改操作员');  
        }else{
        	$.messager.alert('修改操作员','请先选择要修改的操作员','info'); 
        }
	}
	
	function updateCheckRole(){
		if($("#updateRoleFm input[type='checkbox'][name='roles']:checked").length==0){
			$.messager.alert('添加角色','请选择功能');   
			return false;
		}
		return true;
	}
	
	function updateCheckName(){
		var result = "";
		$.ajax({
			type: 'POST',
			url: $("#checkDuplicateAgencyBusiRoleNameUrl").val(),
			data: {
				id: $("#id2").val(),
				name: $("#name2").val(),
				agencyLevel : $("#agencyLevel2").val()
			},
			dataType: 'text',
			async: false,//设成同步
			cache: false,
			traditional: true,
			success: function(data) {
				result = data;
			}
		});
		if(result.indexOf("1") >= 0){
			$.messager.alert('添加角色','角色名已存在','info'); 
			return false;
		}else if(result.indexOf("2") >= 0){
			$.messager.alert('添加角色','不可修改经理角色名称','info'); 
			return false;
		}
		return true;
	}
	
	function updateRole(){
		$('#updateRoleFm').form('submit',{   
			url:$("#updateSysBusiRoleUrl").val(),   
			onSubmit: function(){   
				return $(this).form('validate')  && updateCheckRole();
			},   
			success:function(data){
				if(data=="1"){
					$('#updateRoleDiv').dialog('close');      // close the dialog  
					$('#agrList_data').datagrid('reload');    // reload the user data 
					$.messager.show({
						title : '操作提示',
						msg : "修改成功!"
					});
				}else{
					$.messager.alert('修改角色','修改失败，请稍后重试','info');
				}
			}   
		}); 
	}
	
	function toDeleteRole(){
		var row = $('#agrList_data').datagrid('getSelected'); 
        if (row){  
        	if(row.id==1||row.id==2||row.id==3||row.id==4||row.id==5){
        		$.messager.alert('删除角色','删除失败，不可删除经理角色','info');
        		return ;
        	}
        	if(!confirm("确定删除该角色吗")){
        		return ;
        	}
        	$.ajax({
    			type: 'POST',
    			url: $("#delRoleUrl").val(),
    			data: {
    				id: row.id
    			},
    			dataType: 'text',
    			async: false,//设成同步
    			cache: false,
    			traditional: true,
    			success: function(data) {
    				result = data;
    			}
    		});
        	if(result.indexOf("1") >= 0){
    			$.messager.alert('删除角色','删除角色成功','info'); 
    		}else{
    			$.messager.alert('删除角色','删除角色失败','info'); 
    		}
        	
        	 $('#agrList_data').datagrid('reload'); 
        	
        }else{
        	$.messager.alert('删除操作员','请先选择要删除的操作员','info'); 
        }
	}
