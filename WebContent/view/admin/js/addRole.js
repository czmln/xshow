$(function(){  

	   $('#add_submit').click(function(){
			var check = $('#easy-dataform').form('validate');
			if(!check) return;
			var json = $('#easy-dataform').serializeObject();
			$('#add_submit').attr('disabled','disabled');
			$.messager.progress();
			$.ajax({
				url: baseJS+"/role/saveRole.do",
				type : "POST",
				data : {'data':$.toJSON(json)},
				dataType : "json",
				success : function(data) {
					$.messager.progress('close');
					if(data){
						$.messager.alert("提示消息","新增成功!", 'info', function(){
				    		refresh("角色增加");
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

function refresh(title){
	 var appConfigTab = parent.$("#tabs");
	 var datagrid = parent.$("#gridResult");
	 appConfigTab.tabs("close", title);
	 datagrid.datagrid('reload');  
}


