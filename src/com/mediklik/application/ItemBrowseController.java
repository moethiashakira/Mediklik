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
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class ItemBrowseController implements Initializable {
	@FXML
	private Label saldoLabel;
	@FXML
	private GridPane itemGridPane;
	
	private ArrayList<Item> itemList;
	private ArrayList<ItemDisplay> itemDisplayList;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		SessionController itemBrowseSession = SessionController.getSession();
		Connect itemBrowseConnect = Connect.getConnection();
		int saldo = itemBrowseSession.getUser().getBalance();
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
		
		itemList = new ArrayList<Item>();
		itemDisplayList = new ArrayList<ItemDisplay>();
		Item itemTMP;
		ItemDisplay itemDisplayTMP;
		
		for (int i = 0; i < itemPageCount; i++) {
			try {
				itemPageRS.next();
				itemTMP = new Item(itemPageRS);
				itemDisplayTMP = new ItemDisplay(itemTMP);
				itemList.add(itemTMP);
				itemDisplayList.add(itemDisplayTMP);
				itemGridPane.add(itemDisplayTMP.getVbox(), i/3, i%3);
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
