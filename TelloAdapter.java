package main.dashboard.model;
import java.io.IOException;

import main.java.surelyhuman.jdrone.control.physical.tello.*;

public class TelloAdapter implements DroneInterface {

	TelloDrone telloDrone;
	int droneX;
	int droneY;
	int degrees;

	public TelloAdapter(TelloDrone telloDrone){
		this.telloDrone = telloDrone;
		droneX = 100;
		droneY = 100;
		degrees = 0;
	}


	public void takeoff() {

		try {
			telloDrone.takeoff();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void land() {
		try {
			telloDrone.land();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void gotoXYZ(int x, int y, int z, int speed) {
		try {
			telloDrone.gotoXYZ(x, y, z, speed);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public int convertPixCM(int pixels){
		return pixels/Constants.PIXELS_TO_ONE_MODEL_FOOT * Constants.CENTIMETERS_PER_MODEL_FOOT;
	}

	public void gotoXY(int x, int y, int speed) {
		try {
			telloDrone.gotoXY(convertPixCM(x), convertPixCM(y), speed);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	@Override
	public void activateSDK() {
		try {
			telloDrone.activateSDK();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	@Override
	public void end() {
		try {
			telloDrone.end();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void visitItem(Component item){
		int x2 = (int) item.getLocationX();
		int y2 = (int) item.getLocationY();

		double dir = Math.toDegrees(Math.atan2(y2 - droneY, x2 - droneX));
		if (dir < 0){
			dir+=360;
		}

		turnTo((int) Math.round(dir));

		gotoXY(x2 - droneX, y2 - droneY, 1);
    	turnCCW(360);
    	hoverInPlace(2);

    	double dir2 = Math.toDegrees(Math.atan2(droneY - y2, droneX - x2));
		if (dir2 < 0){
			dir2+=360;
		}
    	turnTo((int) Math.round(dir2));
    	gotoXY(droneX - x2, droneY - y2, 1);


	}

	public void turnTo(int degrees){
		if (this.degrees > degrees){
			turnCCW(this.degrees - degrees);
			this.degrees = degrees;
		} else {
			turnCW(degrees - this.degrees);
			this.degrees = degrees;
		}
	}

	public void scanFarm() {
		int origDegrees = this.degrees;
		goFrom(droneX, droneY, 770, 570);
		for (int i = 0; i < 3; i++) {
			goFrom(droneX, droneY, droneX, droneY - 550);

			goFrom(droneX, droneY, droneX - 100, droneY);

			goFrom(droneX, droneY, droneX, droneY + 550);

			goFrom(droneX, droneY, droneX - 100, droneY);
		}
		goFrom(droneX, droneY, droneX, droneY - 550);

		goFrom(droneX, droneY, droneX - 100, droneY);

		goFrom(droneX, droneY, droneX, droneY + 550);

		goFrom(droneX, droneY, droneX - 50, droneY);

		turnTo(origDegrees);
	}

	private void goFrom(int x1, int y1, int x2, int y2){
		int dir = (int) Math.toDegrees(Math.atan2(y2 - y1, x2 - x1));
		if (dir < 0){
			dir+=360;
		}
		turnTo(dir);
		gotoXY(x2 - x1, y2 - y1, 10);
		droneX = x2;
		droneY = y2;

	}


	@Override
	public void increaseAltitude(int up) {
		try {
			telloDrone.increaseAltitude(up);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	@Override
	public void decreaseAltitude(int down) {
		try {
			telloDrone.decreaseAltitude(down);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	@Override
	public void flyForward(int front, int degrees) {
		try {
			telloDrone.flyForward(front);
		} catch (IOException e) {
			e.printStackTrace();
		}




	}


	@Override
	public void turnCW(int degrees) {
		try {
			telloDrone.turnCW(degrees);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	@Override
	public void turnCCW(int degrees) {
		try {
			telloDrone.turnCCW(degrees);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}




	@Override
	public void flip(String direction) {
		try {
			telloDrone.flip(direction);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public void hoverInPlace(int seconds) {
		try {
			telloDrone.hoverInPlace(seconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
