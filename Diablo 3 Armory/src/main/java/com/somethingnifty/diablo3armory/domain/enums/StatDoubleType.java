package com.somethingnifty.diablo3armory.domain.enums;

import java.util.EnumSet;

public enum StatDoubleType {

    CRIT_DAMAGE("critDamage", "crit_damage_value"),
    BLOCK_CHANCE("blockChance", "block_chance_value"),
    DAMAGE("damage", "damage_value"),
    ATTACK_SPEED("attackSpeed", "attack_speed_value"),
    DAMAGE_INCREASE("damageIncrease", "damage_increase_value"),
    CRIT_CHANCE("critChance", "crit_chance_value"),
    DAMAGE_REDUCTION("damageReduction", "damage_reduction_value"),
    THORNS("thorns", "thorns_value"),
    LIFE_STEAL("lifeSteal", "life_steal_value"),
    LIFE_PER_KILL("lifePerKill", "life_per_kill_value"),
    GOLD_FIND("goldFind", "gold_find_value"),
    MAGIC_FIND("magicFind", "magic_find_value"),
    LIFE_ON_HIT("lifeOnHit", "life_on_hit_value");

    public static final EnumSet<StatDoubleType> ALL = EnumSet.allOf(StatDoubleType.class);

    private String value;
    private String toString;

    private StatDoubleType(String value, String toString){
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
