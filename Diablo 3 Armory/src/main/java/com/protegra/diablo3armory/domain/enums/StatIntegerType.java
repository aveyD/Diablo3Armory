package com.protegra.diablo3armory.domain.enums;

import java.util.EnumSet;

public enum StatIntegerType {

    //Integer values
    LIFE("life"),
    ARMOR("armor"),
    STRENGTH("strength"),
    DEXTERITY("dexterity"),
    VITALITY("vitality"),
    INTELLIGENCE("intelligence"),
    PHYSICAL_RESIST("physicalResist"),
    COLD_RESIST("coldResist"),
    FIRE_RESIST("fireResist"),
    LIGHTNING_RESIST("lightningResist"),
    POISON_RESIST("poisonResist"),
    ARCANE_RESIST("arcaneResist"),
    BLOCK_AMOUNT_MIN("blockAmountMin"),
    BLOCK_AMOUNT_MAX("blockAmountMax"),
    PRIMARY_RESOURCE("primaryResource"),
    SECONDARY_RESOURCE("secondaryResource");

    public static final EnumSet<StatIntegerType> ALL = EnumSet.allOf(StatIntegerType.class);

    private String value;

    private StatIntegerType(String value){
        this.value = value;
    }
}
