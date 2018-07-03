<%@ page language="java" contentType="text/html; charset=Windows-31J"
	pageEncoding="Windows-31J"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<link rel="stylesheet" type="text/css" href="../style.css">
<title><bean:message key="title.setting" /></title>
</head>
<body>
<jsp:include page="/buy/buy_nav.jsp" />

<div align = "center">
<h1><bean:message key="title.setting" /></h1>
</div>
<div align = "center">
<html:form action="/PasswordChangeAction" styleClass = "generic-frm">
	<table>
			<tr>
				<td colspan = "2">
				<font color = "#ff704d">
					<html:errors />
				</font>
				</td>
			</tr>

			<tr>
				<td><bean:message key="label.email" /></td>
				<td><bean:write name="email" scope="session" /></td>
			</tr>

			<tr>
				<td><bean:message key="label.username" /></td>
				<td><bean:write name="username" scope="session" /></td>
			</tr>

			<tr>
				<td><bean:message key="label.current.password" /></td>
				<td><html:password property="old_password" /></td>
			</tr>

			<tr>
				<td><bean:message key="label.new.password" /></td>
				<td><html:password property="new_password1" /></td>
			</tr>

			<tr>
				<td><bean:message key="label.new.password1" /></td>
				<td><html:password property="new_password2" /></td>
			</tr>
		</table>

		<br>

		<html:submit>
			<bean:message key="submit.change" />
		</html:submit>


</html:form><br>
</div>
</body>

</html:html>