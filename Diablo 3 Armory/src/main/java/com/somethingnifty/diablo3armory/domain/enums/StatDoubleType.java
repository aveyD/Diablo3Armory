package com.somethingnifty.diablo3armory.domain.enums;

import java.util.EnumSet;

public enum StatDoubleType {

    CRIT_DAMAGE("critDamage", "crit_damage_string"),
    BLOCK_CHANCE("blockChance", "block_chance_string"),
    DAMAGE("damage", "damage_string"),
    ATTACK_SPEED("attackSpeed", "attack_speed_string"),
    DAMAGE_INCREASE("damageIncrease", "damage_increase_string"),
    CRIT_CHANCE("critChance", "crit_chance_string"),
    DAMAGE_REDUCTION("damageReduction", "damage_reduction_string"),
    THORNS("thorns", "thorn_string"),
    LIFE_STEAL("lifeSteal", "life_steal_string"),
    LIFE_PER_KILL("lifePerKill", "life_per_kill_string"),
    GOLD_FIND("goldFind", "gold_find_string"),
    MAGIC_FIND("magicFind", "magic_find_string"),
    LIFE_ON_HIT("lifeOnHit", "life_on_hit_string");

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
