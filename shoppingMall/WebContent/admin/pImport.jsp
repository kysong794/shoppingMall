<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="song.*" %>
<%
	List<shoppingVo> list = (List<shoppingVo>)request.getAttribute("list");
	List<shoppingVo> pImportList = (List<shoppingVo>)request.getAttribute("pImportList");
%>
<html>
<body>
	<h1>상품 입고</h1>
	<form id="frm" action="pImport" method="post">
	<div>상품:<select name="i_product">
	<option value="">--선택--</option>
	<%for(shoppingVo vo : list){ %>
	<option value="<%=vo.getI_product()%>"><%=vo.getNm() %></option>
	<%} %>
	</select></div>
	<div>수량 : <input type="number" name="qty"></div>
	<input type="submit" value="입고">
	</form>
	
	<h1>입고 리스트</h1>
	<table>
		<tr>
			<th>입고 번호</th>
			<th>상품명</th>
			<th>금액</th>
			<th>수량</th>
		</tr>
		<%for(shoppingVo vo : pImportList) {%>
		<tr>
			<td><%=vo.getI_pi() %></td>
			<td><%=vo.getNm() %></td>
			<td><%=vo.getSaleprice() %>원</td>
			<td><%=vo.getQty()%>개</td>
		</tr>
		<%} %>
	</table>
</body>
</html>