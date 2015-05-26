$(document).ready(function(){
	$('#searchButton').click(function(){
		var param = {
				name: $('#name').val(),
				rtype: $('#rtype').combobox('getValue'),
				menubarId: $('#menubarId').combobox('getValue'),
				state: $('#state').combobox('getValue')
			};
		$("#gridResult").datagrid("load", param);
		$("#gridResult").datagrid("clearSelections");
	});
	
	$('#gridResult').datagrid({
		nowrap: true,
		striped: true,
		autoRowHeight: true,
		url:baseJS+"/resource/getResource.do",
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
				{field:'resourceName',width:'20%',title:'资源名',align:'left'},
				{field:'resourceType',width:'20%',title:'资源类型',align:'left', 
					formatter: function(value, row, index) {
						var str = "";
						if(value == 1) {
							str = "一级菜单";
						} else if(value == 2) {
							str = " 二级菜单";
						} else {
							str = "三级菜单";
						}
						
						return str;
					}
				},
				{field:'parentname',width:'20%',title:'父节点',align:'left'},
				{field:'entryUrl',width:'10%',title:'链接地址',align:'left'},
				{field:'menubarName',width:'10%',title:'栏目名称',align:'left'},
				{field:'statusname',width:'15%',title:'角色状态',align:'left'}
			]
		],
		pagination:true,
		rownumbers:true,
		toolbar:'#tb',
		onLoadSuccess: function (data) {
			
		}
	});
	

	$('#add').click(function(){
		addTabPage('资源增加','/view/admin/jsp/addResource.jsp');
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
		addTabPage('资源修改','/resource/goEdit.do?id='+id);
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
					url:baseJS+'/resource/delResource.do',
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