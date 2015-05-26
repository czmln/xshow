$(document).ready(function(){


	$('#gridResult1').datagrid({
		nowrap: true,
		striped: true,
		autoRowHeight: true,
		url:baseJS+"/resource/getResourceByRoleId.do?id="+$('#roleId').val(),
		remoteSort: false,
		singleSelect:false,
		resizeHandle:'both',
		border:false,
		fitColumns:false, 
	    loadMsg:'数据加载中请稍后……', 
		columns:[
	         [
				{field:'ck',checkbox:true},
				{field:'id',width:'9%',title:'ID',align:'center',hidden:true},
				{field:'resourceName',width:'40%',title:'资源名',align:'left'},
				{field:'resourceType',width:'30%',title:'资源类型',align:'left', 
					formatter: function(value,row,index) {
						var str = "";
						if(value==1) {
							str="一级菜单";
						} else if(value==2) {
							str="二级菜单";
						} else {
							str="三级菜单";
						}
						return str;
					}
				}
			]
		],
		onLoadSuccess: function (data) {
			
		}
	});
	

	$('#gridResult2').datagrid({
		nowrap: true,
		striped: true,
		autoRowHeight: true,
		url:baseJS+"/resource/getResourceByNoRole.do?id="+$('#roleId').val(),
		remoteSort: false,
		singleSelect:false,
		resizeHandle:'both',
		border:false,
		fitColumns:false, 
	    loadMsg:'数据加载中请稍后……', 
		columns:[
	         [
				{field:'ck',checkbox:true},
				{field:'id',width:'9%',title:'ID',align:'center',hidden:true},
				{field:'resourceName',width:'40%',title:'资源名',align:'left'},
				{field:'resourceType',width:'30%',title:'资源类型',align:'left', 
					formatter: function(value,row,index) {
						var str = "";
						if(value==1) {
							str="一级菜单";
						} else if(value==2) {
							str="二级菜单";
						} else {
							str="三级菜单";
						}
						return str;
					}}
			]
		],
		onLoadSuccess: function (data) {
			
		}
	});
	
	
	 $('#fuz').click(function(){
		 
		 var selected = $('#gridResult2').datagrid('getChecked');
		 for(i=0;i<selected.length;i++){
			 $('#gridResult1').datagrid('insertRow',{
			    row: {
			         id: selected[i].id,
			         resourceName: selected[i].resourceName,
			         resourceType: selected[i].resourceType
			     }
			 });
			 $('#gridResult2').datagrid('deleteRow',$('#gridResult2').datagrid('getRowIndex',selected[i]));
		 }
		 
		 var rows = $("#gridResult1").datagrid("getRows");
		 var ids = '';
		 for(i=0;i<rows.length;i++)
		 {
			 ids += rows[i].id + ",";
		 }
		 $("#resourceId").val(ids);

	 });
	 
	 $('#sz').click(function(){
		 
		 var selected = $('#gridResult1').datagrid('getChecked');
		 for(i=0;i<selected.length;i++){
			 $('#gridResult2').datagrid('insertRow',{
			    row: {
			         id: selected[i].id,
			         resourceName: selected[i].resourceName,
			         resourceType: selected[i].resourceType
			     }
			 });
			 $('#gridResult1').datagrid('deleteRow',$('#gridResult1').datagrid('getRowIndex',selected[i]));
		 }
		 
		 var rows = $("#gridResult1").datagrid("getRows");
		 
		 var ids = '';
		 
		 for(i=0;i<rows.length;i++)
		 {
			 ids += rows[i].id + ",";
		 }
		 
		 $("#resourceId").val(ids);
		 
		 if($("#resourceId").val() == '') {
			 $("#resourceId").val('0');
		 }
	 });
	
	 $('#add_submit').click(function(){
		if($("#resourceId").val()=='' || $("#resourceId").val() == null){
			$.messager.alert("没有做任何改变","提交失败!",'error');
			return;
		}
		var check = $('#easy-dataform').form('validate');
		if(!check) return;
		var json = $('#easy-dataform').serializeObject();
		$('#add_submit').attr('disabled','disabled');
		$.messager.progress();
		$.ajax({
			url: baseJS+"/resource/saveRoleResource.do",
			type : "POST",
			data : {'data':$.toJSON(json)},
			dataType : "json",
			success : function(data) {
				$.messager.progress('close');
				if(data){
					$.messager.alert("提示消息","新增成功!", 'info', function(){
						$("#gridResult1").datagrid("reload");
						$("#gridResult2").datagrid("reload");
						$('#add_submit').removeAttr('disabled');
			    	});
				}else{
					$.messager.alert("错误消息","新增失败!",'error');
				}
			},
			error : function(XMLHttpRequest, strError, strObject) {
				$('#deploy-add-btn').removeAttr('disabled');
				$.alert('请求失败！');
			}
		});
	 });
	
	
	
});