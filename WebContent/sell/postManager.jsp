<%@ page language="java" contentType="text/html; charset=Windows-31J"
	pageEncoding="Windows-31J"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<title><bean:message key="title.post.manager" /></title>
<link rel="stylesheet" type="text/css" href="../style.css">
</head>
<body>


	<div class="nav">
		<jsp:include page="/sell/sell_nav.jsp" />
	</div>

	<div class="header" align=center>
		<h1>
			<bean:message key="heading.sell.postManager" />
		</h1>

		<h3>
		<logic:empty name="pbActiveList">
			<logic:empty name="pbExpiredList">
			 	<bean:message key="label.postmanager.postEmpty" />
			</logic:empty>
		</logic:empty>
		</h3>
	</div>

	<logic:iterate id="data" name="pbActiveList" type="beans.PostInfo">

		<div class="post-container" >
			<div class="post-thumb">
				 <html:link
					action="/PostActiveDetailAction.do" paramId="postID"
					paramName="data" paramProperty="postID">
					<img src="<bean:write name="data" property="picturePass" />"
						class="post" height="150" width="150" />
				</html:link>
			</div>
			<div class="post-content">
				<h3 class="post-title">
					<html:link
					action="/PostActiveDetailAction.do" paramId="postID"
					paramName="data" paramProperty="postID">
					<bean:write name="data" property="itemName" />
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
					<bean:message key="label.status" />: <logic:equal
					name="data" property="status" value="1">
					<font color = "green"><bean:message key="label.status.onsale" /></font>
				</logic:equal> <logic:equal name="data" property="status" value="2">
					<font color = "red"><bean:message key="label.status.notsale" /></font>
				</logic:equal>

				</p>
			</div>
		</div>

	</logic:iterate>

	<logic:iterate id="data" name="pbExpiredList" type="beans.PostInfo">

			<div class="post-container" >
			<div class="post-thumb">
				 <html:link
					action="/PostExpiredDetailAction.do" paramId="postID"
					paramName="data" paramProperty="postID">
					<img src="<bean:write name="data" property="picturePass" />"
						class="post" height="150" width="150" />
				</html:link>
			</div>
			<div class="post-content">
				<h3 class="post-title">
					<html:link
					action="/PostExpiredDetailAction.do" paramId="postID"
					paramName="data" paramProperty="postID">
					<bean:write name="data" property="itemName" />
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
					<bean:message key="label.status" />: <font color = "grey"><bean:message key="label.status.expired" /></font>

				</p>
			</div>
		</div>
	</logic:iterate>
</body>
</html:html>