<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${org.orgName}</title>

<%@ include file="common/headfile.jsp"%> 
 
</head>


<body>


<%@ include file="common/headtopnb.jsp"%> 
	


	<div class="counter2">
		<div class="oyelow">
			<div class="btel se1">Recommended</div>
			<div class="stel se1">推荐产品</div>
			<div class="prod">
				<a href="<%=path%>/xwzx/${cp.pathNumber}"><img src="<%=path%>/${cp.coverImgUrl}" /></a>
			</div>
		</div>
		<div class="obulew">
			<div class="btel se2">
				<a href="<%=path%>/cpzx/">Products</a>
			</div>
			<div class="stel se2">
				<a href="<%=path%>/cpzx/">产品中心</a>
			</div>
			<div class="prod2">
				<a href="<%=path%>/cpzx/"><img src="<%=path%>/view/desk/image/menu_img4.jpg" /></a>
				<div class="prod2tel">
					<a href="<%=path%>/cpzx/">产品中心</a>
				</div>
			</div>
		</div>

		<div class="obulew2">
			<div class="btel se3">
				About us<span>公司简介</span>
			</div>
			<div class="obuw se3">        
				${intro.summary}
				<a href="<%=path%>/gsjs/">[更多+]</a>
			</div>
		</div>

		<!-- 第三层开始-->
		<div class="oysan">
			<div class="oyimg">
				<a href="<%=path%>/cgal"><img src="<%=path%>/view/desk/image/cgal.png" /></a>
			</div>
			<div class="stel" style="margin-top: 10px;">
				<a href="<%=path%>/cgal">成功案例</a>
			</div>
			<div class="btel se4">
				<a href="<%=path%>/cgal">successful case</a>
			</div>
		</div>

		<div class="oysan2">
			<div class="btel se5">
				<a href="<%=path%>/xwzx">News Center </a>
			</div>
			<div class="stel se5">
				<a href="<%=path%>/xwzx">新闻中心</a>
			</div>
			<div class="ysanlist">
				<ul>
					<c:forEach items="${news}" var="xw" >
					   <li><a href="<%=path%>/xwzx/${xw.pathNumber}"><font>·</font>${xw.title}</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>


	</div>


	<script language="javascript">
		$(document).ready(function() {
			$(".dbfx a").mouseover(function() {
				$(this).children(".img").stop().animate({
					marginTop : "0px"
				}, 300)
			}).mouseleave(function() {
				$(this).children(".img").stop().animate({
					marginTop : "-33px"
				}, 300)
			})

		})
	</script>

	<%@ include file="common/bottom.jsp"%> 

</body>
</html>
