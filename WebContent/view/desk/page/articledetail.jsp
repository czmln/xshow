<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${data.title}</title>
<%@ include file="common/headfile.jsp"%> 
</head>




<body>



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
			
			
			<div class="new_right left">



				<div class="casetelgsjs">
					<p>&nbsp;</p>
				</div>
				<div class="newlistw">
					<div class="newntel"> ${data.title}</div>

					<div class="newtelx">
						<font>发布于：<fmt:formatDate value="${data.issuedDate}" pattern="yyyy年MM月dd日   HH:mm:ss"/>  浏览：${data.clickRate}</font>
					</div>
 
					<div class="newcontext">


						${data.content}


					</div>
					<script type="text/javascript" src="<%=path%>/view/desk/js/jquery.nicescroll.js"></script>
					<script>
						$(".newcontext").niceScroll({
							cursorcolor : "#08699a",
							cursoropacitymax : 1,
							touchbehavior : true,
							cursorwidth : "5px",
							cursorborder : "0",
							cursorborderradius : "5px"
						});
					</script>



				</div>

			</div>

			<div class="clear"></div>

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