<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<%@ include file="/framework/jsp/header.jsp" %> 
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<title>用户修改</title>
	<script type="text/javascript" src="<%=path%>/view/admin/js/edit.js"></script>
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
				<legend style="font-weight:bold;">用户数据</legend>
		    	<table class="datafrom" style="width:95%;">
			    		<tr>
			    			<td class="td_head">用&ensp;户&ensp;名: <span style="color:red;">*</span></td>
							<td><input class="easyui-validatebox" id="userName" type="text" name="userName" required="true" validType="maxLength[60]" style="width:148px;" value="${demo.userName}"></input></td>
			    		</tr>
			    		<tr>
			    			<td class="td_head">真实姓名: <span style="color:red;">*</span></td>
							<td><input class="easyui-validatebox" id="nickName" type="text" name="nickName" required="true" validType="maxLength[60]" style="width:148px;" value="${demo.nickName}"></input></td>
			    		</tr>
			    		
			    		<tr>	
			    			<td class="td_head">密&emsp;&emsp;码: <span style="color:red;">*</span></td>
							<td><input class="easyui-validatebox" id="userpwd" type="password" name="userpwd" data-options="required:true,validType:['length[6,20]']"  invalidMessage="密码长度为6至20位" style="width:148px;" value="********************" ></input></td>
						</tr>
						
			    		<tr>
			    			<td class="td_head">部&emsp;&emsp;门: <span style="color:red;">*</span></td>
							<td><input class="easyui-validatebox" id="dept" type="text" name="dept" required="true" validType="maxLength[60]" style="width:148px;" value="${demo.dept}"></input></td>
			    		</tr>
						
						
						<tr>	
			    			<td class="td_head">电&emsp;&emsp;话: <span style="color:red;">*</span></td>
							<td><input class="easyui-validatebox" id="telephone" type="text" name="telephone" required="true" validType="maxLength[60]" style="width:148px;" value="${demo.telephone}"></input></td>
						</tr>
						<tr>	
			    			<td class="td_head">移动电话: <span style="color:red;">*</span></td>
							<td><input class="easyui-validatebox" id="mobile" type="text" name="mobile" required="true" validType="maxLength[60]" style="width:148px;" value="${demo.mobile}"></input></td>
						</tr>
						<tr>	
			    			<td class="td_head">电子邮件: <span style="color:red;">*</span></td>
							<td><input class="easyui-validatebox" id="email" type="text" name="email" required="true" validType="maxLength[60]" style="width:148px;" value="${demo.email}"></input></td>
						</tr>
						<tr>	
			    			<td class="td_head">地&emsp;&emsp;址: <span style="color:red;">*</span></td>
							<td><input class="easyui-validatebox" id="address" type="text" name="address" required="true" validType="maxLength[60]" style="width:148px;" value="${demo.address}"></input></td>
						</tr>
						<tr>	
			    			<td class="td_head">用户状态: <span style="color:red;">*</span></td>
							<td>
								<select id="userStatus" class="easyui-combobox" data-options="value:'${demo.userStatus }'" name="userStatus" style="width:148px;">  
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
