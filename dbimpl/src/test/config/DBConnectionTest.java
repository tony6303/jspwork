package test.config;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;

import org.junit.Test;

import config.DBConnection;

public class DBConnectionTest {
	
	//Junit
	@Test
	public void DatabaseConnectionTest() {
		DBConnection.getinstance();
		Connection conn2 = null; // JUnit 빨간불 나옴
		assertNotNull(conn2);
	}
	
	
}
