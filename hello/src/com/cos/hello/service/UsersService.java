package com.cos.hello.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.hello.dao.UsersDao;
import com.cos.hello.model.Users;

public class UsersService {
	public void 회원가입(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		//input 에서 값을 받아옴
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");

		System.out.println("=========joinProc Start=========");
		System.out.println(username);
		System.out.println(password);
		System.out.println(email);
		System.out.println("=========joinProc End=========");
		// 2번 DB에 연결해서 3가지 값을 INSERT하기
		// input 값을 토대로 Users 빌드
		Users user = Users.builder()
				.username(username)
				.password(password)
				.email(email)
				.build();
		
		// insert 함수 실행 (sql로 데이터베이스에 insert)  
		UsersDao usersDao = new UsersDao();
		int result = usersDao.insert(user); //pstmt
		
		if(result == 1) {
               resp.sendRedirect("auth/login.jsp");
            } else {
               resp.sendRedirect("auth/join.jsp");
            }
	}
	
	public Users 로그인(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		System.out.println("=========loginPorc Start=========");
		System.out.println(username);
		System.out.println(password);
		System.out.println("=========loginPorc End=========");
		// 2번 DB값이 있는지 select 해서 확인 (생략)
		Users user = Users.builder()
				.username(username)
				.password(password)
				.build();

		// login 함수 실행 (sql로 데이터베이스에 select)
		UsersDao usersDao = new UsersDao();
		Users userEntity = usersDao.login(user);
		
		if(userEntity != null) {
			// session에는 사용자 패스워드 절대넣지않기
			// 3번 세션 키 발급
			HttpSession session = req.getSession();
			
			//setAttribute : name으로 지정한 이름에 value값을 할당합니다.
			session.setAttribute("sessionUser", userEntity); // name , value
			
			resp.sendRedirect("index.jsp");
			return user;
		}else {
			resp.sendRedirect("auth/login.jsp");
		}
		return null;
	}
	
	public void 유저정보보기(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
//		String result;
		HttpSession session = req.getSession();
//		if (session.getAttribute("sessionUser") != null) {
//			//getAttribute : name이란 이름에 해당되는 속성값을 Object타입으로 반환합니다. 없으면 null로 반환
//			Users user = (Users) session.getAttribute("sessionUser");
////			result = "인증 되었습니다.";
//			System.out.println("인증된 사용자입니다.");
//			System.out.println(user);
//		} else {
////			result = "인증되지 않았습니다.";
//			System.out.println("인증되지 않았습니다.");
//		}
		
//		req.setAttribute("result", result);
		
		Users users = (Users)session.getAttribute("sessionUser");
		UsersDao usersDao = new UsersDao();
		if(users != null) {
			Users userEntity = usersDao.selectById(users.getId());
			req.setAttribute("users", userEntity); // name , Object
			
			// xxx.jsp로 이동할겁니다 라는 객체
			RequestDispatcher dis = req.getRequestDispatcher("user/selectOne.jsp");
			
			// 덮어쓰기
			dis.forward(req, resp);
		}else {
			System.out.println("유저정보보기() 실패");
		}
	}
	
	public void 유저정보수정페이지(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
		HttpSession session = req.getSession();
		Users users = (Users)session.getAttribute("sessionUser");
		UsersDao usersDao = new UsersDao();
		if(users != null) {
			Users userEntity = usersDao.selectById(users.getId());
			req.setAttribute("user", userEntity); // name , Object
			
			// xxx.jsp로 이동할겁니다 라는 객체
			RequestDispatcher dis = req.getRequestDispatcher("user/updateOne.jsp");
			
			// 덮어쓰기
			dis.forward(req, resp);
		}
	}
}
