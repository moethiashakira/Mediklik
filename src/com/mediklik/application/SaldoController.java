package com.mediklik.application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

public class SaldoController implements Initializable {
	@FXML
	private Label saldoLabel;
	@FXML
	private Spinner<Integer> saldoSpinner;
	@FXML
	private Button saldoButton;
	@FXML
	private Button kembaliButton;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		SessionController saldoSession = SessionController.getSession();
		saldoLabel.setText(Integer.toString(saldoSession.getUser().getBalance()));
		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000000);
		valueFactory.setValue(50000);
		saldoSpinner.setValueFactory(valueFactory);
	}
	
	public void handleSaldo() {
		SessionController saldoSession = SessionController.getSession();
		int newBalance = saldoSession.getUser().getBalance() + saldoSpinner.getValue();
		saldoSession.getUser().setBalance(newBalance);
		saldoLabel.setText(Integer.toString(newBalance));
		Alert saldoAlert = new Alert(Alert.AlertType.INFORMATION);
		saldoAlert.setHeaderText("Success!");
		saldoAlert.setContentText(Integer.toString(saldoSpinner.getValue()) + " has been added to your account.");
		saldoAlert.showAndWait();
	}
	
	public void handleKembali() {
		try {
			Parent itemBrowseSceneRoot = FXMLLoader.load(getClass().getResource("item_browse.fxml"));
			Stage stage = (Stage) saldoButton.getScene().getWindow();
			Scene itemBrowseScene = new Scene(itemBrowseSceneRoot);
			stage.setScene(itemBrowseScene);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
