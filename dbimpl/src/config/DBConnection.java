package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	//커넥션을 리턴하는게 목적입니다
	//PC의 서버에 연결할것입니다
	public static Connection getinstance() {
		Connection conn = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
//		String user = "superuser";
//		String password = "1234";
		
		String user = "scott";
		String password = "TIGER";
		
		//OracleDriver 클래스를 메모리에 로드, 알집파일 안에 있음
		//패키지 경로
		try {
			//문자열에 오류가 있을수도있으니 자체적으로 try문을 권장함
			//reflection 메모리에떠있는 타입을 찾아낸다 (getconnection 컨트롤 클릭 해보시오)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("DB연결 성공이요");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("연결 실패");
		return null;
	} // end of getinstance 
}
