<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${m.name}</title>
<%@ include file="common/headfile.jsp"%> 
</head>




<body>

	<!--[if lte IE 6]> 
<script src="css/ppy.js" type="text/javascript"></script> 
<script type="text/javascript"> 
DD_belatedPNG.fix('div, ul, img, li, input , a'); 
</script> 
<![endif]-->

<%@ include file="common/headtopnb.jsp"%>

<script language="javascript">
		$(document).ready(function() {
			$(".gpic").mouseover(function() {
				$(".gpw").slideDown();

			}).mouseleave(function() {
				$(".gpw").slideUp();
			})

		})
</script>



	<div id="counter2">
		<div class="counter">	
			<%@ include file="common/left.jsp"%>
			<script type="text/javascript">
				$(document).ready(function() {
					$(".newlis li").mouseover(function() {
						$(this).stop().animate({
							paddingLeft : 15
						}, 500)
					}).mouseout(function() {
						$(this).stop().animate({
							paddingLeft : 5
						}, 500)
					})
				});
			</script>
			<div class="new_right left">
			
				<script type="text/javascript">
$(document).ready(function() {
    $(".pptt").zoomImgRollover();
});
</script>


<div class="casetel"><p>${m.name}</p></div>

<div class="prodlistw">

<c:forEach items="${list}" var="da" >
   <a class="prodlistw-a" href="<%=path%>/${m.linkUrl}/${da.pathNumber}"><div><img src="<%=path %>${da.coverImgUrl}" class="pptt"/></div><p>${da.title}</p></a> 
</c:forEach>
<div style="width: 100%;height:1px;"></div>

<div class="clear"></div>

</div>
<%@ include file="common/page.jsp"%> 

</div>

<div class="clear"></div>

</div>
</div>



<script language="javascript">
$(document).ready(function(){
 $(".dbfx a").mouseover(function(){
    $(this).children(".img").stop().animate({marginTop:"0px"},300)
	}).mouseleave(function(){
   $(this).children(".img").stop().animate({marginTop:"-33px"},300)
	 })
	
	})

</script>

	 <%@ include file="common/bottom.jsp"%> 

</body>
</html>