package com.somethingnifty.diablo3armory.domain.enums;

import java.util.EnumSet;

public enum ItemWearableType {

    HEAD("head", "Head"),
    SHOULDERS("shoulders", "Shoulders"),
    TORSO("torso", "Torso"),
    BRACERS("bracers", "Bracers"),
    HANDS("hands", "Hands"),
    WAIST("waist", "Waist"),
    LEGS("legs", "Legs"),
    FEET("feet", "Feet"),
    NECK("neck", "Neck"),
    LEFT_FINGER("leftFinger", "Left Finger"),
    RIGHT_FINGER("rightFinger", "Right Finger"),
    MAIN_HAND("mainHand", "Main Hand"),
    OFFHAND("offHand", "Off hand");

    public static final EnumSet<ItemWearableType> ALL = EnumSet.allOf(ItemWearableType.class);

    private String value;
    private String toString;

    private ItemWearableType(String value, String toString){
        this.value = value;
        this.toString = toString;
    }

    public String getValue(){
        return value;
    }

    public String getToString() {
        return toString;
    }
}
