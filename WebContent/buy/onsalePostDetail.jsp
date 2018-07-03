<%@ page language="java" contentType="text/html; charset=Windows-31J"
	pageEncoding="Windows-31J"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<title><bean:message key="title.buy.detail" /></title>
<link rel="stylesheet" type="text/css" href="../style.css">

</head>
<body>
	<div class="nav">
		<jsp:include page="/buy/buy_nav.jsp" />
	</div>

	<div class="header" align="center">
		<h1>
			<bean:message key="title.post.detail" />

		</h1>
	</div>

	<div align="center">

		<font color = "#ff704d">
			<html:errors />
		</font><br><br>

		<img src="<bean:write name="currentPost" property="picturePass" />"
			class="post" height="250" width="200" /> <br><br>

		<html:form action="/SubmitRequestAction" styleClass = "generic-frm">
			<table>
				<tr>
					<td><bean:message key="label.seller" />:</td>
					<td><bean:write name="currentPost" property="username" /></td>
				<tr>
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
					<td><logic:equal name="currentPost" property="status"
							value="1">
							<bean:message key="label.status.onsale" />
						</logic:equal> <logic:equal name="currentPost" property="status" value="2">
							<bean:message key="label.status.notsale" />
						</logic:equal></td>
				<tr>
			</table>
			<br>

			<bean:message key="label.message" />
			<br>

			<html:textarea property="message" rows="6" cols="50" />

			<br>
			<br>

			<input type="hidden" name="postID"
				value="<bean:write name="currentPost" property="postID"/>" />

			<html:submit
				onclick="javascript:return confirm('We will notify the seller. Please confirm your Request.');">
				<bean:message key="submit.request.send" />
			</html:submit>

			<br>
			<br>

			<h3><a href = "../BuyHomeAction.do"><bean:message key="label.link.backToHome" /></a></h3>

		</html:form>
	</div>
</body>
</html>

