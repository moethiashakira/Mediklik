package com.mediklik.models;

import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ItemCartDisplay {
	private HBox hbox;
	private VBox vbox;
	private Image image;
	private ImageView imageView;
	private Label nameLabel;
	private Label priceLabel;
	private Spinner<Integer> spinner;
	private SpinnerValueFactory<Integer> valueFactory;
	
	public ItemCartDisplay(ItemQuantity itemQuantity) {
		Item item = itemQuantity.getItem();
		int quantity = itemQuantity.getQuantity();
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
	}

	public HBox getHbox() {
		return hbox;
	}
}
