package com.mediklik.application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mediklik.db.Connect;
import com.mediklik.models.Item;
import com.mediklik.models.ItemDisplay;
import com.mediklik.models.User;

public class SessionController {
	private static SessionController session = null;
	private static ArrayList<Item> itemList = new ArrayList<Item>();
	private static ArrayList<ItemDisplay> itemDisplayList = new ArrayList<ItemDisplay>();
	private User user;
	
	private SessionController() {
		
	}
	
	private SessionController(User user) {
		this.user = user;
	}
	
	public static SessionController getSession() {
		if (session == null) {
			session = new SessionController();
		}
		return session;
	}
	
	public static SessionController getSession(User user) {
		if (session == null) {
			session = new SessionController(user);
		}
		return session;
	}
	
	public static void loadItemList() {
		Connect sessionConnect = Connect.getConnection();
		itemList.clear();
		itemDisplayList.clear();
		ResultSet itemRS = sessionConnect.query("select * from item");
		Item itemTMP;
		try {
			while (itemRS.next()) {
				itemTMP = new Item(itemRS);
				itemList.add(itemTMP);
				itemDisplayList.add(new ItemDisplay(itemTMP));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
