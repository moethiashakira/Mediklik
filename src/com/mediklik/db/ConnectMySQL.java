package com.mediklik.db;

import java.sql.*;
import com.mysql.cj.jdbc.MysqlDataSource;

public class ConnectMySQL {
	private final static String DBURL = "jdbc:mysql://localhost/mediklik";
	private final static String DBUSER = "root";
	private final static String DBPASSWORD = "password";
	private Connection conn;
	private static ConnectMySQL connect = null;
	
	private ConnectMySQL() {
		MysqlDataSource source = new MysqlDataSource();
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
	
	public static ConnectMySQL getConnection() {
		if (connect == null) {
			synchronized (ConnectMySQL.class) {
				if(connect == null) connect = new ConnectMySQL();
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
