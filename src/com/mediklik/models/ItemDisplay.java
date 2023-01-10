package com.mediklik.models;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ItemDisplay {
	private VBox vbox;
	private Image image;
	private ImageView imageView;
	private Label nameLabel;
	private Label priceLabel;
	
	public ItemDisplay(Item item) {
		vbox = new VBox();
		image = new Image(item.getItemImage());
		imageView = new ImageView(image);
		imageView.setSmooth(true);
		imageView.setPreserveRatio(true);
		imageView.setFitWidth(150);
		nameLabel = new Label(item.getItemName());
		priceLabel = new Label("Rp" + item.getItemPrice());
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().add(imageView);
		vbox.getChildren().add(priceLabel);
		vbox.getChildren().add(nameLabel);
		VBox.setMargin(nameLabel, new Insets(5, 0, 0, 0));
		VBox.setMargin(priceLabel, new Insets(5, 0, 0, 0));
	}

	public VBox getVbox() {
		return vbox;
	}

	public void setVbox(VBox vbox) {
		this.vbox = vbox;
	}

	public ImageView getImageView() {
		return imageView;
	}

	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}

	public Label getNameLabel() {
		return nameLabel;
	}

	public void setNameLabel(Label nameLabel) {
		this.nameLabel = nameLabel;
	}

	public Label getPriceLabel() {
		return priceLabel;
	}

	public void setPriceLabel(Label priceLabel) {
		this.priceLabel = priceLabel;
	}
}
