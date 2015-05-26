$(function(){
    $("#add").click(function(){
    	addTabPage("添加文章",'/view/article/jsp/edit.jsp',"");
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
		addTabPage("编辑文章",'/article/edit.do?id='+id,"");
	});
    
    $("#result").datagrid({
		    nowrap: true,
			striped: true,
			autoRowHeight: true,
			url:baseJS+"/article/getArticleByPage.do",
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
		               {field:'title',title:'标题',align:'center',width:'12%'},
		               {field:'source',title:'出处',align:'center',width:'12%'},
		               {field:'author',title:'作者',align:'center',width:'12%'},
		               {field:'issuedDate',title:'发表时间',align:'center',width:'12%',
		            	   formatter: function(value,row,index){
								if(value){
									return new Date(value).Format("yyyy-MM-dd hh:mm:ss");
								}
								return "";								
							}		               
		               },
		               {field:'updateTime',title:'修改时间',align:'center',width:'12%',
		            	   formatter: function(value,row,index){
								if(value){
									return new Date(value).Format("yyyy-MM-dd hh:mm:ss");
								}
								return "";
								
							}
		               },
		               
		               {field:'menubar',title:'一级栏目',align:'center',width:'12%',
		            	   formatter: function(value,row,index){
		            		   if(value){
		            			  var mp=value.menubar; 
		            			  if(mp){
		            				  return mp.name;
		            			  }else{
		            				  return value.name; 
		            			  }
		            		   }else{
		            			   return "";
		            		   }
		            		   
		            	   }
		               },
		               
		               {field:'menubar2',title:'二级栏目',align:'center',width:'12%',
		            	   formatter: function(value,row,index){ 
		            		      var mp=row["menubar"].menubar;
		            			  if(mp){
		            				  return row["menubar"].name;
		            			  }
		            		      return "";
		            	   }
		               
		               },
		               
		               {field:'coverImgUrl',title:'封面图片',align:'center',width:'13%',					
							formatter: function(value,row,index){
								if(value){								
									return '<div style="padding:5px 0px 5px 0px;"><img height="60"  src='+baseJS+value+'>';
								}	else{
									return "";
								}
							}
						}
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
					url:baseJS+'/article/del.do',
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
								$.messager.alert("错误消息","请先删除该模版下的站点，在删除该模版",'error');
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
});

//添加Tab选项
 function addTabPage(title,url,mid){
  	var configTab=$('#tabs');
  	configTab.addTab({
  		 title:title,
  		 
  		 url:baseJS+url		
  	});
  	
  	
 }