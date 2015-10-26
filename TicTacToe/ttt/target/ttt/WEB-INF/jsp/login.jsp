<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
</head>
<body background="images/b1.jpg">

	<h2 align="center">
		<font color="black" size="14">T!( <font color="red"
			size="14">T@(</font> T0(-
		</font>
	</h2>
	<form:form modelAttribute="player">

		<br>
		<br>
		<br>
		<br>
		<table align="right" border="3">
			<tr>
				<td><font color="blue" size="4"> USERNAME</font></td>
				<td><form:input path="username" /></td>
			</tr>

			<tr>
				<td><font color="blue" size="4">PASSWORD </font></td>
				<td><form:input path="password" /></td>
			</tr>

			<tr>
				<td colspan="2" align="right"><input type="submit"
					value="Login" name="login" /></td>
			</tr>
			<tr>
				<td><font color="blue" size="4">Not a user...</font></td>
				<td><a href="register.html"><font color="red" size="4">Sign
							up</font></a></td>
		</table>

	</form:form>
</body>
</html>