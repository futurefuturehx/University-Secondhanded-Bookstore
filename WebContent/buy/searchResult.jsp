<%@ page language="java" contentType="text/html; charset=Windows-31J"
	pageEncoding="Windows-31J"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<title><bean:message key="title.buy.searchResult" /></title>
<link rel="stylesheet" type="text/css" href="../style.css">
</head>
<body>

	<div class="nav">
		<jsp:include page="/buy/buy_nav.jsp" />
	</div>

	<div class="header" align=center>
		<h1>
			<bean:message key="heading.buy.search.result" />
		</h1>

		<h3><a href = "../buy/search.jsp">
			<bean:message key="label.link.backToSearch" />
		</a>
		</h3>

	</div>

	<logic:iterate id="data" name="searchList" type="beans.PostInfo">

		<div class="post-container" >
			<div class="post-thumb">
				<html:link action="/SearchDetailAction.do" paramId="postID"
					paramName="data" paramProperty="postID">
					<img src="<bean:write name="data" property="picturePass" />"
						class="post" height="150" width="150" />
				</html:link>
			</div>
			<div class="post-content">
				<h3 class="post-title">
					<html:link action="/SearchDetailAction.do" paramId="postID"
						paramName="data" paramProperty="postID">
						<b><bean:write name="data" property="itemName" /></b>
					</html:link>
				</h3>

				<p>
					<bean:write name="data" property="price" />
					<bean:message key="label.currency" />
					<br>
					<bean:message key="label.writer" />
					:
					<bean:write name="data" property="writer" />
					<br>
					<bean:message key="label.seller" />
					:
					<bean:write name="data" property="username" />
					<br>

				</p>
			</div>
		</div>

	</logic:iterate>

</body>
</html>