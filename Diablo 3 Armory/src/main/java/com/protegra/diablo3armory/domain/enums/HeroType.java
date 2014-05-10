package com.protegra.diablo3armory.domain.enums;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

public enum HeroType {

    BARBARIAN("barbarian", "Barbarian"),
    CRUSADER("crusader", "Crusader"),
    DEMON_HUNTER("demon-hunter", "Demon Hunter"),
    MONK("monk", "Monk"),
    WITCH_DOCTOR("witch-doctor", "Witch Doctor"),
    WIZARD("wizard", "Wizard");

    public static final EnumSet<HeroType> ALL = EnumSet.allOf(HeroType.class);

    private String value;
    private String toString;

    private HeroType(String value, String toString){
        this.value = value;
        this.toString = toString;
    }

    public String getValue(){
        return value;
    }

    public String getToString() { return toString; }

    public static HeroType getHeroClass(String stringHeroClass){
        List<HeroType> heroTypes = Arrays.asList(HeroType.values());

        for (HeroType heroType : heroTypes){
            if (heroType.getValue().equals(stringHeroClass)) {
                return heroType;
            }
        }

        return null;
    }
}
