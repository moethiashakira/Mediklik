package com.mediklik.application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mediklik.db.Connect;
import com.mediklik.models.Item;
import com.mediklik.models.ItemBrowseData;
import com.mediklik.models.ItemCartDisplay;
import com.mediklik.models.ItemDisplay;
import com.mediklik.models.ItemQuantity;
import com.mediklik.models.ItemStoreQuantity;
import com.mediklik.models.Store;
import com.mediklik.models.User;

public class SessionController {
	private static SessionController session = null;
	private static ArrayList<Item> itemList = new ArrayList<Item>();
	private static ArrayList<ItemDisplay> itemDisplayList = new ArrayList<ItemDisplay>();
	private static ArrayList<ItemCartDisplay> itemCartDisplayList = new ArrayList<ItemCartDisplay>();
	private static ArrayList<Store> storeList = new ArrayList<Store>();
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
		ResultSet itemRS = sessionConnect.query("select * from Item");
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
		
		int itemListSize = itemList.size();
		ItemBrowseData.setMax((itemListSize / 9) + ((itemListSize % 9 > 0) ? 1 : 0) - 1);
	}

	public static void loadStoreList() {
		Connect sessionConnect = Connect.getConnection();
		storeList.clear();
		ResultSet itemRS = sessionConnect.query("select * from Store");
		Store storeTMP;
		Item itemTMP;
		try {
			while (itemRS.next()) {
				storeTMP = new Store(itemRS);
				storeList.add(storeTMP);
				
				PreparedStatement inventoryPS = sessionConnect.prepare("select * from Inventory where StoreID=?");
				inventoryPS.setInt(1, storeTMP.getStoreID());
				ResultSet inventoryRS = inventoryPS.executeQuery();
				
				while (inventoryRS.next()) {
					int currentItemID = inventoryRS.getInt("ItemID");
					itemTMP = itemList.stream().filter(item -> item.getItemID() == currentItemID).findAny().orElse(null);
					storeTMP.getInventory().add(new ItemQuantity(itemTMP, inventoryRS.getInt("InventoryQuantity")));
				}
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
		Store storeTMP;
		ItemStoreQuantity itemStoreQuantityTMP;
		
		try {
			cartPS.setInt(1, user.getUserID());
			ResultSet cartRS = cartPS.executeQuery();
			while (cartRS.next()) {
				int currentItemID = cartRS.getInt("ItemID");
				int currentStoreID = cartRS.getInt("StoreID");
				itemTMP = itemList.stream().filter(item -> item.getItemID() == currentItemID).findAny().orElse(null);
				storeTMP = storeList.stream().filter(store -> store.getStoreID() == currentStoreID).findAny().orElse(null);
				itemStoreQuantityTMP = new ItemStoreQuantity(itemTMP, cartRS.getInt("CartQuantity"), storeTMP);
				user.getCart().add(itemStoreQuantityTMP);
				itemCartDisplayList.add(new ItemCartDisplay(itemStoreQuantityTMP));
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
	
	public ArrayList<Store> getStoreList() {
		return storeList;
	}
}
