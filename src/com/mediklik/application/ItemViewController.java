package com.mediklik.application;

import java.net.URL;
import java.util.ResourceBundle;

import com.mediklik.models.Item;
import com.mediklik.models.ItemViewData;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

public class ItemViewController implements Initializable {
	@FXML
	private Label nameLabel;
	@FXML
	private Label priceLabel;
	@FXML
	private TextArea textArea;
	@FXML
	private ImageView imageView;
	
	public ItemViewController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ItemViewData itemViewData = ItemViewData.getItemViewData();
		Item item = itemViewData.getItem();
		nameLabel.setText(item.getItemName());
		priceLabel.setText("Rp" + Integer.toString(item.getItemPrice()));
		textArea.setText(item.getItemDesc());
	}

	public void handleAddToCart() {
		
	}
	
	public void handleKembali() {
		
	}
}
