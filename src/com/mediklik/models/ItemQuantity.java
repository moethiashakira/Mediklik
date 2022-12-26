package com.mediklik.models;

public class ItemQuantity {
	private Item item;
	private int quantity;
	
	public ItemQuantity() {
		
	}

	public ItemQuantity(Item item, int quantity) {
		super();
		this.item = item;
		this.quantity = quantity;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
