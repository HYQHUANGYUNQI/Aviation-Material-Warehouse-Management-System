<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="javax.servlet.http.Cookie"%>
<%
	String userId = request.getParameter("username");
	session.setAttribute("userId", userId);
%>	
	window.location.href = "/AMWMS/user/airmaterial.jsp";
</script>
</body>
</html>