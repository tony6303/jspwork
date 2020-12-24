package com.cos.test1.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn {
	public static Connection getConnection() {
		Connection conn = null;
		//localhost 뒤 = DB이름?
		String url = "jdbc:mysql://localhost:3306/ssar?serverTimezone=Asia/Seoul";
		String username = "ssar";
		String password = "bitc5600";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, username, password);
			
			System.out.println("DB 연결성공");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
