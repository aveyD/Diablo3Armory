package com.protegra.diablo3armory.domain;

import com.protegra.diablo3armory.domain.enums.ItemWearableType;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ItemLoadoutFallenHero implements Serializable {
    private Map<ItemWearableType, ItemWearable> itemLoadoutFallenHero = new HashMap<ItemWearableType, ItemWearable>();

    public ItemWearable getItemFallenHero(ItemWearableType itemWearableType) {
        return itemLoadoutFallenHero.get(itemWearableType);
    }

    public void setItemFallenHero(ItemWearableType itemWearableType, ItemWearable itemWearable) {
        itemLoadoutFallenHero.put(itemWearableType, itemWearable);
    }
}
