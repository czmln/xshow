<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<% String topPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath(); %>
<!--[if lte IE 6]> 
<script src="<%=topPath%>/view/desk/js/ppy.js" type="text/javascript"></script> 
<script type="text/javascript"> 
DD_belatedPNG.fix('div, ul, img, li, input , a'); 
</script> 
<![endif]-->

<!--右悬浮-->

<script type="text/javascript">
	$(document).ready(function() {
		$(".testimg").zoomImgRollover();
	});
</script>

<script type="text/javascript">
	$(document).ready(function($) {
		var $width = window.screen.width;
		var $height;

		if ($.browser.msie && $.browser.version <= 6.0) {
			$height = 730;
		} else {

			if ($width >= 1440) {
				$height = document.documentElement.clientHeight;
			} else {
				$height = 730;
				$("body").css("height", "730px");
				$("body").css("position", "relative");
			}
		}

		//alert($height);
		//alert("good");
		$(".slider").slideshow({
			width : $width,
			height : $height,
			transition : [ 'explode', 'square', 'Rain', 'squareRandom' ]
		//transition:'explode/square'
		});
	});
</script>




<div class="flash theme-default">

	<div class="slider">

		<c:forEach items="${banners}" var="banner">
			<div>
				<img src="<%=topPath%>${banner.imgUrl}" alt="" width="100%"
					height="100%" />
			</div>
		</c:forEach>




	</div>

</div>

<div class="headtopnb">
		<div class="zsp1">
			<img src="<%=topPath%>/view/desk/image/guaj.jpg" />
		</div>
		<div class="zsp2">
			<img src="<%=topPath%>/view/desk/image/guaj2.jpg" />
		</div>
		<div class="menuw">
			<%-- <div class="logo left">
				<a href="<%=topPath%>">
				<c:if test="${org.logo!=null}">
			   	 <img src="<%=topPath%>${org.logo}" height="60"/>
				</c:if>
				</a>
			</div> --%>
			<div class="menulist left">
				<a href="<%=topPath%>" 
				<c:if test="${m==null}">class="on"
				</c:if>
				>网站首页</a> 

				<c:forEach var="menu" items="${menus}">
				   <a href="<%=topPath%>/${menu.linkUrl}" <c:if test="${menu.linkUrl==m.linkUrl}">
				     class="on"
				   </c:if> 
				   >${menu.name}</a> 
				</c:forEach>
			</div>
			<div class="clear"></div>
			<div class="clear"></div>
		</div>

	</div>