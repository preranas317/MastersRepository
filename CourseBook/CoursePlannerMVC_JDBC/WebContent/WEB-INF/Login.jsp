<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<c:if test="${not empty param.login}">
<c:forEach items="${list}" var="listEntry" >
  <c:if test="${param.uname == '${listEntry.uname}' and param['pswd'] == '${listEntry.pswd}'}">
    <c:set var="user" value="${param.uname}" scope="session" />
    <c:redirect url="CourseBook_JDBC.jsp" />
  </c:if>
  </c:forEach>
</c:if>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
</head>
<body>
<p> <font color="red">${message.m1}</font></p>
<form action="Login" method="post">
<table border="1">
<tr><td>UserName : </td><td><input type="text" name="uname"></td></tr>
<tr><td>Password : </td><td><input type="text" name="pswd"></td></tr>

</table>
<input type="submit" name="login" value="Login">
</form>
</body>
</html>