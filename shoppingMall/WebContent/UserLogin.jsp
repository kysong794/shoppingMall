<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="song.*" %>
<%
	String mid = (String) request.getAttribute("mid");
	
	if(mid == null){
		mid = "";
	}
	
%>
</head>
<body>
	<header><h1>User Login</h1></header>
	<form id="frm" action="userlogin" method="post" onsubmit='return che();'>
		<table>
			<tr>
				<th>아이디:<input type="text" name="mid" value="<%=mid%>" autocomplete="off"></th>
			</tr>
			<tr>
				<th>비밀번호:<input type="password" name="mpw"></th>
			</tr>
			<tr>
				<td><input type="submit" value="로그인"></td>
			</tr>
		</table>
	</form>
	<div><a href="userjoin" ><button>회원가입</button></a></div>
</body>
</html>
<script>
	function che(){
		if(frm.mid.value==''||frm.mid.value==null){
			alert('존재하지 않는 아이디입니다.');
			return false;
		}
		if(frm.mpw.value==''||frm.mpw.value==null){
			alert('비밀번호를 확인해주세요.');
			return false;
		}
	}
</script>