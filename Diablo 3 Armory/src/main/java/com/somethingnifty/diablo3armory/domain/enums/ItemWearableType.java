package com.somethingnifty.diablo3armory.domain.enums;

import java.util.EnumSet;

public enum ItemWearableType {

    SPECIAL("special", "Special"),
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

    public static final EnumSet<ItemWearableType> ALL_HERO_ITEMS = EnumSet.range(HEAD, OFFHAND);
    public static final EnumSet<ItemWearableType> ALL_FOLLOWER_ITEMS = EnumSet.of(SPECIAL, NECK, LEFT_FINGER, RIGHT_FINGER, MAIN_HAND);

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
