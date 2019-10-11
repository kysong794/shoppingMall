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
		<div>
		<%for (shoppingVo vo : list){ %>
			<p><a href="pdetail?i_product=<%=vo.getI_product()%>"><img src=<%=vo.getPic() %> width="150"height="150"></a></p>
			<p><%=vo.getNm() %></p>
			<p><%=vo.getPrice() %> 원</p>	
			<%} %>
		</div>
		
</body>
</html>
