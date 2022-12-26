package com.mediklik.application;

import java.sql.*;

import com.mediklik.db.Connect;
import com.mediklik.models.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StartController {
	@FXML
	private Button loginButton;
	@FXML
	private TextField usernameTField;
	@FXML
	private PasswordField passwordPField;
	@FXML
	private Hyperlink registerHyperlink;
	
	public void handleLogin() {
		Connect userConnect = Connect.getConnection();
		PreparedStatement usernamePStatement = userConnect.prepare("select * from User where UserName=?");
		Alert loginAlert = new Alert(Alert.AlertType.ERROR);
		try {
			usernamePStatement.setString(1, usernameTField.getText());
			ResultSet usernameRSet = usernamePStatement.executeQuery();
			if (!usernameRSet.next()) {
				loginAlert.setHeaderText("Username Not Found!");
				loginAlert.showAndWait();
				return;
			}
			
			if (!usernameRSet.getString("Password").equals(passwordPField.getText())) {
				loginAlert.setHeaderText("Incorrect Password!");
				loginAlert.showAndWait();
				return;
			}
			
			SessionController.getSession(new User(usernameRSet));
			try {
				Parent itemBrowseSceneRoot = FXMLLoader.load(getClass().getResource("item_browse.fxml"));
				Stage stage = (Stage) loginButton.getScene().getWindow();
				Scene itemBrowseScene = new Scene(itemBrowseSceneRoot);
				stage.setScene(itemBrowseScene);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void handleLinkToRegister() {
		try {
			Parent registerSceneRoot = FXMLLoader.load(getClass().getResource("register.fxml"));
			Stage stage = (Stage) loginButton.getScene().getWindow();
			Scene registerScene = new Scene(registerSceneRoot);
			stage.setScene(registerScene);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
