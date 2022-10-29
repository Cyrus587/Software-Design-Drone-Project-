package main.dashboard.view;

import main.dashboard.MainApp;
import main.dashboard.model.*;
import main.dashboard.model.TelloAdapter;
import main.java.surelyhuman.jdrone.control.physical.tello.TelloDrone;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;


public class DashboardController {
	@FXML
	private RadioButton visitItem;
	@FXML
	private Button startGraphicalDrone;
	@FXML
	private RadioButton scanFarm;
	@FXML
	private Label nameLabel;
	@FXML
	private Label purchasePrice;
	@FXML
	private Label marketValue;
	@FXML
	private Label priceLabel;
	@FXML
	private Label locationXLabel;
	@FXML
	private Label locationYLabel;
	@FXML
	private Label lengthLabel;
	@FXML
	private Label widthLabel;
	@FXML
	private Label heightLabel;
	@FXML
	public TreeItem<Component> drone;
	@FXML
	private Button launchDrone;
	@FXML
	public Button addItemButton;
	@FXML
	public Button addItemContainerButton;
	@FXML
	public Button removeItemButton;
	@FXML
	public Button startSimulatorButton;
	@FXML
	public Button editItemButton;
	@FXML
	TreeItem<Component> root;
	@FXML
    private TreeView<Component> componentTree;
	@FXML
	private AnchorPane farmArea;
	@FXML
	private TreeItem<Component> droneController;
	@FXML
	private Image droneImage = new Image("file:resources/drone.png");

	private ItemContainer rootContainer;
	private MainApp mainApp;
	private SimulatedDrone simDrone;
	private TelloAdapter telloAdapter;

	ToggleGroup radioGroup = new ToggleGroup();

