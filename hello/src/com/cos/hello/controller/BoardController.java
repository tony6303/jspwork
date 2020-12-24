package com.cos.hello.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardController extends HttpServlet{
	//req , res는 톰캣이 만들어줌. (클라이언트의 요청이 있을 때 마다)
	//req는 Reader 할 수 있는 ByteStream 요청
	//res는 Writer 할 수 있는 ByteStream 응답
	//http://localhost:8000/hello/board?gubun=login
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet실행됨");
		doProcess(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doPost실행됨");
		doProcess(req, resp);
	}
	
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("BoardController 실행됨");
		String gubun = req.getParameter("gubun");
		System.out.println(gubun);
		route(gubun, req, resp);
	}
	
	private void route(String gubun, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		if(gubun.equals("insertOne")) {
			resp.sendRedirect("board/insertOne.jsp");
		}else if(gubun.equals("deleteOne")) {
			resp.sendRedirect("board/deleteOne.jsp");
		}else if(gubun.equals("selectAll")) {
			resp.sendRedirect("board/selectAll.jsp");
		}else if(gubun.equals("updateOne")) {
			resp.sendRedirect("board/updateOne.jsp");
		}
	}
}



