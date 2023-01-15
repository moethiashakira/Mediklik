package com.mediklik.models;

public class ItemBrowseData {
	private static int page = 0;
	private static int maxPage;
	
	public static int getPage() {
		return page;
	}

	public static void setPage(int newPage) {
		page = newPage;
	}
	
	public static void incPage() {
		page++;
	}
	
	public static void decPage(){
		page--;
	}
	
	public static int getMax() {
		return maxPage;
	}
	
	public static void setMax(int newMaxPage) {
		maxPage = newMaxPage;
	}
}