    public void DashboardController() {
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void handleAddItem() {
    	Items newItem = new Items();
    	mainApp.showItemPopUp(newItem, this);


    }
    @FXML
    private void handleAddContainerItem() {
    	ItemContainer Item = new ItemContainer();
    	mainApp.ShowItemContainerPopup(Item, this);


    }

    @FXML
    private void handleEditItem() {
    	TreeItem<Component> selected = componentTree.getSelectionModel().getSelectedItem();
    	mainApp.ShowEditItemPopup(selected, this);
    	drawFarm();

    }

    private void showItemDetails(Items item) {
    	if (item != null) {
    		nameLabel.setText(item.getName());
    		priceLabel.setText(Double.toString(item.getPrice()));
    		locationXLabel.setText(Double.toString(item.getLocationX()));
    		locationYLabel.setText(Double.toString(item.getLocationY()));
    		lengthLabel.setText(Integer.toString(item.getLength()));
    		widthLabel.setText(Integer.toString(item.getWidth()));
    		heightLabel.setText(Integer.toString(item.getHeight()));
    	} else {
    		nameLabel.setText("");
    		priceLabel.setText("");
    		locationXLabel.setText("");
    		locationYLabel.setText("");
    		lengthLabel.setText("");
    		widthLabel.setText("");
    		heightLabel.setText("");
    	}
    }

    public void addComponent(Component item){
    	TreeItem<Component> selected = componentTree.getSelectionModel().getSelectedItem();
    	Component selectedItem = componentTree.getSelectionModel().getSelectedItem().getValue();
    	if (selectedItem instanceof ItemContainer){
    		ObservableList<TreeItem<Component>> treeList = selected.getChildren();
    		ArrayList<Component> itemList = selectedItem.getChildren();

        	TreeItem<Component> newItem = new TreeItem<Component>(item);
        	newItem.getChildren().addAll(treeList);
        	item.getChildren().addAll(itemList);

        	selected.getParent().getChildren().set( selected.getParent().getChildren().indexOf(selected), newItem);
    		selectedItem.getParent().getChildren().set( selectedItem.getParent().getChildren().indexOf(selectedItem), item);

    	} else{
    		TreeItem<Component> newItem = new TreeItem<Component>(item);

    		selected.getParent().getChildren().set( selected.getParent().getChildren().indexOf(selected), newItem);
    		selectedItem.getParent().getChildren().set( selectedItem.getParent().getChildren().indexOf(selectedItem), item);

    	}
    }

    public boolean addItemContainer(ItemContainer newItemContainer){
    	Component selected = componentTree.getSelectionModel().getSelectedItem().getValue();
		if (selected instanceof ItemContainer){
			newItemContainer.setComponentParent(selected);
			TreeItem<Component> selectedTreeItem = componentTree.getSelectionModel().getSelectedItem();
			TreeItem<Component> node2 = new TreeItem<Component>(newItemContainer);

			selected.getChildren().add(newItemContainer);
			selectedTreeItem.getChildren().addAll(node2);
			drawFarm();
			return true;
		}
		return false;

    }

    public boolean addItem(Items newItem){
    	if (componentTree.getSelectionModel().getSelectedItem() == null) {
    		return false;
    	}
    	Component selected = componentTree.getSelectionModel().getSelectedItem().getValue();
		if (selected instanceof ItemContainer){
			newItem.setComponentParent(selected);
			TreeItem<Component> selectedTreeItem = componentTree.getSelectionModel().getSelectedItem();
			TreeItem<Component> node2 = new TreeItem<Component>(newItem);

			selected.getChildren().add(newItem);
			selectedTreeItem.getChildren().addAll(node2);
			drawFarm();
			return true;

		}
		return false;

    }

    @FXML
    public void drawFarm(){
    	mainApp.clearFarm(farmArea, droneController);
    	TreeItem<Component> root = componentTree.getRoot();
    	drawContainer(root);
    	createDrone();

    }


    public void drawContainer(TreeItem<Component> root){
    	if (root.getChildren() != null){
	    	ObservableList<TreeItem<Component>> arr =  root.getChildren();
	    	for (int i = 0; i < arr.size(); i++){
	    		Component item = arr.get(i).getValue();
	    		mainApp.drawItem(item, farmArea);
	    		if (item instanceof ItemContainer){
	    			drawContainer(arr.get(i));
	    		}
	    	}
    	}
    }



    public void visitSelected() {
    	Component selected = componentTree.getSelectionModel().getSelectedItem().getValue();
    	PurchasePrice vP = new PurchasePrice();
    	MarketValue vM = new MarketValue();
    	if (selected instanceof ItemContainer){
    		purchasePrice.setText(""+vP.visitItemContainer((ItemContainer) selected));
    		marketValue.setText(""+vM.visitItemContainer((ItemContainer) selected));
    	} else {
    		purchasePrice.setText(""+vP.visitItems((Items) selected));
    		marketValue.setText(""+vM.visitItems((Items) selected));
    	}
    }


    @FXML
    private void initialize() {
    	visitItem.setToggleGroup(radioGroup);
    	scanFarm.setToggleGroup(radioGroup);
    	ItemContainer rootContainer = new ItemContainer("root", 0.0, 0.0, 0.0, 0, 0, 0);
    	root = new TreeItem<Component>(rootContainer);

    	componentTree.setRoot(root);
    	componentTree.setShowRoot(true);
    	try {
			telloAdapter =  new TelloAdapter(new TelloDrone());
			telloAdapter.activateSDK();
		} catch (SocketException e1) {
			e1.printStackTrace();
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}


    	removeItemButton.setOnAction((event) -> {

    		// This gets the current selected tree item
    		TreeItem<Component> selectedTreeItem = componentTree.getSelectionModel().getSelectedItem();

    		// This gets the selected component item
    		Component selected = componentTree.getSelectionModel().getSelectedItem().getValue();

    		if (selected.getName() == "Drone" || selected.getName() == "Drone Controller"){

    		} else {

    		selected.getParent().getChildren().remove(selected);
    		selectedTreeItem.getParent().getChildren().remove(selectedTreeItem);

    		drawFarm();
    		}

		});

    	startSimulatorButton.setOnAction((event) -> {
    		if(radioGroup.getSelectedToggle().equals(visitItem)) {
    			Component selected = componentTree.getSelectionModel().getSelectedItem().getValue();
    			simDrone.visitItem(selected);
    		} else {
    			simDrone.scanFarm();
    		}
		});

    	startGraphicalDrone.setOnAction((event) -> {
    		if (droneController == null){
    			createDrone();
    		}
    	});

    	launchDrone.setOnAction((event) -> {
    		if(radioGroup.getSelectedToggle().equals(visitItem)) {
    			Component selected = componentTree.getSelectionModel().getSelectedItem().getValue();
    			telloAdapter.activateSDK();
				telloAdapter.takeoff();
    			telloAdapter.visitItem(selected);
    			telloAdapter.land();
    			telloAdapter.end();
    		} else {
    			telloAdapter.activateSDK();
				telloAdapter.takeoff();
    			telloAdapter.scanFarm();
    			telloAdapter.land();
    			telloAdapter.end();
    		}
    	});

    	componentTree.setOnMouseClicked((event) -> {
    		visitSelected();
    	});

    }

    public void createDrone(){
    	if (droneController == null){
    		droneController = new TreeItem<Component>(new ItemContainer("Drone Controller", 0.0, 100.0, 100.0, 100, 100, 100));
    		root.getChildren().add(droneController);
    		root.getValue().getChildren().add(droneController.getValue());
    		drone = new TreeItem<Component>(new Items("Drone", 0, 100, 100, 25, 25, 25, 0));
    		droneController.getChildren().add(drone);

    		root.getValue().getChildren().add(drone.getValue());
    		simDrone = new SimulatedDrone(drone, droneImage);
    		simDrone.createDrone(farmArea, drone, droneImage);
        	drawFarm();
		}else {
			//simDrone = new SimulatedDrone(drone, droneImage);
			simDrone.refreshDrone(farmArea);
		}
    }


}

