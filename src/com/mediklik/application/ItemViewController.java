package com.mediklik.application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ItemViewController implements Initializable {
	@FXML
	private Label nameLabel;
	@FXML
	private Label priceLabel;
	@FXML
	private TextArea textArea;
	
	public ItemViewController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	public void handleAddToCart() {
		
	}
	
	public void handleKembali() {
		
	}
}
