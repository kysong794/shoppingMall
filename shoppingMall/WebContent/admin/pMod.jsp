<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="song.*" %>
<%
	String nm = (String)request.getAttribute("nm");
	String price = (String)request.getAttribute("price");
	String pic = (String)request.getAttribute("pic");
	String yn_sale = (String)request.getAttribute("yn_sale");
	String info = (String)request.getAttribute("info");

%>

<html>
<body>
	<header><h1>상품 수정</h1></header>
	<form id="frm" action="pmod" method="post">
	<div>제품명:<input type="text" name="nm" value="<%=nm %>"></div>
	<div>금액:<input type="number" name="price" value="<%=price %>"></div>
	<div>사진:<input type="text" name="pic" value="<%=pic%>">(이미지 업로드 X,웹 이미지 주소)</div>
	<div>판매여부:</div>
	<div><select name="yn_sale">
	<option value="">판매여부 선택</option>
	<option value="0">판매 정지</option>
	<option value="1">판매중</option>	
	</select></div>
	<div>설명:<input type="text" name="info" value="<%=info %>"></div>
	<div><input type="submit" value="수정"></div>
	</form>
</body>
</html>