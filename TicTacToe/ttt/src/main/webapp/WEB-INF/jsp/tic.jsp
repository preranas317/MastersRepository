<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TicTacToe Game</title>
</head>
<body>
	<p align="center" ><font size="6"> Welcome </font><font color="blue" size="6">${player.username}</font></p>
	<br>


	<form:form modelAttribute="game">
		<table border="1" cellspacing="8" cellpadding="8" background="https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcT-MdlBNtlQNDli5oU1IvT82476aGoU9VbbJVbXdmpztOkeDlqG" align="center">

			<c:forEach items="${boards.sarray}" var="a" varStatus="s">
				<tr>
					<c:forEach items="${a}" var="b" varStatus="s1">
						<c:choose>
							

							<c:when test="${b eq 'X' }">
								<td><font color="blue" size="5">${b}</font></td>
							</c:when>
							<c:when test="${b eq 'O' }">
								<td><font color="red" size="5">${b}</font></td>
							</c:when>
							<c:when test="${not empty m1 }">
								<td><font color="black" size="5">${b}</font></td>
							</c:when>
							<c:otherwise>
								<td><a href="tic1.html?i=${s.index }&j=${s1.index}"><font size="5">${b}</font></a></td>
							</c:otherwise>
						</c:choose>

					</c:forEach>
				</tr>
			</c:forEach>
		</table>
		<br>
		<p align="center"><font face="AR BERKLEY" color="#800080" size="7">${m1}</font> </p>
		<br>
		<br>
		<p align="right">
			<font size="5"><a href="tic.html?cnt=10"> NEW GAME</a></font>
		</p>
		
		<p align="right">
		<p><a href="<c:url value="/j_spring_security_logout" />" >logout</a></p>
		</p>
		<c:if test="${empty m1}">
		<p align="right">
			<font size="5"><a href="user/pause1.html"> Pause Game</a></font>
		</p>
		
	</c:if>
	<p align="right"><font size="5"><a href="user/home.html">Home</a></font></p>
	
	
	<p align="right"><font size="5"><a href="register.html">Register</a></font></p>
	</form:form>
</body>
</html>