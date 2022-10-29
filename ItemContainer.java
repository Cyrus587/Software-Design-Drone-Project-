package main.dashboard.model;

import java.util.*;
public class ItemContainer extends Component {
    private ArrayList<Component> items = new ArrayList<Component>();

    private String name;
	private double price;
	private double location_x;
	private double location_y;
	private int length;
	private int width;
    private int height;
    private Component componentParent;


    //default constructor
    public ItemContainer() {

    }


    public ItemContainer(String name, double price, double location_x, double location_y, int length, int width, int height) {
        this.name = name;
        this.price = price;
        this.location_x = location_x;
        this.location_y = location_y;
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public void deleteItemContainer() {
        name = "";
        price = 0;
        location_x = 0.0;
        location_y = 0.0;
        length = 0;
        width = 0;
        height = 0;
    }

    public boolean add(Component i) {
        return items.add(i);
    }

    public ArrayList<Component> getChildren() {
        return items;
    }

    public double accept(Visitor v) {
        return v.visitItemContainer(this);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getLocationX() {
        return this.location_x;
    }

    public void setLocationX(double x) {
        this.location_x = x;
    }

    public double getLocationY() {
        return this.location_y;
    }

    public void setLocationY(double y) {
        this.location_y = y;
    }

    public void setPosition(double x, double y) {
        setLocationX(x);
        setLocationY(y);
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return this.name;
    }

	@Override
	public void deleteItem() {
		// TODO Auto-generated method stub

	}

	public void setComponentParent(Component parent){
    	this.componentParent = parent;
    }

	public Component getParent(){
    	return this.componentParent;
    }




}
