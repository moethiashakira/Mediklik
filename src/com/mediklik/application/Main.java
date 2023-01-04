package com.mediklik.application;

import com.mediklik.db.Connect;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage stage) throws Exception {
		Parent startSceneRoot = FXMLLoader.load(getClass().getResource("start.fxml"));
		Scene startScene = new Scene(startSceneRoot);
		
		stage.setScene(startScene);
		stage.setTitle("Mediklik v1.0");
		stage.setResizable(false);
		stage.show();
	}

	@Override
	public void stop() {
		//Connect exitConnect = Connect.getConnection();
		//exitConnect.update("");
	}
	
	public static void main(String[] args) {
		SessionController.loadItemList();
		launch(args);
	}
}
