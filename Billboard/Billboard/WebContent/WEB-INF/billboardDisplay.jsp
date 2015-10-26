<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BillBoard Display</title>
</head>
<body>
<table border="1">
<tr><th>Rank</th><th>Song</th><th>Artist</th><th>Operations</th></tr>
<c:forEach items="${billboardList}" var="entry">

<tr>
	<td>${entry.rank}</td>
	<td>${entry.song}</td>
	<td>${entry.artist}</td>
	<td><a href="billboardDisplay?index=${entry.rank}&nme=up">up</a>|<a href="billboardDisplay?index=${entry.rank}&nme=down">down</a></td>
</tr>
<input type="hidden" name="index" value="${entry.rank}">
</c:forEach>
<form action="billboardDisplay" method="post">
<tr>
	<td></td>
	<td><input type="text" name="song"></td>
	<td><input type="text" name="artist"></td>
	<td><input type="submit" name="add" value="Add"></td>
</tr> 
</form>
</table>

</body>
</html>