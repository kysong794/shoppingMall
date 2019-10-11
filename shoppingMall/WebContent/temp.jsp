<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="song.*" %>
<%
	String title = (String) request.getAttribute("title");
	String view = (String) request.getAttribute("view");
	
	if(title ==null){
		title = "사용자 홈 화면";
	}
	if(view == null){
		view = "home.jsp";
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
		<a href="pList">상품리스트</a>
		<a href="">구매리스트</a>
		<a href="basket">마이페이지</a>
		<a href="userlogout">로그아웃</a>
	<section>
		<jsp:include page="<%=view %>"></jsp:include>
	</section>
	<footer>
		Copyright&copy;한국쇼핑몰에 오신걸 환영합니다.
	</footer>
</body>
</html>