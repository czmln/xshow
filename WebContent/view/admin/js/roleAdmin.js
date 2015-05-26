$(document).ready(function(){

	
	$('#gridResult1').datagrid({
		nowrap: true,
		striped: true,
		autoRowHeight: true,
		url:baseJS+"/role/getRoleByUserId.do?id="+$('#userId').val(),
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
				{field:'roleName',width:'40%',title:'角色名',align:'left'},
				{field:'status',width:'30%',title:'角色状态',align:'left'}
			]
		],
		onLoadSuccess: function (data) {
			
		}
	});

	$('#gridResult2').datagrid({
		nowrap: true,
		striped: true,
		autoRowHeight: true,
		url:baseJS+"/role/getRoleByNoUser.do?id="+$('#userId').val(),
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
				{field:'roleName',width:'40%',title:'角色名',align:'left'},
				{field:'status',width:'30%',title:'角色状态',align:'left'}
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
			         roleName: selected[i].roleName,
			         status: selected[i].status
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
		 $("#roleId").val(ids);

	 });
	 
	 $('#sz').click(function(){
		 
		 var selected = $('#gridResult1').datagrid('getChecked');
		 for(i=0;i<selected.length;i++){
			 $('#gridResult2').datagrid('insertRow',{
			    row: {
			         id: selected[i].id,
			         roleName: selected[i].roleName,
			         status: selected[i].status
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
		 
		 $("#roleId").val(ids);
		 
		 if($("#roleId").val() == '') {
			 $("#roleId").val('0');
		 }
		 
	 });
	
	 $('#add_submit').click(function(){
		if($("#roleId").val()=='' || $("#roleId").val() == null){
			$.messager.alert("没有做任何改变","提交失败!",'error');
			return;
		}
		var check = $('#easy-dataform').form('validate');
		if(!check) return;
		var json = $('#easy-dataform').serializeObject();
		$('#add_submit').attr('disabled','disabled');
		$.messager.progress();
		$.ajax({
			url: baseJS+"/role/saveRoleAdmin.do",
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