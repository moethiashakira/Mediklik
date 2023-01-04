package com.mediklik.db;

import java.sql.*;
import org.mariadb.jdbc.MariaDbDataSource;

public class Connect {
	private final static String DBURL = "jdbc:mariadb://localhost:3305/mediklik";
	private final static String DBUSER = "root";
	private final static String DBPASSWORD = "password";
	private Connection conn;
	private static Connect connect = null;
	
	private Connect() {
		MariaDbDataSource source = new MariaDbDataSource();
		try {
			source.setUrl(DBURL);
			source.setUser(DBUSER);
			source.setPassword(DBPASSWORD);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			conn = source.getConnection();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connect getConnection() {
		if (connect == null) {
			synchronized (Connect.class) {
				if(connect == null) connect = new Connect();
			}
		}
		return connect;
	}
	
	public ResultSet query(String q) {
		try {
			Statement queryStatement = conn.createStatement();
			return queryStatement.executeQuery(q);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Boolean update(String q) {
		try {
			Statement updateStatement = conn.createStatement();
			updateStatement.executeUpdate(q);
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public PreparedStatement prepare(String sql) {
		try {		
			PreparedStatement preparedQuery = conn.prepareStatement(sql);
			return preparedQuery;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
