package com.protegra.diablo3armory.domain;

import com.protegra.diablo3armory.domain.enums.ItemWearableType;

import java.util.HashMap;
import java.util.Map;

public class ItemLoadoutActiveHero {
    private Map<ItemWearableType, ItemWearableActiveHero> itemLoadoutFallenHero = new HashMap<ItemWearableType, ItemWearableActiveHero>();

    public ItemWearable getItemFallenHero(ItemWearableType itemWearableType) {
        return itemLoadoutFallenHero.get(itemWearableType);
    }

    public void setItemFallenHero(ItemWearableType itemWearableType, ItemWearableActiveHero itemWearable) {
        itemLoadoutFallenHero.put(itemWearableType, itemWearable);
    }
}