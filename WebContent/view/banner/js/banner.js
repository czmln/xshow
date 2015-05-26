$(function(){
	
	
	$("#add").click(function(){
		
		addTabPage("添加背景图片",'/view/banner/jsp/edit.jsp',"");
	});
	
    $("#edit").click(function(){
    	var selected = $('#result').datagrid('getChecked');
		if(selected == null || selected.length < 1){
			$.alert('请选择您要编辑的记录！');
			return;
		}
		if(selected.length  > 1){
			$.alert('一次只能编辑一条数据，请勾选一条数据！');
			return;
		}
		var id = selected[0].id;
		addTabPage("编辑背景图片",'/banner/edit.do?id='+id,"");
	});
	
	
	$("#result").datagrid({
		  nowrap: true,
			striped: true,
			autoRowHeight: true,
			url:baseJS+"/banner/getByPage.do",
			remoteSort: false,
			singleSelect:false,
			border:false,
			pageSize:20,
			fitColumns:false, 
		    loadMsg:'数据加载中请稍后……', 
			columns:[
	                  [
		               {field:'ck',checkbox:true},
		               {field:'id',title:'ID',align:'center',hidden:true},
		               
		               {field:'imgUrl',title:'背景图片',align:'center',width:'50%',					
							formatter: function(value,row,index){
								if(!value) return "";

							    var img='<div style="padding: 5px 0px 5px 0px;"><img src="'+baseJS+value+'"  height="100"></div>';		
					
								
							   return img;
								
							}
						},
						
						{field:'remarks',title:'备注',align:'center',width:'46%'},
		               
		               ]
	                ],
		     pagination:true,
		     rownumbers:true,
		     toolbar:'#tb',
		     onLoadSuccess: function (data) {       			
		     }   
	  });
	
$("#del").click(function(){
		
		var selected = $('#result').datagrid('getChecked');
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
					url:baseJS+'/banner/del.do',
					type: "POST",
					data: {'id': id},
					dataType:"json",
					success:function(data){
						$.messager.progress('close');
						if(data.msg){
							$('#result').datagrid('reload');
							if(data.rs){
								$.alert(data.msg);
							}else{
								$.messager.alert("错误消息",data.msg,'error');
							}	
						}else{
							$.messager.alert("错误消息","删除失败",'error');
							
						}
					},
					error:function(XMLHttpRequest ,strError ,strObject){
						$.alert('请求失败！');	
					}	
				});
			}
		});
		
		
	});
	
})

//添加Tab选项
 function addTabPage(title,url,mid){
  	var configTab=$('#tabs');
  	configTab.addTab({
  		 title:title,
  		 
  		 url:baseJS+url		
  	});
 }