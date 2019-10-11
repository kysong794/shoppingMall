<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="song.*" %>
<%

%>
<html>
<body>
	<header><h1>User Join</h1></header>
	<form id ="frm" action="userjoin" method="post" onsubmit='return che();'>
		<table>
			<tr>
				<td>아이디 : </td>
				<td><input type="text" name="mid"></td>
			</tr>
			<tr>
				<td>비밀번호 : </td>
				<td><input type="password" name="mpw"></td>
			</tr>
			<tr>
				<td>비밀번호 확인 : </td>
				<td><input type="password" name="mpw2"></td>
			</tr>
			<tr>
				<td>이름 : </td>
				<td><input type="text" name="nm"></td>
			</tr>
			<tr>
				<td>성별 : </td>
				<td><input type="radio" name="sex" value="1" checked="checked">남
				<input type="radio" name="sex" value="2">여</td>
			</tr>
		</table>
		<a href="userjoin"><button>회원가입</button></a>
	</form>
</body>
</html>
<script>
	function che(){
		if(frm.mid.value==''||frm.mid.value==null){
			alert('아이디를 입력하세요');
			return false;
		}
		if(frm.mpw.value==''||frm.mpw.value==null){
			alert('비밀번호를 설정하세요');
			return false;
		}
		if(frm.mpw2.value==''||frm.mpw2.value==null){
			alert('비밀번호 확인을 입력하세요');
			return false;
		}
		if(frm.nm.value==''||frm.nm.value==null){
			alert('이름을 입력하세요');
			return false;
		}
		if(frm.mpw.value!==frm.mpw2.value){
			alert('비밀번호가 맞지 안습니다');
			return false;
		}
		return true;
	}
</script>