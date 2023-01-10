package com.mediklik.models;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ItemCartDisplay {
	private Item item;
	private int quantity;
	private HBox hbox;
	private VBox vbox;
	private Image image;
	private ImageView imageView;
	private Label nameLabel;
	private Label priceLabel;
	private Spinner<Integer> spinner;
	private SpinnerValueFactory<Integer> valueFactory;
	
	public ItemCartDisplay(ItemQuantity itemQuantity) {
		item = itemQuantity.getItem();
		quantity = itemQuantity.getQuantity();
		hbox = new HBox();
		vbox = new VBox();
		image = new Image(item.getItemImage());
		imageView = new ImageView(image);
		imageView.setPreserveRatio(true);
		imageView.setFitHeight(100);
		valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100);
		valueFactory.setValue(quantity);
		spinner = new Spinner<Integer>(valueFactory);
		nameLabel = new Label(item.getItemName());
		priceLabel = new Label(Integer.toString(item.getItemPrice()));
		
		hbox.getChildren().add(imageView);
		hbox.getChildren().add(vbox);
		vbox.getChildren().add(nameLabel);
		vbox.getChildren().add(priceLabel);
		vbox.getChildren().add(spinner);
		
		HBox.setMargin(imageView, new Insets(0, 10, 0, 0));
		VBox.setMargin(priceLabel, new Insets(10, 0, 0, 0));
		VBox.setMargin(spinner, new Insets(10, 0, 0, 0));
	}

	public HBox getHbox() {
		return hbox;
	}

	public Item getItem() {
		return item;
	}
	
	public int getQuantity() {
		return quantity;
	}
}
