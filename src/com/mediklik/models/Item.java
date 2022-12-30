package com.mediklik.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Item {
	private int itemID;
	private String itemName;
	private int itemPrice;
	private int categoryID;
	private double itemRating;
	private String itemImage;
		
	public Item(int itemID, String itemName, int itemPrice, int categoryID, double itemRating, String itemImage) {
		this.itemID = itemID;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.categoryID = categoryID;
		this.itemRating = itemRating;
		this.itemImage = "file:/media/" + itemImage;
	}
	
	public Item(ResultSet itemRow) {
		try {
			this.itemID = itemRow.getInt("ItemID");
			this.itemName = itemRow.getString("ItemName");
			this.itemPrice = itemRow.getInt("ItemPrice");
			this.categoryID = itemRow.getInt("CategoryID");
			this.itemRating = itemRow.getDouble("ItemRating");
			this.itemImage = "file:/media/" + itemRow.getString("ItemImage");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getItemID() {
		return itemID;
	}
	
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	
	public String getItemName() {
		return itemName;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public int getItemPrice() {
		return itemPrice;
	}
	
	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}
	
	public int getCategoryID() {
		return categoryID;
	}
	
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public double getItemRating() {
		return itemRating;
	}
	
	public void setItemRating(double itemRating) {
		this.itemRating = itemRating;
	}

	public String getItemImage() {
		return itemImage;
	}

	public void setItemImage(String itemImage) {
		this.itemImage = itemImage;
	}
}
