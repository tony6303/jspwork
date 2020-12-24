package com.cos.hello2.controller;

import java.io.IOException;

import javax.servlet.ServletException;
//javax 로 시작하는 패키지는 톰캣이 갖고있는 라이브러리이다.
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserController extends HttpServlet{
	//12월 21알 월요일
	//req , res는 톰캣이 만들어줌. (클라이언트의 요청이 있을 때 마다)
	//req는 Reader 할 수 있는 ByteStream 요청
	//res는 Writer 할 수 있는 ByteStream 응답
	//http://localhost:8000/hello2/user?gubun=login
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("UserController 실행됨");
		
		String gubun = req.getRequestURI();
		System.out.println(gubun);
		if(gubun.equals("login")) {
			resp.sendRedirect("localhost:8000/hello2/auth/login.jsp");
		}else if(gubun.equals("join")) {
			resp.sendRedirect("localhost:8000/hello2/auth/join.jsp");
		}else if(gubun.equals("selectOne")) {
			resp.sendRedirect("localhost:8000/hello2/user/selectOne.jsp");
		}else if(gubun.equals("updateOne")) {
			resp.sendRedirect("localhost:8000/hello2/user/updateOne.jsp");
		}
	}
}
