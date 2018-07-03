<%@ page language="java" contentType="text/html; charset=Windows-31J"
	pageEncoding="Windows-31J"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="beans.PostInfo"%>
<%
	PostInfo pb = (PostInfo) request.getAttribute("currentPost");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<title><bean:message key="title.post.detail" /></title>
<link rel="stylesheet" type="text/css" href="../style.css">
</head>
<body>
	<div class="nav">
		<jsp:include page="/sell/sell_nav.jsp" />
	</div>

	<div class="header" align="center">
		<h1>
			<bean:message key="title.post.detail" />
		</h1>
	</div>

	<div align="center">
		<img src="<bean:write name="currentPost" property="picturePass" />"
			class="post" height="250" width="200" /><br>
		<table>
			<tr>
				<td><bean:message key="label.itemName" />:</td>
				<td><bean:write name="currentPost" property="itemName" /></td>
			<tr>
			<tr>
				<td><bean:message key="label.version" />:</td>
				<td><bean:write name="currentPost" property="version" /></td>
			<tr>
			<tr>
				<td><bean:message key="label.writer" />:</td>
				<td><bean:write name="currentPost" property="writer" /></td>
			<tr>
			<tr>
				<td><bean:message key="label.isbn" />:</td>
				<td><bean:write name="currentPost" property="isbn" /></td>
			<tr>
			<tr>
				<td><bean:message key="label.courseNo" />:</td>
				<td><bean:write name="currentPost" property="courseNo" /></td>
			<tr>
			<tr>
				<td><bean:message key="label.courseName" />:</td>
				<td><bean:write name="currentPost" property="courseName" /></td>
			<tr>
			<tr>
				<td><bean:message key="label.prof" />:</td>
				<td><bean:write name="currentPost" property="professor" /></td>
			<tr>
			<tr>
				<td><bean:message key="label.price" />:</td>
				<td><bean:write name="currentPost" property="price" /> <bean:message
						key="label.currency" /></td>
			<tr>
			<tr>
				<td><bean:message key="label.condition" />:</td>
				<td><bean:write name="currentPost" property="condition" /></td>
			<tr>
			<tr>
				<td><bean:message key="label.validTill" />:</td>
				<td><bean:write name="currentPost" property="validTil" /></td>
			<tr>
			<tr>
				<td><bean:message key="label.postAt" />:</td>
				<td><bean:write name="currentPost" property="postInpdate" /></td>
			<tr>
			<tr>
				<td><bean:message key="label.currentStatus" />:</td>
				<td><bean:message key="label.status.expired" /></td>
			<tr>
		</table>
		<br>
		<h3>
			<a href = "/bookstore/PostManagerAction.do"><bean:message key="label.link.backToPostManager" /></a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href = "/bookstore/RequestNotificationAction.do"><bean:message key="label.link.backToRequestManager" /></a>
		</h3>

		<logic:notEmpty name="requestList">
			<hr>
			<br>
			<h2>
				<bean:message key="heading.request" />
			</h2>

			<logic:iterate id="data" name="requestList" type="beans.SellRequest">
				<div class="table">
					<table border="1">
						<tr>
							<td><bean:message key="label.email" /></td>
							<td><bean:write name="data" property="email" /></td>
						</tr>

						<tr>
							<td><bean:message key="label.message" /></td>
							<td><pre><bean:write name="data" property="message" /></pre></td>
						</tr>

						<tr>
							<td><bean:message key="label.request.date" /></td>
							<td><bean:write name="data" property="inpdate" /></td>
						</tr>
					</table>
				</div>
			</logic:iterate>
		</logic:notEmpty>
	</div>

</body>
</html:html>
