<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="song.*" %>
<%

%>

<html>
<body>
	<header><h1>상품 등록</h1></header>
	<form id="frm" action="pReg" method="post" onsubmit='return che();'>
		<table>
			<tr>
				<th>상품 번호 : </th>
				<td><input type="number" name="i_product" readonly></td>				
			</tr>
			<tr>
				<th>제품명 : </th>
				<td><input type="text" name="nm"></td>				
			</tr>
			<tr>
				<th>금액 : </th>
				<td><input type="number" name="price"></td>				
			</tr>
			<tr>
				<th>사진 : </th>
				<td><input type="text" name="pic">(이미지 업로드 X,웹 이미지 주소)</td>				
			</tr>
			<tr>
				<th>설명 : </th>
				<td><input type="text" name="info"></td>				
			</tr>
			<tr>
				<th><input type="submit" value="등록"></th>
			</tr>
		</table>
	</form>
</body>
</html>
<script>
	function che(){
		if(frm.nm.value==''||frm.nm.value==null){
			alert('제풍명을 입력하세요.');
			return false;
		}if(frm.price.value==''||frm.price.value==null){
			alert('금액을 입력하세요.');
			return false;
		}if(frm.pic.value==''||frm.pic.value==null){
			alert('사진을 입력하세요.');
			return false;
		}if(frm.info.value==''||frm.info.value==null){
			alert('설명을 입력하세요.');
			return false;
		}
	}
</script>