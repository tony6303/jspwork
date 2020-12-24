package com.cos.hello.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnMySQL{
	public static Connection getInstance() throws SQLException{
		
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("mysql/ssar");
			Connection conn = ds.getConnection();
			System.out.println("DB연결 성공");
			return conn;
		} catch (NamingException e) {
			System.out.println("DB연결 실패");
			e.printStackTrace();
		}
		return null;
		
	}
}
