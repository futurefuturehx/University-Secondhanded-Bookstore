<%@ page language="java" contentType="text/html; charset=Windows-31J"
	pageEncoding="Windows-31J"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<link rel="stylesheet" type="text/css" href="../style.css">
<title><bean:message key="title.request.list" /></title>
</head>
<body>

	<div class="nav">
		<jsp:include page="/sell/sell_nav.jsp" />
	</div>

	<div class="header" align=center>
		<h1>
			<bean:message key="heading.request.notification" />
		</h1>
	</div>

	<div class="table" class="table">
		<table border="1" align="center">
			<tr>
				<th><bean:message key="label.itemName" /></th>

				<th><bean:message key="label.email" /></th>

				<th><bean:message key="label.request.date" /></th>

			</tr>

			<logic:empty name="activeRequestList">
				<logic:empty name="expiredRequestList">

					<td colspan=3 align="center" style="color: red;"><bean:message
							key="error.request.empty" /></td>

				</logic:empty>
			</logic:empty>


			<logic:iterate id="data" name="activeRequestList"
				type="beans.SellRequest">
				<tr>
					<td><html:link action="/PostActiveDetailAction.do"
							paramId="postID" paramName="data" paramProperty="post_id">
							<bean:write name="data" property="item_name" />
						</html:link></td>

					<td><bean:write name="data" property="email" /></td>

					<td><bean:write name="data" property="inpdate" /></td>

				</tr>

			</logic:iterate>

			<logic:iterate id="data" name="expiredRequestList"
				type="beans.SellRequest">
				<tr>
					<td><html:link action="/PostExpiredDetailAction.do"
							paramId="postID" paramName="data" paramProperty="post_id">
							<bean:write name="data" property="item_name" />
					 </html:link></td>

					<td><bean:write name="data" property="email" /></td>

					<td><bean:write name="data" property="inpdate" /></td>

				</tr>

			</logic:iterate>

		</table>
	</div>

</body>
</html:html>