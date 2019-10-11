<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="song.*" %>
<%
	shoppingVo vo = (shoppingVo) request.getAttribute("vo");
	
%>
<html>
<body>
	<header><h1>상품 정보</h1></header>	
		<form id="frm" action="pdetail" method="post" onsubmit='return che()'>
		<div>
			<p><input type="hidden" name="i_product" value="<%=vo.getI_product()%>"></p>
			<p>제품명:<%=vo.getNm() %></p>
			<p><img src="<%=vo.getPic() %>" width="150" height="150"></p>
			<p>금액 :<input type="hidden" name="price" value="<%=vo.getPrice() %>" readonly><%=vo.getPrice() %>원</p>
			<p>수량 :<input type="hidden" name="qty" value="<%=vo.getQty() %>"readonly><%=vo.getQty() %>개</p>
			<p>정보 :<input type="hidden" name="info" value="<%=vo.getInfo() %>"readonly><%=vo.getInfo() %></p>
			<p>수량 :
			<input type="button" name="operator" value="-" onclick="countnum(this);">
			<input type="number" name="cnt" value="0" readonly="readonly">
			<input type="button" name="operator" value="+" onclick="countnum(this);"></p>
			<div><input type="submit" value="장바구니에 담기" name="basket"></div>
			<div><input type="button" value="바로구매" name="purchase"></div>
		</div>
		</form>
</body>
</html>
<script>
	function countnum(e){
		if(e.value === '-'){
			if(frm.cnt.value>0){
				frm.cnt.value--;
			}
		}
		else if(e.value === '+'){
				frm.cnt.value++;
			}
		}
	function che(){
		if(<%=vo.getQty() %><frm.cnt.value){
			alert('재고가 부족합니다.');
			return false;
		}
		if(frm.cnt.value==0||frm.con.value==null){
			alert('제품수량은 0 이상이어야 합니다.');
			return false;
		}
		return true;
	}
	

</script>