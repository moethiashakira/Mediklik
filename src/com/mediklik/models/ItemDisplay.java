package com.mediklik.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mediklik.application.SessionController;
import com.mediklik.db.Connect;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ItemDisplay {
	private VBox vbox;
	private Image image;
	private ImageView imageView;
	private Label nameLabel;
	private Label priceLabel;
	private Item item;
	
	public ItemDisplay(Item item) {
		vbox = new VBox();
		this.item = item;
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
		
		imageView.addEventHandler(MouseEvent.MOUSE_CLICKED,  new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				handleClick();
			}
		});
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
	
	public Image getImage() {
		return image;
	}

	public Item getItem() {
		return item;
	}

	public void handleClick() {
		Connect itemDisplayConnect = Connect.getConnection();
		ArrayList<String> storeList = new ArrayList<String>();
		PreparedStatement itemDisplayPS = itemDisplayConnect.prepare("select Store.StoreName from Inventory inner join Store on Inventory.StoreID=Store.StoreID where Inventory.ItemID=?");
		try {
			itemDisplayPS.setInt(1,this.getItem().getItemID());
			ResultSet itemDisplayRS = itemDisplayPS.executeQuery();
			while (itemDisplayRS.next()) {
				storeList.add(itemDisplayRS.getString(1));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		ItemViewData.getItemViewData(this.getItem(), storeList);
		
		try {
			Parent itemViewSceneRoot = FXMLLoader.load(getClass().getResource("../application/item_view.fxml"));
			Stage stage = (Stage) nameLabel.getScene().getWindow();
			Scene itemViewScene = new Scene(itemViewSceneRoot);
			stage.setScene(itemViewScene);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
