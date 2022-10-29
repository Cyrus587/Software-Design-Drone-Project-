package main.dashboard.model;

public interface DroneInterface {
	public void takeoff();
	public void land();
	public void gotoXYZ(int x, int y, int z, int speed);
	public void gotoXY(int x, int y, int speed);
	public void activateSDK();
	public void end();
	public void increaseAltitude(int up);
	public void decreaseAltitude(int down);
	public void flyForward(int front, int degrees);
	public void turnCW(int degrees);
	public void turnCCW(int degrees);
	public void flip(String direction);
	public void hoverInPlace(int seconds);
	public void visitItem(Component item);
	public void scanFarm();

}
