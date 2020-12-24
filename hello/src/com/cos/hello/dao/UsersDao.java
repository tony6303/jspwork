package com.cos.hello.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cos.hello.config.DBConnMySQL;
import com.cos.hello.model.Users;

public class UsersDao {
	//(String username,String password,String email)
	public int insert(Users users) throws SQLException {// 회원가입수행해줘
		// 데이터 원형 username=ssar&password=1234&email=ssar@nate.com
		// 1번 form의 input태그에 있는 3가지 값 username, passeword, email받기
			
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO users(username, password, email)");
		sb.append("VALUES(?,?,?)");
		String sql = sb.toString();
		Connection conn = DBConnMySQL.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, users.getUsername());
			pstmt.setString(2, users.getPassword());
			pstmt.setString(3, users.getEmail());
			int result2 = pstmt.executeUpdate(); // 변경된 row count를 리턴, 오류 시 -1를 리턴
			
//			pstmt.executeUpdate();
			 
//			return pstmt.executeUpdate();
			if(result2 == 1) {
				System.out.println("result2 : " + result2);
				System.out.println("회원가입 성공");
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 3번 INSERT가 정상적으로 되었다면 index.jsp
		
		return -1;
	}
	
	public Users login(Users users) throws SQLException {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT id, username, password, email FROM users WHERE username = ? AND password = ?");
		String sql = sb.toString();
		Connection conn = DBConnMySQL.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, users.getUsername());
			pstmt.setString(2, users.getPassword());
//			int result2 = pstmt.executeQuery(sql); // 변경된 row count를 리턴, 오류 시 -1를 리턴
//			System.out.println("result2 : " + result2);
			
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				Users userEntity = Users.builder()
						.id(rs.getInt("id"))
						.username(rs.getString("username"))
						.password(rs.getString("password"))
						.build();
				
				return userEntity;
			}
			System.out.println("로그인 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("로그인 실패");
		return null;
	}
	
	public Users selectById(int id) throws SQLException {
		StringBuffer sb = new StringBuffer(); //간단한 문장은 String 으로 , 복잡한 문장은 Buffer로
		sb.append("SELECT id, username, password, email FROM users WHERE id = ?");
		String sql = sb.toString();
		
		Connection conn = DBConnMySQL.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);			
//			int result2 = pstmt.executeQuery(sql); // 변경된 row count를 리턴, 오류 시 -1를 리턴
//			System.out.println("result2 : " + result2);		
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				Users userEntity = Users.builder()
						.id(rs.getInt("id"))
						.username(rs.getString("username"))
						.password(rs.getString("password"))
						.email(rs.getString("email"))
						.build();
				System.out.println("SelectById 성공");
				return userEntity;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("SelectById 실패");
		return null;
	}
}
