package com.somethingnifty.diablo3armory.domain;

import com.somethingnifty.diablo3armory.domain.enums.ItemWearableType;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ItemLoadout implements Serializable {
    private Map<ItemWearableType, ItemWearableEquippable> itemLoadout = new TreeMap<ItemWearableType, ItemWearableEquippable>();

    public ItemWearable getItem(ItemWearableType itemWearableType) {
        return itemLoadout.get(itemWearableType);
    }

    public void addItem(ItemWearableType itemWearableType, ItemWearableEquippable itemWearable) {
        itemLoadout.put(itemWearableType, itemWearable);
    }

    public Set<Map.Entry<ItemWearableType, ItemWearableEquippable>> getItemTypeByItem(){
        return itemLoadout.entrySet();
    }
}