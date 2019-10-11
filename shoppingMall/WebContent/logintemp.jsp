<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="song.*" %>
<%
	String title = (String) request.getAttribute("title");
	String view = (String) request.getAttribute("view");
	
	if(title ==null){
		title = "유저 로그인";
	}
	if(view == null){
		view = "UserLogin.jsp";
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=title %></title>
</head>
<body>
	<header><h1></h1></header>
		<a href="userlogin">로그인</a>
	<section>
		<jsp:include page="<%=view %>"></jsp:include>
	</section>
	<footer>
		Copyright&copy;한국쇼핑몰에 오신걸 환영합니다.
	</footer>
</body>
</html>