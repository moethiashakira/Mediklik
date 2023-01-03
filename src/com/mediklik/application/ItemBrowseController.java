package com.mediklik.application;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.mediklik.db.Connect;
import com.mediklik.models.Item;
import com.mediklik.models.ItemDisplay;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ItemBrowseController implements Initializable {
	@FXML
	private Label saldoLabel;
	@FXML
	private GridPane itemGridPane;
	@FXML
	private Button cartButton;
	@FXML
	private Button topUpButton;
	
	private Image cartButtonImage;
	private Image topUpButtonImage;
	private ImageView cartButtonIV;
	private ImageView topUpButtonIV;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		SessionController itemBrowseSession = SessionController.getSession();
		Connect itemBrowseConnect = Connect.getConnection();
		int saldo = itemBrowseSession.getUser().getBalance();
		cartButtonImage = new Image("/media/basket.png");
		topUpButtonImage = new Image("/media/money.png");
		cartButtonIV = new ImageView(cartButtonImage);
		topUpButtonIV = new ImageView(topUpButtonImage);
		cartButtonIV.setFitWidth(50);
		topUpButtonIV.setFitWidth(50);
		cartButton.setGraphic(cartButtonIV);
		topUpButton.setGraphic(topUpButtonIV);
		
		if (saldo <= 0) {
			saldoLabel.setText("Kosong");
		}
		else {
			saldoLabel.setText("Rp" + Integer.toString(saldo));
		}
		
		
		ResultSet itemPageCountRS = itemBrowseConnect.query("select count(ItemID) from Item");
		ResultSet itemPageRS = itemBrowseConnect.query("select * from item limit 9");
		int itemPageCount = 0;
		try {
			itemPageCountRS.next();
			itemPageCount = itemPageCountRS.getInt(1);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (itemPageCount > 9)
			itemPageCount = 9;
		
		ItemDisplay itemDisplayTMP;
		
		for (int i = 0; i < itemPageCount; i++) {
			itemDisplayTMP = itemBrowseSession.getItemDisplayList().get(i);
			itemGridPane.add(itemDisplayTMP.getVbox(), i/3, i%3);
		}
	}
	
	public void handleSaldo() {
		try {
			Parent registerSceneRoot = FXMLLoader.load(getClass().getResource("saldo.fxml"));
			Stage stage = (Stage) itemGridPane.getScene().getWindow();
			Scene registerScene = new Scene(registerSceneRoot);
			stage.setScene(registerScene);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void handleCart() {
		try {
			Parent registerSceneRoot = FXMLLoader.load(getClass().getResource("cart.fxml"));
			Stage stage = (Stage) itemGridPane.getScene().getWindow();
			Scene registerScene = new Scene(registerSceneRoot);
			stage.setScene(registerScene);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
