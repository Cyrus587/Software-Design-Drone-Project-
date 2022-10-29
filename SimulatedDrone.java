package main.dashboard.model;

import java.util.ArrayList;

import javafx.animation.RotateTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class SimulatedDrone implements DroneInterface {

	@FXML
	public TreeItem<Component> drone;
	private ImageView droneView;
	private ArrayList<Transition> list = new ArrayList<Transition>();
	private int degrees;
	private int speed = 500;

	public SimulatedDrone(TreeItem<Component> drone, Image droneImage){
		this.drone = drone;
		droneView = new ImageView(droneImage);
	}
	public void takeoff(){
    	System.out.println("The Drone Takes Off");
    }
	public void land(){
		System.out.println("The Drone Lands");
	}

	public void gotoXYZ(int x, int y, int z, int speed) {

	}

	public void gotoXY(int x, int y, int speed){

    }

	public void visitItem(Component item){
		int origDegrees = this.degrees;
		int startX = (int) drone.getValue().getLocationX();
		int startY = (int) drone.getValue().getLocationY();

		int x = (int) item.getLocationX();
		int y = (int) item.getLocationY();

		goFrom(startX, startY, x, y);
    	turnTo(360);
    	hoverInPlace(1);
    	goFrom(x, y, startX, startY);
    	turnTo(origDegrees);
    	list.get(0).play();

	}

	private void goFrom(int x1, int y1, int x2, int y2){
		double dir = Math.toDegrees(Math.atan2(y2 - y1, x2 - x1));
		if (dir < 0){
			dir+=360;
		}
		double sec = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)) * 1/speed;

		turnTo((int) Math.round(dir));

    	TranslateTransition transition = new TranslateTransition();
    	list.add(transition);
    	transition.setDuration(Duration.seconds(sec));
    	transition.setToX(x2 - droneView.getLayoutX());
    	drone.getValue().setLocationX(x2);
    	drone.getValue().setLocationY(y2);
    	transition.setToY(y2 - droneView.getLayoutY());
    	transition.setNode(droneView);

    	transition.setOnFinished((event) ->  {
    		list.remove(transition);
			if (list.isEmpty() != true) {
				list.get(0).play();
			}
		});
	}

	public void createDrone(AnchorPane farm, TreeItem<Component> drone, Image droneImage){
    	if (this.drone == null){
	    	this.drone = drone;
	    	droneView = new ImageView(droneImage);

	    	droneView.setLayoutX(this.drone.getValue().getLocationX());
	    	droneView.setLayoutY(this.drone.getValue().getLocationY());
	    	droneView.setFitHeight(10);
	    	droneView.setFitWidth(10);
	    	degrees = 0;

	    	farm.getChildren().add(droneView);
    	} else{
    		farm.getChildren().add(droneView);
    	}

    }

	public void refreshDrone (AnchorPane farm){
		droneView.setLayoutX(this.drone.getValue().getLocationX());
    	droneView.setLayoutY(this.drone.getValue().getLocationY());
		farm.getChildren().add(droneView);
	}

	public void activateSDK() {
		System.out.println("You activate SDK... nothing happens");
	}

	public void end() {
		System.out.println("The drone kamikazes into the ground");
	}

	@Override
	public void increaseAltitude(int up) {
		System.out.println("The drone increases altitude");

	}

	@Override
	public void decreaseAltitude(int down) {
		System.out.println("The drone drecreases altitude");

	}

	public void scanFarm(){
		int origDegrees = this.degrees;
		goFrom((int) drone.getValue().getLocationX(), (int) drone.getValue().getLocationY(), 755, 560);
		for (int i = 0; i < 3; i++) {
			flyForward(535, 270);

			flyForward(100, 180);

			flyForward(535, 90);

			flyForward(100, 180);
		}

		flyForward(540, 270);

		flyForward(100, 180);

		flyForward(540, 90);

		flyForward(50, 180);

		goFrom((int) drone.getValue().getLocationX(), (int) drone.getValue().getLocationY(), 100, 100);
		turnTo(origDegrees);
		list.get(0).play();
	}

	public void flyForward(int front, int degrees) {
		switch (degrees) {
		case 0:
			goFrom((int)drone.getValue().getLocationX(),
					(int) drone.getValue().getLocationY(),
					(int) drone.getValue().getLocationX() + front,
					(int) drone.getValue().getLocationY());

	    	break;
		case 90:
			goFrom((int)drone.getValue().getLocationX(),
					(int) drone.getValue().getLocationY(),
					(int) drone.getValue().getLocationX(),
					(int) drone.getValue().getLocationY() + front);


	    	break;
		case 180:
			goFrom((int)drone.getValue().getLocationX(),
					(int) drone.getValue().getLocationY(),
					(int) drone.getValue().getLocationX() - front,
					(int) drone.getValue().getLocationY());

	    	break;
		case 270:
			goFrom((int)drone.getValue().getLocationX(),
					(int) drone.getValue().getLocationY(),
					(int) drone.getValue().getLocationX(),
					(int) drone.getValue().getLocationY() - front);
	    	break;
		}

	}

	public void turnTo(int degrees) {
		RotateTransition transition = new RotateTransition(Duration.seconds(.5), droneView);
		transition.setToAngle(degrees);
		list.add(transition);
		this.degrees = degrees;
		transition.setOnFinished((event) ->  {
    		list.remove(transition);
			if (list.isEmpty() != true) {
				list.get(0).play();
			}
		});


	}

	@Override
	public void turnCW(int degrees) {

	}

	@Override
	public void turnCCW(int degrees) {

	}

	@Override
	public void flip(String direction) {
		System.out.println("Do a Sick Flip");

	}

	@Override
	public void hoverInPlace(int seconds) {
		RotateTransition transition = new RotateTransition(Duration.seconds(seconds), droneView);
		transition.setToAngle(degrees + .1);
		list.add(transition);
		transition.setOnFinished((event) ->  {
    		list.remove(transition);
			if (list.isEmpty() != true) {
				list.get(0).play();
			}
		});
		RotateTransition transition2 = new RotateTransition(Duration.seconds(.1), droneView);
		transition2.setToAngle(degrees);
		list.add(transition2);
		transition2.setOnFinished((event) ->  {
    		list.remove(transition2);
			if (list.isEmpty() != true) {
				list.get(0).play();
			}
		});


	}

}


