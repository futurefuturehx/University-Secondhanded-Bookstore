<%@ page language="java" contentType="text/html; charset=Windows-31J"
	pageEncoding="Windows-31J"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<title><bean:message key="title.post.register.confirm" /></title>
<link rel="stylesheet" type="text/css" href="../style.css">
</head>
<body>
	<jsp:include page="/sell/sell_nav.jsp" />

	<div class="header" align=center>
		<h1>
			<bean:message key="heading.post.register.confirm" />
		</h1>

	<html:form action="/ConfirmPostAction" enctype="multipart/form-data" styleClass = "generic-frm">
		<table border = "1" style ="text-align:left;">
			<tr>
				<th><bean:message key="label.itemName" /></th>
				<td><bean:write name="PostForm" property="itemName" /></td>
			</tr>

			<tr>
				<th><bean:message key="label.version" /></th>
				<td><bean:write name="PostForm" property="version" /></td>
			</tr>

			<tr>
				<th><bean:message key="label.writer" /></th>
				<td><bean:write name="PostForm" property="writer" /></td>
			</tr>

			<tr>
				<th><bean:message key="label.isbn" /></th>
				<td><bean:write name="PostForm" property="isbn" /></td>
			</tr>

			<tr>
				<th><bean:message key="label.courseNo" /></th>
				<td><bean:write name="PostForm" property="courseNo" /></td>
			</tr>

			<tr>
				<th><bean:message key="label.courseName" /></th>
				<td><bean:write name="PostForm" property="courseName" /></td>
			</tr>

			<tr>
				<th><bean:message key="label.prof" /></th>
				<td><bean:write name="PostForm" property="professor" /></td>
			</tr>

			<tr>
				<th><bean:message key="label.price" /> (<bean:message
							key="label.currency" />)</th>
				<td><bean:write name="PostForm" property="price" /></td>
			</tr>

			<tr>
				<th><bean:message key="label.condition" /></th>
				<td><pre><bean:write name="PostForm" property="condition" /></pre></td>
			</tr>

			<tr>
				<th><bean:message key="label.fileName" /></th>
				<td><bean:write name="PostForm" property="file.fileName" /></td>
			</tr>

			<tr>
				<th><bean:message key="label.fileSize" /></th>
				<td><bean:write name="PostForm" property="file.fileSize" /></td>
			</tr>

		</table>

		<br>

		<html:cancel>
			<bean:message key="submit.back" />
		</html:cancel>

		<html:submit>
			<bean:message key="submit.post.confirm" />
		</html:submit>
	</html:form>
		</div>
</body>
</html:html>