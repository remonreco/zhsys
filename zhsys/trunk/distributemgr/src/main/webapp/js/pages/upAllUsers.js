	function roleTable(){
		$('#agrList_data').datagrid({
			iconCls: 'icon-edit',
			striped: true,
			remoteSort: false,
			idField: 'id',
			fit : true,
			showFooter: false,
			singleSelect: true,
			pagination : true,
		    pagePosition : 'bottom',
		    pageNumber : 1,
			pageSize: config.pageSize, 
			pageList: config.pageList,
			rownumbers: true,
			toolbar : '#tb',
			
		});
	}

	
	function importMessage(){
		var file = $("#messageFile").val();
		var filename=file.replace(/.*(\/|\\)/, "");
		var fileExt=(/[.]/.exec(filename)) ? /[^.]+$/.exec(filename.toLowerCase()) : '';
		if(fileExt=='xlsx'||fileExt=='xls'){
			$("#forms2").submit();
			
		}else{
			$.messager.alert(
				'批量添加分发产品信息',
			    "请导入xls或者xlsx格式文件!",
			    "info"
			);
		}
		
	}
	
	
