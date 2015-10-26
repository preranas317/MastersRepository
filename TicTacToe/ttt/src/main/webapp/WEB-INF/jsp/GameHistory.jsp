<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Game History Page</title>
</head>
<body background="images/b1.jpg">
	<h2 align="center">
		<font color="black" size="14">T!( <font color="red" size="14">T@(</font>
			T0(-
		</font>
	</h2>
	1. No of total games completed : ${games}
	<br>
	<br> 2. No of 1-player games completed : ${oneplayer}
	<br>
	<br> 3. No of 2-player games completed :${twoplayer}
	<br>
	<br> 4. The Win rate against AI : ${oneplayerwon} %
	<br>
	<br> 5. The win rate against human player : ${twoplayerwon} %
	<br>
	<br> 6. Game Played in current month by
	<font color="blue">${user} :</font>
	<br>
	<br>
	<br>
	<table border="1">
		<tr>
			<th>Opponent</th>
			<th>Game Length</th>
			<th>Outcome</th>
		</tr>
		<c:forEach items="${l1}" var="i" varStatus="s">

			<tr>
				<c:choose>
					<c:when test="${empty i.player2 }">
						<td>AI</td>
					</c:when>
					<c:otherwise>
						<c:set var="a" value="${i.player2.username}" />
						<c:set var="b" value="${player.username}" />
						<c:choose>
							<c:when test="${a eq b }">
								<td>${i.player1.username}</td>
							</c:when>
							<c:otherwise>
								<td>${i.player2.username}</td>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
				<td>${l2[s.index]}</td>
				<td>${i.result}</td>
			</tr>

		</c:forEach>
	</table>
	<p>
		<a href="home.html">Back</a>
	</p>
</body>
</html>