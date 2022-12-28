package com.mediklik.models;

public class Item {
	private int ItemID;
	private String ItemName;
	private int ItemPrice;
	private String CategoryID;
	private double ItemRating;
	
	public Item() {
		
	}
	
	public Item(int itemID, String itemName, int itemPrice, String categoryID, double itemRating) {
		super();
		ItemID = itemID;
		ItemName = itemName;
		ItemPrice = itemPrice;
		CategoryID = categoryID;
		ItemRating = itemRating;
	}
	
	public int getItemID() {
		return ItemID;
	}
	
	public void setItemID(int itemID) {
		ItemID = itemID;
	}
	
	public String getItemName() {
		return ItemName;
	}
	
	public void setItemName(String itemName) {
		ItemName = itemName;
	}
	
	public int getItemPrice() {
		return ItemPrice;
	}
	
	public void setItemPrice(int itemPrice) {
		ItemPrice = itemPrice;
	}
	
	public String getCategoryID() {
		return CategoryID;
	}
	
	public void setCategoryID(String categoryID) {
		CategoryID = categoryID;
	}

	public double getItemRating() {
		return ItemRating;
	}
	
	public void setItemRating(double itemRating) {
		ItemRating = itemRating;
	}
}
