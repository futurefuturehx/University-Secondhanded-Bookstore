<%@ page language="java" contentType="text/html; charset=Windows-31J"
	pageEncoding="Windows-31J"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<img src="../pic/admin/nav_cover.jpg"  width="100%" height="150" />
	<ul>
		<li><a href="../PostManagerAction.do"><bean:message key="nav.post.manager" /></a></li>
		<li><a href="../sell/createPost.jsp"><bean:message key="nav.post.register" /></a></li>
		<li><a href="../RequestNotificationAction.do"><bean:message key="nav.request.list" /></a></li>
		<li class="dropdown"><a href="#" class="dropbtn"><bean:message key="nav.myAccount" /></a>
			<div class="dropdown-content">
				<a href="../BuyHomeAction.do"><bean:message key="nav.buy" /></a>
				<a href="../LogoutAction.do"><bean:message key="nav.logout" /></a>
			</div></li>
	</ul>
