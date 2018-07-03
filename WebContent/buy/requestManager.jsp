
<%-----------------------------Comment Out はリクェストの削除/キャンセル機能--------------------%>


<%@ page language="java" contentType="text/html; charset=Windows-31J"
	pageEncoding="Windows-31J"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<title><bean:message key="title.buy.requestManager" /></title>
<link rel="stylesheet" type="text/css" href="../style.css">
</head>
<body>
	<div class="nav">
		<jsp:include page="/buy/buy_nav.jsp" />
	</div>

	<div class="header" align=center>
		<h1>
			<bean:message key="heading.buy.requestManager" />
		</h1>
	</div>


	<div class="table" class="table">
		<table border="1" align="center">
			<tr>
				<th><bean:message key="label.poster.name" /></th>

				<th><bean:message key="label.itemName" /></th>

				<th><bean:message key="label.request.date" /></th>

				<th><bean:message key="label.status" /></th>

				<%--
				<th><bean:message key="label.action" /></th>
				--%>
			</tr>


			<logic:empty name="activeBuyRequestList">
				<logic:empty name="expiredBuyRequestList">
					<tr>
						<td colspan=5 align="center" style="color: red;"><bean:message
								key="error.request.empty" /></td>
					</tr>
				</logic:empty>
			</logic:empty>

			<logic:iterate id="data" name="activeBuyRequestList"
				type="beans.BuyRequest">
				<tr>
					<td><bean:write name="data" property="posterName" /></td>
					<td><html:link action="/RequestedActivePostDetailAction.do"
							paramId="postID" paramName="data" paramProperty="postID">
							<bean:write name="data" property="itemName" />
						</html:link></td>
					<td><bean:write name="data" property="requestTime" /></td>
					<td align="center"><logic:equal name="data" property="status"
							value="1">
							<font color="green"><bean:message
									key="label.status.onsale" /></font>
						</logic:equal> <logic:equal name="data" property="status" value="2">
							<font color="red"><bean:message key="label.status.notsale" /></font>
						</logic:equal></td>

					<%--
					<td align="center"><html:form action="/RemoveRequestAction"
							styleClass="remove-frm">
							<html:hidden property="requestID"
								value="<%=Integer.toString(data.getRequestID())%>" />
							<html:submit
								onclick="javascript:return confirm('キャンセルは売り手に通知します。よろしいですか？');">
								<bean:message key="label.remove" />
							</html:submit>
						</html:form></td>
					--%>
				</tr>
			</logic:iterate>

			<logic:iterate id="data" name="expiredBuyRequestList"
				type="beans.BuyRequest">
				<tr>
					<td><bean:write name="data" property="posterName" /></td>
					<td><html:link action="/RequestedExpiredPostDetailAction.do"
							paramId="postID" paramName="data" paramProperty="postID">
							<bean:write name="data" property="itemName" />
						</html:link></td>
					<td><bean:write name="data" property="requestTime" /></td>
					<td align="center"><font color="grey"><bean:message
								key="label.status.expired" /></font></td>
					<%--
					<td align="center"><html:form action="/RemoveRequestAction"
							styleClass="remove-frm">
							<html:hidden property="requestID"
								value="<%=Integer.toString(data.getRequestID())%>" />
							<html:submit
								onclick="javascript:return confirm('履歴から削除します。 よろしいですか？');">
								<bean:message key="label.remove" />
							</html:submit>
						</html:form></td>
					--%>
				</tr>
			</logic:iterate>



		</table>
	</div>




</body>
</html>