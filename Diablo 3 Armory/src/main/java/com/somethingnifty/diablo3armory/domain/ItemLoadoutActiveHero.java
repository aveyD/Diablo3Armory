package com.somethingnifty.diablo3armory.domain;

import com.somethingnifty.diablo3armory.domain.enums.ItemWearableType;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ItemLoadoutActiveHero implements Serializable {
    private Map<ItemWearableType, ItemWearableActiveHero> itemLoadoutActiveHero = new TreeMap<ItemWearableType, ItemWearableActiveHero>();

    public ItemWearable getItemActiveHero(ItemWearableType itemWearableType) {
        return itemLoadoutActiveHero.get(itemWearableType);
    }

    public void addItemActiveHero(ItemWearableType itemWearableType, ItemWearableActiveHero itemWearable) {
        itemLoadoutActiveHero.put(itemWearableType, itemWearable);
    }

    public Set<Map.Entry<ItemWearableType, ItemWearableActiveHero>> getItemTypeByItem(){
        return itemLoadoutActiveHero.entrySet();
    }
}