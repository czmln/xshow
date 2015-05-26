<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String pathx = request.getContextPath();
	String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathx;
%>
<html>
<head>
<script type="text/javascript">
document.onkeydown=function(e){
    var keyEvent;
    if(e.keyCode==8){
        var d=e.srcElement||e.target;
        if(d.tagName.toUpperCase()=='INPUT'||d.tagName.toUpperCase()=='TEXTAREA'){
           keyEvent=d.readOnly||d.disabled;
        }else{
		   keyEvent=true;
		}
	}else{
		  keyEvent=false;
	}
	if(keyEvent){
		 e.preventDefault();
	}
}

</script>
</head>
<body>
<div style="width: 1000px;margin: 0 auto;overflow: hidden;">

</div>
</body>
</html>

