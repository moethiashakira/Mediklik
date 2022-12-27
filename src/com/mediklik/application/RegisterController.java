package com.mediklik.application;

import java.sql.PreparedStatement;

import com.mediklik.db.Connect;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController {
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
		PreparedStatement usernamePStatement = userConnect.prepare("insert into user values(?, ?, ?, ?)");
	}
	
	private boolean usernameValidation (String username) {
		//TODO
		return username.length() <= 64 && !username.contains(" ");
	}
	
	private boolean passwordValidation (String password) {
		//TODO
		return password.length() <= 64;
	}
}
