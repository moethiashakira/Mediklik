package com.mediklik.application;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.mediklik.db.Connect;
import com.mediklik.models.ItemCartDisplay;
import com.mediklik.models.ItemQuantity;
import com.mediklik.models.ItemStoreQuantity;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CheckoutController implements Initializable {
	@FXML
	private Label totalLabel;
	@FXML
	private Label saldoLabel;
	@FXML
	private Button kembaliButton;
	@FXML
	private Button konfirmasiButton;
	private int total = 0;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		SessionController checkoutSession = SessionController.getSession();
		saldoLabel.setText("Rp" + Integer.toString(checkoutSession.getUser().getBalance()));
		ArrayList<ItemStoreQuantity> cart = checkoutSession.getUser().getCart();
		for (ItemQuantity i : cart) {
			total += i.getQuantity() * i.getItem().getItemPrice();
		}
		totalLabel.setText("Rp" + Integer.toString(total));
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
	
	public void handleCheckout() {
		SessionController checkoutSession = SessionController.getSession();
		Connect checkoutConnect = Connect.getConnection();
		Alert checkoutAlert = new Alert(Alert.AlertType.ERROR);
		int userBalance = checkoutSession.getUser().getBalance();
		if (total > userBalance) {
			checkoutAlert.setHeaderText("Error");
			checkoutAlert.setContentText("Not enough money!");
			checkoutAlert.showAndWait();
			return;
		}
		
		checkoutSession.getUser().setBalance(userBalance-total);
		ArrayList<ItemStoreQuantity> cart = checkoutSession.getUser().getCart();
		ArrayList<ItemCartDisplay> cartDisplay = checkoutSession.getItemCartDisplayList();
		ItemQuantity itemQuantityTMP;
		
		for (ItemStoreQuantity i : cart) {
			int currentItemID = i.getItem().getItemID();
			itemQuantityTMP = i.getStore().getInventory().stream().filter(itemQuantity -> itemQuantity.getItem().getItemID() == currentItemID).findAny().orElse(null);
			itemQuantityTMP.setQuantity(itemQuantityTMP.getQuantity() - i.getQuantity());
		}
		
		cart.clear();
		cartDisplay.clear();
		
		try {
			PreparedStatement cartUpdate = checkoutConnect.prepare("delete from Cart where UserID=?");
			cartUpdate.setInt(1, checkoutSession.getUser().getUserID());
			cartUpdate.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		checkoutAlert.setAlertType(Alert.AlertType.CONFIRMATION);
		checkoutAlert.setHeaderText("Success");
		checkoutAlert.setContentText("Transaction completed successfully");
		checkoutAlert.showAndWait();
		
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
