package com.somethingnifty.diablo3armory.domain;

import com.somethingnifty.diablo3armory.domain.enums.FollowerItemWearableType;
import com.somethingnifty.diablo3armory.domain.enums.ItemWearableType;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ItemLoadoutFollower implements Serializable {
    private Map<FollowerItemWearableType, ItemWearableActiveHero> itemLoadoutFollower = new HashMap<FollowerItemWearableType, ItemWearableActiveHero>();

    public ItemWearable getItemFollower(FollowerItemWearableType followerItemWearableType) {
        return itemLoadoutFollower.get(followerItemWearableType);
    }

    public void addItemFollower(FollowerItemWearableType followerItemWearableType, ItemWearableActiveHero itemWearable) {
        itemLoadoutFollower.put(followerItemWearableType, itemWearable);
    }
}