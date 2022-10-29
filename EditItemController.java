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

public class EditItemController {
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
	private Button saveButton;
	@FXML
	private Button cancelButton;

	private MainApp mainApp;
	private DashboardController dashBoard;
	private Stage itemStage;
	private Component item;
	private TreeItem<Component> TreeItem;
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

	public void setItem(TreeItem<Component> item) {
		this.item = item.getValue();
		this.TreeItem = item;


		addNameField.setText(this.item.getName());
		addPriceField.setText(Double.toString(this.item.getPrice()));
		addLocationXField.setText(Double.toString(this.item.getLocationX()));
		addLocationYField.setText(Double.toString(this.item.getLocationY()));
		addLengthField.setText(Integer.toString(this.item.getLength()));
		addWidthField.setText(Integer.toString(this.item.getWidth()));
		addHeightField.setText(Integer.toString(this.item.getHeight()));
	}

	public boolean isSaveClicked() {
		return saveClicked;
	}

	@FXML
	private void handleSave() {

		item.setName(addNameField.getText());
		item.setPrice(Double.parseDouble(addPriceField.getText()));
		item.setLocationX(Double.parseDouble(addLocationXField.getText()));
		item.setLocationY(Double.parseDouble(addLocationYField.getText()));
		item.setLength(Integer.parseInt(addLengthField.getText()));
		item.setWidth(Integer.parseInt(addWidthField.getText()));
		item.setHeight(Integer.parseInt(addHeightField.getText()));

		saveClicked = true;
		TreeItem = new TreeItem<Component>(item);
		dashBoard.addComponent(item);
		itemStage.close();
	}

	@FXML
	private void handleCancel() {
		itemStage.close();
	}



}


