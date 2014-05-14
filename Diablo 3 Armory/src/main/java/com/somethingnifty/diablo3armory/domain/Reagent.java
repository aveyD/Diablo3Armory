package com.somethingnifty.diablo3armory.domain;

import java.io.Serializable;

public class Reagent implements Serializable {
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
