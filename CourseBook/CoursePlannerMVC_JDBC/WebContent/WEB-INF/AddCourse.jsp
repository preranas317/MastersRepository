<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Course Page</title>
</head>
<body>
	<form action='AddCourse' method='post'>
		<table border="1">
			<tr>
				<td>Code :</td>
				<td><input type='text' name='code'></td>
			</tr>
			<tr>
				<td>Title :</td>
				<td><input type='text' name='title'></td>
			</tr>
			<tr>
				<td>Prerequisite(s) :</td>
				<td><c:forEach items="${bookentry}" var="entry"
						varStatus="status">

						<br>${entry.scode}<input type='checkbox' name='preq'
							value="${entry.scode}">


					</c:forEach></td>
			</tr>
		</table>
		<input type='submit' name='add' value='Add'>

		<p>
			<a href='Logout'>LogOut</a>
		</p>

	</form>

</body>
</html>