package com.mediklik.models;

import java.util.ArrayList;

public class ItemViewData {
	private static Item item;
	private static ArrayList<String> itemStoreList;
	private static ItemViewData itemViewData = null;
	
	private ItemViewData() {

	}
	
	private ItemViewData(Item newItem, ArrayList<String> newItemStoreList) {
		item = newItem;
		itemStoreList = newItemStoreList;
	}

	public static ItemViewData getItemViewData() {
		if (itemViewData == null) {
			itemViewData = new ItemViewData();
		}
		return itemViewData;
	}
	
	public static ItemViewData getItemViewData(Item newItem, ArrayList<String> newItemStoreList) {
		item = newItem;
		itemStoreList = newItemStoreList;
		return itemViewData;
	}
	
	public static void setItemViewData (Item newItem, ArrayList<String> newItemStoreList) {
		item = newItem;
		itemStoreList = newItemStoreList;
	}

	public Item getItem() {
		return item;
	}

	public ArrayList<String> getItemStoreList() {
		return itemStoreList;
	}
}
