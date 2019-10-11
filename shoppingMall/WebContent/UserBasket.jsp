<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="song.*" %>
<%
	List<shoppingVo> list = (List<shoppingVo>) request.getAttribute("list");
%>
<html>
<body>
	<header><h1>장바구니</h1></header>
	<table>
	<tr>
		<th>장바구니 번호</th>
		<th>이미지</th>
		<th>상품명</th>
		<th>금액</th>
		<th>수량</th>
		<th>비고</th>
	</tr>
	<%for(shoppingVo vo : list){ %>
	<tr>
		<td><%=vo.getI_basket() %></td>
		<td><img src="<%=vo.getPic() %>" width="150" height="150"></td>
		<td><%=vo.getNm() %></td>
		<td>단가 : <%=vo.getPrice() %>원 <br>구매 금액 : <%=vo.getSaleprice() %>원</td>
		<td>현재고 : <%=vo.getQty() %>원 <br>구매 수량 : <%=vo.getSaleqty() %>원</td>
		<td></td>
	</tr>
	<%} %>
</table>
	<a href="basket">장바구니</a>
	<a href="">비밀번호 변경</a>

</body>
</html>