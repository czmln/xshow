<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<%
	String pathx = request.getContextPath();
	String bottompath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ pathx ;
	
%>
<div class="dblist">
		<div class="opty" style="text-align: center;">
			<div class="dbfx">
				<!-- <a
					href="http://tieba.baidu.com/f/commit/share/openShareApi?url=http://www.shao-ming.com&title=hello&desc=&pic="
					target="_blank"><img src="image/b1b.png" class="img" alt="百度贴吧" /><img
					src="image/b1.png" /></a><a
					href="http://v.t.qq.com/share/share.php?url=http://www.shao-ming.com&title='木兰歌谷'"
					target="_blank"><img src="image/b2b.png" class="img" alt="腾讯分享" /><img
					src="image/b2.png" /></a><a
					href="http://v.t.sina.com.cn/share/share.php?url=http://www.shao-ming.com&title='木兰歌谷'"
					target="_blank"><img src="image/b3b.png" class="img" alt="新浪分享" /><img
					src="image/b3.png" /></a><a
					href="http://share.renren.com/share/buttonshare.do?link=http://www.shao-ming.com"
					target="_blank"><img src="image/b4b.png" class="img"
					alt="人人网分享" /><img src="image/b4.png" /></a> -->
			</div>
			<span>版权所有© ${org.orgName}
			&nbsp;&nbsp;&nbsp; <a href="<%=bottompath %>/admin/login" style="color:#98c4da;text-decoration: underline;">管理登录</a></span>
		</div>
	</div>