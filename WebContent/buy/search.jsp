<%@ page language="java" contentType="text/html; charset=Windows-31J"
	pageEncoding="Windows-31J"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<title><bean:message key="title.buy.search" /></title>
<link rel="stylesheet" type="text/css" href="../style.css">
</head>
<body>

	<div class="nav">
		<jsp:include page="/buy/buy_nav.jsp" />
	</div>

	<div class = "header" align = center>
		<h1><bean:message key="heading.buy.search" /></h1>
	</div>

	<div align="center">
		<html:form action="/SearchAction" styleClass = "generic-frm">
			<table>

				<tr>
					<html:errors />
				</tr>

				<tr>
					<td><bean:message key="label.itemName" /></td>
					<td><html:text property="itemName" maxlength="100" /></td>
				</tr>

				<tr>
					<td><bean:message key="label.writer" /></td>
					<td><html:text property="writer" maxlength="100" /></td>
				</tr>

				<tr>
					<td><bean:message key="label.isbn" /></td>
					<td><html:text property="isbn" maxlength="20" /></td>
				</tr>

				<tr>
					<td><bean:message key="label.courseNo" /></td>
					<td><html:text property="courseNo" maxlength="20" /></td>
				</tr>

				<tr>
					<td><bean:message key="label.courseName" /></td>
					<td><html:text property="courseName" maxlength="100" /></td>
				</tr>

				<tr>
					<td><bean:message key="label.prof" /></td>
					<td><html:text property="professor" maxlength="100" /></td>
				</tr>

				<tr>
					<td><bean:message key="label.price" /></td>
					<td><html:select property="priceRange">
							<html:option value="0"> &nbsp; </html:option>
							<html:option value="1">
								<bean:message key="label.range1" />
							</html:option>
							<html:option value="2">
								<bean:message key="label.range2" />
							</html:option>
							<html:option value="3">
								<bean:message key="label.range3" />
							</html:option>
							<html:option value="4">
								<bean:message key="label.range4" />
							</html:option>
						</html:select></td>
				</tr>

			</table>

			<br>

			<html:submit>
				<bean:message key="submit.search" />
			</html:submit>

		</html:form>
	</div>

</body>
</html>