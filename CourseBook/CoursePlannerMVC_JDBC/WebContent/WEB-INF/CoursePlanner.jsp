<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="current" class="java.util.Date" />

<%-- <c:set var="session" value="true" scope="session"/> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Course Planner</title>
</head>
<body>
<c:set var="year" value="${weekYear}"></c:set>
 <c:set var="next" value="NEXT"></c:set>
<c:set var="finish1" value="FINISH"></c:set> 
<c:set var="name" value="${quartername}"/>
<c:set var="finish" value="${finish}"></c:set>

<c:set var="tmpliststatus" value="${tmpliststatus}"></c:set>
<%-- <c:if test="${tmpliststatus ne null}">
	 --%>
	 
	 finish : ${finish}
	 
<form action="CoursePlanner" method="post">
<c:choose>

<c:when test="${finish eq null}">
Please select the courses you have already taken:
	<table Border=2 cellspacing='2'>


		<tr>
			<th></th>
			<th>Course Code</th>
			<th>Course Name</th>
			<th>Pre-requisite</th>
		</tr>

		<c:forEach items="${bookentry}" var="entry" varStatus="status">



			<tr>
				<td><input type="checkbox" name="chkcourse" value="${entry.id}"></td>
				<td>${entry.scode}</td>
				<td>${entry.sname}</td>
				<td>${entry.spreq}</td>
			</tr>
		</c:forEach>

	</table>
	<br><input type="submit" name="button" value="NEXT" >
	</c:when>
	
	<c:when test="${finish eq next}">
<%-- 	<p> tmpliststatus : ${tmpliststatus} </p> --%>
<c:choose>
<c:when test="${tmpliststatus ne null}">
	You may take the following courses in  <font color="blue"> ${name} ${year}.</font>
			<table Border=2 cellspacing='2'>

					<tr>
						<th></th>
						<th>Course Code</th>
						<th>Course Name</th>
						<th>Pre-requisite</th>
					</tr>
					<c:forEach items="${tmplist}" var="entry">
					

						<tr>
							<td><input type="checkbox" name="chkcourse" value="${entry.id}"></td>
							<td>${entry.scode}</td>
							<td>${entry.sname}</td>
							<td>${entry.spreq}</td>
						</tr>
					</c:forEach>

			</table>
			<br><input type="submit" name="button" value="NEXT">
			<br><input type="submit" name="button" value="FINISH">
		</c:when>
		<c:otherwise>
		<p>No records to display. Your Course Plan is : </p>
			<c:forEach items="${finalquarterList}" var="entryquarter">
					<br>
						<font color="blue"> ${entryquarter.name} ${entryquarter.year}</font>
						 <table Border=2 cellspacing='2'>
						 <tr>
						<th>Course Code</th>
						<th>Course Name</th>
						<th>Pre-requisite</th>
					</tr>
						
							<c:set var="list" value="${entryquarter.subjlist}"></c:set>
							 
							<c:forEach items="${list}" var="tmpentry">
							<tr>
							<td>${tmpentry.scode}</td>
							<td>${tmpentry.sname}</td>
							<td>${tmpentry.spreq}</td>
							</tr>
							</c:forEach>
						
						</table>
					</c:forEach>
					<fmt:formatDate
				type="both" pattern="yyyy:MM:dd HH:mm:ss" value="${current}" var="t"/>
				<c:if test="${uname ne null}">
						<a href="Done?v=done">Done</a> | <a href="Done?v=save">Save this course plan</a>
				</c:if>
				<c:if test="${uname eq null}">
						<a href="Done?v=done">Done</a>
				</c:if>
		</c:otherwise>
		</c:choose>
			</c:when>
			
			
	<c:when test="${finish eq finish1}">
			

					
					<c:forEach items="${finalquarterList}" var="entryquarter">
					
						<font color="blue"> ${entryquarter.name} ${entryquarter.year}</font>
						 <table Border=2 cellspacing='2'>
						 <tr>
						<th>Course Code</th>
						<th>Course Name</th>
						<th>Pre-requisite</th>
					</tr>
						
							<c:set var="list" value="${entryquarter.subjlist}"></c:set>
							 
							<c:forEach items="${list}" var="tmpentry">
							<tr>
							<td>${tmpentry.scode}</td>
							<td>${tmpentry.sname}</td>
							<td>${tmpentry.spreq}</td>
							</tr>
							</c:forEach>
						
						</table>
					</c:forEach>
					<fmt:formatDate
				type="both" pattern="yyyy:MM:dd HH:mm:ss" value="${current}" var="t" />
				<c:if test="${uname ne null}">
						<a href="Done?v=done">Done</a> | <a href="Done?v=save">Save this course plan</a>
				</c:if>
				<c:if test="${uname eq null}">
						<a href="Done?v=done">Done</a>
				</c:if>
			</c:when>
	
	
	</c:choose>	
	
	</form>

</body>
</html>