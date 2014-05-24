package com.somethingnifty.diablo3armory.domain.enums;

import java.util.EnumSet;

public enum StatIntegerType {

    LIFE("life", "life_string"),
    ARMOR("armor", "armor_string"),
    STRENGTH("strength", "strength_string"),
    DEXTERITY("dexterity", "dexterity_string"),
    VITALITY("vitality", "vitality_string"),
    INTELLIGENCE("intelligence", "intelligence_string"),
    PHYSICAL_RESIST("physicalResist", "physical_resist_string"),
    COLD_RESIST("coldResist", "cold_resist_string"),
    FIRE_RESIST("fireResist", "fire_resist_string"),
    LIGHTNING_RESIST("lightningResist", "lightning_resist_string"),
    POISON_RESIST("poisonResist", "poison_resist_string"),
    ARCANE_RESIST("arcaneResist", "arcane_resist_string"),
    BLOCK_AMOUNT_MIN("blockAmountMin", "block_amount_min_string"),
    BLOCK_AMOUNT_MAX("blockAmountMax", "block_amount_min_max_string"),
    PRIMARY_RESOURCE("primaryResource", "primary_resource_string"),
    SECONDARY_RESOURCE("secondaryResource", "secondary_resource_string");

    public static final EnumSet<StatIntegerType> ALL = EnumSet.allOf(StatIntegerType.class);

    private String value;
    private String toString;

    private StatIntegerType(String value, String toString){
        this.value = value;
        this.toString = toString;
    }

    public String getValue(){
        return value;
    }

    public String getToString(){
        return toString;
    }
}
