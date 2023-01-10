package com.mediklik.models;

public class ItemStoreQuantity extends ItemQuantity {
	private Store store;

	public ItemStoreQuantity(Item item, int quantity, Store store) {
		super(item, quantity);
		this.store = store;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}
}
