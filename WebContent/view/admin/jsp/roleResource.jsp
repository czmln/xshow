<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<%@ include file="/framework/jsp/header.jsp" %> 
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<title>角色授权</title>
	<script type="text/javascript" src="<%=path%>/view/admin/js/roleResource.js"></script>
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
			<input id="roleId" name="roleId" type="hidden" value="<%=request.getParameter("id")%>">
			<input id="resourceId" name="resourceId" type="hidden" >
			<table class="datafrom" style="width:95%;">
				<tr>
					<td height="500px;" width="40%">
						已获取资源：<br>
						<table id="gridResult1" fit="true" fitColumns="true"></table>
					</td>
					
					<td width="10%">
						<input type="button" id="fuz" value="<<">
						<input type="button" id="sz" value=">>">
					</td>
					
					<td width="40%">
						<br>
						未获取资源：<br>
						<table id="gridResult2" fit="true" fitColumns="true"></table>
					</td>
					
					
				
				</tr>
			</table>	
			
    	</form>
	</div>
 </body>
</html>