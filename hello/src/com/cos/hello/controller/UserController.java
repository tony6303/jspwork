package com.cos.hello.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
//javax 로 시작하는 패키지는 톰캣이 갖고있는 라이브러리이다.
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.hello.model.Users;
import com.cos.hello.service.UsersService;

public class UserController extends HttpServlet {
	// 12월 21알 월요일
	// req , res는 톰캣이 만들어줌. (클라이언트의 요청이 있을 때 마다)
	// req는 Reader 할 수 있는 ByteStream 요청
	// res는 Writer 할 수 있는 ByteStream 응답
	// http://localhost:8000/hello/user?gubun=login

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String gubun = req.getParameter("gubun");
//		route(gubun, req, resp);
		System.out.println("doGet실행됨");
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doPost실행됨");
		doProcess(req, resp);
	}

	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//      super.doGet(req, resp);
		System.out.println("UserController 실행됨");

		String gubun = req.getParameter("gubun");
		System.out.println(gubun);
		try {
			route(gubun, req, resp);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void route(String gubun, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, SQLException {
		UsersService usersService = new UsersService();
		
		if (gubun.equals("login")) {
			resp.sendRedirect("auth/login.jsp");
		} else if (gubun.equals("join")) {
			resp.sendRedirect("auth/join.jsp");
		} else if (gubun.equals("selectOne")) {
			usersService.유저정보보기(req, resp);
			// 인증이 필요한 페이지
			
			
//			resp.sendRedirect("/hello/user/selectOne.jsp");
			//쿠키 읽기 (클라이언트에 저장된 모든 쿠키를 읽어옴)
//			Cookie[] c = req.getCookies();
//			if (c != null) {
//				for (int i = 0; i < c.length; ++i) {
//					if (c[i].getName().equals("CookieName")) {
//						System.out.println(c[i].getName());
//						System.out.println(c[i].getValue()); // session key
//					}
//				}
//			}

		} else if (gubun.equals("updateOne")) {
			usersService.유저정보수정페이지(req, resp);
		} else if (gubun.equals("joinProc")) { 
			usersService.회원가입(req, resp);			
			
			
			
			
			HttpSession session = req.getSession();
			session.setAttribute("sessionKey", "9990");
			resp.setHeader("Set-Cookie", "sessionKey=9990");
//			resp.sendRedirect("index.jsp");

		} else if (gubun.equals("loginProc")) {
			usersService.로그인(req, resp);
			//SELECT id, username, email From users where username = ? and password = ?
			//DAO 함수명 : login() , return -> Users Object
			//정상 -> 세션을 담고 index.jsp , 비정상 -> login.jsp
			
			
			
			
//					    	  new Cookie(name, value)
			//new 쿠키
			Cookie myCookie = new Cookie("CookieName", "What a delicious cookie");
			//쿠키 세팅 (안해주면 오류나더라 왜지? new하면서 초기화됬는데.) 
			myCookie.setValue("Wow");
			//쿠키 전달 (클라이언트에 저장됨)
			resp.addCookie(myCookie);			
//			resp.setHeader("Set-Cookie", "sessionKey=9998");
			// 4번 index.jsp로 이동
//			resp.sendRedirect("index.jsp");

		}
	}
}
