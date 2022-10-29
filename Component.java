package main.dashboard.model;

import java.util.ArrayList;

public abstract class Component {


    //default constructor
    public Component() {

    }

    public abstract void deleteItem();
    public abstract ArrayList<Component>getChildren();
    public abstract boolean add(Component i);

    public abstract String getName();

    public abstract void setName(String name);

    public abstract double getPrice();

    public abstract void setPrice(double price);

    public abstract double getLocationX();

    public abstract void setLocationX(double x);

    public abstract double getLocationY();

    public abstract void setLocationY(double y);

    public abstract void setPosition(double x, double y);

    public abstract int getLength();

    public abstract void setLength(int length);

    public abstract int getWidth();
    public abstract void setWidth(int width);

    public abstract int getHeight();

    public abstract void setHeight(int height);

    public abstract void setComponentParent(Component parent);

    public abstract Component getParent();
    public abstract double accept(Visitor v);
}

