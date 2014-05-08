package com.protegra.diablo3armory.domain;

public class Reagent {
    private int quantity = 0;
    private Item reagentItem;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Item getReagentItem() {
        return reagentItem;
    }

    public void setReagentItem(Item reagentItem) {
        this.reagentItem = reagentItem;
    }
}
