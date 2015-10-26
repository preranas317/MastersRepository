<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
   
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Course Book</title>

</head>
<body>
<h1> Course Entries</h1>


 <c:set var="user" value="${uname}"/>
 <c:if test="${user ne null}"> 
<p><h4> WELCOME ${user}</h4></p>
<table Border=2 cellspacing='2'>
 <tr><th>id</th><th>Course Code</th><th>Course Name</th><th>Pre-requisite</th><th>Operation</th></tr>

	<c:forEach items="${bookentry}" var="entry" varStatus="status">
		
		<tr>
		<td>${entry.id}</td>
		<td>${entry.scode}</td>
		<td>${entry.sname}</td>
		<td>${entry.spreq}</td>
		<td><a href ="EditCourse?id=${entry.id}">Edit</a></td>
		</tr>
	</c:forEach>
	</table>
	<p><a href='AddCourse'>Add Course</a></p>
	<p><a href='Logout'>LogOut</a></p>
	<p><a href='CoursePlanner' id="null" name="button">CoursePlanner</a></p>
	<p><a href='abc' >Saved Course Plans</a>
 </c:if> 
 
 
 

<c:if test="${user eq null}">
<table Border=2 cellspacing='2'>
 <tr><th>id</th><th>Course Code</th><th>Course Name</th><th>Pre-requisite</th></tr>
		<c:forEach items="${bookentry}" var="entry" varStatus="status">
			
			<tr>
			<td>${entry.id}</td>
			<td>${entry.scode}</td>
			<td>${entry.sname}</td>
			<td>${entry.spreq}</td>
			</tr>
		</c:forEach>
	</table>	
		<p><a href='Login'>LogIn</a></p>
		<p><a href='Register'>Register</a></p>
		<p><a href='CoursePlanner' id="null" name="button">CoursePlanner</a></p>
		
		
</c:if> 


</body>
</html>