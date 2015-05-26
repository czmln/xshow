$(document).ready(function(){

	$('#searchButton').click(function(){
		var param = {
				name: $('#name').val(),
				state: $('#state').combobox("getValue")
			};
		$("#gridResult").datagrid("load", param);
		$("#gridResult").datagrid("clearSelections");
	});
	

	$('#gridResult').datagrid({
		nowrap: true,
		striped: true,
		autoRowHeight: true,
		url:baseJS+"/role/getRole.do",
		remoteSort: false,
		singleSelect:false,
		resizeHandle:'both',
		border:false,
		pageSize:20,
		fitColumns:false, 
	    loadMsg:'数据加载中请稍后……', 
		columns:[
	         [
				{field:'ck',checkbox:true},
				{field:'id',width:'9%',title:'ID',width:120,align:'center',hidden:true},
				{field:'roleName',width:'47%',title:'角色名',align:'left'},
				{field:'status',width:'47%',title:'角色状态',align:'left',formatter:function(value,row,index){
					
					if(value=="1"){
						
						return "启用";
					}else{
						return "停用";
					}
				}}
			]
		],
		pagination:true,
		rownumbers:true,
		toolbar:'#tb',
		onLoadSuccess: function (data) {
			
		}
	});
	

	$('#add').click(function(){
		addTabPage('角色增加','/view/admin/jsp/addRole.jsp');
	 });
	

	$('#edit').click(function(){
		var selected = $('#gridResult').datagrid('getChecked');
		if(selected == null || selected.length < 1){
			$.alert('请选择您要编辑的记录！');
			return;
		}
		if(selected.length  > 1){
			$.alert('一次只能编辑一条数据，请勾选一条数据！');
			return;
		}
		var id = selected[0].id;
		addTabPage('角色修改','/role/goEdit.do?id='+id);
	});
	

	$('#res').click(function(){
		var selected = $('#gridResult').datagrid('getChecked');
		if(selected == null || selected.length < 1){
			$.alert('请选择您要编辑的记录！');
			return;
		}
		if(selected.length  > 1){
			$.alert('一次只能编辑一条数据，请勾选一条数据！');
			return;
		}
		var id = selected[0].id;
		addTabPage('角色授权','/view/admin/jsp/roleResource.jsp?id='+id);
	});
	

	$('#del').click(function(){
		var selected = $('#gridResult').datagrid('getChecked');
		if(selected == null || selected.length < 1){
			$.alert('请选择您要删除的记录！');
			return;
		}

		var ids = new Array();
		for(var i = 0 ; i < selected.length ; i++){
			ids.push(selected[i].id);
		}
		var id = ids.join(",");
		$.messager.confirm('确认信息','是否确认删除选中的对象？',function(r){  
			if(r){
				$.messager.progress();
				$.ajax({
					url:baseJS+'/role/delRole.do',
					type: "POST",
					data: {'id': id},
					dataType:"json",
					success:function(data){
						$.messager.progress('close');
						if(data){
							$('#gridResult').datagrid('reload');
							$.alert('删除成功！');
						}else{
							$.messager.alert("错误消息","删除失败!",'error');
						}
					},
					error:function(XMLHttpRequest ,strError ,strObject){
						$.alert('请求失败！');	
					}	
				});
			}
		});
	});
	
	
});




function addTabPage(title,url){
	var configTab=$('#tabs');
	configTab.addTab({
		 title:title,
		 url:baseJS+url		
	});
}


function showScroll() {

	$('#gridResult').datagrid('insertRow', {
		row: {
		}
		});
		$("tr[datagrid-row-index='0']").css({"visibility":"hidden"});
}