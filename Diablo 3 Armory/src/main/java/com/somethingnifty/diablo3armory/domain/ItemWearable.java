package com.somethingnifty.diablo3armory.domain;

import java.util.List;

public class ItemWearable extends Item {
    private RandomAffix randomAffix;
    private List<CraftedBy> craftedByList;

    public RandomAffix getRandomAffix() {
        return randomAffix;
    }

    public void setRandomAffix(RandomAffix randomAffix) {
        this.randomAffix = randomAffix;
    }

    public List<CraftedBy> getCraftedByList() {
        return craftedByList;
    }

    public void setCraftedByList(List<CraftedBy> craftedByList) {
        this.craftedByList = craftedByList;
    }
}
