<%@ page language="java" contentType="text/html; charset=Windows-31J"
	pageEncoding="Windows-31J"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<title><bean:message key="title.member.regist.complate" /></title>
<link rel="stylesheet" type="text/css" href="../style.css">
</head>
<body>

	<html:form action="/SignupCompleteAction" styleClass="bootstrap-frm">
		<h1>
			<bean:message key="message.registration.complete" />
		</h1>

		<label style="bootstrap-frm"><html:submit>
				<bean:message key="submit.to.login" />
			</html:submit>
		</label>

	</html:form>
</body>
</html:html>