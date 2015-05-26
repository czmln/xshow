<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<%@ include file="/framework/jsp/header.jsp" %> 
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<title>用户修改</title>
	<script type="text/javascript" src="<%=path%>/view/admin/js/editResource.js"></script>
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
			<input type="hidden" id="id" name="id" value="${demo.id}">
			<div class="icon-information-1" style="width:100%; height:24px; line-height:24px; margin-bottom:8px; text-indent:25px;">提示：<font color="#ff0000">*</font>为必填项!</div>
    		<fieldset style="margin: 5px;">
				<legend style="font-weight:bold;">资源数据</legend>
		    	<table class="datafrom" style="width:95%;">
			    		<tr>
			    			<td class="td_head">资&ensp;源&ensp;名: <span style="color:red;">*</span></td>
							<td><input class="easyui-validatebox" id="resourceName" type="text" name="resourceName" required="true"  style="width:148px;" value="${demo.resourceName}"></input></td>
			    		</tr>
			    		<tr>	
			    			<td class="td_head">资源类型: <span style="color:red;">*</span></td>
							<td>
								<select id="resourceType" class="easyui-combobox" data-options="onSelect:specal_normal" name="resourceType" style="width:148px;">  
								    <option value="1" <c:if test="${demo.resourceType=='1'}">selected="selected"</c:if>>一级菜单</option>  
								    <option value="2" <c:if test="${demo.resourceType=='2'}">selected="selected"</c:if>>二级菜单</option> 
								    <option value="3" <c:if test="${demo.resourceType=='3'}">selected="selected"</c:if>>三级菜单</option>
								</select>  
							</td>
						</tr>
						 
						<tbody id="specal_type" <c:choose>  
                				<c:when test="${demo.menubarId==null || demo.menubarId==''}">style="display:none;"</c:when>  
               					 <c:otherwise>style="display:black;"</c:otherwise>  
            			</c:choose>  >
						<tr>	
			    			<td class="td_head">导航栏目: </td>
							<td>
								<input id="menubarId" name="menubarId" class="easyui-combotree" url="<%=path%>/article/getTreeBySite.do"
									style="width:154px;" valueField="id" method="post" data-options="onBeforeLoad:onBeforeLoad,onBeforeCheck:onbeforecheck,cascadeCheck:true,lines:true,checkbox:false,animate:false,panelMinHeight:500,onLoadSuccess:combotreeSuccess" method="post" textField="text"  editable="false"    value="${demo.menubarId}"/>
							</td>
						</tr>
						</tbody>
						<tbody id="normal_type" <c:choose>  
                				<c:when test="${demo.menubarId==null || demo.menubarId==''}">style="display:black;"</c:when>  
               					 <c:otherwise>style="display:none;"</c:otherwise>  
            			</c:choose>  >
						<tr>	
			    			<td class="td_head">父&ensp;节&ensp;点: &ensp;</td>
							<td>
								<input id="parentId" name="parentId" class="easyui-combobox" url="<%=path%>/resource/getResourceByParentId.do"
									style="width:148px;" valueField="id" textField="resourceName"  editable="false"  value="${demo.parentId}" />
							</td>
						</tr>
						<tr>	
			    			<td class="td_head">链接地址: &ensp;</td>
							<td><input  id="entryUrl" type="text" name="entryUrl"   style="width:148px;" value="${demo.entryUrl}"></input></td>
						</tr>
						<tr>
			    			<td class="td_head">短&ensp;链&ensp;接: <span style="color:red;">*</span></td>
							<td><input class="easyui-validatebox" id="resourceCode" type="text" name="resourceCode" required="true"  style="width:148px;" value="${demo.resourceCode}"></input></td>
						</tr>
						</tbody>
						<tr>	
			    			<td class="td_head">状&emsp;&emsp;态: <span style="color:red;">*</span></td>
							<td>
								<select id="status" class="easyui-combobox" data-options="value:'${demo.status }'" name="status" style="width:148px;">  
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
<script type="text/javascript">

var menubarId="${demo.menubarId}";
$(function(){
	 if(menubarId && menubarId!=null){
		   $("#entryUrl").val("");
		   $("resourceCode").val("");
		   $("#parentId").combobox({required:false});
		   $("#parentId").removeClass("validatebox-text validatebox-invalid").unbind('focus').unbind('blur');
		   $("#resourceCode").validatebox({required:false});
		   $("#resourceCode").removeClass("validatebox-text validatebox-invalid").unbind('focus').unbind('blur');
	   }else{
		   $("#menubarId").removeClass("validatebox-text validatebox-invalid").unbind('focus').unbind('blur');
	   }
	
})
</script>
