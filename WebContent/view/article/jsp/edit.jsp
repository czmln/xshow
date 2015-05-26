<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<%@ include file="/framework/jsp/header.jsp" %> 
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<title>编辑模版</title>
	<script type="text/javascript" src="<%=path%>/view/article/js/edit.js"></script>
    
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
	
						<fieldset style="margin: 5px;"> <legend style="font-weight:bold;"></legend>
						 <form id="edit_form" name="easy-dataform" method="post" 
							target="_self" style="padding: 0 10px 10px 10px;" action="<%=path%>/article/save.do">
							<input type="hidden" id="id" name="id" value="${article.id}" >
                           
								<table style="width:790px; margin: 0 auto;" cellspacing="12">
									<tr>
										<td ><span style="display:inline-block;width: 80px;">栏目:<label style="color: red;">*</label></span> </td>
										<td >
											 <select  id="menubars_select" menuid="${article.menubar.id}" style="width: 260px" class="easyui-combotree"  required="true"   data-options="cascadeCheck:false,lines:true,checkbox:true,animate:true,multiple:true,panelMinHeight:500"   missingMessage="请选择栏目" >
											 </select>  
										</td>
										<td ><span style="display:inline-block;width: 80px;">标题:<label style="color: red;">*</label></span></td>
										<td><input id="title" name="title" style="width: 260px"
											 class="easyui-textbox" 
											 value="${article.title}"
											 style=""
											 missingMessage="请输入标题"
											required="true"></td>
									</tr>
									
									
									<tr>
										<td><span style="display:inline-block;width: 80px;">作者:</span></td>
										<td>
										   <input id="author" name="author" style="width: 260px"
											  class="easyui-textbox" style=""
											  value="${article.author}"
										    >
										</td>
										<td><span style="display:inline-block;width: 80px;">出处:</span></td>
										<td>
										   <input id="source" name="source"
											  class="easyui-textbox" style="width: 260px"
											   value="${article.source}"
											>
										</td>
									</tr>
									
									<tr>
										<td ><span style="display:inline-block;width: 80px;">封面图片:<label style="color: red;">&nbsp;</label></span> </td>
										<td class="td_head" align="left" style="text-align: left;" colspan="3">
										   <div id="fileList" class="uploader-list">
										    <c:if test="${article.coverImgUrl!=null}">
                                               
                                                  <div class="file-item thumbnail ">
                                                 <img title="双击删除" src="<%=path %>${article.coverImgUrl}" width="100" height="100" style="cursor:pointer;" class="template_img" >
                                                 <div class="correct"></div>
                                                 <div class="correct">双击删除</div>
                                                  <input name="coverImgUrl" type="hidden" value="${article.coverImgUrl}" >
                                                 </div>
                                                                             
                                         </c:if>
										   </div>
											<div>
												<div id="filePicker">选择图片</div>
											</div>

										</td>
										
										
										
									</tr>
										
                                    <tr>
										<td ><span style="display:inline-block;width: 80px;">文章摘要:</span></td>
										<td colspan="3">
										<script id="summary" type="text/plain" style="width:723px;height:80px;" name="summary">
                                              ${article.summary}
                                        </script> </td>
									</tr>


									<tr>
										<td ><span style="display:inline-block;width: 80px;">文章内容:</span></td>
										<td colspan="3">
										<script id="content" type="text/plain" style="width:723px;height:360px;" name="content">
                                              ${article.content}
                                        </script> </td>
									</tr>

								</table>



						</form> 
                     
                       
						
				       </fieldset>



					</div>
					
</body>
</html>
