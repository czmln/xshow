<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<%@ include file="/framework/jsp/header.jsp" %> 
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<title>机构信息</title>
	<script type="text/javascript" src="<%=path%>/view/org/js/org.js"></script>
    
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
    <form id="edit_form"  name="easy-dataform"  method="post" target="_self"  style="padding:0 10px 10px 10px;" action="<%=path%>/org/save.do">
			<input type="hidden" id="id" name="id" value="${org.id}">
			<div class="icon-information-1" style="width:100%; height:24px; line-height:24px; margin-bottom:8px; text-indent:25px;">提示：<font color="#ff0000">*</font>为必填项!</div>
    		<fieldset style="margin: 5px;">
				<legend style="font-weight:bold;"></legend>
		    
		       <table style="width:590px;margin: 0 auto;"   cellspacing="12">
		      
		         <tr >
		            <td class="td_head"  >机构名称: <span style="color:red;"></span></td>
		            <td>
		              <input   name="orgName" class="easyui-textbox" 	 style="width: 200px;"	value="${org.orgName}"               
		                missingMessage="请输入机构名称"   
		                 >
		            </td>
		         </tr>
		         <tr >
		            <td class="td_head"  >联系人: <span style="color:red;"></span></td>
		            <td>
		              <input   name="contacter" class="easyui-textbox" 	 style="width: 200px;"	value="${org.contacter}"               
		                missingMessage="请输入联系人"   
		                 >
		            </td>
		         </tr>
		         <tr >
		            <td class="td_head"  >电话: <span style="color:red;"></span></td>
		            <td>
		              <input   name="contactTel" class="easyui-textbox" 	 style="width: 200px;"	value="${org.contactTel}"               
		                missingMessage="请输入电话"   
		                 >
		            </td>
		         </tr>
		         <tr >
		            <td class="td_head"  >手机: <span style="color:red;"></span></td>
		            <td>
		              <input   name="contactPhone" class="easyui-textbox" 	 style="width: 200px;"	value="${org.contactPhone}"               
		                missingMessage="请输入栏目名称"   
		                 >
		            </td>
		         </tr>
		         <tr >
		            <td class="td_head"  >邮箱: <span style="color:red;"></span></td>
		            <td>
		              <input   name="email" class="easyui-textbox" 	 style="width: 200px;"	value="${org.email}"               
		                missingMessage="请输入栏目名称"   
		                 >
		            </td>
		         </tr>
		         <tr >
		            <td class="td_head"  >地址: <span style="color:red;"></span></td>
		            <td>
		              <input   name="address" class="easyui-textbox" 	 style="width: 200px;"	value="${org.address}"               
		                missingMessage="请输入栏目名称"   
		                 >
		            </td>
		         </tr>
		         <tr >
		            <td class="td_head"  >网站备案号: <span style="color:red;"></span></td>
		            <td>
		              <input   name="icp" class="easyui-textbox" 	 style="width: 200px;"	value="${org.icp}"               
		                missingMessage="请输入栏目名称"   
		                 >
		            </td>
		         </tr>
		         <tr >
		            <td class="td_head"  >logo图: <span style="color:red;"></span></td>
		            <td>
		              <div >
                               <!--用来存放item-->
                               <div id="fileList" class="uploader-list">
                                  <c:if test="${org.logo!=null}">                      
                                         <div class="file-item thumbnail ">
                                                 <img title="双击删除" src="<%=path %>${org.logo}" width="100" height="100" style="cursor:pointer;" class="template_img" >
                                                 <div class="correct"></div>
                                                 <div class="correct">双击删除</div>
                                                 <input name="logo" type="hidden" value="${org.logo}">
                                         </div>                                                                      
                                  </c:if>
                               </div>
                               <div id="filePicker">选择图片</div>
                    </div>
		            </td>
		         </tr>
		         
		         
		      </table>
		    	
		    	
		    	
	    	</fieldset>
    	</form> 
	</div>
	
 </body>
</html>
