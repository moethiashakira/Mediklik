package com.mediklik.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User {
	private String userID;
	private String userName;
	private String password;
	private boolean isadmin;
	private int balance;
	private ArrayList<ItemQuantity> cart = new ArrayList<ItemQuantity>();
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String userID, String userName, String password, boolean isadmin, int balance, ArrayList<ItemQuantity> cart) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.password = password;
		this.isadmin = isadmin;
		this.balance = balance;
		this.cart = cart;
	}
	
	public User(ResultSet userRow) {
		try {
			this.userID = userRow.getString("UserID");
			this.userName = userRow.getString("UserName");
			this.password = userRow.getString("Password");
			this.isadmin = userRow.getBoolean("IsAdmin");
			this.balance = userRow.getInt("Balance");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getIsAdmin() {
		return isadmin;
	}

	public void setStatus(boolean isadmin) {
		this.isadmin = isadmin;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public ArrayList<ItemQuantity> getCart() {
		return cart;
	}

	public void setCart(ArrayList<ItemQuantity> cart) {
		this.cart = cart;
	}
}
