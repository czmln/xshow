<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<%@ include file="/framework/jsp/header.jsp"%>
<title>资源管理</title>
<script type="text/javascript"
	src="<%=path%>/view/admin/js/resource.js"></script>
</head>
<body class="no-gap">
	<div class="easyui-tabs" data-options="fit:true,border:false" id="tabs">
		<div title="资源管理">

			<table id="gridResult" fit="true" fitColumns="true"></table>

			<div id="tb" style="height: 90px; background: white; padding: 0px;">

				<div class="operate-button" style="border-top: none;">
					<a id="add" href="javascript:void(0);" class="easyui-linkbutton"
						data-options="plain:true,iconCls:'icon-add'">新增</a> <a id="edit"
						href="javascript:void(0);" class="easyui-linkbutton"
						data-options="plain:true,iconCls:'icon-edit'">修改</a> <a id="del"
						href="javascript:void(0);" class="easyui-linkbutton"
						data-options="plain:true,iconCls:'icon-remove'">删除</a>
				</div>

				<fieldset style="padding-top: 3px; margin: 1px;">
					<legend>搜索条件</legend>
					<table>
						<tr>
							<td align="right">资源名</td>
							<td width="100">
								<input id="name" class="easyui-textbox" name="name" type="text" style="width: 100px;" maxlength="10">
							</td>
							<td>
								&ensp;资源类型
							</td>
							<td>
								<select id="rtype" name="rtype" class="easyui-combobox" data-options="panelHeight:100, editable:false">
									<option value="-1">全部</option>
									<option value="1">一级菜单</option>
									<option value="2">二级菜单</option>
									<option value="3">三级菜单</option>
								</select>
							</td>
							<td>
								&ensp;栏目名
							</td>
							<td>
								<input id="menubarId" name="menubarId" class="easyui-combotree" url="<%=path%>/article/getTreeBySite.do"
									style="width:154px;" valueField="id" 
									data-options="onBeforeLoad:onBeforeLoad,
													onBeforeCheck:onbeforecheck,
													cascadeCheck:true,
													lines:true,
													checkbox:false,
													animate:false,
													panelMinHeight:500,
													onLoadSuccess:combotreeSuccess" 
									method="post" textField="text"  editable="false"  />
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
								<a href="javascript:void(0)" id="searchButton" class="easyui-linkbutton queryType" iconCls="icon-search" data-options="plain:true">搜索</a>
							</td>
						</tr>
					</table>
				</fieldset>

			</div>
		</div>
	</div>
</body>
</html>