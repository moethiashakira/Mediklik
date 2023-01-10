package com.mediklik.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User {
	private int userID;
	private String userName;
	private String password;
	private boolean isadmin;
	private int balance;
	private ArrayList<ItemStoreQuantity> cart = new ArrayList<ItemStoreQuantity>();
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(int userID, String userName, String password, boolean isadmin, int balance) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.password = password;
		this.isadmin = isadmin;
		this.balance = balance;
	}
	
	public User(ResultSet userRow) {
		try {
			this.userID = userRow.getInt("UserID");
			this.userName = userRow.getString("UserName");
			this.password = userRow.getString("Password");
			this.isadmin = userRow.getBoolean("IsAdmin");
			this.balance = userRow.getInt("Balance");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
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

	public ArrayList<ItemStoreQuantity> getCart() {
		return cart;
	}

	public void setCart(ArrayList<ItemStoreQuantity> cart) {
		this.cart = cart;
	}
}
