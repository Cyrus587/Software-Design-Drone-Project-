package main.dashboard;


import java.io.IOException;


import main.dashboard.model.Component;
import main.dashboard.model.ItemContainer;
import main.dashboard.model.Items;
import main.dashboard.view.*;
import main.dashboard.view.AddItemContainerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private AnchorPane personOverview;
    private Stage itemPage;
    private Rectangle rect;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("FarmController");

        initRootLayout();

        showPersonOverview();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showPersonOverview() {
        try {

            // Load person overview.
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(MainApp.class.getResource("view/Dashboard.fxml"));
            personOverview = (AnchorPane) loader2.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            DashboardController controller = loader2.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showItemPopUp(Items item, DashboardController dashboard) {
    	try {
    		FXMLLoader loader3 = new FXMLLoader();
    		loader3.setLocation(MainApp.class.getResource("view/AddItem.fxml"));
    		AnchorPane itemPage = (AnchorPane) loader3.load();

    		Stage itemStage = new Stage();
    		itemStage.setTitle("Add Item");
    		itemStage.initModality(Modality.WINDOW_MODAL);
    		itemStage.initOwner(primaryStage);
    		Scene scene = new Scene(itemPage);
    		itemStage.setScene(scene);

    		ItemController controller = loader3.getController();
    		controller.setItemStage(itemStage);
    		controller.setItem(item);
    		controller.setDashboardController(dashboard);
    		itemStage.showAndWait();

    		return controller.isSaveClicked();

    	} catch (IOException e) {
    		e.printStackTrace();
    		return false;
    	}
    }



    public boolean ShowItemContainerPopup(ItemContainer itemContainer, DashboardController dashboard) {
    	try {
    		FXMLLoader loader4= new FXMLLoader();
    		loader4.setLocation(MainApp.class.getResource("view/AddItemContainer.fxml"));
    		AnchorPane itemContainerPage = (AnchorPane) loader4.load();


    		Stage itemStage = new Stage();
    		itemStage.setTitle("AddItemContainer");
    		itemStage.initModality(Modality.WINDOW_MODAL);
    		itemStage.initOwner(primaryStage);
    		Scene scene = new Scene(itemContainerPage);
    		itemStage.setScene(scene);

    		AddItemContainerController controller2 = loader4.getController();
    		controller2.setDialogStage(itemStage);
    		controller2.setItemContainer(itemContainer);
    		controller2.setDashboardController(dashboard);
    		itemStage.showAndWait();

    		return controller2.okClicked();

    	} catch (IOException e) {
    		e.printStackTrace();
    		return false;
    	}
    }

    public boolean ShowEditItemPopup(TreeItem<Component> item, DashboardController dashboard) {
    	try {
    		FXMLLoader loader4= new FXMLLoader();
    		loader4.setLocation(MainApp.class.getResource("view/EditItem.fxml"));
    		AnchorPane editItemPage = (AnchorPane) loader4.load();


    		Stage itemStage = new Stage();
    		itemStage.setTitle("Edit Item");
    		itemStage.initModality(Modality.WINDOW_MODAL);
    		itemStage.initOwner(primaryStage);
    		Scene scene = new Scene(editItemPage );
    		itemStage.setScene(scene);

    		EditItemController controller2 = loader4.getController();
    		controller2.setItemStage(itemStage);
    		controller2.setItem(item);
    		controller2.setDashboardController(dashboard);
    		itemStage.showAndWait();

    		return controller2.isSaveClicked();


    	} catch (IOException e) {
    		e.printStackTrace();
    		return false;
    	}
    }



    public static void main(String[] args) {
        launch(args);
    }

    public void drawItem(Component item, AnchorPane farm){
    	if (item.getName() == "Drone"){
    		return;
    	}
    	rect = new Rectangle();
    	rect.setFill(Color.WHITE);
    	rect.setStroke(Color.BLACK);
    	rect.setHeight(item.getLength());
    	rect.setWidth(item.getWidth());
    	rect.setArcWidth(0);
    	rect.setArcHeight(0);

    	Text text = new Text(item.getName());
    	StackPane stack = new StackPane();
    	stack.getChildren().addAll(rect, text);
    	stack.setLayoutX(item.getLocationX());
    	stack.setLayoutY(item.getLocationY());
    	farm.getChildren().add(stack);
    }


    public void clearFarm(AnchorPane farm, TreeItem<Component> drone){
    	if (farm.getChildren() != null){
	    	farm.getChildren().clear();
    	}
    }

    /*public void createDrone(AnchorPane farm, TreeItem<Component> drone, Image droneImage){
    	if (this.drone == null){
	    	this.drone = drone;
	    	droneView = new ImageView(droneImage);

	    	droneView.setLayoutX(this.drone.getValue().getLocationX());
	    	droneView.setLayoutY(this.drone.getValue().getLocationY());
	    	droneView.setFitHeight(40);
	    	droneView.setFitWidth(40);

	    	farm.getChildren().add(droneView);
    	} else{
    		farm.getChildren().add(droneView);
    	}

    }*/

    /*public void visitItem( TreeItem<Component> item, ObservableList<TreeItem<Component>> children){
    	droneGoTo(item.getValue().getLocationX(), item.getValue().getLocationY(), drone, children, item);
    }*/

    /*public void droneGoTo(double x, double y, TreeItem<Component> drone, ObservableList<TreeItem<Component>> children, TreeItem<Component> item){
    	transition = new TranslateTransition();
    	transition.setDuration(Duration.seconds(1));
    	transition.setToX(x - droneView.getLayoutX());
    	drone.getValue().setLocationX(x - droneView.getLayoutX());
    	drone.getValue().setLocationY(y - droneView.getLayoutY());
    	transition.setToY(y - droneView.getLayoutY());
    	transition.setNode(droneView);
    	transition.setOnFinished((event) ->{
    		if (item.getValue() instanceof ItemContainer){
    			scanContainer(item);
    		}
    		if (item.nextSibling() != null){
    			if (item.nextSibling().getValue().getName()=="Drone"){
    				if (item.nextSibling().nextSibling() != null){
    					visitItem(item.nextSibling().nextSibling(), children);
    				}
    			}
    				visitItem(item.nextSibling(), children);

    		}else{
    			gotoXY(100,100, 0);
    		}

    	});

    	transition.play();

    }*/

    /*public void gotoXY(int x, int y, int speed){
    	transition = new TranslateTransition();
    	transition.setDuration(Duration.seconds(1));
    	transition.setToX(x - droneView.getLayoutX());
    	drone.getValue().setLocationX(x - droneView.getLayoutX());
    	drone.getValue().setLocationY(y - droneView.getLayoutY());
    	transition.setToY(y - droneView.getLayoutY());
    	transition.setNode(droneView);
    	transition.play();
    }*/

    /*public void scanContainer(TreeItem<Component> container){
    	if (container.getChildren().size() > 0 ){
	    	ObservableList<TreeItem<Component>> children  = container.getChildren();


			TreeItem<Component> item = children.get(0);

			if (item.getValue().getName() == "Drone"){
				if (item.nextSibling()!=null){
					item = item.nextSibling();
					visitItem(item, children);
				}
			}else{

			visitItem(item, children);
			}
    	}
    }*/


}

