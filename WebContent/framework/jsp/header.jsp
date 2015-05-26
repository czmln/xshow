<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%
	String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    String photoPath = application.getInitParameter("photoPath");
%>

<meta http-equiv="X-UA-Compatible" content="IE=100" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<link rel="stylesheet"  id="skin_ztree" type="text/css" href="<%=path%>/framework/ztree/zTreeStyle/zTreeStyle.css">
<link rel="stylesheet"  id="skin_ztree" type="text/css" href="<%=path%>/framework/easyui/themes/default/easyui.css">
<link rel="stylesheet"  type="text/css" href="<%=path%>/framework/easyui/themes/icon.css">
<link rel="stylesheet"  type="text/css" href="<%=path%>/framework/webuploader0.1.5/webuploader.css"  />
<script type="text/javascript">
var baseJS = "<%=path %>";
var photoPath="<%=photoPath%>";
</script>


<script type="text/javascript" src="<%=path %>/framework/resources/jquery-2.0.0.min.js"></script>
<script type="text/javascript" src="<%=path %>/framework/resources/jquery.json-2.3.min.js"></script>

<script type="text/javascript" src="<%=path %>/framework/easyui/jquery.easyui.min.js"></script> 
<script type="text/javascript" src="<%=path %>/framework/easyui/easyui-lang-zh_CN.js"></script>

<script type="text/javascript" src="<%=path%>/framework/ueditor1_4_3/ueditor.config.js"></script>
<script type="text/javascript" src="<%=path%>/framework/ueditor1_4_3/ueditor.all.js"></script>

<script type="text/javascript" src="<%=path %>/framework/resources/tools.js"></script>
<script type="text/javascript" src="<%=path %>/framework/ztree/zTree/jquery.ztree.all-3.5.min.js"></script>




