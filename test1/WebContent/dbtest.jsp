<%@page import="com.cos.test1.config.DBConn"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DB 테스트 파일</title>
</head>

<body>
<%
	Connection conn = DBConn.getConnection();
	if(conn != null){
%>
	연결됨
<%
	}
%>
</body>
</html>