<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="song.*" %>
<%
	String title = (String) request.getAttribute("title");
	String view = (String) request.getAttribute("view");
	
	if(title ==null){
		title = "관리자 홈 화면";
	}
	if(view == null){
		view = "AdminHome.jsp";
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
	<nav>
		<a href="pReg">상품 등록</a>
		<a href="pList">상품 리스트</a>
		<a href="pImport">상품입고</a>
		<a href="">판매 현황(day)</a>
		<a href="">판매 현황(mon)</a>
		<a href="">판매 현황(year)</a>
		<a href="adminlogout">로그아웃</a>
	</nav>
	<section>
		<jsp:include page="<%=view %>"></jsp:include>
	</section>
	<footer>
		Copyright&copy;한국쇼핑몰에 오신걸 환영합니다.
	</footer>
</body>
</html>
