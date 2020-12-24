<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<hr/>
<form action="/hello/user?gubun=joinProc" method="post" >
	<input type="text" name="username" placeholder="username"/>
	<input type="password" name="password" placeholder="password"/>
	<input type="email" name="email" placeholder="email"/>
	<button>회원가입</button> <!-- 로그인은 무조건 post요청입니다 -->
</form>
<h1>Join Page</h1>
</body>
</html>