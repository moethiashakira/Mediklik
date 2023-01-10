package com.mediklik.application;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mediklik.db.Connect;
import com.mediklik.models.Item;
import com.mediklik.models.ItemQuantity;
import com.mediklik.models.Store;

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
		Connect exitConnect = Connect.getConnection();
		SessionController exitSession = SessionController.getSession();
		if (exitSession.getUser() == null) {
			return;
		}		
		PreparedStatement userPS = exitConnect.prepare("update User set Balance=? where UserID=?");
		try {
			userPS.setInt(1, exitSession.getUser().getBalance());
			userPS.setInt(2, exitSession.getUser().getUserID());
			userPS.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		PreparedStatement inventoryPS = exitConnect.prepare("update Inventory set InventoryQuantity=? where StoreID=? and ItemID=?");
		ArrayList<Store> storeList = exitSession.getStoreList();
		for (Store s : storeList) {
			try {
				inventoryPS.setInt(2, s.getStoreID());
				ArrayList<ItemQuantity> storeInventory = s.getInventory();
				for (ItemQuantity i : storeInventory) {
					inventoryPS.setInt(1, i.getQuantity());
					inventoryPS.setInt(3, i.getItem().getItemID()); 
					inventoryPS.executeUpdate();
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		SessionController.loadItemList();
		SessionController.loadStoreList();
		launch(args);
	}
}
