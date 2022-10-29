package main.dashboard.view;

import main.dashboard.MainApp;
import main.dashboard.model.*;

import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import main.dashboard.*;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ItemController {
	@FXML
	private TextField addNameField;
	@FXML
	private TextField addPriceField;
	@FXML
	private TextField addLocationXField;
	@FXML
	private TextField addLocationYField;
	@FXML
	private TextField addLengthField;
	@FXML
	private TextField addWidthField;
	@FXML
	private TextField addHeightField;
	@FXML
	private TextField addMarketValueField;
	@FXML
	private Button saveButton;
	@FXML
	private Button cancelButton;

	private MainApp mainApp;
	private DashboardController dashBoard;
	private Stage itemStage;
	private Items item;
	private boolean saveClicked = false;


	@FXML
	private void initialize() {
	}

	public void setMainApp(MainApp mainApp) {
	       this.mainApp = mainApp;
	}

	public void setDashboardController(DashboardController dashBoard) {
		this.dashBoard = dashBoard;
	}

	public void setItemStage(Stage itemStage) {
		this.itemStage = itemStage;
	}

	public void setItem(Items item) {
		this.item = item;

		addNameField.setText(item.getName());
		addPriceField.setText(Double.toString(item.getPrice()));
		addLocationXField.setText(Double.toString(item.getLocationX()));
		addLocationYField.setText(Double.toString(item.getLocationY()));
		addLengthField.setText(Integer.toString(item.getLength()));
		addWidthField.setText(Integer.toString(item.getWidth()));
		addHeightField.setText(Integer.toString(item.getHeight()));
		addMarketValueField.setText(Double.toString(item.getMarketValue()));
	}

	public boolean isSaveClicked() {
		return saveClicked;
	}

	@FXML
	private void handleSave() {
		Items newItem = new Items();
		newItem.setName(addNameField.getText());
		newItem.setPrice(Double.parseDouble(addPriceField.getText()));
		newItem.setLocationX(Double.parseDouble(addLocationXField.getText()));
		newItem.setLocationY(Double.parseDouble(addLocationYField.getText()));
		newItem.setLength(Integer.parseInt(addLengthField.getText()));
		newItem.setWidth(Integer.parseInt(addWidthField.getText()));
		newItem.setHeight(Integer.parseInt(addHeightField.getText()));
		newItem.setMarketValue(Double.parseDouble(addMarketValueField.getText()));

		saveClicked = true;
		dashBoard.addItem(newItem);
		itemStage.close();
	}

	@FXML
	private void handleCancel() {
		itemStage.close();
	}



}


