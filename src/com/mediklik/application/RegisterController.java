package com.mediklik.application;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mediklik.db.Connect;
import com.mediklik.models.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController implements Initializable  {
	@FXML
	private Button registerButton;
	@FXML
	private TextField usernameTField;
	@FXML
	private PasswordField passwordPField;

	public void handleRegister() {
		String userName = usernameTField.getText();
		String password = passwordPField.getText();
		Alert registerAlert = new Alert(Alert.AlertType.ERROR);
		if (!usernameValidation(userName)) {
			registerAlert.setHeaderText("Invalid username!");
			registerAlert.showAndWait();
			return;
		}
		if (!passwordValidation(password)) {
			registerAlert.setHeaderText("Invalid password!");
			registerAlert.showAndWait();
			return;
		}
		
		Connect userConnect = Connect.getConnection();
		PreparedStatement usernamePStatement = userConnect.prepare("insert into user values(?, ?, ?, ?, ?)");
		try {
			ResultSet currentUserIDRS = userConnect.query("select UserID from CurrentID");
			currentUserIDRS.next();
			int currentUserID = currentUserIDRS.getInt("UserID");
			usernamePStatement.setInt(1, ++currentUserID);
			usernamePStatement.setString(2, userName);
			usernamePStatement.setString(3, password);
			usernamePStatement.setBoolean(4, false);
			usernamePStatement.setInt(5, 0);
			usernamePStatement.executeUpdate();
			SessionController.getSession(new User(currentUserID, userName, password, false, 0));
			registerAlert.setAlertType(Alert.AlertType.CONFIRMATION);
			registerAlert.setHeaderText("Success!");
			registerAlert.showAndWait();
			
			try {
				Parent itemBrowseSceneRoot = FXMLLoader.load(getClass().getResource("item_browse.fxml"));
				Stage stage = (Stage) registerButton.getScene().getWindow();
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
	
	private boolean usernameValidation (String username) {
		//TODO
		return username.length() <= 32 && !username.contains(" ");
	}
	
	private boolean passwordValidation (String password) {
		//TODO
		return password.length() <= 64;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
