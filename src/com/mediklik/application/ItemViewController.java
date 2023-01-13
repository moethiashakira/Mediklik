package com.mediklik.application;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.mediklik.db.Connect;
import com.mediklik.models.Item;
import com.mediklik.models.ItemStoreQuantity;
import com.mediklik.models.ItemViewData;
import com.mediklik.models.Store;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ItemViewController implements Initializable {
	@FXML
	private Label nameLabel;
	@FXML
	private Label priceLabel;
	@FXML
	private TextArea textArea;
	@FXML
	private ImageView imageView;
	@FXML
	private Spinner<Integer> quantitySpinner;
	@FXML
	private ComboBox<String> storeComboBox;
	private Item item;
	
	public ItemViewController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ItemViewData itemViewData = ItemViewData.getItemViewData();
		item = itemViewData.getItem();
		Image image = new Image(item.getItemImage());
		imageView.setImage(image);
		nameLabel.setText(item.getItemName());
		priceLabel.setText("Rp" + Integer.toString(item.getItemPrice()));
		textArea.setText(item.getItemDesc());
		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
		valueFactory.setValue(1);
		quantitySpinner.setValueFactory(valueFactory);
		
		Connect itemViewConnect = Connect.getConnection();
		PreparedStatement itemViewPS = itemViewConnect.prepare("select Store.StoreName from Inventory join Store on Inventory.StoreID=Store.StoreID where Inventory.ItemID=?");
		try {
			itemViewPS.setInt(1, item.getItemID());
			ResultSet itemViewRS = itemViewPS.executeQuery();
			while (itemViewRS.next()) {
				storeComboBox.getItems().add(itemViewRS.getString(1));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void handleAddToCart() {
		SessionController itemViewSession = SessionController.getSession();
		String currentStore = storeComboBox.getValue();
		ArrayList<ItemStoreQuantity> userCart = itemViewSession.getUser().getCart();
		ItemStoreQuantity userCartItem;
		Store itemStore = itemViewSession.getStoreList().stream().filter(store -> store.getStoreName() == currentStore).findAny().orElse(null);
		int quantity = quantitySpinner.getValue();
		
		if ((userCartItem = userCart.stream().filter(itemStoreQuantity -> itemStoreQuantity.getItem() == item).findAny().orElse(null)) == null) {
			userCart.add(new ItemStoreQuantity(item, quantity, itemStore));
		}
		else {
			userCartItem.setQuantity(quantity + userCartItem.getQuantity());
		}
		
		
		Alert itemViewAlert = new Alert(Alert.AlertType.CONFIRMATION);
		itemViewAlert.setHeaderText("Success");
		itemViewAlert.setContentText("Items added to cart");
		itemViewAlert.showAndWait();
	}
	
	public void handleKembali() {
		try {
			Parent itemBrowseSceneRoot = FXMLLoader.load(getClass().getResource("item_browse.fxml"));
			Stage stage = (Stage) imageView.getScene().getWindow();
			Scene itemBrowseScene = new Scene(itemBrowseSceneRoot);
			stage.setScene(itemBrowseScene);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
