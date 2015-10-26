<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="codename" value="${entry.scode}" />
<c:set var="prereq" value="${entry.spreq}" />
<c:set var="preqlist" value="${fn:split(prereq,' ')}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Course Page</title>
</head>
<body>

	<form action='EditCourse' method='post'>

		<table>

			<tr>
				<td>Code :</td>
				<td><input type='text' name='code' value="${entry.scode}"></td>
			</tr>

			<tr>
				<td>Title :</td>
				<td><input type='text' name='title' value="${entry.sname}"></td>
			</tr>

			<tr>
				<td>Prerequisite(s) :</td>
				<td><c:forEach items="${bookentry}" var="temp">
						<c:set var="flag" value="0" />
						<c:set var="tempnme" value="${temp.scode}"></c:set>
						<c:if test="${tempnme ne codename}">

							<c:forEach items="${preqlist}" var="i">
								<c:if test="${i eq tempnme}">
									<br>${temp.scode}<input type='checkbox' name='preq'
										value="${temp.scode}" checked="checked">
									<c:set var="flag" value="1"></c:set>
								</c:if>

							</c:forEach>

							<c:if test="${flag eq 0 }">
								<br>${temp.scode}<input type='checkbox' name='preq'
									value="${temp.scode}">
								<c:set var="flag" value="0" />
							</c:if>
						</c:if>
					</c:forEach>
		</table>
		<input type='hidden' name="id" value="${entry.id}"> <input
			type='submit' name='SAVE' value='save'>
	</form>

	<p>
		<a href='Logout'>LogOut</a>
	</p>


</body>
</html>