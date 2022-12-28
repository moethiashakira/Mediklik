package com.mediklik.models;

import java.util.ArrayList;

public class Transaction {
	private int transactionID;
	private User user;
	private ArrayList<ItemQuantity> transactionItems = new ArrayList<ItemQuantity>();
	
	public Transaction() {
		
	}

	public Transaction(int transactionID, User user, ArrayList<ItemQuantity> transactionItems) {
		super();
		this.transactionID = transactionID;
		this.user = user;
		this.transactionItems = transactionItems;
	}

	public int getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ArrayList<ItemQuantity> getTransactionItems() {
		return transactionItems;
	}

	public void setTransactionItems(ArrayList<ItemQuantity> transactionItems) {
		this.transactionItems = transactionItems;
	}
}
