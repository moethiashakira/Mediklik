package com.mediklik.models;

import java.util.ArrayList;

public class Store {
	private int storeID;
	private String storeName;
	private double storeRating;
	private ArrayList<ItemQuantity> inventory = new ArrayList<ItemQuantity>();
	
	public Store() {

	}

	public Store(int storeID, String storeName, double storeRating, ArrayList<ItemQuantity> inventory) {
		super();
		this.storeID = storeID;
		this.storeName = storeName;
		this.storeRating = storeRating;
		this.inventory = inventory;
	}

	public int getStoreID() {
		return storeID;
	}

	public void setStoreID(int storeID) {
		this.storeID = storeID;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public double getStoreRating() {
		return storeRating;
	}

	public void setStoreRating(double storeRating) {
		this.storeRating = storeRating;
	}

	public ArrayList<ItemQuantity> getInventory() {
		return inventory;
	}

	public void setInventory(ArrayList<ItemQuantity> inventory) {
		this.inventory = inventory;
	}
}
