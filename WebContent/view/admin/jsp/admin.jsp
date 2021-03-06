<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<%@ include file="/framework/jsp/header.jsp" %>
<title>用户管理</title>
<script type="text/javascript" src="<%=path %>/view/admin/js/admin.js"></script>
</head>
 <body class="no-gap"> 
 <div class="easyui-tabs" data-options="fit:true,border:false" id="tabs">
	<div title="用户管理">
	
		<table id="gridResult" fit="true" fitColumns="true"></table>
		
		<div id="tb" style="height:90px;background: white;padding:0px;">
		
		<div class="operate-button" style="border-top: none;">
    		<a id="add" href="javascript:void(0);"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">新增</a>
    		<a  id="edit" href="javascript:void(0);"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">修改</a>
    		<a  id="del" href="javascript:void(0);"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'">删除</a>
    		<a  id="res" href="javascript:void(0);"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-yhsq'">授权</a>
    	</div>
		
   		<fieldset  style="padding-top:3px;margin: 1px;">
   		<legend>搜索条件</legend>
   		<table>
			<tr>
    		  <td align="right">用户名</td>
			  <td width="100">
			  	<input id="name" name="name"  type="text" class="easyui-textbox" style="width:100px;" maxlength="10">
			  </td>
			  <td>
				&ensp;姓名
			  </td>
			  <td>
			  	<input id="nickName" name="nickName" class="easyui-textbox" style="width:100px;" maxlength="10" type="text"/>
			  </td>
			  <td>
			  	&ensp;部门
			  </td>
			  <td>
			  	<input id="dept" name="dept" class="easyui-textbox" style="width:100px;" maxlength="15"/>
			  </td>
			  <td>
			  	&ensp;地址
			  </td>
			  <td>
			  	<input id="address" name="address" class="easyui-textbox" style="width:100px;" maxlength="15"/>
			  </td>
			  <td>
			  	&ensp;状态
			  </td>
			  <td>
			  	<select id="state" name="state" class="easyui-combobox" data-options="panelHeight:80, editable:false">
			  		<option value="-1">全部</option>
			  		<option value="1">启用</option>
			  		<option value="0">停用</option>
			  	</select>
			  </td>
			  
			 <td align="right">   
			  <a href="javascript:void(0)" id="searchButton"  class="easyui-linkbutton queryType" iconCls="icon-search"	data-options="plain:true">搜索</a>
			  </td>
    		</tr>
   		</table>
   		</fieldset>
   		
   		</div>
	</div>
</div>
</body>
</html>