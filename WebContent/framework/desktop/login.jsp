<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
	
<%
	String pathx = request.getContextPath();
	String path = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ pathx ;
	
%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>进口展网站平台</title>
<link type="text/css" href="<%=path %>/framework/desktop/css/login.css" rel="stylesheet">
<script type="text/javascript" src="<%=path %>/framework/resources/jquery-2.0.0.min.js"></script> 
<script language="javascript">

	$(function(){
	    $('#dataInput').bind('keypress',function(event){
	        if(event.keyCode == "13")    
	        {
	        	var userName = document.getElementById("j_username").value;
	    		var password = document.getElementById("j_password").value;
	    		if ("" == userName || "" == password) {
	    			document.getElementById("j_username").focus();
	    			alert("请正确输入用户名和密码！");
	    			return false;
	    		} else{
	    	  		document.getElementById("frm").submit();
	    		  	return true;
	    		}
	        }
	    });
	});


	if(window.top != window){
		alert("登陆状态已过期,请重新登陆!!");
		window.top.location.reload();
	}



    function userLoginValid() {
    
		var userName = document.getElementById("j_username").value;
		var password = document.getElementById("j_password").value;
		if ("" == userName || "" == password) {
			document.getElementById("j_username").focus();
			alert("请正确输入用户名和密码！");
			return false;
		} else{
	  		document.getElementById("frm").submit();
		  	return true;
		}
        
	}
</script>
</head>

<body class="l-bg">
	<form action="<%=path%>/j_spring_security_check" method="post" name="frm" id="frm">
     <div class="l-box">
          <div class="box-h"><h2>企业网站管理后台</h2></div>
          <div class="box-dl">
                <div class="l-left">
                    <img src="<%=path %>/framework/desktop/images/l-logo_03.jpg">
               </div> 
               <div class="l-rig">
                   <div class="rig-tit">
                       用户登录<span>User login</span>
                   </div>
                   <div style="font-style: normal;height:17px; font-size: 13px; color: red;">
  						<span>${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}</span>
				   </div>
                   <div class="rig-i"><input id="j_username" name="j_username" type="text" class="user"></div>
                   <div class="rig-i"><input id="j_password" name="j_password" type="password" class="pwd"></div>
                   <div class="rig-i"><input type="button" class="btn" id="dataInput" onclick="return userLoginValid();"></div>
               </div>
          </div>
     </div>
     </form>
</body>
</html>
