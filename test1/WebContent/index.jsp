<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
//톰캣이라는 자바 컴파일러에게 던져서 해석해달라고한다.
	String name = "hong"; 
%>
<h1><%=name%></h1>
</body>
</html>