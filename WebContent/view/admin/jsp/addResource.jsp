<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<%@ include file="/framework/jsp/header.jsp" %> 
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<title>资源新增</title>
	<script type="text/javascript" src="<%=path%>/view/admin/js/addResource.js"></script>
	<style type="text/css">
		.datafrom{
		width:95%;
		}
		.td_head{
		text-align: right;
		font-size:12px;
		}
	</style>
  </head>
 <body class="easyui-layout">
 	<div data-options="region:'north',border:false" style="background:#fff; padding:5px 10px 5px 10px;">
 	<button id="add_submit" >保存</button>
	</div>
	<div data-options="region:'center',border:false" style="background:#fff;">
    	<form id="easy-dataform"  name="easy-dataform"  method="post" target="_self"  style="padding:0 10px 10px 10px;">
			<div class="icon-information-1" style="width:100%; height:24px; line-height:24px; margin-bottom:8px; text-indent:25px;">提示：<font color="#ff0000">*</font>为必填项!</div>
    		<fieldset style="margin: 5px;">
				<legend style="font-weight:bold;">资源管理</legend>
		    	<table class="datafrom" style="width:95%;">
			    		<tr>
			    			<td class="td_head">资&ensp;源&ensp;名: <span style="color:red;">*</span></td>
							<td><input class="easyui-validatebox" id="resourceName" type="text" name="resourceName" required="true" validType="maxLength[60]" style="width:148px;" ></input></td>
			    		</tr>
			    		<tr>	
			    			<td class="td_head">资源类型: <span style="color:red;">*</span></td>
							<td>
								<select id="resourceType" class="easyui-combobox" data-options="onSelect:specal_normal" name="resourceType" style="width:148px;">  
								    <option value="1">一级菜单</option>  
								    <option value="2">二级菜单</option> 
								    <option value="3">三级菜单</option>
								</select>  
							</td>
						</tr>
						<tbody id="specal_type" style="display:none;">
						<tr>	
			    			<td class="td_head">导航栏目:<span style="color:red;">*</span> </td>
							<td>
								<input id="menubarId" name="menubarId" class="easyui-combotree" url="<%=path%>/article/getTreeBySite.do"
									style="width:154px;" valueField="id" data-options="onBeforeLoad:onBeforeLoad,onBeforeCheck:onbeforecheck,cascadeCheck:true,lines:true,checkbox:false,animate:false,panelMinHeight:500,onLoadSuccess:combotreeSuccess" method="post" textField="text"  editable="false"  />
							</td>
						</tr>
						</tbody>
						<tbody id="normal_type" style="display:black;">
						<tr>	
			    			<td class="td_head">父&ensp;节&ensp;点: &ensp;</td>
							<td>
								<input id="parentId" name="parentId" class="easyui-combobox" url="<%=path%>/resource/getResourceByParentId.do"
									style="width:148px;" valueField="id" textField="resourceName"  editable="false"  />
							</td>
						</tr>
						<tr>	
			    			<td class="td_head">链接地址: &ensp;</td>
							<td><input class="easyui-validatebox" id="entryUrl" type="text" name="entryUrl"  validType="maxLength[60]" style="width:148px;" ></input></td>
						</tr>
						<tr>
			    			<td class="td_head">短&ensp;链&ensp;接: <span style="color:red;">*</span></td>
							<td><input class="easyui-validatebox" id="resourceCode" type="text" name="resourceCode" required="true" validType="maxLength[60]" style="width:148px;" ></input></td>
						</tr>
						</tbody>
						<tr>	
			    			<td class="td_head">状&emsp;&emsp;态: <span style="color:red;">*</span></td>
							<td>
								<select id="status" class="easyui-combobox" name="status" style="width:148px;">  
								    <option value="1">启用</option>  
								    <option value="0">停用</option>  
								</select>  
							</td>
						</tr>
	    		</table>
	    	</fieldset>
    	</form>
	</div>
 </body>
</html>
