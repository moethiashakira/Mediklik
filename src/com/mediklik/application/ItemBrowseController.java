package com.mediklik.application;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mediklik.db.Connect;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ItemBrowseController implements Initializable {
	@FXML
	private Label saldoLabel;

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
		
		ResultSet itemPageCountRS = itemBrowseConnect.query("select count(itemid)");
		ResultSet itemPageRS = itemBrowseConnect.query("select * from item limit 9");
		try {
			itemPageCountRS.next();
			int itemPageCount = itemPageCountRS.getInt(0);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
