<%@ page language="java" contentType="text/html;charset=utf-8" %>
<html>
<head>
<%@ include file="/framework/jsp/header.jsp"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<script type="text/javascript">
	var zNodes;

	var setting = {
		data : {
			simpleData : {
				enable : true
			}
		}
	};

	//添加Tab选项
	function addTabPage(id,title, url) {
			$('#tabs').addTab({
				title : title,
				url : baseJS + url
			});
	}
	

	$(document).ready(function() {
	
		$.ajax( {
		    url: baseJS + '/resource/getTree.do',
		    type:'post',
		    cache:false,
		    dataType:'json',
		    success:function(data) {
		    	zNodes = data;
		    	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		    }
		});
		
		
	});

	function CloseBrowse()//用户关闭浏览器
	{
		var n = window.event.screenX - window.screenLeft;
		var b = n > document.documentElement.scrollWidth - 20;
		if (b && window.event.clientY < 0 || window.event.altKey) {
			clearSession();
		}
	}
	
	function clearSession(){
		$.ajax({
			url : path + '/signout',
			type : "post",
			success : function(data) {
			},
			error : function(xhr, s1, s2) {
			}
		});
	}
	
	function confirmLogout() {
		$.messager.confirm('操作提示', '您确定要注销系统吗？', function(data) {
			if (data) {
				location.href = "<%=request.getContextPath()%>/j_spring_security_logout";
			}
		});
	}
	
	
</script>

<style>
.div2 {
	float: right; color:white
}

.div2 a {
	padding: 0 7px; color:white; line-height: 24px;
}

.div2 img {
	vertical-align: middle;
	border: 0
}
</style>

<title>后台管理平台</title>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height: 60px;background: url('<%=path%>/framework/desktop/images/top_bg.jpg') repeat-x;">

		<div class="loger" style="width: 300px;float: left;">
			
		</div>

		<div class="div2">
				<a><img src="<%=path%>/framework/desktop/images/head_subject.png" />欢迎你：<sec:authentication property="name"></sec:authentication>  </a>
				<a id="t_modifyPwd" href="javascript:void(0)"><img src="<%=path%>/framework/desktop/images/editPwd.png" onclick="javascript:void(0);"/>修改密码</a>
				<a onclick="return confirmLogout();" href="#">
					<img src="<%=path%>/framework/desktop/images/head_out.png"/>注销</a>
				<br/>
				<a><img src="<%=path%>/framework/desktop/images/help.png"/>系统版本:1.0.0</a>
		</div>

	</div>
	<div data-options="region:'west',split:true,border:false,title:' '" style="width: 218px">  
            <div class="easyui-accordion" data-options="fit:true,border:false">  
                <div title="系统主菜单" data-options="selected:true,iconCls:'icon-application-view-list'">  
                    <ul id="treeDemo" class="ztree"></ul>
                </div>   
            </div>  
     </div>

	<div data-options="region:'center',border:false"
		style="margin-left: 0px">
		<div class="easyui-tabs" id="tabs" data-options="fit:true">
			<div title="欢迎页" data-options="cache:true" >
				<iframe id="iframe" scrolling="no" frameborder="0"
					src="<%=path%>/framework/desktop/welcome.jsp"
					style="width: 100%; height: 100%;"></iframe>
			</div>
		</div>
	</div>
	<div id="tabsMenu" class="easyui-menu" style="width: 120px;">
		<div name="close">关闭</div>
		<div name="Other">关闭其他</div>
		<div name="All">关闭所有</div>
	</div>
	<script type="text/javascript">
		//绑定tabs的右键菜单
		$("#tabs").tabs({
			onContextMenu : function(e, title) {
				e.preventDefault();
				$('#tabsMenu').menu('show', {
					left : e.pageX,
					top : e.pageY
				}).data("tabTitle", title);
			}
		});

		//实例化menu的onClick事件
		$("#tabsMenu").menu({
			onClick : function(item) {
				CloseTab(this, item.name);
			}
		});

		//几个关闭事件的实现
		function CloseTab(menu, type) {
			var curTabTitle = $(menu).data("tabTitle");
			var tabs = $("#tabs");

			if (type === "close") {
				tabs.tabs("close", curTabTitle);
				return;
			}

			var allTabs = tabs.tabs("tabs");
			var closeTabsTitle = [];

			$.each(allTabs, function() {
				var opt = $(this).panel("options");
				if (opt.closable && opt.title != curTabTitle
						&& type === "Other") {
					closeTabsTitle.push(opt.title);
				} else if (opt.closable && type === "All") {
					closeTabsTitle.push(opt.title);
				}
			});

			for ( var i = 0; i < closeTabsTitle.length; i++) {
				tabs.tabs("close", closeTabsTitle[i]);
			}
		}

