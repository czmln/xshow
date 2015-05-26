<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<%@ include file="/framework/jsp/header.jsp" %> 
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<title>角色新增</title>
	<script type="text/javascript" src="<%=path%>/view/admin/js/addRole.js"></script>
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
				<legend style="font-weight:bold;">角色管理</legend>
		    	<table class="datafrom" style="width:95%;">
			    		<tr>
			    			<td class="td_head">角&ensp;色&ensp;名: <span style="color:red;">*</span></td>
							<td><input class="easyui-validatebox" id="roleName" type="text" name="roleName" required="true" validType="maxLength[60]" style="width:148px;" ></input></td>
			    		</tr>
			    		
						<tr>	
			    			<td class="td_head">角色状态: <span style="color:red;">*</span></td>
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
