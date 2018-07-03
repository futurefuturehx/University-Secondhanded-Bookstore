<%@ page language="java" contentType="text/html; charset=Windows-31J"
	pageEncoding="Windows-31J"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<title><bean:message key="title.member.regist" /></title>
<link rel="stylesheet" type="text/css" href="../style.css">
</head>
<body>

	<div>
	<html:form action="/SignupAction" styleClass = "bootstrap-frm" >

		<h1>
		<bean:message key="heading.member.regist" />
		</h1>
				<label>
				<font color = "#ff704d"><html:errors /></font>
				<br>
				 </label>

			<label>
				<span><bean:message key="label.email" /></span>
				<html:text property="email" />
			</label>

			<label>
				<span><bean:message key="label.username" /></span>
				<html:text property="username" />
			</label>

			<label>
				<span><bean:message key="label.password" /></span>
				<html:password property="password" />
			</label>

			<label>
				<span><bean:message key="label.password2" /></span>
				<html:password property="password_conf" />
			</label>


		<label>
        <span>&nbsp;</span>
		<html:cancel>
			<bean:message key="submit.back" />
		</html:cancel>
		<html:submit>
			<bean:message key="submit.member.regist" />
		</html:submit>
		</label>

		<!--http://www.sanwebe.com/2013/10/css-html-form-styles-->

	</html:form>
	</div>
</body>
</html:html>