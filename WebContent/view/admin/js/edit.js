$(function(){  

	   $('#add_submit').click(function(){
			var check = $('#easy-dataform').form('validate');
			if(!check) return;
			var json = $('#easy-dataform').serializeObject();
			$('#add_submit').attr('disabled','disabled');
			$.messager.progress();
			$.ajax({
				url: baseJS+"/admin/saveAdmin.do",
				type : "POST",
				data : {'data':$.toJSON(json)},
				dataType : "json",
				success : function(data) {
					$.messager.progress('close');
					if(data){
						$.messager.alert("提示消息","修改成功!", 'info', function(){
				    		refresh("用户修改");
				    	});
					}else{
						$.messager.alert("错误消息","修改失败!",'error');
					}
				},
				error : function(XMLHttpRequest, strError, strObject) {
					$('#deploy-add-btn').removeAttr('disabled');
					$.alert('请求失败！');
				}
			});
	   });
	   
	 $("#userpwd").focus(function(){
		 if($("#userpwd").val()=="********************"){
			 $("#userpwd").val("");
		 }
	 });
	   
});

function refresh(title){
	 var appConfigTab = parent.$("#tabs");
	 var datagrid = parent.$("#gridResult");
	 appConfigTab.tabs("close", title);
	 datagrid.datagrid('reload');  
}


