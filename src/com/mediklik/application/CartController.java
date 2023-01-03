package com.mediklik.application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.mediklik.models.ItemCartDisplay;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CartController implements Initializable {
	@FXML
	private VBox cartVbox;
	@FXML
	private Label totalLabel;
	
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

}
