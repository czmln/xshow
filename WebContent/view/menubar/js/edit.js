$(function(){
	
	// var ue = UE.getEditor('remarks');

    //保存数据
	$("#save_btn").click(function(){
		var me=$(this);
		var form=$("#edit_form");
		var check=form.form('validate');
		if(!check)  return;
		var json =form.serialize();		
		me.attr('disabled','disabled');
		$.messager.progress();		
		$.ajax({
		     type: 'POST',
			 url:form.attr("action"),
			 dataType:'json',
			 data:json,
			 success: function(data) {
				 $.messager.progress('close');				  
				  if(data.msg){
						$.messager.alert("提示消息",data.msg,"info",function(){
							if(data.rs){
								 if($("#id").val()){
									return  refresh("编辑栏目");
								 }
			                	  refresh("添加栏目");
							}
						});
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
	 var datagrid = parent.$("#result");
	 appConfigTab.tabs("close", title);
	 datagrid.treegrid('reload');  
}