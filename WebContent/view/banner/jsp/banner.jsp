<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<%@ include file="/framework/jsp/header.jsp" %>
<title>背景图管理</title>

<script type="text/javascript" src="<%=path %>/view/banner/js/banner.js"></script>

</head>
 <body class="no-gap"> 
 <div class="easyui-tabs" data-options="fit:true,border:false" id="tabs">
	 <div title="背景图管理"> 
	
		<table id="result" fit="true" fitColumns="true"></table>

		<div id="tb" style="height:100px;background: white;padding:0px;">

		<div class="operate-button" style="border-top: none;padding: 5px 0px 5px 5px;">
    		<a  id="add" href="javascript:void(0);"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">新增</a>    
    		<a  id="edit" href="javascript:void(0);"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>  		
    		<a  id="del" href="javascript:void(0);"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'">删除</a>
    	</div>
		
   	 <fieldset  style="padding-top:3px;margin: 1px;">
   		<legend>搜索条件</legend>
   		 <form action="" method="post" id="query_form">
   		<table>
   		  
			<tr>
    		  <td align="right">备注:</td>
			  <td width="80">
			     <input id="template_name_query" class="easyui-textbox" name="name"  type="text"	>
			  </td>
			  
			 <td align="right">	     
			  <a href="javascript:void(0)" id="searchButton"  class="easyui-linkbutton queryType" iconCls="icon-search"	data-options="plain:true">查询</a>
			  </td>
    		</tr>
    	   
   		</table>
   		</form>
   		</fieldset>
   		</div>
	</div> 
</div>




</body>
</html>