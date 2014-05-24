package com.somethingnifty.diablo3armory.domain.enums;

import java.util.EnumSet;

public enum StatIntegerType {

    LIFE("life", "life_value"),
    ARMOR("armor", "armor_value"),
    STRENGTH("strength", "strength_value"),
    DEXTERITY("dexterity", "dexterity_value"),
    VITALITY("vitality", "vitality_value"),
    INTELLIGENCE("intelligence", "intelligence_value"),
    PHYSICAL_RESIST("physicalResist", "physical_resist_value"),
    COLD_RESIST("coldResist", "cold_resist_value"),
    FIRE_RESIST("fireResist", "fire_resist_value"),
    LIGHTNING_RESIST("lightningResist", "lightning_resist_value"),
    POISON_RESIST("poisonResist", "poison_resist_value"),
    ARCANE_HOLY_RESIST("arcaneResist", "arcane_resist_value"),
    BLOCK_AMOUNT_MIN("blockAmountMin", "block_amount_min_value"),
    BLOCK_AMOUNT_MAX("blockAmountMax", "block_amount_min_max_value"),
    PRIMARY_RESOURCE("primaryResource", "primary_resource_value"),
    SECONDARY_RESOURCE("secondaryResource", "secondary_resource_value");

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
