$(function(){  

	   $('#add_submit').click(function(){
			var check = $('#easy-dataform').form('validate');
			if(!check) return;
			var json = $('#easy-dataform').serializeObject();
			$('#add_submit').attr('disabled','disabled');
			$.messager.progress();
			$.ajax({
				url: baseJS+"/resource/saveResource.do",
				type : "POST",
				data : {'data':$.toJSON(json)},
				dataType : "json",
				success : function(data) {
					$.messager.progress('close');
					if(data){
						$.messager.alert("提示消息","新增成功!", 'info', function(){
				    		refresh("资源增加");
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

function specal_normal(record){
	var v=record.value;
	
	 if(v=="3"){
		   
		   $("#specal_type").show();
		   $("#normal_type").hide();
		   $("#parentId").combobox("clear");
		   //$('#menubarId').combobox({required:true});
		   $("#entryUrl").val("");
		   $("resourceCode").val("");
		   $('#menubarId').combotree('setValues',combotreedata[0].id);
		   $("#resourceCode").validatebox({required:false});
		   $("#resourceCode").removeClass("validatebox-text validatebox-invalid").unbind('focus').unbind('blur');
	   }else{
		   //$('#menubarId').combobox({required:false});
		   $("#menubarId").combotree("clear");
		   $("#menubarId").removeClass("validatebox-text validatebox-invalid").unbind('focus').unbind('blur');
		   $("#specal_type").hide();
		   $("#normal_type").show();
		   $("#resourceCode").validatebox({required:true});
		   $("#resourceCode").addClass("validatebox-text");
		   
	   }
}

var combotreedata;
var havedata=false;
function combotreeSuccess(node, data){
	combotreedata = data;
	havedata=true;
}
function onbeforecheck(node, checked){
	var nodes = $('#menubarId').combotree("tree");
	var v=nodes.tree("getChecked",["checked","indeterminate"]);
}
function onBeforeLoad(node, param){
	
	if(havedata){
		return false;
	}
	return true;
}
function refresh(title){
	 var appConfigTab = parent.$("#tabs");
	 var datagrid = parent.$("#gridResult");
	 appConfigTab.tabs("close", title);
	 datagrid.datagrid('reload');  
}


