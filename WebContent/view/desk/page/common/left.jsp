<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script language="javascript">
				$(document).ready(function() {
					$(".gstelist a").mouseover(function() {
						$(this).stop().animate({
							paddingLeft : 5
						}, 500)
					}).mouseout(function() {
						$(this).stop().animate({
							paddingLeft : 0
						}, 500)
					})
				})
</script>

<div class="new_left left">
	<div class="wentel mbot">类别</div>
	<div class="gstelist">
		
		<c:forEach var="menu" items="${menus}">
			<a href="${pageContext.request.contextPath}/${menu.linkUrl}"><font>·</font>${menu.name}</a> 
	    </c:forEach>
	</div>

	<div class="wentel mbot top10">联系我们</div>
	<div class="lxwmimg">
		<img src="${pageContext.request.contextPath}/view/desk/image/lxwm.jpg" />
	</div>
	<div class="lxadr">
		<p>
			<font>地址：</font> ${org.address}
		</p>
		<p>
			<font>电话：</font> ${org.contactTel}
		</p>
		<p>
			<font>手机：</font> ${org.contactPhone}
		</p>
		<p>
			<font>邮箱：</font>${org.email}
		</p>
		<p>
			<font>联系人：</font> ${org.contacter}
		</p>
	</div>


</div>