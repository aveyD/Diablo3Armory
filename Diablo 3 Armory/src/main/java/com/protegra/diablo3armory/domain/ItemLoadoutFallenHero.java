package com.protegra.diablo3armory.domain;

import java.util.HashMap;
import java.util.Map;

public class ItemLoadoutFallenHero {
    private Map<ItemWearableType, ItemWearable> itemLoadoutFallenHero = new HashMap<ItemWearableType, ItemWearable>();

    public ItemWearable getItemFallenHero(ItemWearableType itemWearableType) {
        return itemLoadoutFallenHero.get(itemWearableType);
    }

    public void setItemFallenHero(ItemWearableType itemWearableType, ItemWearable itemWearable) {
        itemLoadoutFallenHero.put(itemWearableType, itemWearable);
    }
}
