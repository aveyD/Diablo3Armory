package com.somethingnifty.diablo3armory.domain.enums;

import java.util.EnumSet;

public enum FollowerItemWearableType {
    SPECIAL("special"),
    MAIN_HAND("mainHand"),
    RIGHT_FINGER("rightFinger"),
    LEFT_FINGER("leftFinger"),
    NECK("neck");

    public static final EnumSet<FollowerItemWearableType> ALL = EnumSet.allOf(FollowerItemWearableType.class);

    private String value;

    private FollowerItemWearableType(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
