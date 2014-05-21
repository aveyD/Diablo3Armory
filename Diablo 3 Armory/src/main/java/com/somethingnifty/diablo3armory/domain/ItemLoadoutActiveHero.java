package com.somethingnifty.diablo3armory.domain;

import com.somethingnifty.diablo3armory.domain.enums.ItemWearableType;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ItemLoadoutActiveHero implements Serializable {
    private Map<ItemWearableType, ItemWearableActiveHero> itemLoadoutActiveHero = new HashMap<ItemWearableType, ItemWearableActiveHero>();

    public ItemWearable getItemActiveHero(ItemWearableType itemWearableType) {
        return itemLoadoutActiveHero.get(itemWearableType);
    }

    public void addItemActiveHero(ItemWearableType itemWearableType, ItemWearableActiveHero itemWearable) {
        itemLoadoutActiveHero.put(itemWearableType, itemWearable);
    }
}