package com.protegra.diablo3armory.domain.enums;

import java.util.EnumSet;

public enum ItemWearableType {

    TORSO("torso"),
    NECK("neck"),
    LEFT_FINGER("leftFinger"),
    MAIN_HAND("mainHand"),
    BRACERS("bracers"),
    HANDS("hands"),
    RIGHT_FINGER("rightFinger"),
    FEET("feet"),
    WAIST("waist"),
    LEG("legs"),
    OFFHAND("offHand"),
    HEAD("head"),
    SHOULDERS("shoulders");

    public static final EnumSet<ItemWearableType> ALL = EnumSet.allOf(ItemWearableType.class);

    private String value;

    private ItemWearableType(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
