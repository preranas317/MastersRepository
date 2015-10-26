<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registeration Page</title>
</head>
<body>
<%-- <c:if test=""></c:if> --%>
<h5><font color="red">
${message.m1}
${message.m5}
</font></h5>
	<form action='Register' method='post'>

		<table>

			<tr>
				<td>Username :</td>
				<td><input type='text' name='uname'> <font  color="red">${message.m2} </font> </td>
			</tr>

			<tr>
				<td>Password :</td>
				<td><input type='text' name='pswd'>  <font  color="red">${message.m3}</font></td>
			</tr>

			<tr>
				<td>Re-type Password :</td>
				<td><input type='text' name='repswd'><font  color="red">  ${message.m4}</font></td>
			</tr>

			<tr>
				<td>First Name(optional) :</td>
				<td><input type='text' name='fname'></td>
			</tr>

			<tr>
				<td>Last Name(optional) :</td>
				<td><input type='text' name='lname'></td>
			</tr>

		</table>
		<br>
		<p>
			<input type='submit' name='register' value='Register'>
		</p>

	</form>

</body>
</html>