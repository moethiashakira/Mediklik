package com.mediklik.application;

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
		System.out.println("test");
	}
}
