<%@ page language="java" contentType="text/html; charset=Windows-31J"
	pageEncoding="Windows-31J"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<title><bean:message key="title.post.register" /></title>
<link rel="stylesheet" type="text/css" href="../style.css">
</head>
<body>
	<div class="nav">
		<jsp:include page="/sell/sell_nav.jsp" />
	</div>

	<div class="header" align=center>
		<h1>
			<bean:message key="heading.sell.postRegister" />
		</h1>
	</div>

	<div align="center">
		<html:form action="/CreatePostAction" enctype="multipart/form-data" styleClass = "generic-frm">


			<table style ="text-align:left;">

				<tr>
					<td colspan="2">
					<font color = "#ff704d">
					<html:errors />
					</font>
					<br>
					</td>
				</tr>


				<tr>
				<td colspan="2">
					<bean:message key="label.required.mark" /> <bean:message key="instruction.required" /><br>
				</td>
				</tr>

				<tr>
					<td><bean:message key="label.itemName" /> <bean:message key="label.required.mark" /></td>
					<td><html:text property="itemName" maxlength="100" /></td>
				</tr>

				<tr>
					<td><bean:message key="label.version" /></td>
					<td><html:text property="version" maxlength="10" /></td>
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
					<td><bean:message key="label.price" /> (<bean:message
							key="label.currency" />) <bean:message key="label.required.mark" /></td>
					<td><html:text property="price" maxlength="9" /></td>
				</tr>

				<tr>
					<td><bean:message key="label.condition" /></td>
					<td><html:textarea property="condition" rows="5" cols="25"  /></td>
				</tr>

				<tr>
					<td><bean:message key="label.peiod" /> <bean:message key="label.required.mark" /></td>
					<td><html:select property="period">
							<html:option value=""></html:option>
							<html:option value="7">
								<bean:message key="label.oneweek" />
							</html:option>
							<html:option value="14">
								<bean:message key="label.twoweek" />
							</html:option>
							<html:option value="30">
								<bean:message key="label.onemonth" />
							</html:option>
						</html:select></td>
				</tr>

				<tr>
					<td><bean:message key="label.image" /></td>
					<td><html:file property="file" size="50" accept=".jpg, .png, .jpeg, .gif, .bmp, .tif, .tiff|images/*" /></td>
				</tr>

			</table>

			<br>

			<html:submit>
				<bean:message key="submit.post.regist" />
			</html:submit>




		</html:form>
	</div>

</body>
</html:html>