// 		$.ajax({
// 			url : path + '/loginse',
// 			type : "post",
// 			success : function(data) {
// 				//alert("fuck");
// 			},
// 			error : function(xhr, s1, s2) {
// 			}
// 		});
	</script>
    <div id="t_vaildateUser">
    	<form id="t_pwdForm">
	 	<div>
			 <a href="javascript:void(0)" class="easyui-linkbutton savebutton" id="submitData" iconCls="icon-save" plain="true">提交</a>
			 <a href="javascript:void(0)" class="easyui-linkbutton savebutton" iconCls="icon-remove" plain="true" onclick="$('#t_password').val('');$('#t_vaildateUser').dialog('close')">取消</a>
	 	</div>
	 	<table>
	 		<tr>
	 			<td>用户名：</td>
	 			<td> <input type="text" id="userName" name="userName" value="<sec:authentication property="name"></sec:authentication>" readonly="readonly" disabled="disabled"/></td>
	 		</tr>
	 		<tr>
	 			<td>原密码：<font color="red">*</font></td>
	 			<td> <input type="password" id="opassword" name="opassword" class="easyui-validatebox" data-options="required:true"  /></td>
	 		</tr>
	 		<tr>
	 			<td>新密码：<font color="red">*</font></td>
	 			<td> <input type="password" id="npassword" name="npassword" class="easyui-validatebox" data-options="required:true,validType:['length[6,20]']" invalidMessage="密码长度为6至20位" /></td>
	 		</tr>
	 		<tr>
	 			<td>确认密码：<font color="red">*</font></td>
	 			<td> <input type="password" id="password2" name="password2" class="easyui-validatebox" data-options="required:true,validType:['length[6,20]']"  invalidMessage="密码长度为6至20位"/></td>
	 		</tr>
	 	</table>
	 	</form>
	</div>
<script type="text/javascript">
$(function(){
	$('#t_modifyPwd').click(function(){$('#t_vaildateUser').dialog('open');})
	$('#t_vaildateUser').dialog({  
	    title: '修改密码',  
	   	width: 300,  
	   	height: 200,  
	    closed: false,
	    cache: false,  
	    modal: true  
	}).dialog('close');
	
	$('#submitData').click(function(){
		
		var password1 = $("#npassword").val();
		var password2 = $("#password2").val();
		if(password2 != password1){
			$.messager.alert("提示","<br/>两次密码输入不一致","error");
			return;
		}
		if($("#t_pwdForm").form("validate")){ 
			$('#submitData').attr('disabled','disabled');
			$.messager.progress();
			jQuery.ajax({
				url: baseJS+"/admin/resetPassword.do",
				type: "POST",
				data: {'opassword':$("#opassword").val(),'npassword':$("#npassword").val()},
				dataType:"json",
				success:function(data){
					$.messager.progress('close');
					if (data.rs) {
						$.messager.alert("提示",data.msg, 'info', function(){
							location.href = '<%=request.getContextPath()%>/j_spring_security_logout';
				    	});
					}else{
						$.messager.alert("提示","<br/>"+data.msg,"warning");
						$('#submitData').removeAttr('disabled');
					}
				}
			});
		}

	});
	
	
});
</script>
</body>
</html>