package main.dashboard.model;

import java.util.*;
public class Items extends Component{

	private String name;
	private double price;
	private double location_x;
	private double location_y;
	private int length;
	private int width;
    private int height;
    private Component componentParent;
    private double marketValue;

    //default constructor
    public Items() {

    }

    public double getMarketValue(){
    	return this.marketValue;
    }

    public void setMarketValue(double marketValue){
    	this.marketValue = marketValue;
    }

    public Items(String name, double price, double location_x, double location_y, int length, int width, int height, double marketValue){
        this.name = name;
        this.price = price;
        this.location_x = location_x;
        this.location_y = location_y;
        this.length = length;
        this.width = width;
        this.height = height;
        this.marketValue = marketValue;
    }

    public void deleteItem() {
        name = "";
        price = 0;
        location_x = 0.0;
        location_y = 0.0;
        length = 0;
        width = 0;
        height = 0;
    }

    public double accept(Visitor v) {
        return v.visitItems(this);
    }


    @Override
    public String toString() {
        return (this.name);
    }

    public boolean addItems(Component i) {
		return false;
       //
    }

    public boolean add(Component i){
		return false;

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

    public ArrayList<Component>getChildren(){
		return null;

    }
    public void setComponentParent(Component parent){
    	this.componentParent = parent;
    }

    public Component getParent(){
    	return this.componentParent;
    }
}
