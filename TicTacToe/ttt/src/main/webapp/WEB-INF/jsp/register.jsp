<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registeration Page</title>
</head>
<body background="images/b1.jpg">
	<h2 align="center">
		<font color="black" size="14">T!( <font color="red"
			size="14">T@(</font> T0(-
		</font>
	</h2>
	<br>
	<br>
	<form:form modelAttribute="player">
		<table align="right" border="3">
			<tr>
				<td><font color="blue" size="4"> USERNAME</font></td>
				<td><form:input path="username" /></td>
			</tr>

			<tr>
				<td><font color="blue" size="4">PASSWORD</font></td>
				<td><form:input path="password" /></td>
			</tr>
			<tr>
				<td><font color="blue" size="4"> EMAIL-ID</font></td>
				<td><form:input path="email_id" /></td>
				<td><form:input type="hidden" path="authority" value="ROLE_USER" /></td>
				<%-- //<td><form:input path="email_id" /></td> --%>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="submit" value="Register" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>