package com.mediklik.application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mediklik.db.Connect;
import com.mediklik.models.Item;
import com.mediklik.models.ItemCartDisplay;
import com.mediklik.models.ItemDisplay;
import com.mediklik.models.ItemQuantity;
import com.mediklik.models.User;

public class SessionController {
	private static SessionController session = null;
	private static ArrayList<Item> itemList = new ArrayList<Item>();
	private static ArrayList<ItemDisplay> itemDisplayList = new ArrayList<ItemDisplay>();
	private static ArrayList<ItemCartDisplay> itemCartDisplayList = new ArrayList<ItemCartDisplay>();
	private static User user;
	
	private SessionController() {
		
	}
	
	private SessionController(User userU) {
		user = userU;
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

	public static void loadCart() {
		Connect sessionConnect = Connect.getConnection();
		user.getCart().clear();
		itemCartDisplayList.clear();
		PreparedStatement cartPS = sessionConnect.prepare("select * from cart where UserID=?");
		Item itemTMP;
		ItemQuantity itemQuantityTMP;
		
		try {
			cartPS.setInt(1, user.getUserID());
			ResultSet cartRS = cartPS.executeQuery();
			while (cartRS.next()) {
				int currentItemID = cartRS.getInt("ItemID");
				itemTMP = itemList.stream().filter(item -> item.getItemID() == currentItemID).findAny().orElse(null);
				itemQuantityTMP = new ItemQuantity(itemTMP, cartRS.getInt("CartQuantity"));
				user.getCart().add(itemQuantityTMP);
				itemCartDisplayList.add(new ItemCartDisplay(itemQuantityTMP));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public User getUser() {
		return user;
	}
	
	public ArrayList<ItemCartDisplay> getItemCartDisplayList() {
		return itemCartDisplayList;
	}

	public ArrayList<Item> getItemList() {
		return itemList;
	}

	public ArrayList<ItemDisplay> getItemDisplayList() {
		return itemDisplayList;
	}
}
