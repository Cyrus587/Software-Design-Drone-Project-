package main.dashboard.view;

import java.awt.Button;
import java.awt.Label;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import main.dashboard.model.ItemContainer;
import main.dashboard.model.Items;

public class AddItemContainerController {
	@FXML
	private TextField nameTF;
	@FXML
	private TextField priceTF;
	@FXML
	private TextField xTF;
	@FXML
	private TextField yTF;
	@FXML
	private TextField lengthTF;
	@FXML
	private TextField widthTF;
	@FXML
	private TextField heightTF;




	private Stage dialogStage;
	private ItemContainer item;
	public boolean okClicked = false;
	private DashboardController Dashboard;

	@FXML
	private void initialize() {
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setItemContainer(ItemContainer item) {
		this.item = item;

		nameTF.setText(item.getName());
		priceTF.setText(Double.toString(item.getPrice()));
		xTF.setText(Double.toString(item.getLocationX()));
		yTF.setText(Double.toString(item.getLocationY()));
		lengthTF.setText(Integer.toString(item.getLength()));
		widthTF.setText(Integer.toString(item.getWidth()));
		heightTF.setText(Integer.toString(item.getHeight()));
	}

	private boolean isInputValid() {
		String errorMessage = "";

		if (nameTF.getText() == null || nameTF.getText().length() == 0) {
			errorMessage += "No valid name!\n";
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message.
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);

			alert.showAndWait();

			return false;
		}
	}

	@FXML
	private void handleSave() {
		if (isInputValid()) {
			item.setName(nameTF.getText());
			item.setPrice(Double.parseDouble(priceTF.getText()));
			item.setLocationX(Double.parseDouble(xTF.getText()));
			item.setLocationY(Double.parseDouble(yTF.getText()));
			item.setWidth(Integer.parseInt(widthTF.getText()));
			item.setHeight(Integer.parseInt(heightTF.getText()));
			item.setLength(Integer.parseInt(lengthTF.getText()));

			okClicked = true;
			Dashboard.addItemContainer(item);
			dialogStage.close();

		}



	}
	public boolean okClicked(){
		return this.okClicked;
	}


	@FXML
	private void handleCancel() {
		dialogStage.close();
	}
	public void setDashboardController(DashboardController Dashboard){
		this.Dashboard = Dashboard;
	}

}
