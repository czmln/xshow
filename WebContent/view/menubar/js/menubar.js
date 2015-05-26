$(function(){
	
	$("#add").click(function(){
		addTabPage("添加栏目",'/view/menubar/jsp/edit.jsp',"");
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
		addTabPage("编辑栏目",'/menubar/edit.do?id='+id,"");
	});
	
	$("#result").treegrid({    
	    url:baseJS+'/menubar/getMenubarByPage.do',    
	    idField:'id',    
	    treeField:'name',
	    remoteSort: false,
		singleSelect:false,
		border:false,
		pageSize:20,
		fitColumns:false, 
	    loadMsg:'数据加载中请稍后……', 
	    columns:[[   
            {field:'ck',checkbox:true},
	        {field:'name', title:'名称',width:'17%'},    
	        {field:'navType',title:'栏目类型',width:'17%',
	        	formatter: function(value,row,index){
	        		if(value=='10011'){
	        			return "单页面"
	        		}else if(value=='10012'){
	        			return "数据列表";
	        		}
	        	}
	        },    
	        {field:'ord',title:'排序',width:'19%'},    
	        {field:'addDate',title:'添加时间',width:'9%',
	        	formatter: function(value,row,index){
	        		if(value){
	        			return new Date(value).Format("yyyy-MM-dd hh:mm:ss");
	        	    }else{
	        	    	return "";
	        	    }
	        	}
	        },
	        {field:'enable',title:'是否启用',width:'17%',
	        	formatter: function(value,row,index){
	        		if(value=='1'){
	        			return "启用";
	        	    }else{
	        	    	return "禁用";
	        	    }
	        	}
	        }, 
	        {field:'editDate',title:'修改时间',width:'18%',
	        	formatter: function(value,row,index){
	        		if(value){
	        			return new Date(value).Format("yyyy-MM-dd hh:mm:ss");
	        	    }else{
	        	    	return "";
	        	    }
	        	}
	        
	        }
	    ]],
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
					url:baseJS+'/menubar/del.do',
					type: "POST",
					data: {'id': id},
					dataType:"json",
					success:function(data){
						$.messager.progress('close');
						if(data.msg){
							$('#result').treegrid('reload');
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
	
	
	$("#searchButton").click(function(){
		var query_form=$("#query_form");
		var parms=query_form.serializeObject();
		var resultGrid=$("#result");
		resultGrid.treegrid("getPager").pagination({pageNumber:1}); 
		resultGrid.treegrid("load",parms);
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