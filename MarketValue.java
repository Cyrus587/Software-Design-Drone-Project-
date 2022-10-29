package main.dashboard.model;

import java.util.*;

public class MarketValue implements Visitor{
    private double marketValue;
    private double totalMarketValue;

    public MarketValue(){
        totalMarketValue = 0;
    }

    public double visitItems(Items i){
        marketValue = i.getMarketValue();
        return marketValue;
    }

    public double visitItemContainer(ItemContainer itemContainer){
        Iterator<Component> iterator = itemContainer.getChildren().iterator();
        while(iterator.hasNext()){
            Component component = iterator.next();
            totalMarketValue += component.accept(new MarketValue());
        }
        return totalMarketValue;
    }
}

