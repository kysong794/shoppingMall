<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="song.*" %>
<%
	List<shoppingVo> list = (List<shoppingVo>) request.getAttribute("list");
%>
<html>
<body>
	<header><h1>상품 리스트</h1></header>
	<table>
		<tr>
			<th>상품 번호</th>
			<th>이미지</th>
			<th>상품명</th>
			<th>금액</th>
			<th>수량</th>
			<th>판매 여부</th>
			<th>비고</th>
		</tr>
		<%for(shoppingVo vo : list){ %>
		<tr>
			<th><%=vo.getI_product() %></th>
			<th><img src="<%=vo.getPic() %>" height="150" width="150"></th>
			<th><%=vo.getNm() %></th>
			<th><%=vo.getPrice() %></th>
			<th><%=vo.getQty() %></th>
			<%if("1".equals(vo.getYn_sale())){ %>
			<th>판매중</th>
			<%}else{ %>
			<th>판매정지</th>
			<%} %>
			<th><a href="pmod?nm=<%=vo.getNm() %>&pic=<%=vo.getPic()%>&price=<%=vo.getPrice()%>&info=<%=vo.getInfo()%>&yn_sale=<%=vo.getYn_sale()%>"><button>수정</button></a></th>
		</tr>
		<%} %>
	</table>
</body>
</html>