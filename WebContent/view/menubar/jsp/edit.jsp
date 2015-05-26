<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<%@ include file="/framework/jsp/header.jsp" %> 
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<title>编辑模版</title>
	<script type="text/javascript" src="<%=path%>/view/menubar/js/edit.js"></script>
    
    <link href="<%=path%>/framework/webuploader0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<%=path%>/framework/webuploader0.1.5/webuploader.js"></script>
  


<style type="text/css">
		.datafrom{
		width:95%;
		}
		.td_head{
		text-align: right;
		font-size:12px;
		}
.td_textarea{	

	-moz-border-radius: 5px;   
    -webkit-border-radius: 5px;   
    border-radius:5px;
    border:1px solid #95B8E7;
}
	</style>
  </head>
 <body class="easyui-layout">
 	<div data-options="region:'north',border:false" style="background:#fff; padding:5px 10px 5px 10px;">
 	   <a href="javascript:void(0)"  class="easyui-linkbutton" style="width: 80px;" id="save_btn" >保存</a>
	</div>
	<div data-options="region:'center',border:false" style="background:#fff;">
    <form id="edit_form"  name="easy-dataform"  method="post" target="_self"  style="padding:0 10px 10px 10px;" action="<%=path%>/menubar/save.do">
			<input type="hidden" id="id" name="id" value="${menubar.id}">
			<div class="icon-information-1" style="width:100%; height:24px; line-height:24px; margin-bottom:8px; text-indent:25px;">提示：<font color="#ff0000">*</font>为必填项!</div>
    		<fieldset style="margin: 5px;">
				<legend style="font-weight:bold;"></legend>
		    
		       <table style="width:590px;margin: 0 auto;"   cellspacing="12">
		      
		         <tr >
		            <td class="td_head"  >栏目名称: <span style="color:red;">*</span></td>
		            <td>
		              <input  id="menubar_name" name="name" class="easyui-textbox" 	 style="width: 200px;"	value="${menubar.name}"               
		                missingMessage="请输入栏目名称"   
		                required="true"  
		                 >
		            </td>
		            
		            
		             
		         </tr>
		         
		         <tr >
		            <td class="td_head"  >链接url: <span style="color:red;">*</span></td>
		            <td>
		              <input  id="menubar_url" name="linkUrl" class="easyui-textbox" 	 style="width: 200px;"	value="${menubar.name}"               
		                missingMessage="链接url"   
		                required="true"  
		                 >
		            </td>
		         </tr>
		         
		         
		         <tr >
		            <td class="td_head"  >栏目类型: <span style="color:red;">*</span></td>
		            <td>
		                <select id="menubar_navType" editable="false" class="easyui-combobox" name="navType" style="width: 200px;" required="true" data-options="value:'${menubar.navType}'">
								<option value="10011">单页面</option>
								<option value="10012">文字列表</option>	
								<option value="10013">图片列表</option>
								<option value="10014">文字图片列表</option>
						</select>
						</td>
		         </tr>
 
		         <tr >
		             <td class="td_head"  >排序: <span style="color:red;">&nbsp;</span></td>
		             <td  >
		               <input type="text" class="easyui-numberspinner" name="ord" style="width: 200px;" data-options="min:0,precision:0" value="${menubar.ord}" ></input>  
		             
		             </td>
		         </tr>		
		             
		         <tr style="display: none">
		             <td class="td_head"  >父栏目: <span style="color:red;">&nbsp;</span></td>
		             <td  >
                         <input id="menubar_menubar_id" class="easyui-combobox" editable="false" name="menubarId" style="width: 200px;" data-options="valueField:'id',textField:'name',url:'<%=path%>/menubar/getAll.do',value:'${!empty menubar.menubar?menubar.menubar.id:"" }' " />  		             
		             </td>
		         </tr>
		         
		          <tr >
		            <td class="td_head"  >是否启用: <span style="color:red;">*</span></td>
		            <td>
		              <select id="menubar_enable" class="easyui-combobox" name="enable" style="width: 200px;" required="true" editable="false" data-options="value:'${menubar.enable}'">
								<option value="1">启用</option>
								<option value="0">禁用</option>								
						</select>
					</td>
		         </tr>
		         
		      </table>
		    	
		    	
		    	
	    	</fieldset>
    	</form> 
	</div>
	
 </body>
</html>
