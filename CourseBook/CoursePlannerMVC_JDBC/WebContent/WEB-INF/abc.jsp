<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>abc page</title>
</head>
<body>

<c:if test="${t eq null}">
<p>Your Course Plans</p>
<ul>
<c:forEach items="${abc}" var="i">
<fmt:formatDate value="${i}"
							pattern="yyyy-MM-dd HH:mm:ss " var="a" />
 <li><a href="abc?t=${a}">Saved at <fmt:formatDate value="${i}"
							pattern="M/dd/yyyy hh:mm a " /></a></li>
</c:forEach>
</ul>
</c:if>
<c:if test="${t ne null}">

<p>Here is your course plan:</p>
<c:forEach items="${l2}" var="l">
					<br>
						<font color="blue"> ${l.name}</font>
						 <table Border=2 cellspacing='2'>
						 <tr>
						<th>Course Code</th>
						<th>Course Name</th>
						<th>Pre-requisite</th>
					</tr>
						
							<c:set var="list" value="${l.subjlist}"></c:set>
							 
							<c:forEach items="${list}" var="tmpentry">
							<tr>
							<td>${tmpentry.scode}</td>
							<td>${tmpentry.sname}</td>
							<td>${tmpentry.spreq}</td>
							</tr>
							</c:forEach>
						
						</table>
					</c:forEach>
					<a href="abc">Back</a>
</c:if>

</body>
</html>