<%@page import="com.cos.hello.model.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<h1>userInfo Page</h1>

<%
	
	String result = (String)request.getAttribute("result");
	Users users = (Users)request.getAttribute("user");
%>
<%=result %>

<h1>${result}</h1> 

<table border="1">
	<tr>
		<th>번호</th>
		<th>유저네임</th>
		<th>패스워드</th>
		<th>이메일</th>
	</tr>
	<tr>
	<!-- 톰캣 컴파일러가 알아서 get 함수를 호출해준다.  -->
		<td>${user.id }</td>
		<td>${user.username }</td>
		<td>${user.password }</td>
		<td>${user.email }</td>
	</tr>
</table>
</body>
</html>