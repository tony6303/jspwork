package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.DBConnection;
import model.Dept;

//데이터에 접근하게 해주는 객체, 재사용하기위해 따로 만듦
//Data Access Object
public class DeptDao {
	// 함수로 모듈화
	public static void 추가(int id) {
//			String sql = "INSERT INTO test1(id) VALUES("+id+")"; //이렇게하면 인젝션 뚫림
		String sql = "INSERT INTO test1(id) VALUES(?)";
		Connection conn = DBConnection.getinstance();
		// Byte Stream (?)

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id); // 첫번째 '?' 부분에 id를 넣겠다.
			int result = pstmt.executeUpdate(); // 변경된 row count를 리턴, 오류 시 -1를 리턴
			System.out.println("result : " + result);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 함수로 모듈화
	public static void 삭제(int id) {
		String sql = "DELETE FROM test1 WHERE id = ?";
		Connection conn = DBConnection.getinstance();
		// Byte Stream (?)

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id); // 첫번째 '?' 부분에 id를 넣겠다.
			int result = pstmt.executeUpdate(); // 변경된 row count를 리턴, 오류 시 -1를 리턴
			System.out.println("result : " + result);
//				pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 함수로 모듈화
	public static void 수정(int id) {
		String sql = "UPDATE test1 SET 4 WHERE id = ?";
		Connection conn = DBConnection.getinstance();
		// Byte Stream (?)

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id); // 첫번째 '?' 부분에 id를 넣겠다.
			int result = pstmt.executeUpdate(); // 변경된 row count를 리턴, 오류 시 -1를 리턴
			System.out.println("result : " + result);
//				pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 함수로 모듈화 , 사용자 scott으로 변경해야함
	// return값 Dept
	public static Dept 찾기(int deptno) {
		String sql = "SELECT deptno, dname, loc FROM dept WHERE deptno=?";
		Connection conn = DBConnection.getinstance();
		// Byte Stream (?)

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, deptno); // 첫번째 '?' 부분에 deptno를 넣겠다.
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Dept dept = Dept.builder()
						.deptno(rs.getInt("deptno"))
						.dname(rs.getString("dname"))
						.loc(rs.getString("loc"))
						.build();
				//build 쓰는이유 : Value를 순서대로 넣지않아도 된다 .
				System.out.println(dept);
				return dept;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 함수로 모듈화 , 사용자 scott으로 변경해야함
	// return값 List<Dept>
	// 혼자쓰는 프로그램이라서 static 이 가능, 여러명이 쓴다면 반드시 new 로 사용해야 함
	// 데이터베이스는 비동기를 못 한다는 특징이 있다. (작업하나가 끝나길 기다려야함)
	public static List<Dept> 전체찾기() {
		String sql = "SELECT deptno, dname, loc FROM dept";
		Connection conn = DBConnection.getinstance();
		// Byte Stream (?)

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			// List 하위에 vector , ArrayList가 있음
			List<Dept> listDept = new ArrayList<Dept>();
			while (rs.next()) {
				Dept dept = Dept.builder()
						.deptno(rs.getInt("deptno"))
						.dname(rs.getString("dname"))
						.loc(rs.getString("loc"))
						.build();
				//build 쓰는이유 : Value를 순서대로 넣지않아도 된다 .
				System.out.println(dept); // toString() 작동 ?

				// 컬렉션에 담기
				listDept.add(dept);
			}
			return listDept;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
