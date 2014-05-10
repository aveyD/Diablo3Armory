package com.protegra.diablo3armory.domain.enums;

import java.util.EnumSet;

public enum StatDoubleType {

    CRIT_DAMAGE("critDamage"),
    BLOCK_CHANCE("blockChance"),
    DAMAGE("damage"),
    ATTACK_SPEED("attackSpeed"),
    DAMAGE_INCREASE("damageIncrease"),
    CRIT_CHANCE("critChance"),
    DAMAGE_REDUCTION("damageReduction"),
    THORNS("thorns"),
    LIFE_STEAL("lifeSteal"),
    LIFE_PER_KILL("lifePerKill"),
    GOLD_FIND("goldFind"),
    MAGIC_FIND("magicFind"),
    LIFE_ON_HIT("lifeOnHit");

    public static final EnumSet<StatDoubleType> ALL = EnumSet.allOf(StatDoubleType.class);

    private String value;

    private StatDoubleType(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
