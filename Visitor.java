package main.dashboard.model;

interface Visitor {
    double visitItemContainer(ItemContainer i);
    double visitItems(Items i);
}
