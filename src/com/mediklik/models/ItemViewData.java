package com.mediklik.models;

import java.util.ArrayList;

public class ItemViewData {
	private static Item item;
	private static ArrayList<Store> itemStoreList;
	private static ItemViewData itemViewData = null;
	
	private ItemViewData(Item newItem, ArrayList<Store> newItemStoreList) {
		item = newItem;
		itemStoreList = newItemStoreList;
	}

	public static ItemViewData getItemViewData() {
		return itemViewData;
	}
	
	public static ItemViewData getItemViewData(Item newItem, ArrayList<Store> newItemStoreList) {
		item = newItem;
		itemStoreList = newItemStoreList;
		return itemViewData;
	}
	
	public static void setItemViewData (Item newItem, ArrayList<Store> newItemStoreList) {
		item = newItem;
		itemStoreList = newItemStoreList;
	}

	public Item getItem() {
		return item;
	}

	public ArrayList<Store> getItemStoreList() {
		return itemStoreList;
	}
}
