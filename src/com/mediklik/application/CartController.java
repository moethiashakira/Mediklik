package com.mediklik.application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.mediklik.models.ItemCartDisplay;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CartController implements Initializable {
	@FXML
	private VBox cartVbox;
	@FXML
	private Label totalLabel;
	@FXML
	private Button kembaliButton;
	@FXML
	private Button checkoutButton;
	
	public CartController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		SessionController cartSession = SessionController.getSession();
		ArrayList<ItemCartDisplay> itemCartDisplayList = cartSession.getItemCartDisplayList();
		for (ItemCartDisplay i : itemCartDisplayList) {
			cartVbox.getChildren().add(i.getHbox());
		}
	}

	public void handleCheckout() {
		
	}
	
	public void handleKembali() {
		try {
			Parent itemBrowseSceneRoot = FXMLLoader.load(getClass().getResource("item_browse.fxml"));
			Stage stage = (Stage) kembaliButton.getScene().getWindow();
			Scene itemBrowseScene = new Scene(itemBrowseSceneRoot);
			stage.setScene(itemBrowseScene);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
