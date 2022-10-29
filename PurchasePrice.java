package main.dashboard.model;
import java.util.*;

public class PurchasePrice implements Visitor{
    private double totalPrice;
    public PurchasePrice(){
        totalPrice = 0;
    }
    public double visitItemContainer(ItemContainer itemContainer){
        totalPrice = itemContainer.getPrice();
        Iterator<Component> iterator = itemContainer.getChildren().iterator();
        while(iterator.hasNext()){
            Component currentComponent = iterator.next();
            totalPrice += currentComponent.accept(new PurchasePrice());
        }
        return totalPrice;
    }
    public double visitItems(Items item){
        totalPrice = item.getPrice();
        return totalPrice;
    }
}
