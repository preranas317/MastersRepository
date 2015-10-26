<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="todaydate" value="${todaydate}"></c:set>
<c:set var="tmrwdate" value="${tmrwdate}" />
<c:set var="yesterdayDate" value="${yesterdayDate}" />
<c:set var="yesdate" value="${yesdate}" />
<c:set var="tmrwdate" value="${tmrwdate}" />


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Task Manager Display JSP Page</title>
</head>

<body>
	<c:set var="value" value="${completedlink}" />
	<c:choose>

		<c:when test="${value ne true }">
Current Task | <a href="TaskManagerDisplay?name=completedlink">Competed
				Task</a>


			<table border="" cellspacing="2">

				<tr>
					<th>Message</th>
					<th>Due Date</th>
					<th>Operations</th>
				</tr>

				<c:forEach items="${taskList}" var="listentry">
					<c:set var="entrydate" value="${listentry.dueDate}"></c:set>
					<c:choose>

						<c:when test="${entrydate lt yesdate}">
							<tr bgcolor="red">
								<td>${listentry.message}</td>
								<td><fmt:formatDate value="${listentry.dueDate}"
										pattern="MM/dd/yyyy" /></td>
								<%-- <td><a href="TaskManagerDisplay?id=${listentry.id}&nme=completed">Completed</a> --%>
								<td><a
									href="TaskManagerDisplay?id=${listentry.id}&name=remove">Remove</a>
							</tr>
						</c:when>

						<c:when test="${entrydate le tmrwdate}">

							<tr bgcolor="yellow">
								<td>${listentry.message}</td>
								<td><fmt:formatDate value="${listentry.dueDate}"
										pattern="MM/dd/yyyy" /></td>
								<%-- <td><a href="TaskManagerDisplay?id=${listentry.id}&nme=completed">Completed</a> --%>
								<td><a
									href="TaskManagerDisplay?id=${listentry.id}&name=completed">Completed
										| </a><a href="TaskManagerDisplay?id=${listentry.id}&name=remove">Remove</a>
							</tr>

						</c:when>

						<c:otherwise>
							<tr>
								<td>${listentry.message}</td>
								<td><fmt:formatDate value="${listentry.dueDate}"
										pattern="MM/dd/yyyy" /></td>
								<%-- <td><a href="TaskManagerDisplay?id=${listentry.id}&nme=completed">Completed</a> --%>
								<td><a
									href="TaskManagerDisplay?id=${listentry.id}&name=completed">Completed
										| </a><a href="TaskManagerDisplay?id=${listentry.id}&name=remove">Remove</a>
							</tr>
						</c:otherwise>

					</c:choose>
				</c:forEach>
				<form action="TaskManagerDisplay" method="post">
					<tr>
						<td><input type="text" name="message"></td>
						<td><input type="text" name="dueDate"></td>
						<td><input type="submit" name="add" value="Add"></td>
					</tr>
				</form>
			</table>
		</c:when>
		<c:otherwise>

			<a href="TaskManagerDisplay?name=currentlink">Current Task</a> |Competed Task
<table border="" cellspacing="2">
				<tr>
					<th>Message</th>
					<th>Due Date</th>
					<th>Completion Date</th>
				</tr>
				<c:forEach items="${completedtaskList}" var="listentry">
					<tr>
						<td>${listentry.message}</td>
						<td><fmt:formatDate value="${listentry.dueDate}"
								pattern="MM/dd/yyyy" /></td>
						<td>${calstr}</td>
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
</body>
</html>