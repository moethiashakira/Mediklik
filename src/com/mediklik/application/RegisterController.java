package com.mediklik.application;

import java.sql.PreparedStatement;

import com.mediklik.db.Connect;

import javafx.fxml.FXML;
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
		Connect userConnect = Connect.getConnection();
		PreparedStatement usernamePStatement = userConnect.prepare("insert into user values(?, ?, ?, ?)");
	}
}
